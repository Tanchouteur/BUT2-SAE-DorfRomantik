package fr.iutfbleau.SAE31_2024_LTA.settings;
import fr.iutfbleau.SAE31_2024_LTA.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.menu.ControllerMenuCard;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class VueSettings extends JPanel {
    private final JSlider musicVolumeSlider;
    private final JSlider effectsVolumeSlider;

    public VueSettings(ControllerPopup controllerPopup, VuePrincipale vuePrincipale) {
        setLayout(new BorderLayout());

        musicVolumeSlider = new JSlider(35, 100,  vuePrincipale.getModelPrincipale().getModelMediaLoader().getVolumeMusic());
        effectsVolumeSlider = new JSlider(35, 100, vuePrincipale.getModelPrincipale().getModelMediaLoader().getVolumeEffect());


        JPanel slidersPanel = new JPanel(new GridLayout(2, 2));

        slidersPanel.add(new JLabel("Musique Volume:"));
        slidersPanel.add(musicVolumeSlider);

        slidersPanel.add(new JLabel("Effets Volume:"));
        slidersPanel.add(effectsVolumeSlider);

        musicVolumeSlider.addChangeListener(new ControllerVolumeChange(vuePrincipale,0));
        effectsVolumeSlider.addChangeListener(new ControllerVolumeChange(vuePrincipale,1));
        add(slidersPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(e -> onResume(controllerPopup));

        buttonPanel.add(resumeButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> onQuit(controllerPopup));
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void onResume(ControllerPopup controllerPopup) {
        controllerPopup.closePopup();
    }

    private void onQuit(ControllerPopup controllerPopup) {
        ControllerMenuCard controllerMenuCard = new ControllerMenuCard(controllerPopup.vuePrincipale.getModelPrincipale());
        controllerPopup.closePopup();
        controllerMenuCard.goMenu();
    }

    public void setMusicVolume(int volume) {
        musicVolumeSlider.setValue(volume);
    }

    public void setEffectsVolume(int volume) {
        effectsVolumeSlider.setValue(volume);
    }
}
