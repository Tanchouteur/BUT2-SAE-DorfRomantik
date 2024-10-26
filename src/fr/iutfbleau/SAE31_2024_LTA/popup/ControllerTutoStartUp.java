package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerTutoStartUp implements ActionListener {

    private final ConfigManager configManager;
    private JCheckBox showAtStartupCheckBox;
    public ControllerTutoStartUp(ConfigManager configManager, JCheckBox showAtStartupCheckBox) {
        this.configManager = configManager;
        this.showAtStartupCheckBox = showAtStartupCheckBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        configManager.setTuto(showAtStartupCheckBox.isSelected());
    }
}
