package fr.iutfbleau.SAE31_2024_LTA.settings;

import fr.iutfbleau.SAE31_2024_LTA.VuePrincipale;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControllerVolumeChange implements ChangeListener {
    private final VuePrincipale vuePrincipale;
    private final int type;

    public ControllerVolumeChange(VuePrincipale vuePrincipale, int groupToChange) {
        this.vuePrincipale = vuePrincipale;
        this.type = groupToChange; // si grouToChange = 0 c'est les musique, si c'est 1 c'est les effets
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int volume = ((JSlider) e.getSource()).getValue();
        if (type == 0) {
            vuePrincipale.getModelPrincipale().getMediaPlayerManager().setVolumeMusique(volume);
        } else if (type == 1) {
            vuePrincipale.getModelPrincipale().getMediaPlayerManager().setVolumeEffect(volume);
        }
    }
}
