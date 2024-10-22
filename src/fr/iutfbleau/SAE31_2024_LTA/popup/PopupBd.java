package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent;

import javax.swing.*;
import java.awt.*;

public class PopupBd extends JPanel {

    public PopupBd(Boolean connected){
        setLayout(null);
        setOpaque(false);
        setBackground(new Color(255,255,255,0));
        setBounds(0,0, VuePrincipale.frameWidth, VuePrincipale.frameHeight);
        JLabel label = new JLabel();
        label.setBounds((VuePrincipale.frameWidth -600)/2, 30, 600, 50);

        if (connected){
            label.setText("Connection à la base de donnée Réussi");
            this.add(StyleComponent.setStyleLabelSucces(label,26));
        }else {
            label.setText("Connection à la base de donnée Echouer");
            this.add(StyleComponent.setStyleLabelErreur(label,26));
        }
    }
    public PopupBd(){
        setLayout(null);
        setOpaque(false);
        setBackground(new Color(255,255,255,0));
        setBounds(0,0, VuePrincipale.frameWidth, VuePrincipale.frameHeight);
        JLabel label = new JLabel();
        label.setBounds((VuePrincipale.frameWidth -600)/2, 30, 600, 50);

        label.setText("Connection à la base de donnée en cours...");
        this.add(StyleComponent.setStyleLabelScore(label,26));
    }
}
