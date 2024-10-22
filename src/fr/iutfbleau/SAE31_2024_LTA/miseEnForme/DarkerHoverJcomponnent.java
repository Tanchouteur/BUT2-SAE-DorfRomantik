package fr.iutfbleau.SAE31_2024_LTA.miseEnForme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DarkerHoverJcomponnent extends MouseAdapter{
    JComponent jComponnent;
    DarkerHoverJcomponnent(JComponent jComponnent){
        this.jComponnent = jComponnent;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        Color ancienBG = jComponnent.getBackground();
        jComponnent.setBackground(ancienBG.darker());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Color brighterBG =  jComponnent.getBackground();
        jComponnent.setBackground(brighterBG.brighter());
    }
}
