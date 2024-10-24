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
    boolean saved = false;

    public ControllerSaveGame(ModelPrincipale modelPrincipale, VueScoreScreen vueScoreScreen) {
        this.vueScoreScreen = vueScoreScreen;
        this.modelBDD = modelPrincipale.getBdd();
        this.modelPrincipale = modelPrincipale;
        try {
            saveInBdd();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            saveInBdd();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveInBdd() throws SQLException {
        if (saved){
            return;
        }

        if (modelBDD.saveGame(modelPrincipale.getPlayerName(), modelPrincipale.getModelJeux().getScore(),modelPrincipale.getSeedIndex()-1)){
            saved = true;
            vueScoreScreen.getSaveBddButton().setText("Sauvegarder !");
            vueScoreScreen.getSaveBddButton().setEnabled(false);
            vueScoreScreen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            return;
        }

        if (saved){
            return;
        }

        System.out.println("" + saved);

        vueScoreScreen.getSaveBddButton().setText("Re-essayer");
        vueScoreScreen.getSaveBddButton().setEnabled(true);
        vueScoreScreen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vueScoreScreen.getSaveBddButton().addActionListener(this);
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}
