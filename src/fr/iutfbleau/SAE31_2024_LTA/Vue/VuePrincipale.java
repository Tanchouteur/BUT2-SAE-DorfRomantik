package fr.iutfbleau.SAE31_2024_LTA.Vue;

import fr.iutfbleau.SAE31_2024_LTA.Model.ModelPrincipale;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * La classe VuePrincipale représente la fenêtre principale de l'application DorfRomantique.
 */
public class VuePrincipale extends JFrame {
    private final CardLayout cardLayout;
    private final Container framePane;

    ModelPrincipale modelPrincipale;

    private final VueScoreScreen vueScoreScreen;
    /**
     * Constructeur de la classe VuePrincipale. Initialise la fenêtre,
     * les composants et les vues de l'application.
     */
    public VuePrincipale(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
        setTitle("DorfRomantique Alpha");
        setSize(1920,1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1650,1050));
        setResizable(false);

        setIconImage(modelPrincipale.getLogo());

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        framePane = getContentPane();

        vueScoreScreen = new VueScoreScreen();

        add(vueScoreScreen, "score");
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public Container getFramePane() {
        return framePane;
    }

    public VueScoreScreen getVueScoreScreen() {
        return vueScoreScreen;
    }
}
