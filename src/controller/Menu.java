/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package controller;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_CONTROL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import model.Race;

/**
 * Class to create the menu bar for the GUI.
 * 
 * @author Jonathan Kim
 * @version 1 December 2018
 */
public class Menu extends JMenuBar implements ActionListener, PropertyChangeListener {

    /** 
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = 1L;
    
    /** 
     * A String constant for making new lines. 
     */
    private static final String NEW_LINE = "\n";
    
    /** 
     * A List to hold JMenuItems. 
     */
    private final List<JMenuItem> myMenuItems;
    
    /** 
     * A JMenuItem for information about the race. 
     */
    private JMenuItem myAboutRace;
    
    /** 
     * JMenuItem for information about the user.
     */
    private JMenuItem myAboutUser;
    
    /** 
     * RaceInfo object that holds information about the race after parsing.
     */
    private final Race myRace;
    
    /**
     * A constructor to create the menu bar for the GUI.
     * 
     * @param theControl 
     * @param theRace 
     */
    public Menu(final List<Action> theControl, final Race theRace) {
        super();
        myMenuItems = new ArrayList<JMenuItem>();
        initMenuItems(theControl);
        myRace = theRace;
    }
    
    /**
     * Helper method to create the JMenuItems.
     * 
     * @param theControl 
     */
    private void initMenuItems(final List<Action> theControl) {
        final JMenu fileTab = new JMenu("File");
        final JMenuItem loadFile = new JMenuItem("Load File...");
        final JMenuItem exit = new JMenuItem("Exit");
        fileTab.add(loadFile);
        fileTab.add(exit);
        loadFile.addActionListener(theEvent -> loadFile());
        exit.addActionListener(theEvent -> exit());
        final JMenu controlsTab = new JMenu("Controls");
        final JMenu helpTab = new JMenu("Help");
        
        fileTab.addSeparator();
        
        
        for (final Action action : theControl) {
            final JMenuItem controlItem = new JMenuItem(action);
            controlItem.setEnabled(false);
            final char mnemonic = controlItem.getText().toUpperCase().charAt(0);
            controlItem.setMnemonic((int) mnemonic);
            controlsTab.add(controlItem);
            myMenuItems.add(controlItem);
            
        }
        
        myAboutRace = new JMenuItem("Race Info...");
        myAboutRace.setEnabled(false);
        myAboutRace.addActionListener(this);
        myMenuItems.add(myAboutRace);
        
        myAboutUser = new JMenuItem("About...");
        myAboutUser.addActionListener(this);
        
        helpTab.add(myAboutRace);
        helpTab.add(myAboutUser);
        
        add(fileTab);
        add(controlsTab);
        add(helpTab);
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (theEvent.getSource().equals(myAboutRace)) {
            JOptionPane.showMessageDialog(this, myRace.getRaceInfo().getRaceName()
                                          + NEW_LINE
                                          + "Track type: "
                                          + myRace.getRaceInfo().getTrack()
                                          + NEW_LINE
                                          + "Total time: "
                                          + myRace.getRaceInfo().getTime()
                                          + NEW_LINE
                                          + "Lap distance: "
                                          + myRace.getRaceInfo().getDistance());
        }
        
        if (theEvent.getSource().equals(myAboutUser)) {
            JOptionPane.showMessageDialog(this, "Race Day!\n"
                            + "Author: Jonathan Kim\n"
                            + "TCSS 305 C\n"
                            + "Autumn 2018\n");
        }
    }
    
    /**
     * A method to load the file.
     */
    private void loadFile() {
        final JFileChooser fileChooser = new JFileChooser("race_files");
        final int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                myRace.loadRace(fileChooser.getSelectedFile());
            } catch (final IOException e) {
                JOptionPane.showMessageDialog(this, "Incorrect File Format...",
                                              "Error Loading", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * A method that exits the GUI.
     */
    private void exit() {
        System.exit(0);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_CONTROL.equals(theEvent.getPropertyName())) {
            for (int i = 0; i < myMenuItems.size(); i++) {
                myMenuItems.get(i).setEnabled(true);
            }
        }
    }
    
}
