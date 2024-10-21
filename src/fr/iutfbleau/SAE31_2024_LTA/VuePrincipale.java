package fr.iutfbleau.SAE31_2024_LTA;

import fr.iutfbleau.SAE31_2024_LTA.popup.ControllerPopup;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

/**
 * La classe VuePrincipale représente la fenêtre principale de l'application DorfRomantique.
 */
public class VuePrincipale extends JFrame {
    private final CardLayout cardLayout;
    private final Container framePane;

    ModelPrincipale modelPrincipale;
    /**
     * Constructeur de la classe VuePrincipale. Initialise la fenêtre,
     * les composants et les vues de l'application.
     */
    public VuePrincipale(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
        setTitle("DorfRomantique Alpha");
        setSize(1370,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900,600));
        setResizable(true);

        try {
            URL logoUrl = getClass().getResource("/Images/logo.png");
            if (logoUrl != null) {
                setIconImage(ImageIO.read(logoUrl));
            } else {
                System.err.println("Logo non trouvé : /Images/Logo.png");
            }
        } catch (IOException e) {
            System.out.println("logo err : " + e);
        }

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        framePane = getContentPane();

    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public Container getFramePane() {
        return framePane;
    }

    public ModelPrincipale getModelPrincipale() {
        return modelPrincipale;
    }

    public void setActionMap(){
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "openSettings");
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put("openSettings", modelPrincipale.getControllerPopup());
    }
}
