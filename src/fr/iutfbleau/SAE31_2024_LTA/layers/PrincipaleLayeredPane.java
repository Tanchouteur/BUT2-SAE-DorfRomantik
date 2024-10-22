package fr.iutfbleau.SAE31_2024_LTA.layers;

import javax.swing.*;
import java.awt.*;

public class PrincipaleLayeredPane extends JLayeredPane {
    private final MainPanel mainPanel;

    public PrincipaleLayeredPane(VuePrincipale vuePrincipale) {
        mainPanel = new MainPanel(vuePrincipale);
        this.add(mainPanel, Integer.valueOf(0));
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public CardLayout getMainCardLayout() {
        return mainPanel.getCardLayout();
    }
}
