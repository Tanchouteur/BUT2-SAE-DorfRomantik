package fr.iutfbleau.SAE31_2024_LTA.partieJouer;

import fr.iutfbleau.SAE31_2024_LTA.Bdd.BddPartieJouer;
import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControllerSearchPartieJouer implements ActionListener {

    private final ModelPrincipale modelPrincipale;
    private String search;

    public ControllerSearchPartieJouer(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.search = modelPrincipale.getModelPartieJouer().getVuePartieJouer().getSearchField().getText();

        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getModelMediaLoader().getClicAudioClip(), false);

        if (search.isEmpty() || search.equals("Entrer le nom du joueur")) {
            resetPartie();
        } else {
            searchPartie();
        }
    }

    private void resetPartie() {
        if (modelPrincipale.getBdd().updateBdd()) {
            List<BddPartieJouer> allParties = modelPrincipale.getModelPartieJouer().getAllParties();

            modelPrincipale.getModelPartieJouer().getVuePartieJouer().getTableModel().setRowCount(0);
            for (BddPartieJouer partie : allParties) {
                modelPrincipale.getModelPartieJouer().getVuePartieJouer().getTableModel().addRow(new Object[]{partie.getPlayerName(), partie.getListeTuile().getId(), partie.getScore()});
            }

        }
    }

    private void searchPartie() {
        if (modelPrincipale.getBdd().updateBdd()) {
            List<BddPartieJouer> filteredParties = modelPrincipale.getModelPartieJouer().getVuePartieJouer().getModelPartieJouer().getFilteredParties(search);

            modelPrincipale.getModelPartieJouer().getVuePartieJouer().getTableModel().setRowCount(0);
            for (BddPartieJouer partie : filteredParties) {
                modelPrincipale.getModelPartieJouer().getVuePartieJouer().getTableModel().addRow(new Object[]{partie.getPlayerName(), partie.getListeTuile().getId(), partie.getScore()});
            }
        }
    }
    public int searchPartieOfPlayer(String playerName, int seed) {
        if (modelPrincipale.getBdd().updateBdd()) {
            List<BddPartieJouer> filteredParties = modelPrincipale.getModelPartieJouer().getVuePartieJouer().getModelPartieJouer().getFilteredParties(playerName);
            int score = 0;
            for (BddPartieJouer partie : filteredParties) {
                if (partie.getListeTuile().getSeed() == seed) {
                    if (partie.getScore() > score) {
                        score = partie.getScore();
                    }
                }
            }
            return score;
        }

        return 0;
    }
}
