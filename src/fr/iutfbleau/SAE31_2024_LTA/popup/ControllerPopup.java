package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.animator.Animator;
import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;

import javax.swing.*;

public class ControllerPopup  {

    private ConfigManager configManager;

    private VueSettingsPopup vueSettings;

    private VueTuto vueTuto;

    private final VuePrincipale vuePrincipale;

    public ControllerPopup(VuePrincipale vuePrincipale) {
        this.vuePrincipale = vuePrincipale;

    }

    public void createSettings(){
        if (vueSettings == null) {
            this.vueSettings = new VueSettingsPopup(this, vuePrincipale.getModelPrincipale());
            vuePrincipale.getPrincipaleLayeredPane().add(vueSettings, Integer.valueOf(1));
            if (this.configManager == null) {
                this.configManager = vuePrincipale.getModelPrincipale().getConfigManager();
                vuePrincipale.getModelPrincipale().getMediaPlayerManager().setVolumeMusique(configManager.getVolumeMusique());
                vuePrincipale.getModelPrincipale().getMediaPlayerManager().setVolumeEffect(configManager.getVolumeEffet());
            }
            vueSettings.setMusicVolume(configManager.getVolumeMusique());
            vueSettings.setEffectsVolume(configManager.getVolumeEffet());
        }
    }

    public void closeSettings(){
        if (vueSettings != null) {
            Animator.moveTo(vueSettings , (vuePrincipale.getWidth()-vueSettings.getWidth())/2, vueSettings.getY()  ,(vuePrincipale.getWidth()-vueSettings.getWidth())/2,-vueSettings.getHeight(), 800,true);
        }
        vueSettings.setOpen(false);
    }

    public void closeTuto(){
        if (vueTuto != null) {
            Animator.moveTo(vueTuto , (vuePrincipale.getWidth()-vueTuto.getWidth())/2,vueTuto.getY() ,(vuePrincipale.getWidth()-vueTuto.getWidth())/2,-vueTuto.getHeight(), 600,true);
        }
    }

    public Action showSettingsDialog() {
            Animator.moveTo(vueSettings , (vuePrincipale.getWidth()-vueSettings.getWidth())/2, vueSettings.getY() ,(vuePrincipale.getWidth()-vueSettings.getWidth())/2,(vuePrincipale.getHeight()-vueSettings.getHeight())/2, 800,true);

        return null;
    }

    public void showTutoDialog() {
        if (vueTuto == null) {
            this.vueTuto = new VueTuto(this,this.configManager);
            vuePrincipale.getPrincipaleLayeredPane().add(vueTuto, Integer.valueOf(2));
        }
        Animator.moveTo(vueTuto , (vuePrincipale.getWidth()-vueTuto.getWidth())/2,vueTuto.getY() ,(vuePrincipale.getWidth()-vueTuto.getWidth())/2,(vuePrincipale.getHeight()-vueTuto.getHeight())/2, 600,true);
    }

    public VuePrincipale getVuePrincipale(){
        return this.vuePrincipale;
    }

    public void togleSettingsDialog() {
        if (!vueSettings.isOpen()){
            showSettingsDialog();
            vueSettings.setOpen(true);
        }else {
            closeSettings();
            vueSettings.setOpen(false);
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
