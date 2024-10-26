package fr.iutfbleau.SAE31_2024_LTA.jeux.controller;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ControllerMouseWheelDecalage implements MouseWheelListener {
    ModelPrincipale modelPrincipale;
    public ControllerMouseWheelDecalage(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {//WheelListener
        int wheelRotation = e.getWheelRotation();
        if (!modelPrincipale.getModelJeux().getListTuiles().isEmpty()) {
            modelPrincipale.getModelJeux().getListTuiles().getFirst().decalage(wheelRotation,modelPrincipale.getModelJeux().getVueJeux());
        }
    }
}
