package fr.iutfbleau.SAE31_2024_LTA.Vue;

import fr.iutfbleau.SAE31_2024_LTA.Model.ModelGBC;
import fr.iutfbleau.SAE31_2024_LTA.Model.ModelJeux;
import fr.iutfbleau.SAE31_2024_LTA.Model.ModelTuile;

import javax.swing.*;
import java.awt.*;

public class VueJeux extends JPanel {
    private final VuePrincipale principal;
    private ModelJeux modelJeux;

    public VueJeux(VuePrincipale principal){
        this.principal = principal;
        setLayout(new GridBagLayout());
    }

    public void createSuite() {
        modelJeux = new ModelJeux(principal.getModelPrincipale().getSelectedSeed(), principal.getModelPrincipale().getPlayerName());

        poseeTuile(0, 0);
        poseeTuile(1, 0);
        poseeTuile(0, 1);
        poseeTuile(1, 1);
    }

    void poseeTuile(int q, int r) {
        ModelTuile tuile = modelJeux.getListTuiles().getFirst();
        tuile.setCoordonner(q, r);
        VueTuile vue = tuile.getVueTuile();

        GridBagConstraints c = new ModelGBC().setCoordonner(q, r);

        add(vue, c);
        tuile.setEstPosee(true);
        modelJeux.getListTuilesPosee().add(tuile);
        modelJeux.getListTuiles().removeFirst();
    }

}
