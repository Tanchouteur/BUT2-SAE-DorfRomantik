package fr.iutfbleau.SAE31_2024_LTA.config;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

public class ConfigManager {

    private final Configuration configuration;
    private final ConfigFileHandler fileHandler;

    public ConfigManager(ModelPrincipale modelPrincipale) {
        this.fileHandler = new ConfigFileHandler();
        this.configuration = fileHandler.loadConfiguration(modelPrincipale);
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

    public boolean isShowTutorialPopup() {
        return configuration.isShowTutorialPopup();
    }

    public void setShowTutorialPopup(boolean show) {
        configuration.setShowTutorialPopup(show);
        fileHandler.saveConfiguration(configuration);
    }
}
