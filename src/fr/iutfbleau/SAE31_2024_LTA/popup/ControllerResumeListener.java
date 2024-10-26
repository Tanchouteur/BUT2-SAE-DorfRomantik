package fr.iutfbleau.SAE31_2024_LTA.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerResumeListener implements ActionListener {
    ControllerPopup controllerPopup;
    int wich;
    ControllerResumeListener(ControllerPopup controllerPopup, int wich) {
        this.controllerPopup = controllerPopup;
        this.wich = wich;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (wich == 0){
            controllerPopup.togleSettingsDialog();
        }
        else if (wich == 1){
            controllerPopup.closeTuto();
        }

    }
}
