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
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(174, 171, 171, 121));  // Transparent avec fond gris
        sidebarPanel.setBorder(BorderFactory.createLineBorder(new Color(44, 44, 44, 230), 8, true));

        sidebarPanel.setOpaque(true);

        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 40));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreLabel2 = new JLabel("0");
        scoreLabel2.setFont(new Font("Serif", Font.BOLD, 40));
        scoreLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel2.setText(modelPrincipale.getModelJeux().getScore() + " Points");

        sidebarPanel.add(scoreLabel);
        sidebarPanel.add(scoreLabel2);

        JButton menuButton = new JButton("Menu");
        menuButton.setFont(new Font("Arial", Font.BOLD, 36));
        menuButton.setBackground(new Color(44, 44, 44, 230));
        menuButton.setForeground(Color.WHITE);
        menuButton.setPreferredSize(new Dimension(440, 80));
        menuButton.setFocusPainted(false);
        menuButton.setBorder(BorderFactory.createLineBorder(new Color(44, 44, 44, 230), 8, true));
        menuButton.addActionListener(new ControllerMenuCard(modelPrincipale));
        sidebarPanel.add(menuButton);
    }
}
