package fr.iutfbleau.SAE31_2024_LTA.config;

import fr.iutfbleau.SAE31_2024_LTA.popup.ControllerPopup;

public class ConfigManager {

    private final Configuration configuration;
    private final ConfigFileHandler fileHandler;

    public ConfigManager(ControllerPopup controllerPopup) {
        this.fileHandler = new ConfigFileHandler();
        this.configuration = fileHandler.loadConfiguration();
        if (this.isTuto()){
            controllerPopup.showTutoDialog(this);
        }
    }

    public int getVolumeEffet() {
        return configuration.getVolumeEffet();
    }

    public void setVolumeEffet(int volume) {
        configuration.setVolumeEffet(volume);
        fileHandler.saveConfiguration(configuration);
    }

    public int getVolumeMusique() {
        return configuration.getVolumeMusique();
    }

    public void setVolumeMusique(int volume) {
        configuration.setVolumeMusique(volume);
        fileHandler.saveConfiguration(configuration);
    }

    public String getPlayerName() {
        return configuration.getPlayerName();
    }

    public void setPlayerName(String userName) {
        configuration.setPlayerName(userName);
        fileHandler.saveConfiguration(configuration);
    }

    public boolean isTuto() {
        return configuration.isTuto();
    }

    public void setTuto(boolean show) {
        configuration.setTuto(show);
        fileHandler.saveConfiguration(configuration);
    }
}
