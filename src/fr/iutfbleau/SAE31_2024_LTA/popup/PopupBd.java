package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent;

import javax.swing.*;
import java.awt.*;

public class PopupBd extends JPanel {

    public PopupBd(String message,Boolean good){
        setLayout(null);
        setOpaque(false);
        setBackground(new Color(255,255,255,0));

        setBounds((VuePrincipale.frameWidth ) - 700,30, 600, 50);

        JLabel label = new JLabel();
        label.setSize(new Dimension(600,50));
        label.setBounds(0 ,0, 600, 50);

        label.setText(message);

        if (good){
            this.add(StyleComponent.setStyleLabelSucces(label,24));
        }else {
            this.add(StyleComponent.setStyleLabelErreur(label,24));
        }

        repaint();
    }
}
