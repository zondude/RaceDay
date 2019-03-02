/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package model;

/**
 * An interface to define the behavior of leader, finish, and telemetry messages.
 * @author Jonathan Kim
 * @version 1 December 2018
 *
 */
public interface Message {
    
    /**
     * A method to get the time.
     * @return the time.
     */
    int getTimeStamp();
    
}
