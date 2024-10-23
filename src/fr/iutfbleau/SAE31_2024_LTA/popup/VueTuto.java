package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;
import fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent;

import javax.swing.*;

import java.awt.*;
import java.util.Objects;

import static fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent.setStyleCheckBox;
import static fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent.setStyleLabelScore;

public class VueTuto extends JPanel {
    private final JCheckBox showAtStartupCheckBox;

    VueTuto(ControllerPopup controllerPopup, ConfigManager configManager) {
        setLayout(null);
        this.setBackground(StyleComponent.getPopupColor());
        setSize(700,430);

        JLabel tutoLabel = new JLabel("Tutoriel");
        tutoLabel.setBounds((getWidth()-190)/2,20,190,50);
        add(setStyleLabelScore(tutoLabel,24));

        ImageIcon tutoIcon = new ImageIcon(Objects.requireNonNull(StyleComponent.class.getResource("/Images/tuto.png")));
        Image image = tutoIcon.getImage();
        Image resizedImage = image.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        logoLabel.setBounds((getWidth()-190)/2,40,200,150);
        this.add(logoLabel);

        showAtStartupCheckBox = new JCheckBox("Montrer au démarrage", configManager.isTuto());
        setStyleCheckBox(showAtStartupCheckBox);
        showAtStartupCheckBox.setBounds(20, this.getHeight()-100, 220, 50);
        showAtStartupCheckBox.addActionListener(e -> onShowAtStartupChange(configManager));
        add(showAtStartupCheckBox);

        updateVueTuto(controllerPopup.getVuePrincipale());

        JButton resumeButton = new JButton("Resume");
        resumeButton = StyleComponent.setStyleButton(resumeButton,18);
        resumeButton.setBounds(this.getWidth()-220, this.getHeight()-110, 180, 50);
        resumeButton.addActionListener(e -> onResume(controllerPopup));
        add(resumeButton);

    }

    private void onResume(ControllerPopup controllerPopup) {
        controllerPopup.closeTuto();
    }

    private void onShowAtStartupChange(ConfigManager configManager) {
        configManager.setTuto(showAtStartupCheckBox.isSelected());
    }

    public void updateVueTuto(VuePrincipale vuePrincipale) {
        this.setBounds((vuePrincipale.getWidth()-this.getWidth())/2,-this.getHeight(),getWidth(),getHeight());
    }
}
