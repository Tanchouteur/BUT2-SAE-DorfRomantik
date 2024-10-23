package fr.iutfbleau.SAE31_2024_LTA.layers;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.popup.ControllerInputMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;


public class VuePrincipale extends JFrame {
    private final PrincipaleLayeredPane principaleLayeredPane;
    ModelPrincipale modelPrincipale;

    public static int frameWidth;
    public static int frameHeight;

    public static int screenWidth;
    public static int screenHeight;

    public VuePrincipale(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
        setTitle("DorfRomantique");

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize);
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;

        frameWidth = this.getSize().width;
        frameHeight = this.getSize().height;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900,600));
        setResizable(true);
        principaleLayeredPane = new PrincipaleLayeredPane(this);
        this.add(principaleLayeredPane);
        this.getPrincipaleLayeredPane().getMainPanel().setSize(this.getWidth(),this.getHeight());

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
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                updateSize();
                modelPrincipale.getControllerPopup().updatePopup();
            }
        });
    }
    public void updateSize(){
        frameWidth = this.getWidth();
        frameHeight = this.getHeight();
        this.getPrincipaleLayeredPane().getMainPanel().setSize(this.getWidth(),this.getHeight());
    }

    public PrincipaleLayeredPane getPrincipaleLayeredPane(){
        return this.principaleLayeredPane;
    }

    public ModelPrincipale getModelPrincipale() {
        return modelPrincipale;
    }

    public void setActionMap(){
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "toggleSettings");
        actionMap.put("toggleSettings", new ControllerInputMap(modelPrincipale,"toggleSettingsAction"));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), "undo");
        actionMap.put("undo", new ControllerInputMap(modelPrincipale, "undo"));
    }
}
