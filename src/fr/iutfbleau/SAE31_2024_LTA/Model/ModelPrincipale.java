package fr.iutfbleau.SAE31_2024_LTA.Model;

import fr.iutfbleau.SAE31_2024_LTA.Model.Bdd.ModelBDD;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ModelPrincipale {
    private final ModelMediaLoader mediaLoader;
    private final MediaPlayerManager mediaPlayerManager;

    private Clip menuMusicClip;
    private Clip clicAudioClip;

    private final ModelBDD bdd;
    private String playerName;
    private int selectedSeed;

    public ModelPrincipale() {
        bdd = new ModelBDD();
        mediaLoader = new ModelMediaLoader();
        loadMedia();
        mediaPlayerManager = new MediaPlayerManager(menuMusicClip, clicAudioClip);
    }

    private void loadMedia() {
        try {
            InputStream menuMusicStream = getClass().getResourceAsStream("/Audio/MenuSoundTrack.wav");
            if (menuMusicStream == null) {
                throw new IllegalArgumentException("Le fichier audio MenuSoundTrack.wav est introuvable.");
            }

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int nRead;
            while ((nRead = menuMusicStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.toByteArray());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(byteArrayInputStream);
            menuMusicClip = AudioSystem.getClip();
            menuMusicClip.open(audioStream);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de la musique du menu : " + e.getMessage());
            System.exit(1);
        }
        try {
            InputStream clicAudioStream = getClass().getResourceAsStream("/Audio/buttonClic.wav");
            if (clicAudioStream == null) {
                throw new IllegalArgumentException("Le fichier audio buttonClic.wav est introuvable.");
            }

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int nRead;
            while ((nRead = clicAudioStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.toByteArray());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(byteArrayInputStream);
            clicAudioClip = AudioSystem.getClip();
            clicAudioClip.open(audioStream);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement du son de clic : " + e.getMessage());
            System.exit(1);
        }
    }

    public MediaPlayerManager getMediaPlayerManager() {
        return mediaPlayerManager;
    }

    public ModelMediaLoader getMediaLoader() {
        return mediaLoader;
    }

    public ModelBDD getBdd() {
        return this.bdd;
    }

    public int getSelectedSeed() {
        return selectedSeed;
    }

    public void setSelectedSeed(int seed) {
        selectedSeed = seed;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
