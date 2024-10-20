package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.endGame.VueScoreScreen;

import java.util.LinkedList;

public class ModelJeux {
    private VueJeux vueJeux;
    private VueScoreScreen vueScoreScreen;

    private final ModelPrincipale modelPrincipale;
    private final ModelMatrice modelMatrice;
    private final ModelListePoche modelListePoche;
    private final LinkedList<ModelTuile> listTuiles;

    private int seed;
    private int score = 0;

    public ModelJeux(ModelPrincipale modelPrincipale, int seed) {
        this.modelPrincipale = modelPrincipale;
        this.modelListePoche = new ModelListePoche(this);
        this.modelMatrice = new ModelMatrice(this);
        listTuiles = new LinkedList<>();

        this.seed = seed;
        for (int i = 10; i >= 0; i--) {
            ModelTuile tuile = new ModelTuile(seed+i, false);
            listTuiles.add(tuile);
        }

        createView();

        modelMatrice.poseeTuile(50, 50);// Pose de la tuile centrale
        setScore(5);
        createButton();
    }

    private void createView(){
        this.vueJeux = new VueJeux(this);
        modelPrincipale.getVuePrincipale().add(vueJeux, "jeux");
    }

    public void createEndView(){
        this.vueScoreScreen = new VueScoreScreen(modelPrincipale);
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

    public ModelPrincipale getModelPrincipale() {
        return this.modelPrincipale;
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

    public void deleteButtons() {
        for (int row = 0; row < modelMatrice.getListTuilesPosee().length; row++) {
            for (int col = 0; col < modelMatrice.getListTuilesPosee()[row].length; col++) {

                ModelTuile tuile = modelMatrice.getListTuilesPosee()[row][col];

                if (tuile != null && tuile.isButton()) {
                    modelMatrice.deleteButton(row, col);
                }
            }
        }
    }

    public void playTuileSound(int soundIndex) {
        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getModelMediaLoader().getClipsTuiles()[soundIndex], false);
    }

    public VueScoreScreen getVueScoreScreen() {
        return vueScoreScreen;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ModelListePoche getModelListePoche() {
        return modelListePoche;
    }
}
