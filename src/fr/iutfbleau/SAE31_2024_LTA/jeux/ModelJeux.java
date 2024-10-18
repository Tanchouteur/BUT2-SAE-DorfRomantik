package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import java.util.LinkedList;

public class ModelJeux {
    private VueJeux vueJeux;
    private final ModelPrincipale modelPrincipale;
    private final ModelMatrice modelMatrice;

    private final LinkedList<ModelTuile> listTuiles;

    int seed;

    public ModelJeux(ModelPrincipale modelPrincipale, int seed) {
        this.modelPrincipale = modelPrincipale;

        this.modelMatrice = new ModelMatrice();
        listTuiles = new LinkedList<>();

        this.seed = seed;
        for (int i = 50; i >= 0; i--) {
            ModelTuile tuile = new ModelTuile(seed+i);
            listTuiles.add(tuile);
        }

        listTuiles.getFirst().setCoordinates(modelPrincipale.getVuePrincipale().getWidth()/2, modelPrincipale.getVuePrincipale().getHeight()/2,50);//on pose la premiere tuile au milieux de l'écrant
        modelMatrice.poseeTuile(50,50,listTuiles.getFirst());//Pose de la premiere tuile
        listTuiles.removeFirst();

        createView();
    }

    private void createView(){
        this.vueJeux = new VueJeux(modelPrincipale, this);
        modelPrincipale.getVuePrincipale().add(vueJeux, "jeux");
        modelPrincipale.getVuePrincipale().repaint();
    }

    public LinkedList<ModelTuile> getListTuiles() {
        return listTuiles;
    }

    public VueJeux getVueJeux() {
        return this.vueJeux;
    }

    public ModelMatrice getModelMatrice() {
        return this.modelMatrice;
    }
}
