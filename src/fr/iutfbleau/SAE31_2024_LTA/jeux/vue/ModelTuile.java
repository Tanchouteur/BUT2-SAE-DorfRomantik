package fr.iutfbleau.SAE31_2024_LTA.jeux.vue;

public class ModelTuile {
    private int[] composition;

    private int seed;

    private int x;
    private int y;

    private final boolean button;
    private boolean preview = false;
    private boolean suivante;
    private boolean onBoard = false;

    private VueTuile vueTuile;

    private TuileRandomFactory tuileRandomFactory;

    private int soundIndex;

    public ModelTuile(int seed, boolean preview, boolean suivante, boolean AntiAliasing) {//Tuile de jeux
        this.composition = new int[6];
        this.suivante = suivante;

        this.tuileRandomFactory = new TuileRandomFactory(seed);
        this.seed = seed;
        this.preview = preview;

        composition = tuileRandomFactory.getComposition();

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

    public void decalage(int decaler, VueJeux vueJeux){
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
        return tuileRandomFactory.getSoundIndex();
    }

    public boolean isPreview() {
        return preview;
    }

    public void setComposition(int[] composition){
        this.composition = composition;
    }

    public int getIndexcouleur1() {
        return tuileRandomFactory.getIndexcouleur1();
    }
    public int getIndexcouleur2() {
        return tuileRandomFactory.getIndexcouleur2();
    }

    public void setVueTuile(VueTuile vueTuile) {
        this.vueTuile = vueTuile;
    }

    public boolean isSuivante() {
        return suivante;
    }

    public boolean isOnBoard() {
        return onBoard;
    }

    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }
}
