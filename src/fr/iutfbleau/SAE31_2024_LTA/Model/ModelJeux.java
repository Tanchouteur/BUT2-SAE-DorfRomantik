package fr.iutfbleau.SAE31_2024_LTA.Model;

import java.util.LinkedList;

public class ModelJeux {
    private final LinkedList<ModelTuile> listTuiles;
    
    int seed;
    String playerName;
    public ModelJeux(int seed, String playerName) {
        listTuiles = new LinkedList<>();
        this.seed = seed;
        this.playerName = playerName;

        for (int i = 50; i >= 0; i--) {
            ModelTuile tuile = new ModelTuile(seed+i);
            listTuiles.add(tuile);
        }
    }

    public LinkedList<ModelTuile> getListTuiles() {
        return listTuiles;
    }
    
}
