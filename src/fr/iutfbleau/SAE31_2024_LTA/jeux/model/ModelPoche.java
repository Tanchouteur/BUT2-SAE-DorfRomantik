package fr.iutfbleau.SAE31_2024_LTA.jeux.model;

import fr.iutfbleau.SAE31_2024_LTA.jeux.vue.ModelTuile;

public class ModelPoche {
    private final int couleur;
    private final ModelTuile[] tuiles;
    private int length;

    public ModelPoche(int couleur) {
        this.couleur = couleur;
        this.tuiles = new ModelTuile[50];
        this.length = 0;

    }

    public ModelTuile[] getTuiles() {
        return tuiles;
    }

    public int getLength() {
        return this.length;
    }

    private void incrementLength() {
        this.length++;
    }

    public void addTuile(ModelTuile m) {
        this.tuiles[this.length] = m;
        incrementLength();
    }

    public int getCouleur() {
        return this.couleur;
    }

}