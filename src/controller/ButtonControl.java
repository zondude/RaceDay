/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */
package controller;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_TIME;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_PAUSE;


import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Race;
import view.StatusBarPanel;

/**
 * A class to handle button functionality.
 * @author Jonathan Kim
 * @version 1 December 2018
 *
 */
public class ButtonControl extends JPanel implements PropertyChangeListener {

    /** 
     * Universal version identifier for a Serializable class. 
     */
    private static final long serialVersionUID = 1L;

    /** 
     * Times four frequency. 
     */
    private static final int TIMER_TIMES_FOUR = 4;
    
    /** 
     * Value for regular multiplier. 
     */
    private static final int SPEED_REGULAR = 1;

    /** 
     * Timer firing an event every 10 milliseconds. 
     */
    private static final int DELAY = 1;

    /** 
     * Play button image. 
     */
    private static final String PLAY_ICON = "./images/ic_play.png";

    /** 
     * Pause button image. 
     */
    private static final String PAUSE_ICON = "./images/ic_pause.png";

    /** 
     * Times one button image. 
     */
    private static final String TIMES_ONE_ICON = "./images/ic_one_times.png";

    /** 
     * Times four button image. 
     */
    private static final String TIMES_FOUR_ICON = "./images/ic_four_times.png";

    /** 
     * Restart button image. 
     */
    private static final String RESTART_ICON = "./images/ic_restart.png";

    /** 
     * Repeat button image. 
     */
    private static final String REPEAT_ICON = "./images/ic_repeat.png";

    /** 
     * Clear button image. 
     */
    private static final String CLEAR_ICON = "./images/ic_clear.png";

    /** 
     * Repeat color button image. 
     */
    private static final String REPEAT_COLOR_ICON = "./images/ic_repeat_color.png";
    
    
    /**
     * A property name for the controls.
     */
    private static final String CLEAR = "Clear";

    /**
     * A property name for the controls.
     */
    private static final String RESTART = "Restart";

    /** A race object from the race class.. */
    private Race myRace;

    /** 
     * a Timer to run the clock. 
     */
    private Timer myTimer;


    /** 
     * A List of actions for file tab. 
     */
    private List<JMenuItem> myFileTab;

    /** 
     * A List of actions for control tab. 
     */
    private List<Action> myControlsTab;

    /** 
     * A List of actions for help tab. 
     */
    private List<Action> myHelpTab;
    
    /** 
     * A Tabbed pane in the center of the GUI. 
     */
    private TabbedPane myTabbedPane;

    /** 
     * The time multiplier. 
     */
    private int myMultiplier;

    /**
     * A slider from the slider class.
     */
    private Slider mySlider;

    /**
     *  Boolean for switching actions.
     */
    private boolean myRepeatRace;
    
    /**
     *  a property change support.
     */
    private PropertyChangeSupport myPCS;
    
    /**
     * a private statusbar class.
     */
    private StatusBarPanel myStatusBarPanel;
    

    /**
     * Constructor to create ButtonControl objects.
     * 
     * @param theRace Race object in the model package.
     * @param theTabbedPane Tabbed pane in the center of the GUI.
     * @param theSlider the slider object.
     * @param theStatusBarPanel the statusbar class.
     */
    public ButtonControl(final Race theRace,
                         final Slider theSlider,
                         final TabbedPane theTabbedPane,
                         final StatusBarPanel theStatusBarPanel) {
        super();
        initializer(theRace, theSlider, theTabbedPane, theStatusBarPanel);
        myPCS = new PropertyChangeSupport(this);
        myPCS.addPropertyChangeListener(mySlider);
        myPCS.addPropertyChangeListener(myStatusBarPanel);
        initActions();
        configButtons();
    }

    
    /**
     * An accessor to get actions in the file tab.
     * @param theRace Race object in the model package.
     * @param theTabbedPane Tabbed pane in the center of the GUI.
     * @param theSlider the slider object.
     * @param theStatusBarPanel the statusbar class.
     * 
     */
    public void initializer(final Race theRace,
                            final Slider theSlider,
                            final TabbedPane theTabbedPane,
                            final StatusBarPanel theStatusBarPanel) {
        myRace = theRace;
        mySlider = theSlider;
        myStatusBarPanel = theStatusBarPanel;
        myFileTab = new ArrayList<JMenuItem>();
        myControlsTab = new ArrayList<Action>();
        myHelpTab = new ArrayList<Action>();
        myTimer = new Timer(DELAY, this::timerSpeed);
        myTabbedPane = theTabbedPane;
        myMultiplier = 1;
    }
    
