package fr.iutfbleau.SAE31_2024_LTA.media;


public class ModelMediaLoader {


    public ModelMediaLoader() {

    }

    /*public void loadMusicTracks(String path) {
        URL musicFolderUrl = getClass().getResource(path);
        if (musicFolderUrl != null) {
            File musicFolder = new File(musicFolderUrl.getFile());

            if (musicFolder.isDirectory()) {
                File[] files = musicFolder.listFiles((_, name) -> name.toLowerCase().endsWith(".mp3"));
                if (files != null) {
                    for (File file : files) {
                        String mediaUrl = file.toURI().toString();
                        Media media = new Media(mediaUrl);
                        musicTracks.add(media);
                    }
                    System.out.println("Musiques chargées : " + musicTracks.size());
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

    public void playMusicGame() {
        if (musicTracks.isEmpty()) {
            System.err.println("Aucune musique à jouer !");
            return;
        }

        if (currentPlayer != null) {
            currentPlayer.stop();
        }

        currentPlayer = new MediaPlayer(musicTracks.getFirst());
        currentPlayer.setOnEndOfMedia(() -> {
            musicTracks.add(musicTracks.removeFirst());
            playMusicGame();
        });
        currentPlayer.play();
    }*/
}