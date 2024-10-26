package fr.iutfbleau.SAE31_2024_LTA.jeux.model;

import fr.iutfbleau.SAE31_2024_LTA.jeux.vue.ModelTuile;

public class ModelListePoche {
    private final ModelPoche[] poches;
    private int nbPoches;
    private final ModelJeux modelJeux;
    private int Score;

    public ModelListePoche(ModelJeux modelJeux) {
        this.poches = new ModelPoche[100];
        this.nbPoches = 0;
        this.modelJeux = modelJeux;
        this.Score = 0;
    }

    public void addListePoche(int couleur) {
        this.poches[nbPoches] = new ModelPoche(couleur);
        this.nbPoches++;
        addScore();
    }
    public void addTuilePoche(ModelPoche p, ModelTuile t) {
        this.Score -= p.getLength()*p.getLength();
        p.addTuile(t);
        this.Score += p.getLength()*p.getLength();

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
    public int getScore() {
        return this.Score;
    }

    protected void addScore() {
        this.Score++;
    }




}
