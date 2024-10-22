package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControllerPopup extends AbstractAction {

    private ConfigManager configManager;

    private VueSettingsPopup vueSettings;

    private VueTuto vueTuto;

    private final VuePrincipale vuePrincipale;

    public ControllerPopup(VuePrincipale vuePrincipale) {
        this.vuePrincipale = vuePrincipale;
    }

    public void closeSettings(){
        if (vueSettings != null) {
            vuePrincipale.getPrincipaleLayeredPane().remove(vueSettings);
        }
    }
    public void closeTuto(){
        if (vueTuto != null) {
            vuePrincipale.getPrincipaleLayeredPane().remove(vueTuto);
        }
    }

    public Action showSettingsDialog() {
        if (vueSettings == null) {
            this.vueSettings = new VueSettingsPopup(this, vuePrincipale.getModelPrincipale());
        }
            if (this.configManager == null) {
                this.configManager = vuePrincipale.getModelPrincipale().getConfigManager();
            }
        vueSettings.setMusicVolume(configManager.getVolumeMusique());
        vueSettings.setEffectsVolume(configManager.getVolumeEffet());
        vuePrincipale.getPrincipaleLayeredPane().add(vueSettings, Integer.valueOf(1));
        return null;
    }

    public void showTutoDialog(ConfigManager configManager) {
        if (vueTuto == null) {
            this.vueTuto = new VueTuto(this,configManager);
        }
        vuePrincipale.getPrincipaleLayeredPane().add(vueTuto, Integer.valueOf(2));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        togleSettingsDialog();
    }

    public VuePrincipale getVuePrincipale(){
        return this.vuePrincipale;
    }

    public void togleSettingsDialog() {
        if (vuePrincipale.getPrincipaleLayeredPane().getComponentsInLayer(1).length == 0){
            showSettingsDialog();
        }else {
            closeSettings();
        }
        vuePrincipale.getPrincipaleLayeredPane().repaint();
    }

    public void updatePopup() {
        if (vueSettings != null) {
            vueSettings.updateVueSettings();
        }
        if (vueTuto != null){
            vueTuto.updateVueTuto(vuePrincipale);
        }
    }
}
