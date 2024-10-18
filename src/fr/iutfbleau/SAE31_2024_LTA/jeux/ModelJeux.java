package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import java.util.LinkedList;

public class ModelJeux {
    private VueJeux vueJeux;
    private final ModelPrincipale modelPrincipale;
    //private final ModelMatrice modelMatrice;
    //private final ModelTuile[][] listTuilesPosee;

    private final LinkedList<ModelTuile> listTuiles;

    int seed;

    public ModelJeux(ModelPrincipale modelPrincipale, int seed) {
        this.modelPrincipale = modelPrincipale;
        //this.modelMatrice = new ModelMatrice();
        listTuiles = new LinkedList<>();
        this.seed = seed;
        //this.listTuilesPosee = modelMatrice.getListTuilesPosee();

        for (int i = 50; i >= 0; i--) {
            ModelTuile tuile = new ModelTuile(seed+i);
            listTuiles.add(tuile);
        }

        createView();
    }

    private void createView(){
        this.vueJeux = new VueJeux(modelPrincipale);
        modelPrincipale.getVuePrincipale().add(vueJeux, "jeux");
        modelPrincipale.getVuePrincipale().repaint();
    }

    public LinkedList<ModelTuile> getListTuiles() {
        return listTuiles;
    }

    public LinkedList<ModelTuile> getListTuilesPosee() {
        return listTuilesPosee;
    }

    public VueJeux getVueJeux() {
        return this.vueJeux;
    }
}
