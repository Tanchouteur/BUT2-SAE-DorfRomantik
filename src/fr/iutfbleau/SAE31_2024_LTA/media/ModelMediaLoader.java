package fr.iutfbleau.SAE31_2024_LTA.media;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class ModelMediaLoader {

    private final Clip menuMusicClip;
    private final Clip clicAudioClip;
    private final Clip[] clipsTuiles = new Clip[5];

    private int volumeEffect;
    private int volumeMusic;

    private final List<Clip> gameMusicClips;

    public ModelMediaLoader() {//Charge touts les son du jeux
        volumeEffect = 100;
        volumeMusic = 100;

        menuMusicClip = loadMedia("/Audio/MenuSoundTrack.wav");
        clicAudioClip = loadMedia("/Audio/buttonClic.wav");

        String[] pathTuileSound = new String[5];
        pathTuileSound[0] = "/Audio/TuileSound/eau.wav";
        pathTuileSound[1] = "/Audio/TuileSound/montagne.wav";
        pathTuileSound[2] = "/Audio/TuileSound/champ.wav";
        pathTuileSound[3] = "/Audio/TuileSound/plaine.wav";
        pathTuileSound[4] = "/Audio/TuileSound/foret.wav";

        for (int i = 0; i < pathTuileSound.length; i++) {
            clipsTuiles[i] = loadMedia(pathTuileSound[i]);
        }

        MusiqueTrack musiqueTrack = new MusiqueTrack("/Audio/MusicInGame");
        gameMusicClips = musiqueTrack.getMusicClips();
    }

    /**
     * Charge un fichier audio et retourne un Clip.
     *
     * @param path Le chemin vers le fichier audio.
     * @return Un Clip chargé avec l'audio.
     */
    private Clip loadMedia(String path) {
        Clip clip = null;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int nRead;

        try {
            InputStream audioStream = getClass().getResourceAsStream(path);
            if (audioStream == null) {
                throw new IllegalArgumentException("Le fichier audio " + path + " est introuvable.");
            }

            while ((nRead = audioStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.toByteArray());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de " + path + " : " + e.getMessage());
            System.exit(1);
        }

        return clip;
    }

    public Clip getMenuMusicClip() {
        return menuMusicClip;
    }

    public Clip getClicAudioClip() {
        return clicAudioClip;
    }

    public List<Clip> getGameMusicClips() {
        return gameMusicClips;
    }

    public Clip[] getClipsTuiles() {
        return clipsTuiles;
    }

    public void setVolumeEffect(int volume) {
        volumeEffect = volume;
    }
    public void setVolumeMusic(int volume) {
        volumeMusic = volume;
    }

    public int getVolumeEffect() {
        return volumeEffect;
    }
    public int getVolumeMusic() {
        return volumeMusic;
    }
}
