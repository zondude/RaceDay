/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * A race class that is used for the GUI.
 * @author Jonathan Kim
 * @version 1 December 2018
 *
 */
public class Race implements PropertyChangeEnabledRaceControls {
    /** 
     * For creating a new line. 
     */
    private static final String NEW_LINE = "\n";
    
    /** 
     * The delimiter used in the ledger file format. 
     */
    private static final String DELIMITER = ":";
    
    /**
     *  The regular expression for an integer. 
     */
    private static final String INTEGER_REGULAREXPRESS = "\\d+";
    
    /** 
     * The regular expression for an floating point number. 
     */
    private static final String FLOAT_REGULAREXPRESS = "^-?\\d*\\.{0,1}\\d+$";
    
    /** 
     * The error message for an incorrectly formatted file. 
     */
    private static final String FILE_FORMAT_ERROR_MESSAGE = "Invalid file format.";
    
    /** 
     * Expected length of a telemetry after split at the delimiter. 
     */
    private static final int TELEMETRY_CROSSING_SIZE = 5;
    
    /** 
     * The expected time. 
     */
    private static final int EXPECTED_TIME = 0;
    
    /** 
     * A List to hold the race messages. 
     */
    protected final List<ArrayList<Message>> myRaceMessages;
    
    /** 
     * A List to hold the race participants and their information. 
     */
    private final List<Racer> myRacersInfo;
    
    /** 
     * A RaceInfo object from the raceinfo class to hold information about the race. 
     */
    private RaceInfo myRaceInfo;
    
    /** 
     * Map to store the ID of racers and whether to toggle. 
     */
    private final Map<Integer, Boolean> myRacerToggle;
    
    /** 
     * The time of the race. 
     */
    private int myTime;
    
    /**
     * A property change support.
     */
    private final PropertyChangeSupport myPCS;
    
    
    
    /**
     * Constructor to instantiate the ArrayLists.
     */
    public Race() {
        super();
        myRaceMessages = new ArrayList<ArrayList<Message>>();
        myRacersInfo = new ArrayList<Racer>();
        myRacerToggle = new HashMap<Integer, Boolean>();
        myTime = EXPECTED_TIME;
        myPCS = new PropertyChangeSupport(this);
    }
    
    @Override
    public void advance() {
        advance(1);

    }

    @Override
    public void advance(final int theMillisecond) {
        changeTime(myTime + theMillisecond);
        if (myTime + theMillisecond < myRaceInfo.getTime()) {
            myPCS.firePropertyChange(PROPERTY_ADVANCE
                                 , 0, myRaceMessages.get(myTime + theMillisecond));
        }
        
    }

    @Override
    public void moveTo(final int theMillisecond) {
        if (theMillisecond < 0) {
            throw new IllegalArgumentException("Time can not be negative.");
        }
        changeTime(theMillisecond);
        
        if (myTime == 1) {
            sendMessage(printDataOutput(myTime - 1));
        } else {
            sendMessage(printDataOutput(myTime));
        }
    }
    
    /**
     * A method to change the time.
     * @param theMillisecond the milliseconds.
     */
    private void changeTime(final int theMillisecond) {
        final int old = myTime;
        myTime = theMillisecond;
        if (myTime < myRaceInfo.getTime()) {
            myPCS.firePropertyChange(PROPERTY_TIME, old, myTime);
        }
        
    }
    
    /**
     * A method to send messages and fire to the GUI.
     * @param theNewValue the new value.
     */
    private void sendMessage(final String theNewValue) {
        myPCS.firePropertyChange(PROPERTY_MESSAGE, 0, theNewValue);
    }

    @Override
    public void toggleParticipant(final int theParticpantID, final boolean theToggle) {
        if (myRacerToggle.containsKey(theParticpantID)) {
            myRacerToggle.put(theParticpantID, theToggle);
        }
    }
    
    @Override
    public void loadRace(final File theRaceFile) throws IOException {
        final Scanner inputFile = new Scanner(theRaceFile);
        myRacersInfo.clear();
        myRaceMessages.clear();
        if (theRaceFile.getName().endsWith(".rce")) {
            loadHeader(inputFile);
            loadMessages(inputFile);
            myPCS.firePropertyChange(PROPERTY_CONTROL, 1, 2);
            myPCS.firePropertyChange(PROPERTY_RACE, 1, this);
            myPCS.firePropertyChange(PROPERTY_RACERS, 1, getRaceInfo());
            
            
            
        } else {
            inputFile.close();
            throw new IOException();
        }
        inputFile.close();
        
        
    }
    
    
    /**
     * An accessor to retrieve a RaceInfo object that holds information about the race.
     * 
     * @return RaceInfo object that holds information about the race.
     */
    public RaceInfo getRaceInfo() {
        return myRaceInfo;
    }
    
