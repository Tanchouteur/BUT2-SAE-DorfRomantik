package fr.iutfbleau.SAE31_2024_LTA;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;


public class VuePrincipale extends JFrame {
    private final PrincipaleLayeredPane principaleLayeredPane;
    ModelPrincipale modelPrincipale;

    public VuePrincipale(ModelPrincipale modelPrincipale) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension screenSize = toolkit.getScreenSize();
        int dpi = toolkit.getScreenResolution();

        double scaleFactor = dpi / 96.0; // 96 DPI est souvent considéré comme 100% (100%)

        int adjustedWidth = (int) (screenSize.width / scaleFactor);
        int adjustedHeight = (int) (screenSize.height / scaleFactor);

        this.modelPrincipale = modelPrincipale;
        setTitle("DorfRomantique Alpha");
        setSize(adjustedWidth,adjustedHeight);
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
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "openSettings");
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put("openSettings", modelPrincipale.getControllerPopup());
    }
}
