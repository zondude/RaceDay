/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package model;

/**
 * A class that holds the leader board information.
 * @author Jonathan Kim
 * @version 1 December 2018
 *
 */
public class LeaderBoard extends AbstractMessage {
    
    /** 
     * Colon for toString(). 
     */
    private static final String COLON = ":";
    
    /** 
     * The time of the race. 
     */
    private final int myTimeStamp;
    
    /** 
     * The int array to hold the id of racers. 
     */
    private final int[] myRacers;
    
    /** 
     * The int array to the leader board. 
     */
    private final int[] myLeaderboard;
    /**
     * 
     * @param theTimeStamp the time.
     * @param theRacers the racer participants.
     */
    public LeaderBoard(final int theTimeStamp,
                       final int[] theRacers) {
        myTimeStamp = theTimeStamp;
        myRacers = theRacers;
        myLeaderboard = (int[]) theRacers.clone();
    }
    
    @Override
    public int getTimeStamp() {
        return myTimeStamp;
    }
    
    /** 
     * Accessor for the leader board.
     * @return getLeaderBoard returns the leaderboard.
     */
    public int[] getLeaderBoard() {
        return myLeaderboard;
    }

    @Override
    public String toString() {
        final StringBuffer result = new StringBuffer("$L:" + getTimeStamp());
        for (int i = 0; i < myRacers.length; i++) {
            result.append(COLON + myRacers[i]);
        }
        return result.toString();
    }
}