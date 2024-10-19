package fr.iutfbleau.SAE31_2024_LTA.jeux;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerPoseTuile implements MouseListener {

    ModelJeux modelJeux;
    ModelTuile buttonTuile;

    ControllerPoseTuile(ModelJeux modelJeux, ModelTuile buttonTuile) {
        this.modelJeux = modelJeux;
        this.buttonTuile = buttonTuile;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source instanceof VueTuile) {

            modelJeux.getModelMatrice().poseeTuile(buttonTuile.getX(), buttonTuile.getY());
            modelJeux.createButton();
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
