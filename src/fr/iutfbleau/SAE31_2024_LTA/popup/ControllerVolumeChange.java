package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControllerVolumeChange implements ChangeListener {
    private final int type;
    private final ConfigManager configManager;

    public ControllerVolumeChange(ConfigManager configManager, int groupToChange) {
        this.type = groupToChange; // si grouToChange = 0 c'est les musique, si c'est 1 c'est les effets
        this.configManager = configManager;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int volume = ((JSlider) e.getSource()).getValue();
        if (type == 0) {
            configManager.setVolumeMusique(volume);
        } else if (type == 1) {
            configManager.setVolumeEffet(volume);
        }
    }
}
