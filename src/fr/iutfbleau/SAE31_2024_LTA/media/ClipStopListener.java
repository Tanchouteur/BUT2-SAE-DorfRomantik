package fr.iutfbleau.SAE31_2024_LTA.media;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.util.List;

public class ClipStopListener implements LineListener {

    private final MediaPlayerManager mediaPlayerManager;
    private final Clip currentClip;
    private final int currentClipIndex;
    private final List<Clip> musicClips;

    public ClipStopListener(MediaPlayerManager mediaPlayerManager, Clip currentClip, int currentClipIndex, List<Clip> musicClips) {
        this.mediaPlayerManager = mediaPlayerManager;
        this.currentClip = currentClip;
        this.currentClipIndex = currentClipIndex;
        this.musicClips = musicClips;
    }

    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP) {
            currentClip.stop();
            int nextClipIndex = (currentClipIndex + 1) % musicClips.size();
            mediaPlayerManager.startClip(musicClips, nextClipIndex);
        }
    }
}
