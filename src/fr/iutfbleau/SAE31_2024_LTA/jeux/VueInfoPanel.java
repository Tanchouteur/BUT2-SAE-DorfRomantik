package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;

import javax.swing.*;
import java.awt.*;

import static fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent.setStyleButtonInGame;
import static fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent.setStyleLabelScore;

public class VueInfoPanel extends javax.swing.JPanel {
    private final JLabel currentScore;
    private JButton centrer;

    public VueInfoPanel(ModelJeux modelJeux) {
        this.setLayout(null);
        this.setBackground(new Color(40, 40, 40, 10));
        this.setBounds(0, 0, modelJeux.getModelPrincipale().getVuePrincipale().getWidth(), modelJeux.getModelPrincipale().getVuePrincipale().getHeight());
        this.setOpaque(false);
        JLabel playerNameLabel = new JLabel(modelJeux.getModelPrincipale().getPlayerName());
        playerNameLabel.setBounds(30,30,200,50);
        this.add(setStyleLabelScore(playerNameLabel,18));

        JLabel bestScoreLabel = new JLabel("Record " + modelJeux.getModelPrincipale().getModelPartieJouer().getVuePartieJouer().getControllerSearchPartieJouer().searchPartieOfPlayer(modelJeux.getModelPrincipale().getPlayerName(), modelJeux.getModelPrincipale().getSelectedSeed()) + " Points");
        bestScoreLabel.setBounds(250,30,250,50);
        this.add(setStyleLabelScore(bestScoreLabel,16));

        currentScore = new JLabel("Score " + modelJeux.getScore() + " Points");
        currentScore.setBounds(520,30,250,50);
        this.add(setStyleLabelScore(currentScore,18));

        centrer = new JButton("Centrer");
        centrer.addActionListener(e -> modelJeux.getVueJeux().centrer());
        centrer.setBounds((VuePrincipale.frameWidth / 2) - 200, 100, 200, 50);
        centrer = setStyleButtonInGame(centrer, 34);
    }
    public JLabel getCurrentScore() {
        return currentScore;
    }

    public JButton getCentrerButton() {
        return centrer;
    }
}
