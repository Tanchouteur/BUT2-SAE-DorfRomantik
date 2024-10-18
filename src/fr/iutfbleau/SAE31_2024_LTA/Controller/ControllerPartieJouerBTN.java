package fr.iutfbleau.SAE31_2024_LTA.Controller;

import fr.iutfbleau.SAE31_2024_LTA.Model.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.Vue.VuePrincipale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPartieJouerBTN implements ActionListener {
    VuePrincipale vuePrincipale;
    ModelPrincipale modelPrincipale;
    public ControllerPartieJouerBTN(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
        this.vuePrincipale = modelPrincipale.getVuePrincipale();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getMediaPlayerManager().getClicAudioClip(), false);

        vuePrincipale.getCardLayout().show(vuePrincipale.getFramePane(), "partieJouer");
        vuePrincipale.setTitle("DorfJavatik - Liste des parties jouer");
    }
}
