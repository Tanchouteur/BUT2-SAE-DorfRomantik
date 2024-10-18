package fr.iutfbleau.SAE31_2024_LTA.menu;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.VuePrincipale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenuCard implements ActionListener {
    private final ModelPrincipale modelPrincipale;

    public ControllerMenuCard(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        VuePrincipale vuePrincipale = modelPrincipale.getVuePrincipale();

        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getMediaPlayerManager().getClicAudioClip(), false);

        vuePrincipale.getCardLayout().show(vuePrincipale.getFramePane(), "menu");

        vuePrincipale.setTitle("DorfJavatik - Menu");
    }
}
