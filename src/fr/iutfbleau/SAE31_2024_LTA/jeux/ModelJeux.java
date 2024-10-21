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
    private boolean undoActivate = false;
    private boolean undo = false;
    private ModelTuile tuileUndoAble;

    private int score = 0;

    public ModelJeux(ModelPrincipale modelPrincipale, int seed) {
        this.modelPrincipale = modelPrincipale;
        this.modelListePoche = new ModelListePoche(this);
        this.modelMatrice = new ModelMatrice(this);
        listTuiles = new LinkedList<>();

        for (int i = 10; i >= 0; i--) {
            ModelTuile tuile = new ModelTuile(seed*i, false);
            listTuiles.add(tuile);
        }

        createView();

        createFirstTuile();

        setScore(0);
        vueJeux.updatePreviewTuileList();
        createButton();
    }

    private void createFirstTuile() {

        int centerX = vueJeux.getX() / 2;
        int centerY = vueJeux.getHeight() / 2;

        int tuileCentreRow = 50;
        int tuileCentreCol = 50;

        int initialOffsetX = centerX - (3 * 50 / 2) * tuileCentreCol;
        int initialOffsetY = centerY - 50-7 * tuileCentreRow;

        int totalOffsetX = initialOffsetX;
        int totalOffsetY = initialOffsetY;

        int x = totalOffsetX + 50 * (3 * 50 / 2);
        int y = totalOffsetY + 50 * 43;

        listTuiles.getFirst().createVueTuile(x,y,50);
        vueJeux.add(listTuiles.getFirst().getVueTuile());
        modelMatrice.poseeTuile(50, 50);// Pose de la tuile centrale
        vueJeux.repaint();
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

                    if (!getModelMatrice().isNordOuest(tuile)) {
                        modelMatrice.poseeButton(row-1, col-1, new ModelTuile());
                    }

                    if (!getModelMatrice().isNord(tuile)) {
                        modelMatrice.poseeButton(row-2,col, new ModelTuile());
                    }

                    if (!getModelMatrice().isNordEst(tuile)) {
                        modelMatrice.poseeButton(row-1,col+1, new ModelTuile());
                    }

                    if (!getModelMatrice().isSudOuest(tuile)) {
                        modelMatrice.poseeButton(row+1,col-1, new ModelTuile());
                    }

                    if (!getModelMatrice().isSud(tuile)) {
                        modelMatrice.poseeButton(row+2,col, new ModelTuile());
                    }

                    if (!getModelMatrice().isSudEst(tuile)) {
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
                    modelMatrice.deleteTuile(row, col);
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

    public boolean isUndo() {
        return undo;
    }

    public void setUndo(boolean undo) {
        this.undo = undo;
    }

    public boolean isUndoActivate() {
        return undoActivate;
    }

    public void setUndoActivate(boolean undoActivate) {
        this.undoActivate = undoActivate;
    }



    public void setTuileUndoAble(ModelTuile tuileUndoAble) {
        this.tuileUndoAble = tuileUndoAble;
    }
    public void undoLastTuile() {
        if (!undo) {
            listTuiles.addFirst(tuileUndoAble);
            deleteButtons();
            modelMatrice.deleteTuile(tuileUndoAble.getX(), tuileUndoAble.getY());
            getVueJeux().updatePreviewTuileList();

            createButton();
            undo = true;
        }
    }
}
