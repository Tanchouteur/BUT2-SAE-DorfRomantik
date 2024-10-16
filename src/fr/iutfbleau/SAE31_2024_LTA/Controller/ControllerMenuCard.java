package fr.iutfbleau.SAE31_2024_LTA.Controller;

import fr.iutfbleau.SAE31_2024_LTA.Model.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.Vue.VuePrincipale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenuCard implements ActionListener {
    private final VuePrincipale vuePrincipale;

    public ControllerMenuCard(VuePrincipale vuePrincipale) {
        this.vuePrincipale = vuePrincipale;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        ModelPrincipale modelPrincipale = vuePrincipale.getModelPrincipale();

        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getMediaPlayerManager().getClicAudioClip(), false);

        vuePrincipale.getCardLayout().show(vuePrincipale.getFramePane(), "menu");

        vuePrincipale.setTitle("DorfJavatik - Menu");
    }
}
