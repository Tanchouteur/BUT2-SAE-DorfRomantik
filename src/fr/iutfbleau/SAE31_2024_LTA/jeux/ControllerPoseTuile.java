package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPoseTuile implements ActionListener {
    ModelJeux modelJeux;
    ControllerPoseTuile(ModelJeux modelJeux) {
        this.modelJeux = modelJeux;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            modelJeux.getVueJeux().remove(button);
        }
    }
}
