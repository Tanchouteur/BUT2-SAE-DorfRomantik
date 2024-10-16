package fr.iutfbleau.SAE31_2024_LTA.Controller;

import fr.iutfbleau.SAE31_2024_LTA.Model.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.Vue.VuePrincipale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPartieJouerBTN implements ActionListener {
    VuePrincipale vuePrincipale;
    public ControllerPartieJouerBTN(VuePrincipale vuePrincipale){
        this.vuePrincipale = vuePrincipale;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ModelPrincipale modelPrincipale = vuePrincipale.getModelPrincipale();

        modelPrincipale.getMediaPlayerManager().startClip(vuePrincipale.getModelPrincipale().getMediaPlayerManager().getClicAudioClip(), false);

        vuePrincipale.getCardLayout().show(vuePrincipale.getFramePane(), "partieJouer");
        vuePrincipale.setTitle("DorfJavatik - Liste des parties jouer");
    }
}
