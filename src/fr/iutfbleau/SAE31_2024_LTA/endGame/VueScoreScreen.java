package fr.iutfbleau.SAE31_2024_LTA.endGame;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.menu.ControllerMenuCard;

import javax.swing.*;
import java.awt.*;

public class VueScoreScreen extends JPanel {
    private JPanel sidebarPanel;
    ModelPrincipale modelPrincipale;
    public VueScoreScreen(ModelPrincipale modelPrincipale) {
        setLayout(new BorderLayout());
        this.modelPrincipale = modelPrincipale;
        initSidebarComponent();
        add(sidebarPanel, BorderLayout.CENTER);
    }

    private void initSidebarComponent() {
        Color greyColor = new Color(44, 44, 44, 255);
        Font buttonMenuFont = new Font("Arial", Font.BOLD, 36);
        Font labelMenuFont = new Font("Arial", Font.BOLD, 30);

        sidebarPanel = new JPanel(new GridBagLayout());
        sidebarPanel.setBackground(new Color(193, 193, 193, 126));
        sidebarPanel.setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.gridx = 0;

        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setFont(labelMenuFont);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        gbc.gridy = 0;
        sidebarPanel.add(scoreLabel, gbc);

        scoreLabel.setText("Score : "+modelPrincipale.getModelJeux().getScore() + " Points");

        gbc.gridy = 1;

        JButton menuButton = new JButton("Menu");
        menuButton.setFont(buttonMenuFont);
        menuButton.setBackground(greyColor);
        menuButton.setForeground(Color.WHITE);
        menuButton.setPreferredSize(new Dimension(440, 80));
        menuButton.setFocusPainted(false);
        menuButton.setBorder(BorderFactory.createLineBorder(greyColor, 8, true));
        menuButton.addActionListener(new ControllerMenuCard(modelPrincipale));

        gbc.gridy = 3;
        sidebarPanel.add(menuButton, gbc);

        JButton settingsButton = new JButton("Paramètres");
        settingsButton.setFont(buttonMenuFont);
        settingsButton.setBackground(greyColor);
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setFocusPainted(false);
        settingsButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        settingsButton.addActionListener(modelPrincipale.getVuePrincipale().getControllerPopup());

        gbc.gridy = 4;
        sidebarPanel.add(settingsButton, gbc);

        JButton quitButton = new JButton("Quitter");
        quitButton.setFont(buttonMenuFont);
        quitButton.setBackground(greyColor);
        quitButton.setForeground(Color.WHITE);
        quitButton.setFocusPainted(false);
        quitButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        quitButton.addActionListener(e -> System.exit(0));

        gbc.gridy = 5;
        sidebarPanel.add(quitButton, gbc);
    }
}
