package fr.iutfbleau.SAE31_2024_LTA.Model;

public class ModelListePoche {
    private final ModelPoche[] poches;
    private int nbPoches;

    public ModelListePoche() {
        this.poches = new ModelPoche[50];
        this.nbPoches = 0;
    }

    public void addListePoche(ModelPoche p) {
        this.poches[nbPoches++] = p;
        this.nbPoches++;
    }
    private ModelPoche[] getListePoche() {
        return this.poches;

    }
    private int getNbPoches() {
        return this.nbPoches;
    }


}
