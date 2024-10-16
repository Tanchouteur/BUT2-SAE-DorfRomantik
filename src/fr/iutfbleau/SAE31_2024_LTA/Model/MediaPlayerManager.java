package fr.iutfbleau.SAE31_2024_LTA.Model;

import fr.iutfbleau.SAE31_2024_LTA.Vue.VuePrincipale;
import javax.sound.sampled.Clip;

public class MediaPlayerManager {

    private final Clip menuMusicClip;
    private final Clip clicAudioClip;

    public MediaPlayerManager(Clip menuMusicClip, Clip clicAudioClip) {
        this.menuMusicClip = menuMusicClip;
        this.clicAudioClip = clicAudioClip;
    }

    public void startClip(Clip clip, boolean loopIndefinitely) {
        if (clip != null) {
            clip.setFramePosition(0);
            if (loopIndefinitely) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        }
    }


    public void stopClip(Clip clip) {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public Clip getMenuMusicClip() {
        return menuMusicClip;
    }

    public Clip getClicAudioClip() {
        return clicAudioClip;
    }
}
