/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package view;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_TIME;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_RACERS;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_ADVANCE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import model.Message;
import model.Race;
import model.Telemetry;
import track.VisibleRaceTrack;



/**
 * A demo of Observable/Observer. 
 * 
 * @author Charles Bryan
 * @version Autumn 2015
 */
public class TrackPanel extends JPanel implements PropertyChangeListener {
    
    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 8385732728740430466L;
    
    /** The size of the Race Track Panel. */
    private static final Dimension TRACK_SIZE = new Dimension(500, 400);
    
    
    /** The x and y location of the Track. */
    private static final int OFF_SET = 40;

    /** The stroke width in pixels. */
    private static final int STROKE_WIDTH = 25;

    /** The size of participants moving around the track. */
    private static final int OVAL_SIZE = 20;
    
    /** The visible track. */
    private VisibleRaceTrack myTrack;
    
    
    /**
     * A hash map for circles.
     */
    private Map<Integer, Ellipse2D> myCircles;
    
    /**
     * An array list of messages.
     */
    private ArrayList<Message> myMessages;
    
    /**
     * a race object.
     */
    private Race myRace;
    
    /**
     * An array of IDS.
     */
    private ArrayList<Integer> myIDs;
    
    
    /**
     * Construct a Time Panel. 
     * @param theRace the race object.
     */
    public TrackPanel(final Race theRace) {
        super();
        setPreferredSize(TRACK_SIZE);
        setBackground(Color.WHITE);
        myRace = theRace;
        myMessages = new ArrayList<Message>();
        myCircles = new HashMap<Integer, Ellipse2D>();
        myIDs = new ArrayList<Integer>();
//        setupComponents();
    }
    
    /**
     * Setup and layout components. 
     */
    private void setupComponents() {
        final int width = (int) TRACK_SIZE.getWidth() - (OFF_SET * 2);
        final int height =
                        ((int) TRACK_SIZE.getWidth()  - 2 * OFF_SET) / 5 * 2;
        final int x = OFF_SET;
        final int y = (int) TRACK_SIZE.getHeight()  / 2 - height / 2;

        myTrack = new VisibleRaceTrack
        (x, y, width, height, myRace.getRaceInfo().getDistance());

        for (int i = 0; i < myRace.getRacersInfo().size(); i++) {
            final Point2D start = myTrack.
                            getPointAtDistance(myRace.
                                               getRacersInfo().get(i).getRacerInitialPos());
            final Ellipse2D circle = new Ellipse2D.Double(start.getX() - OVAL_SIZE / 2,
                                                          start.getY() - OVAL_SIZE / 2,
                                                          OVAL_SIZE,
                                                          OVAL_SIZE);
            final int racerID = myRace.getRacersInfo().get(i).getRacerID();
            myCircles.put(racerID, circle);
            myIDs.add(racerID);
        }
    }
    /**
     * Paints the VisibleTrack with a single ellipse moving around it.
     * 
     * @param theGraphics The graphics context to use for painting.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        if (myTrack != null) {
            g2d.setPaint(Color.BLACK);
            g2d.setStroke(new BasicStroke(STROKE_WIDTH));
            g2d.draw(myTrack);
        }

        
        for (int i = 0; i < myRace.getRacersInfo().size(); i++) {
            g2d.setPaint(myRace.getRacersInfo().get(i).getColor());
            g2d.setStroke(new BasicStroke(1));
            g2d.fill(myCircles.get(myIDs.get(i)));
        }

    }
    


    @SuppressWarnings("unchecked")
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_RACERS.equals(theEvent.getPropertyName())) {
            myCircles.clear();
            myIDs.clear();
            setupComponents();
            repaint();
        }
        
        if (PROPERTY_ADVANCE.equals(theEvent.getPropertyName())) {
            myMessages = (ArrayList<Message>) theEvent.getNewValue();
        }
        
        if (PROPERTY_TIME.equals(theEvent.getPropertyName())) {
            for (int i = 0; i < myMessages.size(); i++) {
                if (myMessages.get(i).getClass() == Telemetry.class) {
                    final Telemetry telemetry = (Telemetry) myMessages.get(i);
                    
                    final Point2D current = 
                                    myTrack.getPointAtDistance(telemetry.getDistance());
                    myCircles.get(telemetry.getRacerID()).
                        setFrame(current.getX() - OVAL_SIZE / 2, 
                                      current.getY() - OVAL_SIZE / 2, 
                                      OVAL_SIZE, 
                                      OVAL_SIZE);
                    repaint();
                    
                }
            }
        }
    }
}