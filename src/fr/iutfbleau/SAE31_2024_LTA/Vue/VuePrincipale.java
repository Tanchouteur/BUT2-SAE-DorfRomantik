package fr.iutfbleau.SAE31_2024_LTA.Vue;

import fr.iutfbleau.SAE31_2024_LTA.Model.ModelPrincipale;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * La classe VuePrincipale représente la fenêtre principale de l'application DorfRomantique.
 * Elle gère les différentes vues de l'application (menu, jeu, score) en utilisant un CardLayout.
 */
public class VuePrincipale extends JFrame {
    private final CardLayout cardLayout;
    private final Container framePane;
    private final ModelPrincipale modelPrincipale;
    private final VueMenu vueMenu;
    private final VueScoreScreen vueScoreScreen;
    private final VueJeux vueJeux;
    private final VuePartieJouer vuePartieJouer;

    /**
     * Constructeur de la classe VuePrincipale. Initialise la fenêtre,
     * les composants et les vues de l'application.
     */
    public VuePrincipale(){
        modelPrincipale = new ModelPrincipale();

        setTitle("DorfRomantique Alpha");
        setSize(1920,1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1650,1050));
        setResizable(false);

        try {
            URL logoUrl = getClass().getResource("/Images/logo.png");
            if (logoUrl != null) {
                Image logo = ImageIO.read(logoUrl);
                setIconImage(logo);
            } else {
                System.err.println("Logo non trouvé : /Images/Logo.png");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        framePane = getContentPane();

        vueMenu = new VueMenu(this);
        vuePartieJouer = new VuePartieJouer(this);
        vueJeux = new VueJeux(this);
        vueScoreScreen = new VueScoreScreen();

        add(vueMenu, "menu");
        add(vuePartieJouer, "partieJouer");
        add(vueJeux, "jeux");
        add(vueScoreScreen, "score");
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public Container getFramePane() {
        return framePane;
    }
    public VueMenu getVueMenu() {
        return vueMenu;
    }
    public VuePartieJouer getVuePartieJouer() { return vuePartieJouer; }
    public VueJeux getVueJeux() {
        return this.vueJeux;
    }
    public VueScoreScreen getVueScoreScreen() {
        return vueScoreScreen;
    }
    public ModelPrincipale getModelPrincipale() {
        return modelPrincipale;
    }
}
