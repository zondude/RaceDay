/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package model;

/**
 * A class with the information of the race.
 * @author Jonathan Kim
 * @version 1 December 2018
 *
 */
public class RaceInfo {

    /** 
     * The name of the race. 
     */
    private final String myRaceName;
    
    /** 
     * The track geometry. 
     */
    private final String myTrack;
    
    /** 
     * The width of the track. 
     */
    private final int myWidth;
    
    /** 
     * The height of the track. 
     */
    private final int myHeight;
    
    /** 
     * The single lap distance. 
     */
    private final int myDistance;
    
    /** 
     * The total race time (in milliseconds). 
     */
    private final int myTime;
    
    /** 
     * The number of participants. 
     */
    private final int myParticipantsCount;
    
    /**
     * Constructor that will create a RaceInfo object, holding
     * information about the race.
     * 
     * @param theRaceName name of the track.
     * @param theTrack type of track.
     * @param theWidth the width of the track.
     * @param theHeight the height of the track.
     * @param theDistance the distance of one lap around the track.
     * @param theTime the time to run the track.
     * @param theParticipantsCount the amout of racers in the race.
     */
    public RaceInfo(final String theRaceName,
                    final String theTrack,
                    final int theWidth,
                    final int theHeight,
                    final int theDistance,
                    final int theTime,
                    final int theParticipantsCount) {
        myRaceName = theRaceName;
        myTrack = theTrack;
        myWidth = theWidth;
        myHeight = theHeight;
        myDistance = theDistance;
        myTime = theTime;
        myParticipantsCount = theParticipantsCount;
    }
    
    /**
     * An accessor for the name of the race.
     * 
     * @return the name of the race.
     */
    public String getRaceName() {
        return myRaceName;
    }
    
    /**
     * An accessor for the geometry of the track.
     * 
     * @return the geometry of the track.
     */
    public String getTrack() {
        return myTrack;
    }
    
    /**
     * An accessor for the width of the track.
     * 
     * @return the width of the track.
     */
    public int getWidth() {
        return myWidth;
    }
    
    /**
     * An accessor for the height of the track.
     * 
     * @return the height of the track.
     */
    public int getHeight() {
        return myHeight;
    }
    
    /**
     * An accessor for the single lap distance.
     * 
     * @return the single lap distance.
     */
    public int getDistance() {
        return myDistance;
    }
    
    /**
     * An accessor for the total race time (in milliseconds).
     * 
     * @return the total race time (in milliseconds).
     */
    public int getTime() {
        return myTime;
    }
    
    /**
     * An accessor for the number of participants.
     * 
     * @return the number of participants.
     */
    public int getParticipantsCount() {
        return myParticipantsCount;
    }
}
