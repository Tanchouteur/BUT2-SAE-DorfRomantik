package fr.iutfbleau.SAE31_2024_LTA.jeux;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ModelTuile {
    private final Color[] composition;


    private int x;
    private int y;

    private boolean button;

    private VueTuile vueTuile;

    public ModelTuile(int seed) {//Tuile de jeux
        composition = new Color[6];
        Random random = new Random();
        random.setSeed(seed);

        List<Color> colorPalette = List.of(
                new Color(30, 142, 216),
                new Color(119, 119, 119),
                new Color(235, 222, 33),
                new Color(119, 198, 119),
                new Color(20, 119, 69)
        );

        Color couleur1 = colorPalette.get(random.nextInt(colorPalette.size()));
        Color couleur2 = colorPalette.get(random.nextInt(colorPalette.size()));
        int territory = random.nextInt(7);
        int decalage = random.nextInt(6);
        int taille2 = 6 - territory;

        for (int i = 0; i < territory; i++) {
            composition[decalage] = couleur1;
            decalage = (decalage + 1) % 6;
        }
        for (int i = 0; i < taille2; i++) {
            composition[decalage] = couleur2;
            decalage = (decalage + 1) % 6;
        }

         button = false;
    }

    public ModelTuile() {//Tuile grise qui sert de bouton
        composition = new Color[6];

        Color couleur1 = new Color(26, 32, 46);

        for (int i = 0; i < 6; i++) {
            composition[i] = couleur1;
        }

        button = true;
    }

    // Méthode pour définir les coordonnées du polygone visuelement
    public void createVueTuile(int centerX, int centerY, int radius) {

        vueTuile = new VueTuile(this, centerX, centerY, radius);
    }

    public Color[] getComposition() {
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
}
