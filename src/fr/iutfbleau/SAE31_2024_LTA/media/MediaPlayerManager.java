package fr.iutfbleau.SAE31_2024_LTA.media;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import java.util.List;

public class MediaPlayerManager {
    ModelPrincipale modelPrincipale;

    public MediaPlayerManager(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
    }

    public void startClip(Clip clip, boolean loopIndefinitely) {
        if (clip != null) {
            if (!clip.isRunning()) {
                clip.setFramePosition(0);
                if (loopIndefinitely) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    clip.start();
                }
            }
        }
    }

    public void startClip(List<Clip> musicClips, int currentClipIndex) {//Démmarre une liste de clip
        if (musicClips.isEmpty()) {
            System.out.println("Aucune musique n'a été chargée.");
            return;
        }

        Clip currentClip = musicClips.get(currentClipIndex);

        if (currentClip != null) {
            currentClip.setFramePosition(0);
            currentClip.start();

            currentClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    currentClip.stop();
                    int nextClipIndex = (currentClipIndex + 1) % musicClips.size();
                    startClip(musicClips, nextClipIndex);
                }
            });
        }
    }

    public void stopClip(Clip clip) {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void stopClip(List<Clip> musicClips) {
        if (!musicClips.isEmpty()) {
            for (Clip clip : musicClips) {
                if (clip.isRunning()) {
                    stopClip(clip);
                }
            }
        }
    }

    /**
     * Change le volume d'un clip audio.
     *
     * @param clip  Le clip dont on veut changer le volume.
     * @param level Le niveau de volume entre 0 (muet) et 1 (volume maximum). Attention sa déscend tres vite
     */
    private void setClipVolume(Clip clip, float level) {
        if (clip != null) {
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                float min = volumeControl.getMinimum();
                float max = volumeControl.getMaximum();
                float newVolume = min + (max - min) * level;

                volumeControl.setValue(newVolume);

            }
        }
    }

    public void setVolumeEffect(int levelP) {
        float level = levelP / 100.0f;
        for (int i = 0; i < modelPrincipale.getModelMediaLoader().getClipsTuiles().length; i++) {
            setClipVolume(modelPrincipale.getModelMediaLoader().getClipsTuiles()[i], level);
        }
        setClipVolume(modelPrincipale.getModelMediaLoader().getClicAudioClip(), level);
        modelPrincipale.getModelMediaLoader().setVolumeEffect(levelP);
    }

    public void setVolumeMusique(int levelP) {
        float level = levelP / 100.0f;
        for (int i = 0; i < modelPrincipale.getModelMediaLoader().getGameMusicClips().size(); i++) {
            setClipVolume(modelPrincipale.getModelMediaLoader().getGameMusicClips().get(i), level);
        }
        setClipVolume(modelPrincipale.getModelMediaLoader().getMenuMusicClip(), level);
        modelPrincipale.getModelMediaLoader().setVolumeMusic(levelP);
    }

    public float getClipVolume(Clip clip) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            return volumeControl.getValue();
        }
        return 0;
    }
}
