package fr.iutfbleau.SAE31_2024_LTA.jeux;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerPoseTuile implements MouseListener {
    ModelJeux modelJeux;
    ControllerPoseTuile(ModelJeux modelJeux) {
        this.modelJeux = modelJeux;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            modelJeux.getVueJeux().remove(button);
        }
        System.out.println("Bouton hexagonal cliqué !");
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
