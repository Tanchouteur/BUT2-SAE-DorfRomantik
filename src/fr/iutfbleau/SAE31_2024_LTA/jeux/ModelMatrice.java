package fr.iutfbleau.SAE31_2024_LTA.jeux;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class ModelMatrice {

    private Map<Point, ModelTuile> tuilesPartie;
    private final ModelJeux modelJeux;


    public ModelMatrice(ModelJeux modelJeux) {
        this.modelJeux = modelJeux;

        this.tuilesPartie = new HashMap<>(); // Utilisation d'une HashMap pour les tuiles
    }

    public void poseTuile(int x, int y) {
        ModelTuile tuile = modelJeux.getListTuiles().getFirst();
        this.tuilesPartie.put(new Point(x, y), tuile);
        this.tuilesPartie.get(new Point(x, y)).setCoordonner(x, y);
        modelJeux.setTuileUndoAble(tuile);
        ModelTuile[] voisin = getVoisins(tuile);
        modelJeux.setScore(ModelComptagePoints.updatePoints(modelJeux.getScore(),tuile,voisin));
        modelJeux.getListTuiles().removeFirst();

        if (!modelJeux.isUndoActivate() && x != 0 && y != 0) {
            modelJeux.setUndoActivate(true);

        }
        if (modelJeux.isUndo()) {
            modelJeux.setUndo(false);
        }
    }

    public void deleteTuile(ModelTuile tuile) {
        if (!tuilesPartie.isEmpty() && tuile != null &&tuile.getVueTuile() != null) {
            modelJeux.getVueJeux().remove(tuile.getVueTuile());
            this.tuilesPartie.remove(new Point(tuile.getX(), tuile.getY()));
        }
    }

    public void poseeButton(int x, int y, ModelTuile tuile) {
        this.tuilesPartie.put(new Point(x, y), tuile);
        this.tuilesPartie.get(new Point(x, y)).setCoordonner(x, y);
    }

    public boolean isOccupied(int x, int y) {
        ModelTuile tuile = tuilesPartie.get(new Point(x, y));
        return tuile != null;
    }


    public boolean isNordOuest(ModelTuile tuile) {
        return this.tuilesPartie.containsKey(new Point(tuile.getX() - 1, tuile.getY() - 1));
    }

    public boolean isNord(ModelTuile tuile) {
        return this.tuilesPartie.containsKey(new Point(tuile.getX() , tuile.getY() - 2));
    }

    public boolean isNordEst(ModelTuile tuile) {
        return this.tuilesPartie.containsKey(new Point(tuile.getX() + 1, tuile.getY() - 1));
    }

    public boolean isSudOuest(ModelTuile tuile) {
        return this.tuilesPartie.containsKey(new Point(tuile.getX() - 1, tuile.getY() + 1));
    }

    public boolean isSud(ModelTuile tuile) {
        return this.tuilesPartie.containsKey(new Point(tuile.getX() , tuile.getY() + 2));
    }

    public boolean isSudEst(ModelTuile tuile) {
        return this.tuilesPartie.containsKey(new Point(tuile.getX() + 1, tuile.getY() + 1));
    }

    // Il faut éventuellement verifier si la tuile demandee existe avec un isPosition ou alors le mettre dans cette methode.
    public ModelTuile getNordOuest(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() - 1, tuile.getY() - 1));
    }

    public ModelTuile getNord(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() , tuile.getY() - 2));
    }

    public ModelTuile getNordEst(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() + 1, tuile.getY() - 1));
    }

    public ModelTuile getSudOuest(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() - 1, tuile.getY() + 1));
    }

    public ModelTuile getSud(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() , tuile.getY() + 2));
    }

    public ModelTuile getSudEst(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() + 1, tuile.getY() + 1));
    }

    public ModelTuile[] getVoisins(ModelTuile tuile) {
        ModelTuile[] voisins = new ModelTuile[6];

        if (this.isSudEst(tuile) ) {  //trouve bien
            if (!this.getSudEst(tuile).isButton()) {
                voisins[0] = this.getSudEst(tuile);
            }

        }

        if (this.isSud(tuile) ) {    // n'arrive pas à le trouver
            if (!this.getSud(tuile).isButton()) {
                voisins[1] = this.getSud(tuile);
            }

        }

        if (this.isSudOuest(tuile)) {   //trouve deux voisins alors que 1 seul
            if (!this.getSudOuest(tuile).isButton()) {
                voisins[2] = this.getSudOuest(tuile);
            }

        }

        if (this.isNordOuest(tuile)) {   //trouve deux voisins alors que 1 seul
            if (!this.getNordOuest(tuile).isButton()) {
                voisins[3] = this.getNordOuest(tuile);

            }

        }

        if (this.isNord(tuile)) {  //n'arrive pas à le trouver
            if (!this.getNord(tuile).isButton()) {
                voisins[4] = this.getNord(tuile);
            }
        }


        if (this.isNordEst(tuile)) {   //trouve 1 seul mais pas le bon endroit
            if (!this.getNordEst(tuile).isButton()) {
                voisins[5] = this.getNordEst(tuile);
            }
        }
        return voisins;

    }
    public static boolean correspond(ModelTuile tuile, ModelTuile comparaison, int direction)  {
        if (tuile.getComposition()[direction] == comparaison.getComposition()[(direction+3)%6]) {
            return true;
        }
        return false;
    }

    // cette fonction retourne une liste de boolean
    public static boolean[] correspondVoisins(ModelTuile tuile, ModelTuile[] voisin) {
        boolean[] correspond = new boolean[voisin.length];
        for (int i = 0; i < voisin.length; i++) {
            if (voisin[i]!=null) {


                if (ModelMatrice.correspond(tuile, voisin[i], i)) {
                    correspond[i] = true;
                } else {
                    correspond[i] = false;
                }
            }
        }
        return correspond;
    }

    public Map<Point, ModelTuile> getTuilesPartie() {
        return this.tuilesPartie;
    }
}
