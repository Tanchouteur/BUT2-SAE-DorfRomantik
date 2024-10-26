package fr.iutfbleau.SAE31_2024_LTA.jeux.controller;

import fr.iutfbleau.SAE31_2024_LTA.jeux.model.ModelJeux;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CentrerAction implements ActionListener {
    private final ModelJeux modelJeux;

    public CentrerAction(ModelJeux modelJeux) {
        this.modelJeux = modelJeux;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        modelJeux.getVueJeux().centrer();
    }
}