package fr.iutfbleau.SAE31_2024_LTA.jeux;

public class ModelListePoche {
    private final ModelPoche[] poches;
    private int nbPoches;
    private final ModelJeux modelJeux;
    private int points;

    public ModelListePoche(ModelJeux modelJeux) {
        this.poches = new ModelPoche[100];
        this.nbPoches = 0;
        this.modelJeux = modelJeux;
        this.points = 0;
    }

    public void addListePoche(int couleur) {

        this.poches[nbPoches++] = new ModelPoche(couleur);
        this.nbPoches++;
        addPoints();
    }
    public void addTuilePoche(ModelPoche p) {
        this.points -= p.getLength()*p.getCouleur();


    }
    public ModelPoche[] getListePoche() {
        return this.poches;

    }
    public int getNbPoches() {

        return this.nbPoches;
    }
    public ModelJeux getModelJeux() {
        return this.modelJeux;
    }
    public int getPoints() {
        return this.points;
    }
    protected void addPoints(ModelPoche poche) {


    }
    protected void addPoints() {
        this.points++;
    }




}
