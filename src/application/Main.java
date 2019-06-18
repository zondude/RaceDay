/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package application;

import controller.ControllerGUI;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A main class to run the application.
 * @author Jonathan Kim
 * @version 1 December 2018
 *
 */
public final class Main {

    /**
     * Private contrusctor for main.
     */
    private Main() { 
        
    }
    
    
    /**
     * A main method to run the GUI.
     * @param theArgs the arguments.
     */
    public static void main(final String[] theArgs) {
        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ControllerGUI().start();
                //new TrackGUI().start();
                
            }
        });
    }
    
    
}
