package fr.iutfbleau.SAE31_2024_LTA;

import fr.iutfbleau.SAE31_2024_LTA.Bdd.ModelBDD;
import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;
import fr.iutfbleau.SAE31_2024_LTA.jeux.ModelJeux;
import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.media.MediaPlayerManager;
import fr.iutfbleau.SAE31_2024_LTA.media.ModelMediaLoader;
import fr.iutfbleau.SAE31_2024_LTA.menu.ModelMenu;
import fr.iutfbleau.SAE31_2024_LTA.partieJouer.ModelPartieJouer;
import fr.iutfbleau.SAE31_2024_LTA.popup.ControllerPopup;

public class ModelPrincipale {
    private final ConfigManager configManager;
    private final MediaPlayerManager mediaPlayerManager;
    private final ModelMediaLoader modelMediaLoader;

    private final ModelBDD bdd;

    private final ModelPartieJouer modelPartieJouer;
    private final ModelMenu modelMenu;
    private ModelJeux modelJeux;

    private final VuePrincipale vuePrincipale;

    private String playerName;
    private int selectedSeed;
    private int seedIndex;

    private final ControllerPopup controllerPopup;

    public ModelPrincipale() {

        bdd = new ModelBDD();

        vuePrincipale = createView();
        controllerPopup = new ControllerPopup(vuePrincipale);
        configManager = new ConfigManager(controllerPopup);
        mediaPlayerManager = new MediaPlayerManager(this);
        modelMediaLoader = new ModelMediaLoader();
        modelMenu = new ModelMenu(this);
        modelPartieJouer = new ModelPartieJouer(this);

        vuePrincipale.setVisible(true);
    }

    private VuePrincipale createView(){
        return new VuePrincipale(this);
    }

    private void createWaitScreen(){

    }

    public VuePrincipale getVuePrincipale() {
        return vuePrincipale;
    }

    public ModelBDD getBdd() {
        return this.bdd;
    }

    public ModelMenu getModelMenu() {
        return modelMenu;
    }

    public ModelJeux getModelJeux() {
        return modelJeux;
    }

    public void createJeux(){
        this.modelJeux = new ModelJeux(this, selectedSeed);
    }

    public int getSelectedSeed() {
        return selectedSeed;
    }

    public void setSelectedSeed(int seed) {
        selectedSeed = seed;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ModelPartieJouer getModelPartieJouer() {
        return modelPartieJouer;
    }

    public MediaPlayerManager getMediaPlayerManager() {
        return mediaPlayerManager;
    }

    public ModelMediaLoader getModelMediaLoader() {
        return modelMediaLoader;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setSeedIndex(int index) {
        this.seedIndex = index;
    }

    public int getSeedIndex() {
        return this.seedIndex;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ControllerPopup getControllerPopup() {
        return controllerPopup;
    }
}
