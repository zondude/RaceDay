/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package view;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_RACE;


import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import model.Race;

/**
 * A class for the Track GUI.
 * @author Jonathan Kim
 * @version 7 December 2018
 */
public class TrackGUI implements PropertyChangeListener {
    
    /**
     * A frame object.
     */
    private JFrame myFrame;
    
    /**
     * a panel object.
     */
    private JPanel myPanel;
    
    /**
     * a race object.
     */
    private Race myRace;
    
    /**
     * a track panel object.
     */
    private final TrackPanel myTrackPanel;
    
    /**
     * a leader board object.
     */
    private final LeaderBoardPanel myLeaderBoardPanel;
    
    /**
     * a status bar object.
     */
    private final StatusBarPanel myStatusBarPanel;
    
    /**
     * a title border object.
     */
    private TitledBorder myTitle;
    
    /**
     * A constructor for the GUI.
     * @param theRace the race object.
     */
    public TrackGUI(final Race theRace) {
        myFrame = new JFrame("Race Day!");
        myFrame.setLayout(new BorderLayout());
        myPanel = new JPanel(new BorderLayout());
        myTrackPanel = new TrackPanel(theRace);
        myLeaderBoardPanel = new LeaderBoardPanel(theRace);
        myStatusBarPanel = new StatusBarPanel();

        setupComponents();

        
        
    }
    
    /**
     * A method to set up the components.
     */
    private void setupComponents() {
        myFrame.setBackground(Color.WHITE);
        myPanel.setBackground(Color.WHITE);
        myTitle = BorderFactory.createTitledBorder("Race Track");
        myPanel.setBorder(myTitle);
        myPanel.add(myTrackPanel, BorderLayout.WEST);
        myFrame.add(myLeaderBoardPanel, BorderLayout.EAST);
        myFrame.add(myPanel, BorderLayout.WEST);
        myFrame.add(myStatusBarPanel, BorderLayout.SOUTH);

        
    }
    
    /**
     * A start method for the gui.
     */
    public void start() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_RACE.equals(theEvent.getPropertyName())) {
            myRace = (Race) theEvent.getNewValue();
            myRace.addPropertyChangeListener(myTrackPanel);
            myRace.addPropertyChangeListener(myStatusBarPanel);
            myRace.addPropertyChangeListener(myLeaderBoardPanel);
        }
        
    }

}
