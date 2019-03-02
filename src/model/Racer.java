/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package model;

import java.awt.Color;
import java.util.Random;

/**
 * A class with the racer participants and its information.
 * @author Jonathan Kim
 * @version 1 December 2018
 */
public class Racer {

    /**
     * a random number generator.
     */
    private static final Random RANDOM = new Random();
    
    /** 
     * The ID if a racer. 
     */
    private final int myRacerID;
    
    /** 
     * The name of the racer. 
     */
    private final String myRacerName;
    
    /** 
     * The initial starting position of the racer. 
     */
    private final double myRacerInitialPos;
    
    /**
     * A color object.
     */
    private Color myColor;
    

    
    /**
     * Constructor to create a Racer object that stores information about the
     * racer.
     * 
     * @param theRacerID the ID of the racer.
     * @param theRacerName the name of the racer.
     * @param theRacerInitialPos the initial starting position of the racer.
     */
    public Racer(final int theRacerID,
                 final String theRacerName,
                 final double theRacerInitialPos) {
        myRacerID = theRacerID;
        myRacerName = theRacerName;
        myRacerInitialPos = theRacerInitialPos;
        myColor = setColor();
    }
    
    /**
     * An accessor for the racer ID.
     * 
     * @return the ID of the racer
     */
    public int getRacerID() {
        return myRacerID;
    }
    
    /**
     * An accessor for the racer name.
     * 
     * @return the name of the racer
     */
    public String getRacerName() {
        return myRacerName;
    }
    
    /**
     * An accessor for the color.
     * 
     * @return the color.
     */
    public Color getColor() {
        return myColor;
    }
    
    
    /**
     * An accessor for the racer color in RGB.
     * 
     * @return the racer color.
     */
    public Color setColor() {
        final int red = RANDOM.nextInt(155) + 100;
        final int green = RANDOM.nextInt(155) + 100;
        final int blue = RANDOM.nextInt(155) + 100;
        
        return new Color(red, green, blue);
    }
    
    /**
     * An accessor for the initial position of the racer.
     * 
     * @return the initial position of the racer
     */
    public double getRacerInitialPos() {
        return myRacerInitialPos;
    }
}
