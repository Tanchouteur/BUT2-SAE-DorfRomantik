package fr.iutfbleau.SAE31_2024_LTA.partieJouer;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.VuePrincipale;

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

        modelPrincipale.getMediaPlayerManager().setClipVolume(modelPrincipale.getModelMediaLoader().getMenuMusicClip(),0.85f);

        vuePrincipale.getCardLayout().show(vuePrincipale.getFramePane(), "partieJouer");
        vuePrincipale.setTitle("DorfJavatik - Liste des parties jouer");
    }
}
