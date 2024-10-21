package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControllerPopup extends AbstractAction {

    private ConfigManager configManager;

    private final JDialog settingsDialog;
    private VueSettingsPopup vueSettings;

    private final JDialog tutoDialog;
    private VueTuto vueTuto;

    private final VuePrincipale vuePrincipale;

    public ControllerPopup(VuePrincipale vuePrincipale) {
        this.vuePrincipale = vuePrincipale;


        settingsDialog = new JDialog(vuePrincipale, "Paramètres", true);
        settingsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        settingsDialog.setSize(700, 500);
        settingsDialog.setLocationRelativeTo(vuePrincipale);



        this.tutoDialog = new JDialog(vuePrincipale, "Tutoriel", true);
        tutoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        tutoDialog.setSize(700, 500);
        tutoDialog.setLocationRelativeTo(vuePrincipale);


    }

    public void closePopup(JDialog popupDialog){
        if (popupDialog != null) {
            if (popupDialog.isVisible()) {
                popupDialog.setVisible(false);
            }
        }
    }


    public Action showSettingsDialog() {
        if (settingsDialog != null && vueSettings == null) {
            this.vueSettings = new VueSettingsPopup(this, vuePrincipale.getModelPrincipale());
            settingsDialog.add(vueSettings);
        }
            if (this.configManager == null) {
                this.configManager = vuePrincipale.getModelPrincipale().getConfigManager();
            }
            if (!settingsDialog.isVisible()) {
                vueSettings.setMusicVolume(configManager.getVolumeMusique());
                vueSettings.setEffectsVolume(configManager.getVolumeEffet());
                settingsDialog.setVisible(true);
            }else {{
                settingsDialog.setVisible(false);
            }}

        return null;
    }

    public void showTutoDialog(ConfigManager configManager) {

        if (tutoDialog != null) {
            if (vueTuto == null) {
                this.vueTuto = new VueTuto(this,configManager);
                tutoDialog.add(vueTuto);
            }
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
