package fr.iutfbleau.SAE31_2024_LTA.partieJouer;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;

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
        modelPrincipale.getBdd().updateBdd();
        vuePrincipale.getPrincipaleLayeredPane().getMainPanel().getCardLayout().show(vuePrincipale.getPrincipaleLayeredPane().getMainPanel(), "partieJouer");
        vuePrincipale.setTitle("DorfJavatik - Liste des parties jouer");
    }
}
