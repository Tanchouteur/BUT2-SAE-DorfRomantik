package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControllerInputMap extends AbstractAction {

    private final ModelPrincipale modelPrincipale;
    private final String action;

    public ControllerInputMap(ModelPrincipale modelPrincipale, String action){
        this.modelPrincipale = modelPrincipale;
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("undo".equals(action)) {
            modelPrincipale.getModelJeux().undoLastTuile();
        }else if ("toggleSettingsAction".equals(action) || "Paramètres".equals(e.getActionCommand())) {
            modelPrincipale.getControllerPopup().togleSettingsDialog();
        }
    }
}
