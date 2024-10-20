package fr.iutfbleau.SAE31_2024_LTA.Bdd;

public class BddListeTuiles {
    private final int id;
    private final int seed;
    private final Integer bestScore;

    public BddListeTuiles(int id, int seed, Integer bestScore) {
        this.id = id;
        this.seed = seed;
        this.bestScore = bestScore;
    }


    public int getId() {
        return id;
    }

    public int getSeed() {
        return seed;
    }

    public Integer getBestScore() {
        return bestScore;
    }


}
