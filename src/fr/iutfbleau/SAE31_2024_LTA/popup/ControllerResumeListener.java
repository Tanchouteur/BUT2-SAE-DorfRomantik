package fr.iutfbleau.SAE31_2024_LTA.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerResumeListener implements ActionListener {
    ControllerPopup controllerPopup;
    ControllerResumeListener(ControllerPopup controllerPopup) {
        this.controllerPopup = controllerPopup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controllerPopup.togleSettingsDialog();
    }
}
