/**
 * TCSS 305 - Autumn 2018
 * Assignment 5 RaceDay
 */

package controller;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_CONTROL;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * This class creates a toolbar for the GUI.
 * @author Jonathan Kim
 * @version 1 December 2018
 *
 */
public class ToolBar extends JToolBar implements PropertyChangeListener  {
    
   
    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A list of tool bar buttons.
     */
    private final List<JButton> myToolBarList;

    /**
     * A constructor for the tool bar.
     * @param theAction the actions for the tool bar.
     */
    public ToolBar(final List<Action> theAction) {
        super();
        myToolBarList = new ArrayList<JButton>();
        for (final Action action : theAction) {
            final JButton button = new JButton(action);
            button.setEnabled(false);
            myToolBarList.add(button);
            button.setHideActionText(true);
            add(button);
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_CONTROL.equals(theEvent.getPropertyName())) {
            for (int i = 0; i < myToolBarList.size(); i++) {
                myToolBarList.get(i).setEnabled(true);
            }
        }
    }
    
    
}
