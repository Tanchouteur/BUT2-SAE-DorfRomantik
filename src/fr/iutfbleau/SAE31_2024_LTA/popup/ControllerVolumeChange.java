package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControllerVolumeChange implements ChangeListener {
    private final int type;
    private final ConfigManager configManager;
    private final ModelPrincipale modelPrincipale;
    public ControllerVolumeChange(ConfigManager configManager, int groupToChange, ModelPrincipale modelPrincipal) {
        this.type = groupToChange; // si grouToChange = 0 c'est les musique, si c'est 1 c'est les effets
        this.configManager = configManager;
        this.modelPrincipale = modelPrincipal;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int volume = ((JSlider) e.getSource()).getValue();
        if (type == 0) {
            configManager.setVolumeMusique(volume);
            modelPrincipale.getMediaPlayerManager().setVolumeMusique(configManager.getVolumeMusique());
            modelPrincipale.getMediaPlayerManager().setVolumeEffect(configManager.getVolumeEffet());
        } else if (type == 1) {
            configManager.setVolumeEffet(volume);
        }
    }
}
