/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package controller;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_ADVANCE;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_CONTROL;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_MESSAGE;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import model.Message;
import model.Race;

/**
 * A class to create the tabbed pane for the GUI.
 * 
 * @author Jonathan Kim
 * @version 1 December 2018
 */
public class TabbedPane extends JTabbedPane implements PropertyChangeListener {

    /** 
     * A generated serial version UID for object Serialization. 
     */
    private static final long serialVersionUID = 1L;

    /** 
     * The amount of rows. 
     */
    private static final int TEXT_AREA_ROWS = 10;

    /** 
     * The amount of columns. 
     */
    private static final int TEXT_AREA_COLUMNS = 50;

    /** 
     * A Race object in from the race class. 
     */
    private final Race myRace;

    /** 
     * A JTextArea to display the output stream messages. 
     */
    private final JTextArea myDataOutput;

    /** 
     * A JTextArea to display race participants in the checkbox. 
     */
    private final JPanel myRaceParticipants;

    /**
     * A list of check boxes.
     */
    private final List<JCheckBox> myCheckBoxList;

    /**
     * Constructor to create the tabbed panes for the GUI.
     * 
     * @param theRace the race object.
     */
    public TabbedPane(final Race theRace) {
        super();
        myRace = theRace;
        myCheckBoxList = new ArrayList<JCheckBox>();
        myDataOutput = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        myRaceParticipants = new JPanel();
        configTabbedPane();
    }

    /**
     * Method to initialize the participants checkbox's in the race participants pane.
     */
    private void participantsCheckbox() {
        myRaceParticipants.setLayout(new GridLayout(myRace.getRaceInfo().
                                                    getParticipantsCount() + 1, 1));
        myCheckBoxList.add(new JCheckBox("Select All"));
        for (int i = 0; i < myRace.getRaceInfo().getParticipantsCount(); i++) {
            final JCheckBox checkBox =
                            new JCheckBox(myRace.getRacersInfo().get(i).getRacerName());
            myCheckBoxList.add(checkBox);
        }

        for (int i = 0; i < myCheckBoxList.size(); i++) {
            myRaceParticipants.add(myCheckBoxList.get(i));
        }
    }

    /**
     * Configuring the tabbed panes.
     */
    private void configTabbedPane() {
        final JScrollPane dataOutputScroll = new JScrollPane(myDataOutput);
        dataOutputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dataOutputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        myDataOutput.setEditable(false);

        final JScrollPane racersScroll = new JScrollPane(myRaceParticipants);
        racersScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        racersScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        racersScroll.setPreferredSize(myDataOutput.getSize());

        addTab("Data Output Stream", dataOutputScroll);
        addTab("Race Participants", racersScroll);
        setEnabledAt(0, false);
        setEnabledAt(1, false);
    }

    /**
     * This is for clearing the data output stream.
     */
    public void clearDataOutput() {
        myDataOutput.setText("");
    }
    

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        
        if (PROPERTY_CONTROL.equals(theEvent.getPropertyName())) {
            setEnabledAt(0, true);
            setEnabledAt(1, true);
            participantsCheckbox();

        }
        
        
        if (PROPERTY_ADVANCE.equals(theEvent.getPropertyName())) {
            @SuppressWarnings("unchecked")
            final List<Message> messages = (List<Message>) theEvent.getNewValue();
            for (Message message : messages) {
                myDataOutput.append(message.toString() + "\n");
            }
        }
        
//        if (PROPERTY_MESSAGE.equals(theEvent.getPropertyName())) {
//            myDataOutput.append(theEvent.getNewValue().toString());
//        }

    }
    
    
}
