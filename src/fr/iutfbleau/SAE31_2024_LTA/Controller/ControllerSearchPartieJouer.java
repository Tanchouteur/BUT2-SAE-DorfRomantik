package fr.iutfbleau.SAE31_2024_LTA.Controller;

import fr.iutfbleau.SAE31_2024_LTA.Model.Bdd.BddPartieJouer;
import fr.iutfbleau.SAE31_2024_LTA.Model.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.Vue.VuePrincipale;

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

        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getMediaPlayerManager().getClicAudioClip(), false);

        if (search.isEmpty() || search.equals("Entrer le nom du joueur")) {
            resetPartie();
        } else {
            searchPartie();
        }
    }

    private void resetPartie() {
        List<BddPartieJouer> allParties = modelPrincipale.getModelPartieJouer().getAllParties();
        SwingUtilities.invokeLater(() -> {
            modelPrincipale.getModelPartieJouer().getVuePartieJouer().getTableModel().setRowCount(0);
            for (BddPartieJouer partie : allParties) {
                modelPrincipale.getModelPartieJouer().getVuePartieJouer().getTableModel().addRow(new Object[]{partie.getPlayerName(), partie.getListeTuile().getId(), partie.getScore()});
            }
        });
    }

    private void searchPartie() {
        List<BddPartieJouer> filteredParties = modelPrincipale.getModelPartieJouer().getVuePartieJouer().getModelPartieJouer().getFilteredParties(search);
        SwingUtilities.invokeLater(() -> {
            modelPrincipale.getModelPartieJouer().getVuePartieJouer().getTableModel().setRowCount(0);
            for (BddPartieJouer partie : filteredParties) {
                modelPrincipale.getModelPartieJouer().getVuePartieJouer().getTableModel().addRow(new Object[]{partie.getPlayerName(), partie.getListeTuile().getId(), partie.getScore()});
            }
        });
    }
}
