package fr.iutfbleau.SAE31_2024_LTA.menu;

import fr.iutfbleau.SAE31_2024_LTA.Bdd.BddListeTuiles;
import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.jeux.ControllerPlayCard;
import fr.iutfbleau.SAE31_2024_LTA.partieJouer.ControllerPartieJouerBTN;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class VueMenu extends JPanel {
    private JTextField playerNameInput;
    private JComboBox<String> suiteSelector;
    private final ModelPrincipale modelPrincipale;
    private final JLabel backgroundImage;
    private JPanel sidebarPanel;
    private JLayeredPane layeredPane;

    public VueMenu(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
        setLayout(null);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, modelPrincipale.getVuePrincipale().getWidth(), modelPrincipale.getVuePrincipale().getHeight());
        add(layeredPane);

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
        layeredPane.setBounds(0, 0, modelPrincipale.getVuePrincipale().getWidth(), modelPrincipale.getVuePrincipale().getHeight());
        sidebarPanel.setBounds(getWidth() - 470, (getHeight() - 520) / 2, 400, 500);
        //repaint();
    }

    /**
     * Initialise la barre latérale avec les composants nécessaires.
     */
    private void initSidebarComponent() {
        Color greyColor = new Color(44, 44, 44, 255);
        Font buttonMenuFont = new Font("Arial", Font.BOLD, 30);
        Font inputMenuFont = new Font("Arial", Font.BOLD, 24);

        sidebarPanel = new JPanel(new GridBagLayout());
        sidebarPanel.setBackground(new Color(193, 193, 193, 89));
        sidebarPanel.setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.gridx = 0;

        playerNameInput = new JTextField("Player Name...", 15);
        playerNameInput.setFont(inputMenuFont);
        playerNameInput.setForeground(Color.GRAY);
        playerNameInput.setBackground(new Color(245, 245, 245, 216));
        playerNameInput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(7, 7, 7, 7)
        ));
        playerNameInput.addFocusListener(new ControllerFocus(this, modelPrincipale));

        gbc.gridy = 0;
        sidebarPanel.add(playerNameInput, gbc);

        suiteSelector = new JComboBox<>();
        suiteSelector.setFont(inputMenuFont);
        suiteSelector.setBackground(Color.WHITE);
        suiteSelector.setForeground(Color.BLACK);
        suiteSelector.addItem("Choisir une suite...");

        List<BddListeTuiles> listeTuiles;
        if (modelPrincipale.getBdd().updateBdd()) {
            listeTuiles = modelPrincipale.getBdd().getListeTuiles();
            for (BddListeTuiles tuile : listeTuiles) {
                String suiteName = "";
                if (tuile.getId() != -1) {
                    suiteName = String.valueOf(tuile.getId());
                } else {
                    suiteName = "Aléatoire";
                }
                suiteSelector.addItem("Suite : " + suiteName + " - BestScore: " +
                        (tuile.getBestScore() != null ? tuile.getBestScore() : "N/A"));
            }
        }else {
            suiteSelector.addItem("Suite : Aléatoire");
            listeTuiles = null;
        }

        suiteSelector.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        suiteSelector.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                repaint();
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                repaint();
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                repaint();
            }
        });

        gbc.gridy = 1;
        sidebarPanel.add(suiteSelector, gbc);

        JButton playButton = new JButton("Jouer");
        playButton.setFont(buttonMenuFont);
        playButton.setBackground(greyColor);
        playButton.setForeground(Color.WHITE);
        playButton.setFocusPainted(false);
        playButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        playButton.addActionListener(new ControllerPlayCard(modelPrincipale, listeTuiles));

        gbc.gridy = 2;
        sidebarPanel.add(playButton, gbc);


        JButton partieJouerBtn = new JButton("Partie Jouer");
        partieJouerBtn.setFont(buttonMenuFont);
        partieJouerBtn.setBackground(greyColor);
        partieJouerBtn.setForeground(Color.WHITE);
        partieJouerBtn.setFocusPainted(false);
        partieJouerBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        partieJouerBtn.addActionListener(new ControllerPartieJouerBTN(modelPrincipale));

        gbc.gridy = 3;
        sidebarPanel.add(partieJouerBtn, gbc);

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


    public JComboBox<String> getSuiteSelector() {
        return suiteSelector;
    }

    public JTextField getPlayerNameInput() {
        return playerNameInput;
    }
}
