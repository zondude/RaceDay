/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package model;

/**
 * A class that holds information of the telemetry.
 * @author Jonathan Kim
 * @version 1 December 2018
 */
public class Telemetry extends AbstractMessage {
    
    /** 
     * Colon for toString(). 
     */
    private static final String COLON = ":";
    
    /** 
     * The distance a racer has traveled this lap. 
     */
    private final double myDistance;
    
    /** 
     * The number of laps the racer has traveled.
     */
    private final int myLap;
    
    /** 
     * The time of the race. 
     */
    private final int myTimeStamp;
    
    /** 
     * The ID of a racer. 
     */
    private final int myRacerID;
    
    /**
     * Constructor for telemetry message object.
     * 
     * @param theTimeStamp 
     * @param theRacerID 
     * @param theDistance 
     * @param theLap 
     */
    public Telemetry(final int theTimeStamp,
                     final int theRacerID,
                     final double theDistance,
                     final int theLap) {
        
        myTimeStamp = theTimeStamp;
        myRacerID = theRacerID;
        myDistance = theDistance;
        myLap = theLap;
    }
    
    
    @Override
    public int getTimeStamp() {
        return myTimeStamp;
    }

    /**
     * An accessor for the ID of the racer.
     * 
     * @return the ID of the racer.
     */
    public int getRacerID() {
        return myRacerID;
    }

    /**
     * An accessor for the distance the racer traveled this lap.
     * 
     * @return the distance the racer has traveled this lap.
     */
    public double getDistance() {
        return myDistance;   
    }
    
    /**
     * An accessor for the lap the racer is on.
     * 
     * @return the lap the racer is on.
     */
    public int getLap() {
        return myLap;
    }
    
    @Override
    public String toString() {
        return "$T:" + getTimeStamp()
            + COLON + getRacerID()
            + COLON + getDistance()
            + COLON + getLap();
    }
}

