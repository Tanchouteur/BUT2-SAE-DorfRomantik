package fr.iutfbleau.SAE31_2024_LTA.jeux.controller;

import fr.iutfbleau.SAE31_2024_LTA.Bdd.BddListeTuiles;
import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.menu.VueMenu;
import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;

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
        String playerName = modelPrincipale.getModelMenu().getVueMenu().getPlayerNameInput().getText();
        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getModelMediaLoader().getClicAudioClip(), false);

        if (playerName.isEmpty() || playerName.equals("Player Name...")) {
            JOptionPane.showMessageDialog(vueMenu, "Veuillez entrer un nom de joueur !");
        } else {
            int selectedIndex = vueMenu.getSuiteSelector().getSelectedIndex();

            if (selectedIndex == 0) {
                JOptionPane.showMessageDialog(vueMenu, "Veuillez choisir une suite");
            } else {
                int seed;
                if (listeTuiles != null) {
                    if (selectedIndex == 1) {
                        Random rand = new Random();
                        seed = rand.nextInt();
                        modelPrincipale.setSeedIndex(-1);
                    } else {

                        seed = listeTuiles.get(selectedIndex - 1).getSeed();//listetuile de la bdd
                        modelPrincipale.setSeedIndex(selectedIndex);

                    }
                }else {
                    Random rand = new Random();
                    seed = rand.nextInt();
                    modelPrincipale.setSeedIndex(-1);
                }

                modelPrincipale.setPlayerName(playerName);
                modelPrincipale.getConfigManager().setPlayerName(playerName);
                modelPrincipale.setSelectedSeed(seed);


                modelPrincipale.getMediaPlayerManager().stopClip(modelPrincipale.getModelMediaLoader().getMenuMusicClip());
                modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getModelMediaLoader().getGameMusicClips(),0);

                modelPrincipale.createJeux();
                vuePrincipale.getPrincipaleLayeredPane().getMainPanel().getCardLayout().show(vuePrincipale.getPrincipaleLayeredPane().getMainPanel(), "jeux");
            }
        }
        vuePrincipale.setTitle("DorfJavatik - Jeux en cours...  0 points");
    }
}