package fr.iutfbleau.SAE31_2024_LTA.jeux;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerPoseTuile implements MouseListener {

    private final ModelJeux modelJeux;
    private final ModelTuile buttonTuile;

    private ModelTuile modeleTuilePreviewed;

    private boolean clicked = false;

    ControllerPoseTuile(ModelJeux modelJeux, ModelTuile buttonTuile) {
        this.modelJeux = modelJeux;
        this.buttonTuile = buttonTuile;

    }

    @Override
    public void mouseClicked(MouseEvent e) {    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        Object source = e.getSource();
        if (source instanceof VueTuile btnCliked && clicked) {
            if (btnCliked.getModelTuile().isButton() && e.getButton() == 1) {
                if (!modelJeux.getListTuiles().isEmpty()) {
                    modelJeux.playTuileSound(modelJeux.getListTuiles().getFirst().getSoundIndex());

                    modelJeux.getModelMatrice().poseeTuile(buttonTuile.getX(), buttonTuile.getY());
                    modelJeux.createButton();
                    modelJeux.getVueJeux().updateTuile(btnCliked);
                }
            }
        }
        if (e.getButton() == 3 && modelJeux.isUndoActivate()){ //3 c'est clic gauche je crois
            modelJeux.getModelMatrice().undoLastTuile();
        }
        clicked = false;
        modelJeux.getVueJeux().unsetPreviewOnButton(modeleTuilePreviewed);
        //modelJeux.getVueJeux().repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        clicked = true;

        Object source = e.getSource();
        if (source instanceof VueTuile btnHovered && clicked) {
            modeleTuilePreviewed = modelJeux.getVueJeux().setPreviewOnButton(btnHovered);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        clicked = false;
        modelJeux.getVueJeux().unsetPreviewOnButton(modeleTuilePreviewed);
    }
}
