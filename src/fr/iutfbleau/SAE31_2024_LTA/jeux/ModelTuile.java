package fr.iutfbleau.SAE31_2024_LTA.jeux;

import java.util.Random;

public class ModelTuile {
    private final int[] composition;

    private int seed;

    private int x;
    private int y;

    private final boolean button;

    private VueTuile vueTuile;

    private int soundIndex;

    public ModelTuile(int seed) {//Tuile de jeux
        composition = new int[6];
        Random random = new Random();
        this.seed = seed;
        random.setSeed(seed);

        int[] indexBiome = new int[5];
        indexBiome[0] = 0; //Mer
        indexBiome[1] = 1; //Montagne
        indexBiome[2] = 2; //Champ
        indexBiome[3] = 3; //Plaine
        indexBiome[4] = 4; //Foret

        int indexCouleur1 = indexBiome[random.nextInt(indexBiome.length)];
        int indexCouleur2 = indexBiome[random.nextInt(indexBiome.length)];

        int territory = random.nextInt(composition.length+1);
        int decalage = random.nextInt(composition.length);
        int taille2 = 6 - territory;

        for (int i = 0; i < territory; i++) {
            composition[decalage] = indexCouleur1;
            decalage = (decalage + 1) % 6;
        }
        for (int i = 0; i < taille2; i++) {
            composition[decalage] = indexCouleur2;
            decalage = (decalage + 1) % 6;
        }

        if (territory > 3)
            soundIndex = indexCouleur1;
        else
            soundIndex = indexCouleur2;

        button = false;
    }

    public ModelTuile() {//Tuile grise qui sert de bouton
        composition = new int[6];

        for (int i = 0; i < 6; i++) {
            composition[i] = 5;
        }

        button = true;
    }

    // Méthode pour définir les coordonnées du polygone visuelement
    public void createVueTuile(int centerX, int centerY, int radius) {

        vueTuile = new VueTuile(this, centerX, centerY, radius);
    }

    public int[] getComposition() {
        return this.composition;
    }

    // Méthode pour définir les coordonnées du polygone dans la matrice
    public void setCoordonner(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public boolean isButton() {
        return button;
    }

    public VueTuile getVueTuile() {
        return vueTuile;
    }

    public void deleteVueTuile() {
        vueTuile = null;
    }

    public int getSeed() {
        return seed;
    }

    public int getSoundIndex() {
        return soundIndex;
    }
}
