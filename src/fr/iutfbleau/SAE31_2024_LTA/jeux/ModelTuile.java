package fr.iutfbleau.SAE31_2024_LTA.jeux;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ModelTuile {
    private final Color[] composition;
    private final int seed;
    private boolean estPosee;
    private int[] xPoints; // Coordonnées X du polygone
    private int[] yPoints; // Coordonnées Y du polygone

    public ModelTuile(int seed) {
        this.seed = seed;
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

        Color couleur1 = colorPalette.get(random.nextInt(colorPalette.size())); // Correction ici
        Color couleur2 = colorPalette.get(random.nextInt(colorPalette.size())); // Correction ici
        int territory = random.nextInt(7);
        int decalage = random.nextInt(6);
        int taille2 = 6 - territory;

        for (int i = 0; i < territory; i++) {
            composition[decalage] = couleur1;
            decalage = (decalage + 1) % 6; // Utilisation de modulo pour éviter les débordements
        }
        for (int i = 0; i < taille2; i++) {
            composition[decalage] = couleur2;
            decalage = (decalage + 1) % 6; // Utilisation de modulo pour éviter les débordements
        }
        this.estPosee = false;
        this.xPoints = new int[6]; // Initialiser le tableau des coordonnées X
        this.yPoints = new int[6]; // Initialiser le tableau des coordonnées Y
    }

    // Méthode pour définir les coordonnées du polygone
    public void setCoordinates(int centerX, int centerY, int radius) {
        for (int i = 0; i < 6; i++) {
            xPoints[i] = (int) (centerX + radius * Math.cos(i * Math.PI / 3));
            yPoints[i] = (int) (centerY + radius * Math.sin(i * Math.PI / 3));
        }
    }

    // Méthode pour obtenir le polygone
    public Polygon getPolygon() {
        Polygon hexagon = new Polygon(xPoints, yPoints, 6);
        return hexagon;
    }

    public Color[] getComposition() {
        return this.composition;
    }


}
