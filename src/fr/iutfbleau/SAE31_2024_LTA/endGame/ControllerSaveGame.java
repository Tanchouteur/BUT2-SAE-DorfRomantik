package fr.iutfbleau.SAE31_2024_LTA.endGame;

import fr.iutfbleau.SAE31_2024_LTA.Bdd.ModelBDD;
import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ControllerSaveGame implements ActionListener {
    private final ModelBDD modelBDD;
    private final ModelPrincipale modelPrincipale;
    private final VueScoreScreen vueScoreScreen;

    public ControllerSaveGame(ModelPrincipale modelPrincipale, VueScoreScreen vueScoreScreen) {
        this.vueScoreScreen = vueScoreScreen;
        this.modelBDD = modelPrincipale.getBdd();
        this.modelPrincipale = modelPrincipale;
        saveInBdd();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        saveInBdd();
    }

    public void saveInBdd(){
        boolean saved = false;
        try {
            saved = modelBDD.saveGame(modelPrincipale.getPlayerName(), modelPrincipale.getModelJeux().getScore(),modelPrincipale.getSeedIndex());
        } catch (SQLException ex) {
            System.out.println("Probleme poour sauvegarder la partie dans la bdd : "+ex.getMessage());
        }
        if (saved){
            vueScoreScreen.getSaveBddButton().setText("Sauvegarder !");
            vueScoreScreen.getSaveBddButton().setEnabled(false);
            vueScoreScreen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }else {
            vueScoreScreen.getSaveBddButton().setText("Re-essayer");
            vueScoreScreen.getSaveBddButton().setEnabled(true);
            vueScoreScreen.setCursor(new Cursor(Cursor.HAND_CURSOR));
            vueScoreScreen.getSaveBddButton().addActionListener(this);
        }
    }
}
