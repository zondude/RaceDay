/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package controller;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_TIME;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_CONTROL;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_PAUSE;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Race;

/**
 * Class to create the slider for the GUI.
 * 
 * @author Jonathan Kim
 * @version 1 December 2018
 */
public class Slider extends JSlider implements ChangeListener, PropertyChangeListener {
    
    /** 
     * Milliseconds in a second. 
     */
    public static final int MILLISECOND_PER_SEC = 1000;
    
    /** Seconds in a minute. */
    public static final int SECOND_PER_MIN = 60;
    
    /** Minutes in a hour. */
    public static final int MINUTE_PER_HOUR = 60;
    
    /** Major tick spacing on the slider. */
    public static final int BIG_MARK = 60000;
    
    /** Minor tick spacing on the slider. */
    public static final int SMALL_MARK = 10000;
    
    /** Universal version identifier for a Serializable class. */
    private static final long serialVersionUID = 1L;
    
    /** The delimiter used in the ledger file format. */
    private static final String DELIMITER = ":";
    
    /** A formatter to require at least 1 digit, leading 0. */
    private static final DecimalFormat ONE_DIGIT_FORMAT = new DecimalFormat("0");
    
    /** A formatter to require at least 2 digit, leading 0s. */
    private static final DecimalFormat TWO_DIGIT_FORMAT = new DecimalFormat("00");
    
    /** A formatter to require at least 3 digit, leading 0s. */
    private static final DecimalFormat THREE_DIGIT_FORMAT = new DecimalFormat("000");
    
    /** Race object in the model package. */
    private final Race myRace;
    
    /**
     * Constructor to create the slider for the GUI.
     * 
     * @param theRace 
     */
    public Slider(final Race theRace) {
        super();
        myRace = theRace;
        initSlider();
        addChangeListener(this);
    }
    
    /**
     * Method to help initialize the slider.
     */
    private void initSlider() {
        setEnabled(false);
        setPaintTicks(false);
        setMajorTickSpacing(0);
        setValue(0);
        
    }

    /**
     * Method to format the time.
     * 
     * @param theTime time to format.
     * @return the string that represents the formatted time.
     */
    public static String formatTime(final long theTime) {
        long time = theTime;
        
        final long milliseconds = time % MILLISECOND_PER_SEC;
        time /= MILLISECOND_PER_SEC;
        final long seconds = time % SECOND_PER_MIN;
        time /= SECOND_PER_MIN;
        final long min = time % MINUTE_PER_HOUR;
        time /= MINUTE_PER_HOUR;
        
        return ONE_DIGIT_FORMAT.format(min)
                        + DELIMITER
                        + TWO_DIGIT_FORMAT.format(seconds)
                        + DELIMITER
                        + THREE_DIGIT_FORMAT.format(milliseconds);
    }
    
    @Override
    public void stateChanged(final ChangeEvent theEvent) {
        final JSlider source = (JSlider) theEvent.getSource();
        if (source.getValueIsAdjusting()) {
            myRace.moveTo(source.getValue());
            
        }
        
    }
    
    /**
     * A property change listener to work with the GUI.
     * @param theEvent the event constant.
     */
    public void propertyChange(final PropertyChangeEvent theEvent) {
       
        if (PROPERTY_TIME.equals(theEvent.getPropertyName())) {
            setMajorTickSpacing(BIG_MARK);
            setMinorTickSpacing(SMALL_MARK);
            setPaintTicks(true);
            setMaximum(myRace.getRaceInfo().getTime() - 1);
            setValue((Integer) theEvent.getNewValue());
        }
        
        if (PROPERTY_CONTROL.equals(theEvent.getPropertyName())) {
            setMajorTickSpacing(BIG_MARK);
            setMinorTickSpacing(SMALL_MARK);
            setPaintTicks(true);
            setEnabled(true);
            setMaximum(myRace.getRaceInfo().getTime());
            setValue((Integer) theEvent.getNewValue());
        }
        
        if (PROPERTY_PAUSE.equals(theEvent.getPropertyName())) {
            setEnabled((boolean) theEvent.getNewValue());
        
        }
        
        
    }
    
}

