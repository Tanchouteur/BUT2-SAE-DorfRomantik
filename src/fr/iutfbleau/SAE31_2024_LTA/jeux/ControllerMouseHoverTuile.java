package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.VuePrincipale;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerMouseHoverTuile implements MouseListener {
    ModelTuile tuile;
    VuePrincipale vuePrincipale;
    ControllerMouseHoverTuile(ModelTuile tuile, VuePrincipale vuePrincipale) {
        this.tuile = tuile;
        this.vuePrincipale = vuePrincipale;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
