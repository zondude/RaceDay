/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package controller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Race;
import view.LeaderBoardPanel;
import view.StatusBarPanel;
import view.TrackGUI;
import view.TrackPanel;




/**
 * A class for a GUI with all controls.
 * @author Jonathan Kim
 * @version 1 December 2018
 */
public class ControllerGUI {
    
    /**
     * A frame for the class.
     */
    private JFrame myFrame;
    
    /**
     * A Panel for the class to use.
     */
    private JPanel myPanel;
    
    /**
     * A main panel to put components in.
     */
    private JPanel myMainPanel;
    
    /**
     * A slider from the slider class.
     */
    private Slider mySlider;
    
    /**
     * A south panel for the components.
     */
    private JPanel mySouthPanel;
    
    /**
     * A center panel for the components.
     */
    private JPanel myCenterPanel;
    
    /**
     * a Time panel for the the timer to go into.
     */
    private TimePanel myTimePanel;
    
    /**
     * a menu object from the menu class for the menu.
     */
    private Menu myMenuBar;
    
    /**
     * A tool bar object from the toolbar class.
     */
    private ToolBar myToolBar;
    
    /**
     * A tabbed pane object from the tabbed pane class.
     */
    private TabbedPane myTabbedPane;
    
    /**
     * A race object from the race class.
     */
    private final Race myRace;
    
    /**
     * A private track gui object.
     */
    private TrackGUI myTrackGUI;
    
    /**
     * a private track panel object.
     */
    private TrackPanel myTrackPanel;
    
    /**
     * A private leader board object panel.
     */
    private LeaderBoardPanel myLeaderBoardPanel;
    
    /**
     * my status bar panel object.
     */
    private StatusBarPanel myStatusBarPanel;
    
    
    /**
     * A constructor for the GUI.
     */
    public ControllerGUI() {
        myFrame = new JFrame("Race Day!");
        myFrame.setLayout(new BorderLayout());
        myPanel = new JPanel(new BorderLayout());
        myRace = new Race();
        mySlider = new Slider(myRace);
        myTabbedPane = new TabbedPane(myRace);
        myTrackPanel = new TrackPanel(myRace);
        myLeaderBoardPanel = new LeaderBoardPanel(myRace);
        myStatusBarPanel = new StatusBarPanel();
        final ButtonControl buttonControl = new ButtonControl(myRace, 
                                                              mySlider,
                                                              myTabbedPane,
                                                              myStatusBarPanel);
        myMenuBar = new Menu(buttonControl.getControlsTab(), myRace);

        myTimePanel = new TimePanel();
        
        myToolBar = new ToolBar(buttonControl.getControlsTab());
        myTrackGUI = new TrackGUI(myRace);
        myTrackGUI.start();
        
        setupComponents();
        controlPCL();
        
        myRace.addPropertyChangeListener(model.PropertyChangeEnabledRaceControls.
                                         PROPERTY_TIME, buttonControl);
        myRace.addPropertyChangeListener(model.PropertyChangeEnabledRaceControls.
                                         PROPERTY_PAUSE, buttonControl);
       
        

    }
    
    /**
     * An accessor for the frame.
     * @return myFrame the frame.
     */
    public JFrame getFrame() {
        return myFrame;
    }
    
    /**
     * A method to setup the components.
     */
    private void setupComponents() {
        
        myMainPanel = new JPanel(new BorderLayout());
        mySouthPanel = new JPanel(new FlowLayout());
        myCenterPanel = new JPanel(new BorderLayout());
        
        mySouthPanel.add(myTabbedPane);
        
        myCenterPanel.add(mySlider, BorderLayout.CENTER);
        myCenterPanel.add(myTimePanel, BorderLayout.EAST);
        
        myPanel.add(mySouthPanel, BorderLayout.SOUTH);
        myPanel.add(myCenterPanel, BorderLayout.CENTER);
        myPanel.add(myMenuBar, BorderLayout.NORTH);
        
        myMainPanel.add(myPanel, BorderLayout.CENTER);
        
        myFrame.add(myPanel, BorderLayout.CENTER);
        myFrame.add(myToolBar, BorderLayout.SOUTH);
        
        myFrame.pack();
        
        
    }
    
    /**
     * A method to add property change listeners.
     */
    private void controlPCL() {
        myRace.addPropertyChangeListener(myMenuBar);
        myRace.addPropertyChangeListener(myToolBar);
        myRace.addPropertyChangeListener(myTimePanel);
        myRace.addPropertyChangeListener(myTabbedPane);
        myRace.addPropertyChangeListener(mySlider);
        myRace.addPropertyChangeListener(myTrackGUI);
        myRace.addPropertyChangeListener(myTrackPanel);
        myRace.addPropertyChangeListener(myLeaderBoardPanel);
        myRace.addPropertyChangeListener(myStatusBarPanel);
        
    }

    /**
     * A method that will start the GUI.
     */
    public void start() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setMinimumSize(myFrame.getSize());
        myFrame.setLocationRelativeTo(null);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

}
