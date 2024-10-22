package fr.iutfbleau.SAE31_2024_LTA;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;


public class VuePrincipale extends JFrame {
    private final PrincipaleLayeredPane principaleLayeredPane;
    ModelPrincipale modelPrincipale;

    public VuePrincipale(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
        setTitle("DorfRomantique Alpha");
        setSize(1370,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900,600));
        setResizable(true);
        setLayout(new BorderLayout());

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

        principaleLayeredPane = new PrincipaleLayeredPane();
        this.add(principaleLayeredPane, BorderLayout.CENTER);
    }

    public CardLayout getCardLayout() {
        return principaleLayeredPane.getCardLayout();
    }

    public Container getFramePane() {
        return principaleLayeredPane.getMainPanel();
    }

    public JPanel getMainPanel() {
        return principaleLayeredPane.getMainPanel();
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
