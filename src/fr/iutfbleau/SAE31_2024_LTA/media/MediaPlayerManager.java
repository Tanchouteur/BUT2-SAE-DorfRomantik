package fr.iutfbleau.SAE31_2024_LTA.media;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.util.List;

public class MediaPlayerManager {

    public MediaPlayerManager() {
    }

    public void startClip(Clip clip, boolean loopIndefinitely) {//démarre un clip
        if (clip != null) {
            clip.setFramePosition(0);
            if (loopIndefinitely) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
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
                    currentClip.close();
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


}
