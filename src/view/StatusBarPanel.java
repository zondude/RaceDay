/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package view;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_TIME;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Class to create the time panel for the GUI.
 * 
 * @author Charles Bryan & Jonathan Kim
 * @version Autumn 2015
 */
public class StatusBarPanel extends JPanel implements PropertyChangeListener {
    
    
    
    /** 
     * The separator for formatted. 
     */
    public static final String SEPARATOR = ":";
    
    /** 
     * The number of milliseconds in a second. 
     */
    public static final int MILLIS_PER_SEC = 1000;
    
    /** 
     * The number of seconds in a minute. 
     */
    public static final int SEC_PER_MIN = 60;
    
    /** 
     * The number of minute in a hour. 
     */
    public static final int MIN_PER_HOUR = 60;
        
    /** 
     * A formatter to require at least 1 digit, leading 0. 
     */
    public static final DecimalFormat ONE_DIGIT_FORMAT = new DecimalFormat("0");
    
    /** 
     * A formatter to require at least 2 digits, leading 0s. 
     */
    public static final DecimalFormat TWO_DIGIT_FORMAT = new DecimalFormat("00");
    
    /** 
     * A formatter to require at least 3 digits, leading 0s. 
     */
    public static final DecimalFormat THREE_DIGIT_FORMAT = new DecimalFormat("000");
    
    /** 
     * The timer string. 
     */
    private static final String THE_TIME = "Timer: ";
    
    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 8385732728740430466L;
    
    
    /** A label to display time. */
    private final JLabel myLabel;
    
    
    /**
     * Construct a Time Panel. 
     */
    public StatusBarPanel() {
        super();
        myLabel = new JLabel(THE_TIME + formatTime(0));
        setupComponents();
    }
    
    /**
     * Setup and layout components. 
     */
    private void setupComponents() {
        
        final JLabel statusLabel = new JLabel("Participants:");
        setLayout(new BorderLayout());
        
        statusLabel.setForeground(Color.BLACK);
        myLabel.setForeground(Color.BLACK);
        add(myLabel, BorderLayout.EAST);
        add(statusLabel, BorderLayout.WEST);
    }

    /**
     * This formats a positive integer into minutes, seconds, and milliseconds. 
     * 00:00:000
     * @param theTime the time to be formatted
     * @return the formated string. 
     */
    public static String formatTime(final long theTime) {
        long time = theTime;
        final long milliseconds = time % MILLIS_PER_SEC;
        time /= MILLIS_PER_SEC;
        final long seconds = time % SEC_PER_MIN;
        time /= SEC_PER_MIN;
        final long min = time % MIN_PER_HOUR;
        time /= MIN_PER_HOUR;
        return TWO_DIGIT_FORMAT.format(min) + SEPARATOR
                        + TWO_DIGIT_FORMAT.format(seconds) 
                        + SEPARATOR + THREE_DIGIT_FORMAT.format(milliseconds);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_TIME.equals(theEvent.getPropertyName())) {
            myLabel.setText("Time: " + formatTime((Integer) theEvent.getNewValue()));
        }
        
    }
}
