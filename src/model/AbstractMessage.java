/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package model;

/**
 * An abstract class for the Message interface.
 * @author Jonathan Kim
 * @version 1 December 2018
 *
 */
public abstract class AbstractMessage implements Message {

    /**
     * An integer for the time value.
     */
    private int myTimeStamp;
    
    /**
     * An accessor for the time.
     * @return myTimeStamp the time.
     */
    public int getTimeStamp() {
        return myTimeStamp;
        
    }

}