    /**
     * An accessor to get actions in the file tab.
     * 
     * @return List of actions in the file tab.
     */
    public List<JMenuItem> getFileTab() {
        return myFileTab;
    }

    /**
     * An Accessor to get actions in the controls tab.
     * 
     * @return List of actions in the controls tab.
     */
    public List<Action> getControlsTab() {
        return myControlsTab;
    }

    /**
     * An accessor to get actions in the help tab.
     * 
     * @return List of actions in the help tab.
     */
    public List<Action> getHelpTab() {
        return myHelpTab;
    }

    /**
     * Helper method to initialize all the buttons to have actions.
     */
    private void initActions() {

        final ToggleAction clear =
                        new ToggleAction(CLEAR,
                                         CLEAR,
                                         CLEAR_ICON,
                                         CLEAR_ICON,
                            () -> clear(),
                            () -> clear(),
                                         false);
        final ToggleAction restart =
                        new ToggleAction(RESTART,
                                         RESTART,
                                         RESTART_ICON,
                                         RESTART_ICON,
                            () -> reset(),
                            () -> reset(),
                                         false);

        final ToggleAction repeat =
                        new ToggleAction("Singe Race",
                                         "Loop Race",
                                         REPEAT_ICON,
                                         REPEAT_COLOR_ICON,
                            () -> repeat(),
                            () -> repeat(),
                        true);

        final ToggleAction play =
                        new ToggleAction("Play",
                                         "Pause",
                                         PLAY_ICON,
                                         PAUSE_ICON,
                            () -> timerStart(),
                            () -> timerStop(),
                                         false);

        final ToggleAction fastslow =
                        new ToggleAction("One Times",
                                         "Four Times",
                                         TIMES_ONE_ICON,
                                         TIMES_FOUR_ICON,
                            () -> myMultiplier = SPEED_REGULAR,
                            () -> myMultiplier = TIMER_TIMES_FOUR,
                                         true);

        
        myControlsTab.add(restart);
        myControlsTab.add(play);
        myControlsTab.add(fastslow);
        myControlsTab.add(repeat);
        myControlsTab.add(clear);

    }

    /**
     * Configure buttons in myFileTab and myControls tab, the file and control tab of the menu
     * bar.
     */
    private void configButtons() {
        for (final JMenuItem item : myFileTab) {
            add(item);
        }

        for (final Action action : myControlsTab) {
            final JButton button = new JButton(action);
            add(button);
        }
    }

    /**
     * This is for the timer.
     * 
     * @param theEvent adsasd.
     */
    private void timerSpeed(final ActionEvent theEvent) {
        myRace.advance(DELAY * myMultiplier);
    }
    

    /**
     * This is for clearing the data output stream.
     */
    private void clear() {
        myTabbedPane.clearDataOutput();
    }
     
    
    /**
     * This is for restarting the timer.
     */
    private void reset() {
        myRace.moveTo(0);
        myTabbedPane.clearDataOutput();
    }
    
    /**
     * This method checks if the race is on a loop or not.
     */
    private void repeat() {
        myRepeatRace = !myRepeatRace;
    }
    
    /**
     * This method checks if the race is on a loop or not.
     */
    private void timerStart() {
        myTimer.start();
        myPCS.firePropertyChange(PROPERTY_PAUSE, null, false);
        
        
    }
    /**
     * This method checks if the race is on a loop or not.
     */
    private void timerStop() {
        myTimer.stop();
        myPCS.firePropertyChange(PROPERTY_PAUSE, null, true);
        
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_TIME.equals(theEvent.getPropertyName())) {
            final int endTime = (Integer) theEvent.getNewValue();
            if (myRepeatRace && endTime == myRace.getRaceInfo().getTime() - 1) {
                myRace.moveTo(0);
            }
        }
    }



}
