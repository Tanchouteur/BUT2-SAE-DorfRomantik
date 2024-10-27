package fr.iutfbleau.SAE31_2024_LTA.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerTutoListener implements ActionListener {
    ControllerPopup controllerPopup;
    ControllerTutoListener(ControllerPopup controllerPopup) {
        this.controllerPopup = controllerPopup;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (controllerPopup != null) {
            controllerPopup.closeSettings();
            controllerPopup.showTutoDialog();
        } else {
            System.err.println("erreur qui existe depuis le MakeFile");
        }
    }
}
