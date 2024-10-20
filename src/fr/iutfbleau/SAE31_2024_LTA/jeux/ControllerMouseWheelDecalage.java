package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ControllerMouseWheelDecalage implements MouseWheelListener {
    ModelPrincipale modelPrincipale;
    ControllerMouseWheelDecalage(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int wheelRotation = e.getWheelRotation();
        if (wheelRotation > 0) {

        }else {

        }
    }
}
