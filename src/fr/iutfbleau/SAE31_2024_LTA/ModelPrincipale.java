package fr.iutfbleau.SAE31_2024_LTA;

import fr.iutfbleau.SAE31_2024_LTA.Bdd.ModelBDD;
import fr.iutfbleau.SAE31_2024_LTA.endGame.ModelEndGame;
import fr.iutfbleau.SAE31_2024_LTA.jeux.ModelJeux;
import fr.iutfbleau.SAE31_2024_LTA.media.MediaPlayerManager;
import fr.iutfbleau.SAE31_2024_LTA.media.ModelMediaLoader;
import fr.iutfbleau.SAE31_2024_LTA.menu.ModelMenu;
import fr.iutfbleau.SAE31_2024_LTA.partieJouer.ModelPartieJouer;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ModelPrincipale {
    private final MediaPlayerManager mediaPlayerManager;
    private final ModelMediaLoader modelMediaLoader;

    private final ModelBDD bdd;

    private final ModelPartieJouer modelPartieJouer;
    private final ModelMenu modelMenu;
    private ModelJeux modelJeux;
    private ModelEndGame modelEndGame;

    private final VuePrincipale vuePrincipale;

    private String playerName;
    private int selectedSeed;
    private int seedIndex;

    public ModelPrincipale() {
        bdd = new ModelBDD();

        mediaPlayerManager = new MediaPlayerManager(this);
        modelMediaLoader = new ModelMediaLoader();

        vuePrincipale = createView();

        modelMenu = new ModelMenu(this);
        modelPartieJouer = new ModelPartieJouer(this);

        vuePrincipale.setVisible(true);

        this.modelEndGame = new ModelEndGame();
    }

    private VuePrincipale createView(){
        return new VuePrincipale(this);
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

    public ModelEndGame getModelEndGame() {
        return modelEndGame;
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
}
