package fr.iutfbleau.SAE31_2024_LTA.jeux;

import javax.swing.*;
import java.awt.*;

public class VueInfoPanel extends javax.swing.JPanel {
    private final JLabel currentScore;
    public VueInfoPanel(ModelJeux modelJeux) {
        Color greyColor = new Color(44, 44, 44, 255);
        Font buttonMenuFont = new Font("Arial", Font.BOLD, 18);
        Font inputMenuFont = new Font("Arial", Font.BOLD, 24);

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(112, 112, 112, 181));

        JLabel playerNameLabel = new JLabel(modelJeux.getModelPrincipale().getPlayerName());
        playerNameLabel.setFont(inputMenuFont);
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setBackground(greyColor);
        playerNameLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(7, 7, 7, 7)
        ));

        JLabel bestScoreLabel = new JLabel("Ton record : "+modelJeux.getModelPrincipale().getModelPartieJouer().getVuePartieJouer().getControllerSearchPartieJouer().searchPartieOfPlayer(modelJeux.getModelPrincipale().getPlayerName(),modelJeux.getModelPrincipale().getSelectedSeed())+" Points");
        bestScoreLabel.setFont(buttonMenuFont);
        bestScoreLabel.setBackground(greyColor);
        bestScoreLabel.setForeground(Color.WHITE);
        bestScoreLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(7, 10, 7, 10)
        ));


        currentScore = new JLabel("Score : "+modelJeux.getScore()+" Points");
        currentScore.setFont(buttonMenuFont);
        currentScore.setBackground(greyColor);
        currentScore.setForeground(Color.WHITE);
        currentScore.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        this.add(playerNameLabel,BorderLayout.WEST);
        this.add(bestScoreLabel,BorderLayout.CENTER);
        this.add(currentScore,BorderLayout.EAST);
    }
    public JLabel getCurrentScore() {
        return currentScore;
    }
}
