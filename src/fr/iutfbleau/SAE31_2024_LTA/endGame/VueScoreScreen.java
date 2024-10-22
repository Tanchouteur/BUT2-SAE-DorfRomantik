package fr.iutfbleau.SAE31_2024_LTA.endGame;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.menu.ControllerMenuCard;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class VueScoreScreen extends JPanel {
    private JPanel sidebarPanel;
    private final ModelPrincipale modelPrincipale;
    private final int widthSidebar, heightSidebar;
    public VueScoreScreen(ModelPrincipale modelPrincipale) {
        widthSidebar = 450;
        heightSidebar = 600;
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

        ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/Titre.png")));
        Image image = logoIcon.getImage();
        Image resizedImage = image.getScaledInstance(180, 110, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        sidebarPanel.add(logoLabel, gbc);

        JLabel scoreLabel = getjLabel(labelMenuFont, buttonMenuFont, greyColor);
        gbc.gridy = 1;
        sidebarPanel.add(scoreLabel, gbc);

        JButton saveBddButton = new JButton();
        setFontButton(greyColor, buttonMenuFont, saveBddButton);
        if (modelPrincipale.getBdd().updateBdd()) {
            saveBddButton.addActionListener(new ControllerSaveGame(modelPrincipale));
            saveBddButton.setText("Save in Cloud");
        }else {
            saveBddButton.setText("Hors Ligne...");
        }

        gbc.gridy = 2;
        sidebarPanel.add(saveBddButton, gbc);

        JButton menuButton = new JButton("Menu");
        setFontButton(greyColor, buttonMenuFont, menuButton);
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
        settingsButton.addActionListener(modelPrincipale.getControllerPopup());

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

    private JLabel getjLabel(Font labelMenuFont, Font buttonMenuFont, Color greyColor) {
        JLabel scoreLabel = new JLabel();
        scoreLabel.setFont(labelMenuFont);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setText("Score : "+modelPrincipale.getModelJeux().getScore() + " Points");
        scoreLabel.setFont(buttonMenuFont);
        scoreLabel.setBackground(greyColor);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setPreferredSize(new Dimension(400, 80));
        scoreLabel.setBorder(BorderFactory.createLineBorder(greyColor, 5, true));
        return scoreLabel;
    }

    private void setFontButton(Color greyColor, Font buttonMenuFont, JButton button) {
        button.setFont(buttonMenuFont);
        button.setBackground(greyColor);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(440, 80));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(greyColor, 8, true));
    }

    public int getWidthSidebar() {
        return widthSidebar;
    }

    public int getHeightSidebar() {
        return heightSidebar;
    }
}
