package fr.iutfbleau.SAE31_2024_LTA.endGame;

import fr.iutfbleau.SAE31_2024_LTA.Bdd.ModelBDD;
import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ControllerSaveGame implements ActionListener {
    ModelBDD modelBDD;
    ModelPrincipale modelPrincipale;
    public ControllerSaveGame(ModelPrincipale modelPrincipale) {

        modelBDD = modelPrincipale.getBdd();
        this.modelPrincipale = modelPrincipale;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            modelBDD.saveGame(modelPrincipale.getPlayerName(), modelPrincipale.getModelJeux().getScore(),modelPrincipale.getSeedIndex());
        } catch (SQLException ex) {
            System.out.println("Probleme poour sauvegarder la partie dans la bdd : "+ex.getMessage());
        }


    }
}
