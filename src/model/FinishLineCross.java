/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package model;

/**
 * A class that gets the messages for finish line crossing.
 * @author Jonathan Kim
 * @version 1 December 2018
 */
public class FinishLineCross extends AbstractMessage {
    
    /** 
     * A colon constant for the toString(). 
     */
    private static final String COLON = ":";
    
    /** The time of the race. 
     */
    private final int myTimeStamp;
    
    /** The ID of a race participants. 
     */
    private final int myRacerID;
    
    /** 
     * The distance a racer has traveled this lap. 
     */
    private final int myNewLap;
    
    /** 
     * The number of laps the racer has traveled. 
     */
    private final boolean myFinished;
    
    /**
     * Constructor for crossing message object.
     * 
     * @param theTimeStamp The time of the message.
     * @param theRacerID The ID of the racer.
     * @param theNewLap What the new lap is of the racer.
     * @param theFinished True if race is over, false if not.
     */
    public FinishLineCross(final int theTimeStamp,
                    final int theRacerID,
                    final int theNewLap,
                    final boolean theFinished) {
        myTimeStamp = theTimeStamp;
        myRacerID = theRacerID;
        myNewLap = theNewLap + 1;
        myFinished = theFinished;
    }
    
    
    @Override
    public int getTimeStamp() {
        return myTimeStamp;
    }
    
    /**
     * An accessor for racer ID.
     * 
     * @return the ID of the racer.
     */
    public int getRacerID() {
        return myRacerID;
    }
    
    /**
     * An accessor for the new lap the racer is on.
     * 
     * @return the lap of the racer.
     */
    public int getNewLap() {
        return myNewLap;
    }
    
    /**
     * An accessor for if the racer is finished or not.
     * 
     * @return if the race is finished then true, false if just another lap.
     */
    public boolean isFinished() {
        return myFinished;
    }
    
    @Override
    public String toString() {
        return "$C:" + getTimeStamp()
            + COLON + getRacerID()
            + COLON + getNewLap()
            + COLON + isFinished();
    }

}
