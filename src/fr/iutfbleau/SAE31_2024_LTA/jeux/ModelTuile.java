package fr.iutfbleau.SAE31_2024_LTA.jeux;

import java.util.Random;

public class ModelTuile {
    private int[] composition;

    private int Indexcouleur1;
    private int Indexcouleur2;

    private ModelPoche[] inpoche;

    private int seed;

    private int x;
    private int y;

    private final boolean button;
    private boolean preview = false;
    private boolean suivante;
    private boolean onBoard = false;

    private VueTuile vueTuile;

    private int soundIndex;

    public ModelTuile(int seed, boolean preview, boolean suivante, boolean AntiAliasing) {//Tuile de jeux
        composition = new int[6];
        this.inpoche = new ModelPoche[2];
        this.suivante = suivante;
        Random random = new Random();
        this.seed = seed;
        this.preview = preview;
        random.setSeed(seed);

        int[] indexBiome = new int[5];
        indexBiome[0] = 0; //Mer
        indexBiome[1] = 1; //Montagne
        indexBiome[2] = 2; //Champ
        indexBiome[3] = 3; //Plaine
        indexBiome[4] = 4; //Foret

        int indexCouleur1 = indexBiome[random.nextInt(indexBiome.length)];
        int indexCouleur2 = indexBiome[random.nextInt(indexBiome.length)];

        if (indexCouleur1 == indexCouleur2){
            random = new Random();
            random.setSeed(seed+2);
            indexCouleur2 = indexBiome[random.nextInt(indexBiome.length)];
        }

        int territory = random.nextInt(composition.length-1);
        int taille1 = random.nextInt(composition.length+1);
        int decalage = random.nextInt(composition.length);
        int taille2 = 6 - taille1;

        for (int i = 0; i < taille1; i++) {
            composition[decalage] = indexCouleur1;
            decalage = (decalage + 1) % 6;
        }
        for (int i = 0; i < taille2; i++) {
            composition[decalage] = indexCouleur2;
            decalage = (decalage + 1) % 6;
        }
        if (taille1!=6 && taille2!=6){
            this.Indexcouleur1 = indexCouleur1;
            this.Indexcouleur2 = indexCouleur2;
        }
        else {
            if (taille1==6){
                this.Indexcouleur1 = indexCouleur1;
                this.Indexcouleur2 = indexCouleur1;
            }
            else {
                this.Indexcouleur1 = indexCouleur2;
                this.Indexcouleur2 = indexCouleur2;
            }
        }
        if (taille1 > 3)
            soundIndex = indexCouleur1;
        else
            soundIndex = indexCouleur2;

        button = false;
    }

    public ModelTuile() {//Tuile grise qui sert de bouton
        button = true;
    }

    // Méthode pour définir les coordonnées du polygone visuelement
    public void createVueTuile(int centerX, int centerY, int radius, boolean isAA) {

        vueTuile = new VueTuile(this, centerX, centerY, radius, isAA);
    }

    public int[] getComposition() {
        return this.composition;
    }

    // Méthode pour définir les coordonnées du polygone dans la matrice
    public void setCoordonner(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void decalage(int decaler,VueJeux vueJeux){
        //decaler > 0 = molette vers le bas
        if(decaler>0) {
            int tmp = this.composition[0];
            for (int i = 0; i < composition.length - 1; i++) {
                this.composition[i] = this.composition[i + 1];
                if(i == composition.length-2){
                    this.composition[i+1] = tmp;
                }
            }
        }
        //decaler < 0 = molette vers le haut
        else{
            int tmp = this.composition[this.composition.length-1];
            for (int i = composition.length - 1; i > 0 ; i--) {
                this.composition[i] = this.composition[i - 1];
                if(i == 1){
                    this.composition[i-1] = tmp;
                }
            }
        }
        vueJeux.updatePreviewTuileList();
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

    public boolean isPreview() {
        return preview;
    }

    public void setComposition(int[] composition){
        this.composition = composition;
    }

    public int getIndexcouleur1() {
        return Indexcouleur1;
    }
    public int getIndexcouleur2() {
        return Indexcouleur2;
    }

    public void setVueTuile(VueTuile vueTuile) {
        this.vueTuile = vueTuile;
    }

    public boolean isSuivante() {
        return suivante;
    }

    public void setPoche(ModelPoche poche1,ModelPoche poche2) {
        this.inpoche[0] = poche1;
        this.inpoche[1] = poche2;
    }
    public void setPoche1(ModelPoche poche) {
        this.inpoche[0]=poche;

    }
    public void setPoche2(ModelPoche poche) {
        this.inpoche[1]=poche;
    }
    public ModelPoche[] getPoche() {
        return inpoche;
    }



    public boolean isOnBoard() {
        return onBoard;
    }

    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }
}
