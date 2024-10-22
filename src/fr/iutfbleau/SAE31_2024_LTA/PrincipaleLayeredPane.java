package fr.iutfbleau.SAE31_2024_LTA;

import javax.swing.*;
import java.awt.*;

public class PrincipaleLayeredPane extends JLayeredPane {
    private  CardLayout cardLayout;
    private  JPanel mainPanel;
    private  JPanel panelCouchePopup;

    public PrincipaleLayeredPane() {

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        this.add(mainPanel, Integer.valueOf(0));


        panelCouchePopup = new JPanel();
        panelCouchePopup.setLayout(new BorderLayout());
        this.add(mainPanel, Integer.valueOf(1));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    public JPanel getPanelCouchePopup() {
        return panelCouchePopup;
    }
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public Container getFramePane() {
        return this.mainPanel.getRootPane();
    }
}
