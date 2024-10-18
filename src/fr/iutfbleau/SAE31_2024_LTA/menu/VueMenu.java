package fr.iutfbleau.SAE31_2024_LTA.menu;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

import fr.iutfbleau.SAE31_2024_LTA.partieJouer.ControllerPartieJouerBTN;
import fr.iutfbleau.SAE31_2024_LTA.jeux.ControllerPlayCard;
import fr.iutfbleau.SAE31_2024_LTA.Bdd.BddListeTuiles;
import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

public class VueMenu extends JPanel {
    public JTextField playerNameInput;
    private JComboBox<String> suiteSelector;
    private final ModelPrincipale modelPrincipale;

    public VueMenu(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(modelPrincipale.getVuePrincipale().getWidth(), modelPrincipale.getVuePrincipale().getHeight()));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        JLabel backgroundImage = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/FondMenu.png"))));
        backgroundImage.setBounds(0, 0, modelPrincipale.getVuePrincipale().getWidth(), modelPrincipale.getVuePrincipale().getHeight());
        layeredPane.add(backgroundImage, Integer.valueOf(0));

        initSidebarComponent(layeredPane);
    }

    /**
     * Initialise la barre latérale avec les composants nécessaires.
     */
    private void initSidebarComponent(JLayeredPane layeredPane) {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBounds(1300, 450, 450, 400);
        sidebarPanel.setBackground(new Color(44, 44, 44, 0));
        sidebarPanel.setOpaque(true);

        sidebarPanel.add(Box.createVerticalStrut(40));

        playerNameInput = new JTextField("Player Name...", 15);
        playerNameInput.setMaximumSize(new Dimension(400, 60));
        playerNameInput.setFont(new Font("Arial", Font.BOLD, 24));
        playerNameInput.setForeground(Color.GRAY);
        playerNameInput.setBackground(new Color(245, 245, 245, 216));

        playerNameInput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(44, 44, 44, 240), 10, true),
                BorderFactory.createEmptyBorder(7, 7, 7, 7)
        ));

        playerNameInput.addFocusListener(new ControllerFocus(this, modelPrincipale));
        sidebarPanel.add(playerNameInput);

        sidebarPanel.add(Box.createVerticalStrut(20));

        suiteSelector = new JComboBox<>();
        suiteSelector.setMaximumSize(new Dimension(400, 50));
        suiteSelector.setFont(new Font("Arial", Font.PLAIN, 20));
        suiteSelector.setBackground(Color.WHITE);
        suiteSelector.setForeground(Color.BLACK);

        suiteSelector.addItem("Choisir une suite...");
        List<BddListeTuiles> listeTuiles = modelPrincipale.getBdd().getListeTuiles();
        for (BddListeTuiles tuile : listeTuiles) {
            suiteSelector.addItem("Suite : " + tuile.getId() + " - BestScore: " +
                    (tuile.getBestScore() != null ? tuile.getBestScore() : "N/A"));
        }
        suiteSelector.addItem("Suite Aléatoire");

        suiteSelector.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(44, 44, 44, 255), 10, true),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        sidebarPanel.add(suiteSelector);

        sidebarPanel.add(Box.createVerticalStrut(40));

        JButton partieJouerBtn = new JButton("Partie Jouer");
        partieJouerBtn.setFont(new Font("Arial", Font.BOLD, 36));
        partieJouerBtn.setBackground(new Color(44, 44, 44, 230));
        partieJouerBtn.setForeground(Color.WHITE);
        partieJouerBtn.setPreferredSize(new Dimension(440, 80));
        partieJouerBtn.setFocusPainted(false);
        partieJouerBtn.setBorder(BorderFactory.createLineBorder(new Color(44, 44, 44, 230), 8, true));
        partieJouerBtn.setAlignmentY(100);
        partieJouerBtn.addActionListener(new ControllerPartieJouerBTN(modelPrincipale));
        sidebarPanel.add(partieJouerBtn);

        sidebarPanel.add(Box.createVerticalStrut(20));

        JButton playButton = new JButton("Jouer");
        playButton.setFont(new Font("Arial", Font.BOLD, 36));
        playButton.setBackground(new Color(44, 44, 44, 230));
        playButton.setForeground(Color.WHITE);
        playButton.setPreferredSize(new Dimension(440, 80));
        playButton.setFocusPainted(false);
        playButton.setBorder(BorderFactory.createLineBorder(new Color(44, 44, 44, 230), 8, true));

        playButton.addActionListener(new ControllerPlayCard(modelPrincipale, listeTuiles));
        sidebarPanel.add(playButton);

        layeredPane.add(sidebarPanel, Integer.valueOf(1));
    }

    public JComboBox<String> getSuiteSelector() {
        return suiteSelector;
    }
}
