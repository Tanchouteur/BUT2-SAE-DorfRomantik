package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.Bdd.BddListeTuiles;
import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.menu.VueMenu;
import fr.iutfbleau.SAE31_2024_LTA.VuePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class ControllerPlayCard implements ActionListener {
    private final VuePrincipale vuePrincipale;
    private final List<BddListeTuiles> listeTuiles;
    ModelPrincipale modelPrincipale;

    public ControllerPlayCard(ModelPrincipale modelPrincipale, List<BddListeTuiles> listeTuiles) {
        this.modelPrincipale = modelPrincipale;
        this.vuePrincipale = modelPrincipale.getVuePrincipale();
        this.listeTuiles = listeTuiles;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VueMenu vueMenu = modelPrincipale.getModelMenu().getVueMenu();
        String playerName = modelPrincipale.getModelMenu().getVueMenu().playerNameInput.getText();
        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getMediaPlayerManager().getClicAudioClip(), false);

        if (playerName.isEmpty() || playerName.equals("Player Name...")) {
            JOptionPane.showMessageDialog(vueMenu, "Veuillez entrer un nom de joueur !");
        } else {
            int selectedIndex = vueMenu.getSuiteSelector().getSelectedIndex();
            int lastIndex = vueMenu.getSuiteSelector().getItemCount() - 1;

            if (selectedIndex == 0) {
                JOptionPane.showMessageDialog(vueMenu, "Veuillez choisir une suite");
            } else {
                int seed;
                if (selectedIndex == lastIndex) {
                    Random rand = new Random();
                    seed = rand.nextInt();
                } else {
                    seed = listeTuiles.get(selectedIndex - 1).getSeed();//listetuile de la bdd
                }

                modelPrincipale.setPlayerName(playerName);
                modelPrincipale.setSelectedSeed(seed);

                modelPrincipale.getMediaPlayerManager().stopClip(modelPrincipale.getMediaPlayerManager().getMenuMusicClip());

                modelPrincipale.createJeux();
                vuePrincipale.getCardLayout().show(vuePrincipale.getFramePane(), "jeux");
            }
        }
        vuePrincipale.setTitle("DorfJavatik - Jeux en cours...  0 points");
    }
}
