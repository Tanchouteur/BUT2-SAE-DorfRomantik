package fr.iutfbleau.SAE31_2024_LTA.Model;

import fr.iutfbleau.SAE31_2024_LTA.Vue.VueTuile;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ModelTuile {
    private final Color[] composition;
    private final int seed;
    private final VueTuile vueTuile;
    private boolean estPosee;
    private int x;
    private int y;

    public ModelTuile(int seed) {
        this.seed = seed;
        Color couleur1;
        Color couleur2;
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

        couleur1 = colorPalette.get(random.nextInt(colorPalette.size()));
        couleur2 = colorPalette.get(random.nextInt(colorPalette.size()));
        int territory = random.nextInt(7);
        int decalage = random.nextInt(6);
        int taille2 = 6 - territory;

        for (int i = 0; i < territory; i++) {
            if (decalage > 5) {
                decalage = 0;
            }
            composition[decalage] = couleur1;
            decalage++;
        }
        for (int i = 0; i < taille2; i++) {
            if (decalage > 5) {
                decalage = 0;
            }
            composition[decalage] = couleur2;
            decalage++;
        }
        vueTuile = new VueTuile(composition);
        this.estPosee = false;
    }

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

    public int getSeed() {
        return this.seed;
    }

    public Color[] getComposition() {
        return this.composition;
    }

    public VueTuile getVueTuile() {
        return this.vueTuile;
    }

    public boolean getEstPosee() {
        return this.estPosee;
    }

    public void setEstPosee(boolean estPosee) {
        this.estPosee = estPosee;
    }
}
