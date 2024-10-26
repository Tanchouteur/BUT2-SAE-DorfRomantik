package fr.iutfbleau.SAE31_2024_LTA.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerTutoListener implements ActionListener {
    ControllerPopup controllerPopup;
    ControllerTutoListener(ControllerPopup controllerPopup) {

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controllerPopup.closeSettings();
        controllerPopup.showTutoDialog();
    }
}
