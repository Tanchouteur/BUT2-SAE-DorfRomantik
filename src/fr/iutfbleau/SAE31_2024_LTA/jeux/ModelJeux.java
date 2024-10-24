package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.endGame.VueScoreScreen;

import javax.swing.*;
import java.awt.Point;
import java.util.*;

public class ModelJeux {
    private VueJeux vueJeux;
    private VueScoreScreen vueScoreScreen;

    private final ModelPrincipale modelPrincipale;
    private final ModelMatrice modelMatrice;


    private final LinkedList<ModelTuile> listTuiles;//Liste de tuile généré

    private boolean undoActivate = false;
    private boolean undo = false;
    private ModelTuile tuileUndoAble;
    private int lastscore;
    private int score = 0;

    private static final int nombreTuile = 50;

    public ModelJeux(ModelPrincipale modelPrincipale, int seed) {
        this.modelPrincipale = modelPrincipale;

        this.modelMatrice = new ModelMatrice(this);
        listTuiles = new LinkedList<>();

        for (int i = nombreTuile; i >= 0; i--) {
            ModelTuile tuile = new ModelTuile(seed*i, false,false,modelPrincipale.getConfigManager().isAA());
            listTuiles.add(tuile);
        }

        createView();

        vueJeux.updatePreviewTuileList();
    }

    private void createView(){
        this.vueJeux = new VueJeux(this);
        modelPrincipale.getVuePrincipale().getPrincipaleLayeredPane().getMainPanel().add(vueJeux, "jeux");
    }

    public void createEndView(){
        this.vueScoreScreen = new VueScoreScreen(modelPrincipale);
        this.getVueScoreScreen().setBounds(vueJeux.getWidth(), (vueJeux.getHeight()-this.getVueScoreScreen().getHeightSidebar())/2, this.getVueScoreScreen().getWidthSidebar(), this.getVueScoreScreen().getHeightSidebar());
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
        List<Point> pointsToAdd = new ArrayList<>(); // Liste pour stocker les points à ajouter

        for (Map.Entry<Point, ModelTuile> entry : modelMatrice.getTuilesPartie().entrySet()) {
            Point point = entry.getKey();
            ModelTuile tuile = entry.getValue();

            if (tuile != null && !tuile.isButton()) {
                // verifie si chaque position est disponible et l'ajoute à la liste pour print les bouttons
                if (tryCreateButton(point.x - 1, point.y - 1)) {  // Nord-Ouest
                    pointsToAdd.add(new Point(point.x - 1, point.y - 1));
                }
                if (tryCreateButton(point.x , point.y - 2)) {      // Nord
                    pointsToAdd.add(new Point(point.x , point.y - 2));
                }
                if (tryCreateButton(point.x - 1, point.y + 1)) {  // Nord-Est
                    pointsToAdd.add(new Point(point.x - 1, point.y + 1));
                }
                if (tryCreateButton(point.x + 1, point.y - 1)) {  // Sud-Ouest
                    pointsToAdd.add(new Point(point.x + 1, point.y - 1));
                }
                if (tryCreateButton(point.x , point.y + 2)) {      // Sud
                    pointsToAdd.add(new Point(point.x , point.y + 2));
                }
                if (tryCreateButton(point.x + 1, point.y + 1)) {  // Sud-Est
                    pointsToAdd.add(new Point(point.x + 1, point.y + 1));
                }
            }
        }


        for (Point newPoint : pointsToAdd) {
            modelMatrice.poseeButton(newPoint.x, newPoint.y, new ModelTuile());
        }
    }

    private boolean tryCreateButton(int x, int y) {
        return !modelMatrice.isOccupied(x, y);
    }


    public void deleteButtons() {
        Map<Point, ModelTuile> tuiles = modelMatrice.getTuilesPartie();

        for (ModelTuile tuile : tuiles.values()) {
            if (tuile.isButton() && tuile.getVueTuile() != null) {
                vueJeux.remove(tuile.getVueTuile());
            }
        }

        tuiles.values().removeIf(ModelTuile::isButton);
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

    public Action undoLastTuile() {
        if (!undo) {
            listTuiles.addFirst(tuileUndoAble);
            deleteButtons();

            modelMatrice.deleteTuile(tuileUndoAble);
            setScore(ModelComptagePoints.UndoPocheTuile(getScore(),listTuiles.getFirst(),modelMatrice));
            getVueJeux().updatePreviewTuileList();
            tuileUndoAble.setVueTuile(null);
            createButton();
            undo = true;
            tuileUndoAble.setOnBoard(false);
        }
        return null;
    }

    public boolean isAA() {
        return modelPrincipale.getConfigManager().isAA();
    }
    public void setLastscore(int points) {
        this.lastscore = points;
    }

    public int getLastscore() {
        return this.lastscore;
    }
}
