package fr.iutfbleau.SAE31_2024_LTA.menu;

import fr.iutfbleau.SAE31_2024_LTA.Bdd.BddListeTuiles;
import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.jeux.ControllerPlayCard;
import fr.iutfbleau.SAE31_2024_LTA.partieJouer.ControllerPartieJouerBTN;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class VueMenu extends JPanel {
    private JTextField playerNameInput;
    private JComboBox<String> suiteSelector;
    private final ModelPrincipale modelPrincipale;
    private final JLabel backgroundImage;
    private JPanel sidebarPanel;

    public VueMenu(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
        setLayout(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        ImageIcon bgIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/FondMenu.jpg")));
        backgroundImage = new JLabel(bgIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = bgIcon.getImage();

                int windowWidth = getWidth();
                int windowHeight = getHeight();
                int imageWidth = img.getWidth(null);
                int imageHeight = img.getHeight(null);

                double windowRatio = (double) windowWidth / windowHeight;
                double imageRatio = (double) imageWidth / imageHeight;

                int newWidth, newHeight;

                if (imageRatio > windowRatio) {
                    newWidth = windowWidth;
                    newHeight = (int) (windowWidth / imageRatio);
                }else {
                    newHeight = windowHeight;
                    newWidth = (int) (windowHeight * imageRatio);
                }

                int x = (windowWidth - newWidth) / 2;
                int y = (windowHeight - newHeight) / 2;

                g.drawImage(img, x, y, newWidth, newHeight, this);
            }
        };

        layeredPane.add(backgroundImage, Integer.valueOf(0));

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                updateMenu();
            }
        });

        initSidebarComponent();
        layeredPane.add(sidebarPanel, Integer.valueOf(1));
    }

    public void updateMenu() {
        backgroundImage.setSize(getWidth(), getHeight());

        sidebarPanel.setBounds(getWidth() - 560, (getHeight() - 400) / 2, 450, 400);
        repaint();
    }

    /**
     * Initialise la barre latérale avec les composants nécessaires.
     */
    private void initSidebarComponent() {
        Color greyColor = new Color(44, 44, 44, 230);
        Font buttonMenuFont = new Font("Arial", Font.BOLD, 36);
        Font inputMenuFont = new Font("Arial", Font.BOLD, 24);
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(174, 171, 171, 121));  // Transparent avec fond gris
        sidebarPanel.setOpaque(true);

        sidebarPanel.add(Box.createVerticalStrut(40));

        playerNameInput = new JTextField("Player Name...", 15);
        playerNameInput.setMaximumSize(new Dimension(400, 60));
        playerNameInput.setFont(inputMenuFont);
        playerNameInput.setForeground(Color.GRAY);
        playerNameInput.setBackground(new Color(245, 245, 245, 216));
        playerNameInput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 10, false),
                BorderFactory.createEmptyBorder(7, 7, 7, 7)
        ));
        playerNameInput.addFocusListener(new ControllerFocus(this, modelPrincipale));
        sidebarPanel.add(playerNameInput);

        sidebarPanel.add(Box.createVerticalStrut(20));

        suiteSelector = new JComboBox<>();
        suiteSelector.setMaximumSize(new Dimension(400, 50));
        suiteSelector.setFont(inputMenuFont);
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
                BorderFactory.createLineBorder(greyColor, 10, false),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        sidebarPanel.add(suiteSelector);

        sidebarPanel.add(Box.createVerticalStrut(40));

        JButton partieJouerBtn = new JButton("Partie Jouer");
        partieJouerBtn.setFont(buttonMenuFont);
        partieJouerBtn.setBackground(greyColor);
        partieJouerBtn.setForeground(Color.WHITE);
        partieJouerBtn.setPreferredSize(new Dimension(440, 80));
        partieJouerBtn.setFocusPainted(false);
        partieJouerBtn.setBorder(BorderFactory.createLineBorder(greyColor, 8, true));
        partieJouerBtn.addActionListener(new ControllerPartieJouerBTN(modelPrincipale));
        sidebarPanel.add(partieJouerBtn);

        sidebarPanel.add(Box.createVerticalStrut(20));

        JButton playButton = new JButton("Jouer");
        playButton.setFont(buttonMenuFont);
        playButton.setBackground(greyColor);
        playButton.setForeground(Color.WHITE);
        playButton.setPreferredSize(new Dimension(440, 80));
        playButton.setFocusPainted(false);
        playButton.setBorder(BorderFactory.createLineBorder(greyColor, 8, true));
        playButton.addActionListener(new ControllerPlayCard(modelPrincipale, listeTuiles));
        sidebarPanel.add(playButton);
    }

    public JComboBox<String> getSuiteSelector() {
        return suiteSelector;
    }

    public JTextField getPlayerNameInput() {
        return playerNameInput;
    }
}
