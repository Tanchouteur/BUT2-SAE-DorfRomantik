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
        if (source instanceof VueTuile btnCliked) {

            if(!modelJeux.getListTuiles().isEmpty()) {
                modelJeux.getModelMatrice().poseeTuile(buttonTuile.getX(), buttonTuile.getY());
                modelJeux.createButton();

                for (int row = 0; row < modelJeux.getModelMatrice().getListTuilesPosee().length; row++) {
                    for (int col = 0; col < modelJeux.getModelMatrice().getListTuilesPosee()[row].length; col++) {

                        ModelTuile tuile = modelJeux.getModelMatrice().getListTuilesPosee()[row][col];

                        if (tuile != null && tuile.getVueTuile() != null && tuile.isButton()) {
                            modelJeux.getVueJeux().remove(tuile.getVueTuile());
                            modelJeux.getVueJeux().remove(btnCliked);
                            tuile.deleteVueTuile();
                        }
                    }
                }

            }else {
                //Crée un controlleur de fin de partit

            }
        }
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
