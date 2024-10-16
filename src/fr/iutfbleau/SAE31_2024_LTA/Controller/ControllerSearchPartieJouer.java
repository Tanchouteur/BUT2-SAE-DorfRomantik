package fr.iutfbleau.SAE31_2024_LTA.Controller;

import fr.iutfbleau.SAE31_2024_LTA.Model.Bdd.BddPartieJouer;
import fr.iutfbleau.SAE31_2024_LTA.Vue.VuePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControllerSearchPartieJouer implements ActionListener {

    private final VuePrincipale vuePrincipale;
    private String search;

    public ControllerSearchPartieJouer(VuePrincipale vuePrincipale) {
        this.vuePrincipale = vuePrincipale;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.search = vuePrincipale.getVuePartieJouer().getSearchField().getText();

        vuePrincipale.getModelPrincipale().getMediaPlayerManager().startClip(vuePrincipale.getModelPrincipale().getMediaPlayerManager().getClicAudioClip(), false);

        if (search.isEmpty() || search.equals("Entrer le nom du joueur")) {
            resetPartie();
        } else {
            searchPartie();
        }
    }

    private void resetPartie() {
        List<BddPartieJouer> allParties = vuePrincipale.getVuePartieJouer().getModelPartieJouer().getAllParties();
        SwingUtilities.invokeLater(() -> {
            vuePrincipale.getVuePartieJouer().getTableModel().setRowCount(0);
            for (BddPartieJouer partie : allParties) {
                vuePrincipale.getVuePartieJouer().getTableModel().addRow(new Object[]{partie.getPlayerName(), partie.getListeTuile().getId(), partie.getScore()});
            }
        });
    }

    private void searchPartie() {
        List<BddPartieJouer> filteredParties = vuePrincipale.getVuePartieJouer().getModelPartieJouer().getFilteredParties(search);
        SwingUtilities.invokeLater(() -> {
            vuePrincipale.getVuePartieJouer().getTableModel().setRowCount(0);
            for (BddPartieJouer partie : filteredParties) {
                vuePrincipale.getVuePartieJouer().getTableModel().addRow(new Object[]{partie.getPlayerName(), partie.getListeTuile().getId(), partie.getScore()});
            }
        });
    }
}
