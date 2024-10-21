package fr.iutfbleau.SAE31_2024_LTA.settings;

import fr.iutfbleau.SAE31_2024_LTA.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPopup extends AbstractAction {
    private final VueSettings vueSettings;
    private final JDialog settingsDialog;
    private final ConfigManager configManager;

    public ControllerPopup(VuePrincipale vuePrincipale, ConfigManager configManager) {
        this.configManager = configManager;

        this.vueSettings = new VueSettings(this, vuePrincipale.getModelPrincipale());

        settingsDialog = new JDialog(vuePrincipale, "Paramètres", true);
        settingsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        settingsDialog.setSize((int) (vuePrincipale.getWidth() * 0.8), (int) (vuePrincipale.getHeight() * 0.8));
        settingsDialog.setLocationRelativeTo(vuePrincipale);

        settingsDialog.add(vueSettings);
    }

    public ActionListener closePopup(){
        if (settingsDialog != null) {
            if (settingsDialog.isVisible()) {
                settingsDialog.setVisible(false);
            }
        }
        return null;
    }


    public Action showSettingsDialog() {
        if (settingsDialog != null) {
            if (!settingsDialog.isVisible()) {
                vueSettings.setMusicVolume(configManager.getVolumeMusique());
                vueSettings.setEffectsVolume(configManager.getVolumeEffet());
                settingsDialog.setVisible(true);
            }else {{
                settingsDialog.setVisible(false);
            }}
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showSettingsDialog();
    }
}
