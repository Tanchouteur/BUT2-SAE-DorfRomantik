package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AntiAliasingChange implements ActionListener {
    ConfigManager configManager;
    JCheckBox AACheckBox;
    public AntiAliasingChange(ConfigManager configManager, JCheckBox AACheckBox) {
        this.configManager = configManager;
        this.AACheckBox = AACheckBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        configManager.setAA(AACheckBox.isSelected());
    }
}
