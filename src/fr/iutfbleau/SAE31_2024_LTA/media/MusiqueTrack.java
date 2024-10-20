package fr.iutfbleau.SAE31_2024_LTA.media;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MusiqueTrack {

    private final List<Clip> musicClips;

    MusiqueTrack(String path) { //Charge le dossier qui contient les musiques quand on est en jeux
        musicClips = new ArrayList<>();

        URL musicFolderUrl = getClass().getResource(path);
        if (musicFolderUrl != null) {
            File musicFolder = new File(musicFolderUrl.getFile());

            if (musicFolder.isDirectory()) {
                File[] files = musicFolder.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.toLowerCase().endsWith(".wav");
                    }
                });

                if (files != null) {
                    for (File file : files) {
                        try {
                            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioStream);
                            musicClips.add(clip);
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                            System.err.println("Erreur lors du chargement du fichier : " + file.getName());
                        }
                    }
                    System.out.println("Musiques chargées : " + musicClips.size());
                } else {
                    System.out.println("Le dossier est vide ou une erreur s'est produite.");
                }
            } else {
                System.out.println("L'URL ne correspond pas à un dossier.");
            }
        } else {
            System.err.println("Dossier non trouvé : " + path);
        }

    }

    public List<Clip> getMusicClips() {
        return musicClips;
    }
}
