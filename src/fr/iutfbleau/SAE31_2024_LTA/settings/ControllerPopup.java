package fr.iutfbleau.SAE31_2024_LTA.settings;

import fr.iutfbleau.SAE31_2024_LTA.VuePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPopup extends AbstractAction {
    VueSettings vueSettings;
    JDialog settingsDialog;
    VuePrincipale vuePrincipale;
    public ControllerPopup(VuePrincipale vuePrincipale){
        this.vuePrincipale = vuePrincipale;
        vueSettings = new VueSettings(this, vuePrincipale);

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
                vueSettings.setMusicVolume(vuePrincipale.getModelPrincipale().getModelMediaLoader().getVolumeMusic());
                vueSettings.setEffectsVolume(vuePrincipale.getModelPrincipale().getModelMediaLoader().getVolumeEffect());
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
