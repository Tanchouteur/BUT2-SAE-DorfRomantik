package fr.iutfbleau.SAE31_2024_LTA.config;

public class Configuration {

    private int volumeEffet;
    private int volumeMusique;
    private String playerName;
    private boolean showTutorialPopup;

    public Configuration() {
        //cest les valeurs par défault
        this.volumeEffet = 95;
        this.volumeMusique = 95;
        this.playerName = "player";
        this.showTutorialPopup = true;
    }

    public int getVolumeEffet() {
        return volumeEffet;
    }

    public void setVolumeEffet(int volumeGroup1) {
        this.volumeEffet = volumeGroup1;
    }

    public int getVolumeMusique() {
        return volumeMusique;
    }

    public void setVolumeMusique(int volumeGroup2) {
        this.volumeMusique = volumeGroup2;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isShowTutorialPopup() {
        return showTutorialPopup;
    }

    public void setShowTutorialPopup(boolean showTutorialPopup) {
        this.showTutorialPopup = showTutorialPopup;
    }
}
