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

        this.modelMatrice = new ModelMatrice(this);
        listTuiles = new LinkedList<>();

        this.seed = seed;
        for (int i = 50; i >= 0; i--) {
            ModelTuile tuile = new ModelTuile(seed+i);
            listTuiles.add(tuile);
        }

        createView();

        modelMatrice.poseeTuile(50, 50); // Pose de la tuile centrale

        createButton();


    }

    private void createView(){
        this.vueJeux = new VueJeux(this);
        modelPrincipale.getVuePrincipale().add(vueJeux, "jeux");
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

    public void createButton() {
        for (int row = 0; row < modelMatrice.getListTuilesPosee().length; row++) {
            for (int col = 0; col < modelMatrice.getListTuilesPosee()[row].length; col++) {

                ModelTuile tuile = modelMatrice.getListTuilesPosee()[row][col];

                if (tuile != null && !tuile.isButton()) {

                    if (!getModelMatrice().getNordOuest(tuile)) {
                        modelMatrice.poseeButton(row-1, col-1, new ModelTuile());
                    }

                    if (!getModelMatrice().getNord(tuile)) {
                        modelMatrice.poseeButton(row-2,col, new ModelTuile());
                    }

                    if (!getModelMatrice().getNordEst(tuile)) {
                        modelMatrice.poseeButton(row-1,col+1, new ModelTuile());
                    }

                    if (!getModelMatrice().getSudOuest(tuile)) {
                        modelMatrice.poseeButton(row+1,col-1, new ModelTuile());
                    }

                    if (!getModelMatrice().getSud(tuile)) {
                        modelMatrice.poseeButton(row+2,col, new ModelTuile());
                    }

                    if (!getModelMatrice().getSudEst(tuile)) {
                        modelMatrice.poseeButton(row+1,col+1, new ModelTuile());
                    }
                }
            }
        }
    }
}
