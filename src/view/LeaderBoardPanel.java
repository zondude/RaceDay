/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */


package view;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_ADVANCE;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_TIME;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_RACERS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import model.LeaderBoard;
import model.Message;
import model.Race;


/**
 * A leader board panel class to put into the view gui.
 * @author Jonathan Kim
 * @version 7 December 2018
 */
public class LeaderBoardPanel extends JPanel implements PropertyChangeListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * a number for the dimension.
     */
    private static final int THREE_HUNDRED = 300;
    
    /**
     * a number for the dimension.
     */
    private static final int FOUR_HUNDRED = 400;

    /**
     * a private race object.
     */
    private Race myRace;
    
    /**
     * an array list of messages.
     */
    private ArrayList<Message> myMessages;
    
    /**
     * A map for the racers and their id that holds a panel.
     */
    private Map<Integer, JPanel> myRacerLeaders;
    

    

    /**
     * Constructor for the leader board panel.
     * @param theRace the race object.
     */
    public LeaderBoardPanel(final Race theRace) {
        setBackground(Color.white);
        setPreferredSize(new Dimension(THREE_HUNDRED, FOUR_HUNDRED));
        
        
        myRace = theRace;
        myMessages = new ArrayList<Message>();
        myRacerLeaders = new HashMap<Integer, JPanel>();
    }
    
    
    /**
     * a method to set up the components of this gui.
     */
    private void setupComponents() {
        setLayout(new GridLayout(myRace.getRaceInfo().getParticipantsCount(), 1));
        for (int i = 0; i < myRace.getRacersInfo().size(); i++) {
            final String name = myRace.getRacersInfo().get(i).getRacerName();
            final int racerNumber = myRace.getRacersInfo().get(i).getRacerID();
            final JLabel racerLabel = new JLabel(racerNumber + ": " + name);
            racerLabel.setBackground(Color.WHITE);
            racerLabel.setOpaque(true);
            racerLabel.setBorder(new LineBorder(Color.black));
            final JPanel racerPanel = new JPanel();
            racerPanel.add(racerLabel);
            racerPanel.setBackground(myRace.getRacersInfo().get(i).getColor());

            myRacerLeaders.put(racerNumber, racerPanel);
            add(myRacerLeaders.get(racerNumber));
            
        }
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_RACERS.equals(theEvent.getPropertyName())) {
            removeAll();
            myRacerLeaders.clear();
            setupComponents();
            for (JPanel panel : myRacerLeaders.values()) {
                add(panel);
                
            }
            revalidate();

        }
        
        if (PROPERTY_TIME.equals(theEvent.getPropertyName())) {
            for (int i = 0; i < myMessages.size(); i++) {
                if (myMessages.get(i).getClass() == LeaderBoard.class) {
                    final LeaderBoard leaderboard = (LeaderBoard) myMessages.get(i);
                    for (int raceID : leaderboard.getLeaderBoard()) {
                        add(myRacerLeaders.get(raceID));

                    }
                    revalidate();

                }
            }
            
        }
        
        if (PROPERTY_ADVANCE.equals(theEvent.getPropertyName())) {
            myMessages = (ArrayList<Message>) theEvent.getNewValue();
        }
    }

}
