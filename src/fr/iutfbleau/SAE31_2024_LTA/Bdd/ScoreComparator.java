package fr.iutfbleau.SAE31_2024_LTA.Bdd;

import java.util.Comparator;

public class ScoreComparator implements Comparator<BddPartieJouer> {
    @Override
    public int compare(BddPartieJouer p1, BddPartieJouer p2) {
        return Integer.compare(p2.getScore(), p1.getScore());
    }
}
