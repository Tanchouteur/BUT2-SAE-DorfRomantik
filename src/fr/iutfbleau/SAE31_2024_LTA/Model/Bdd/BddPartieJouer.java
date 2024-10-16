package fr.iutfbleau.SAE31_2024_LTA.Model.Bdd;

public class BddPartieJouer {
    private final int id;
    private final String playerName;
    private final int score;
    private final BddListeTuiles listeTuile;

    public BddPartieJouer(int id, String playerName, int score, BddListeTuiles listeTuile) {
        this.id = id;
        this.playerName = playerName;
        this.score = score;
        this.listeTuile = listeTuile;
    }

    public int getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public BddListeTuiles getListeTuile() {
        return listeTuile;
    }
}
