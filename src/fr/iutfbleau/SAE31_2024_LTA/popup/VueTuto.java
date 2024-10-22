package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;
import fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent;

import javax.swing.*;
import java.awt.*;

import static fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent.setStyleCheckBox;

public class VueTuto extends JPanel {
    private final JCheckBox showAtStartupCheckBox;

    VueTuto(ControllerPopup controllerPopup, ConfigManager configManager) {
        setLayout(null);
        setBackground(StyleComponent.getPopupColor());
        setSize(new Dimension(controllerPopup.getTutoDialog().getWidth(), controllerPopup.getTutoDialog().getHeight()));

        showAtStartupCheckBox = new JCheckBox("Montrer au démarrage", configManager.isTuto());
        setStyleCheckBox(showAtStartupCheckBox);
        showAtStartupCheckBox.setBounds(20, this.getHeight()-100, 220, 30);
        showAtStartupCheckBox.addActionListener(e -> onShowAtStartupChange(configManager));
        add(showAtStartupCheckBox);

        JButton resumeButton = new JButton("Resume");
        resumeButton = StyleComponent.setStyleButton(resumeButton,18);
        resumeButton.setBounds(this.getWidth()-220, this.getHeight()-110, 180, 50);
        resumeButton.addActionListener(e -> onResume(controllerPopup));
        add(resumeButton);
    }

    private void onResume(ControllerPopup controllerPopup) {
        controllerPopup.closePopup(controllerPopup.getTutoDialog());
    }
    private void onShowAtStartupChange(ConfigManager configManager) {
        configManager.setTuto(showAtStartupCheckBox.isSelected());
    }
}
