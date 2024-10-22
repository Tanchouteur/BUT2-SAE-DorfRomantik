package fr.iutfbleau.SAE31_2024_LTA.miseEnForme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BrighterHoverJComponent extends MouseAdapter {
    JComponent jComponnent;
    BrighterHoverJComponent(JComponent jComponnent){
        this.jComponnent = jComponnent;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        Color ancienBG = jComponnent.getBackground();
        jComponnent.setBackground(ancienBG.brighter());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Color brighterBG =  jComponnent.getBackground();
        jComponnent.setBackground(brighterBG.darker());
    }
}
