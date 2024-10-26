package fr.iutfbleau.SAE31_2024_LTA.Bdd;

import java.util.function.Predicate;

public class PlayerNameFilter implements Predicate<BddPartieJouer> {
    private final String playerName;

    public PlayerNameFilter(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public boolean test(BddPartieJouer partie) {
        return partie.getPlayerName().equalsIgnoreCase(playerName);
    }
}