package fr.iutfbleau.SAE31_2024_LTA.layers;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final CardLayout cardLayout;
    public MainPanel(VuePrincipale vuePrincipale){
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
    }
    public CardLayout getCardLayout(){
        return this.cardLayout;
    }
}