    /**
     * An accessor to retrieve a list of racer objects that holds information about the racers.
     * 
     * @return List of racer objects that hold information about the racers.
     */
    public List<Racer> getRacersInfo() {
        return myRacersInfo;
    }
    

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
        
    }

    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(thePropertyName, theListener);
        
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
        
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(thePropertyName, theListener);
        
    }
    
    /**
     * A helper method to load the header.
     * @param theScanner the scanner file reader.
     * @throws IOException throws IOExceptions.
     */
    private void loadHeader(final Scanner theScanner) throws IOException {
        String line = "";
        String raceName = "";
        String trackType = "";
        int trackWidth = 0;
        int trackHeight = 0;
        int lapDistance = 0;
        int raceTime = 0;
        int raceParticipants = 0;
        
        while (theScanner.hasNextLine()) {
            line = theScanner.nextLine();
            if (line.startsWith("#RACE")) {
                raceName = parseString(line, 2, 1);
            } else if (line.startsWith("#TRACK")) {
                trackType = parseString(line, 2, 1);
            } else if (line.startsWith("#WIDTH")) {
                trackWidth = parseInteger(line, 2, 1);
            } else if (line.startsWith("#HEIGHT")) {
                trackHeight = parseInteger(line, 2, 1);
            } else if (line.startsWith("#DISTANCE")) {
                lapDistance = parseInteger(line, 2, 1);
            } else if (line.startsWith("#TIME")) {
                raceTime = parseInteger(line, 2, 1);
            } else if (line.startsWith("#PARTICIPANTS")) {
                raceParticipants = parseInteger(line, 2, 1);
                parseRaceParticipants(theScanner, raceParticipants);
                break;
            } else {
                throw new IOException();
            }
            
            
        }
        myRaceInfo = new RaceInfo(raceName,
                                  trackType,
                                  trackWidth,
                                  trackHeight,
                                  lapDistance,
                                  raceTime,
                                  raceParticipants);
    }
    
    /**
     * A helper to loadRace. Parses the messages in a race file. 
     * 
     * @param theScanner a scanner open on the race file
     * @throws IOException if the race file is in the incorrect format
     */
    private void loadMessages(final Scanner theScanner) throws IOException {
        for (int i = 0; i < myRaceInfo.getTime(); i++) {
            myRaceMessages.add(new ArrayList<Message>());
        }
        
        while (theScanner.hasNextLine()) {
            final String line = theScanner.nextLine();
            if (line.startsWith("$L")) {
                final String[] leaderboardArray = line.split(DELIMITER);
                final int leaderboardTime = parseInteger(line, myRacersInfo.size() + 2, 1);
                final int[] racerArray = new int[myRacersInfo.size()];
                if (leaderboardArray.length == myRacersInfo.size() + 2) {
                    int index = 0;
                    for (int i = 2; i < leaderboardArray.length; i++) {
                        racerArray[index] = Integer.parseInt(leaderboardArray[i]);
                        index++;
                    }
                }
                myRaceMessages.get(leaderboardTime).add(new LeaderBoard(leaderboardTime,
                                                                         racerArray));
            } else if (line.startsWith("$T")) {
                int time = parseInteger(line, TELEMETRY_CROSSING_SIZE, 1);
                final int racerID = parseInteger(line, TELEMETRY_CROSSING_SIZE, 2);
                final double distance = parseDouble(line, TELEMETRY_CROSSING_SIZE, 3);
                final int lap = parseInteger(line, TELEMETRY_CROSSING_SIZE, 4);
                if (time == myRaceMessages.size()) {
                    time = time - 1;
                    myRaceMessages.get(time).add(new Telemetry(time + 1,
                                                               racerID,
                                                               distance,
                                                               lap));
                } else {
                    myRaceMessages.get(time).add(new Telemetry(time, racerID, distance, lap));
                }
            } else if (line.startsWith("$C")) {
                final int crossingTime = parseInteger(line, TELEMETRY_CROSSING_SIZE, 1);
                final int racerID = parseInteger(line, TELEMETRY_CROSSING_SIZE, 2);
                final int newLap = parseInteger(line, TELEMETRY_CROSSING_SIZE, 3);
                final boolean isFinish = parseBoolean(line, TELEMETRY_CROSSING_SIZE, 4);
                myRaceMessages.get(crossingTime).add(new FinishLineCross(crossingTime,
                                                            racerID,
                                                            newLap,
                                                            isFinish));
            } else {
                throw new IOException();
            }
        }

    }
    
    /**
     * A helper method to create a String to be printed on the data output.
     * 
     * @param theTime int representing the time to print.
     * @return a String to be printed on the data output.
     */
    private String printDataOutput(final int theTime) {
        final StringBuffer result = new StringBuffer();
        //final ArrayList<Message> messages = new ArrayList<Message>();
        for (int i = theTime; i <= theTime; i++) {
            for (int j = 0; j < myRaceMessages.get(i).size(); j++) {
                result.append(myRaceMessages.get(i).get(j).toString() + NEW_LINE);
                //messages.add(myRaceMessages.get(i).get(j));
            }
        }
        return result.toString();
    }
    
    /**
     * A helper method to parse the string.
     * @param theLine the line of the text.
     * @param theExpectedLength the expected length.
     * @param theIndex the index at which the string is.
     * @return parts the parts of the string.
     * @throws IOException throws an IOException.
     */
    private String parseString(final String theLine,
                               final int theExpectedLength,
                               final int theIndex) throws IOException {
        final String[] parts = theLine.split(DELIMITER);
        if (parts.length != theExpectedLength) {
            throw new IOException(FILE_FORMAT_ERROR_MESSAGE);
        }
        return parts[theIndex];
    }
    
    /**
     * A helper method to parse the integer.
     * @param theLine the line of the text.
     * @param theExpectedLength the expected length.
     * @param theIndex the index at which the integer is.
     * @return an integer of the part of the line.
     * @throws IOException throws IOException.
     */
    private int parseInteger(final String theLine,
                             final int theExpectedLength,
                             final int theIndex) throws IOException {
        final String[] parts = theLine.split(DELIMITER);
        if (parts.length != theExpectedLength || !parts[theIndex].
                        matches(INTEGER_REGULAREXPRESS)) {
            throw new IOException(FILE_FORMAT_ERROR_MESSAGE);
        }
        return Integer.parseInt(parts[theIndex]);
    }
    
    /**
     * Parse an boolean from a String. The String is expected to split on 
     * the DELIMITER ":" and find a boolean value at theIndex in the 
     * resulting String[].
     * 
     * @param theLine the boolean to parse
     * @param theExpectedLength  the number of expected String the Line should split into
     * @param theIndex the expected location of the boolean value
     * @return the parsed integer value as an boolean
     * @throws IOException if a boolean value is not found at the given index
     */
    private boolean parseBoolean(final String theLine,
                                 final int theExpectedLength,
                                 final int theIndex) throws IOException {
        final String[] parts = theLine.split(DELIMITER);
        if (parts.length != theExpectedLength) {
            throw new IOException(FILE_FORMAT_ERROR_MESSAGE);
        }
        return Boolean.parseBoolean(parts[theIndex]);
    }
    
    /**
     * Parse a BigDecimal from a String. The String is expected to split on 
     * the DELIMITER ":" and find a floating point value at theIndex in the 
     * resulting String[].
     * 
     * @param theLine the String to parse
     * @param theExpectedLength  the number of expected String the Line should split into
     * @param theIndex the expected location of the floating point value
     * @return the parsed floating point value as a BigDecimal 
     * @throws IOException if a floating point value is not found at the given index
     */
    private double parseDouble(final String theLine,
                               final int theExpectedLength,
                               final int theIndex) throws IOException {
        final String[] parts = theLine.split(DELIMITER);
        if (parts.length != theExpectedLength || !parts[theIndex].
                        matches(FLOAT_REGULAREXPRESS)) {
            throw new IOException(FILE_FORMAT_ERROR_MESSAGE);
        }
        return Double.parseDouble(parts[theIndex]);
    }
    
    /**
     * Method to parse the race participants and add them to the list myRacerInfo.
     * 
     * @param theScanner a scanner open on the race file
     * @param theRaceParticipants the number of race participants
     * @throws IOException if the race file is in the incorrect format
     */
    private void parseRaceParticipants(final Scanner theScanner,
                                       final int theRaceParticipants) throws IOException {
        for (int i = 0; i < theRaceParticipants; i++) {
            final String line = theScanner.nextLine();
            
            final int racerID = parseInteger(line.substring(1, line.length()), 3, 0);
            final String racerName = parseString(line, 3, 1);
            final double racerInitialPos = parseDouble(line, 3, 2);
            
            final Racer racer = new Racer(racerID, racerName, racerInitialPos);
            
            myRacerToggle.put(racerID, true);
            myRacersInfo.add(racer);
        }
    }

    
}