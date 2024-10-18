package fr.iutfbleau.SAE31_2024_LTA.jeux;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ModelTuile {
    private final Color[] composition;
    private boolean estPosee;
    private final int[] xPoints;
    private final int[] yPoints;

    public ModelTuile(int seed) {
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
        this.estPosee = false;
        this.xPoints = new int[6];
        this.yPoints = new int[6];
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
