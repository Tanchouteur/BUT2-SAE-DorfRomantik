package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.menu.ControllerMenuCard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenuBListener implements ActionListener {

    private final ControllerPopup controllerPopup;
    private final ModelPrincipale modelPrincipale;
    public ControllerMenuBListener(ControllerPopup controllerPopup, ModelPrincipale modelPrincipale) {
        this.controllerPopup = controllerPopup;
        this.modelPrincipale = modelPrincipale;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      ControllerMenuCard controllerMenuCard = new ControllerMenuCard(modelPrincipale);
      controllerPopup.closeSettings();
      controllerMenuCard.goMenu();
}
}
