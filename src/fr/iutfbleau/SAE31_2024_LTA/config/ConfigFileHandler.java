package fr.iutfbleau.SAE31_2024_LTA.config;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import java.io.*;
import java.util.Properties;

public class ConfigFileHandler {

    private static final String CONFIG_FILE_PATH = "configDorfJavatik.properties";

    public void saveConfiguration(Configuration configuration) {
        Properties properties = new Properties();

        properties.setProperty("volumeEffet", Integer.toString(configuration.getVolumeEffet()));
        properties.setProperty("volumeMusique", Integer.toString(configuration.getVolumeMusique()));
        properties.setProperty("playerName", configuration.getPlayerName());
        properties.setProperty("showTutoPopup", Boolean.toString(configuration.isShowTutorialPopup()));

        try (FileOutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
            properties.store(output, "Configuration Settings");
            System.out.println("Configuration enregistrée avec succès.");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement de la configuration : " + e.getMessage());
        }
    }

    public Configuration loadConfiguration(ModelPrincipale modelPrincipale) {
        Properties properties = new Properties();
        Configuration config = new Configuration();

        try (FileInputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);

            // Lecture des parametre
            config.setVolumeEffet(Integer.parseInt(properties.getProperty("volumeEffet", "100")));
            config.setVolumeMusique(Integer.parseInt(properties.getProperty("volumeMusique", "100")));
            config.setPlayerName(properties.getProperty("playerName", "Player Name..."));
            config.setShowTutorialPopup(Boolean.parseBoolean(properties.getProperty("showTutorialPopup")));

            if (config.isShowTutorialPopup()){
                modelPrincipale.getControllerPopup().showTutoDialog();
            }

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la configuration : " + e.getMessage());
        }

        return config;
    }
}
