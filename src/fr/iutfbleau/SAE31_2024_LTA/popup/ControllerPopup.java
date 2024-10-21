package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControllerPopup extends AbstractAction {

    private final ConfigManager configManager;

    private final JDialog settingsDialog;
    private final VueSettingsPopup vueSettings;

    private final JDialog tutoDialog;
    private final VueTuto vueTuto;

    public ControllerPopup(VuePrincipale vuePrincipale, ConfigManager configManager) {
        this.configManager = configManager;

        this.vueSettings = new VueSettingsPopup(this, vuePrincipale.getModelPrincipale());

        settingsDialog = new JDialog(vuePrincipale, "Paramètres", true);
        settingsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        settingsDialog.setSize(700, 500);
        settingsDialog.setLocationRelativeTo(vuePrincipale);
        settingsDialog.add(vueSettings);

        this.vueTuto = new VueTuto(this, vuePrincipale.getModelPrincipale());
        this.tutoDialog = new JDialog(vuePrincipale, "Tutoriel", true);
        tutoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        tutoDialog.setSize(700, 500);
        tutoDialog.setLocationRelativeTo(vuePrincipale);
        tutoDialog.add(vueTuto);

    }

    public void closePopup(JDialog popupDialog){
        if (popupDialog != null) {
            if (popupDialog.isVisible()) {
                popupDialog.setVisible(false);
            }
        }
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

    public void showTutoDialog() {
        if (tutoDialog != null) {
            tutoDialog.setVisible(true);
            vueTuto.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showSettingsDialog();
    }

    public JDialog getSettingsDialog() {
        return settingsDialog;
    }
    public JDialog getTutoDialog() {
        return settingsDialog;
    }
}
