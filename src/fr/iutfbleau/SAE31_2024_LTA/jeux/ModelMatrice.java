package fr.iutfbleau.SAE31_2024_LTA.jeux;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class ModelMatrice {

    private Map<Point, ModelTuile> tuilesPartie;
    private final ModelJeux modelJeux;
    private final ModelListePoche modelListePoche;

    public ModelMatrice(ModelJeux modelJeux) {
        this.modelJeux = modelJeux;
        this.modelListePoche = new ModelListePoche(modelJeux);
        this.tuilesPartie = new HashMap<>(); // Utilisation d'une HashMap pour les tuiles
    }

    public void poseTuile(int x, int y) {
        ModelTuile tuile = modelJeux.getListTuiles().getFirst();
        this.tuilesPartie.put(new Point(x, y), tuile);
        this.tuilesPartie.get(new Point(x, y)).setCoordonner(x, y);
        modelJeux.setTuileUndoAble(tuile);
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
        return this.tuilesPartie.containsKey(new Point(tuile.getX() - 2, tuile.getY()));
    }

    public boolean isNordEst(ModelTuile tuile) {
        return this.tuilesPartie.containsKey(new Point(tuile.getX() - 1, tuile.getY() + 1));
    }

    public boolean isSudOuest(ModelTuile tuile) {
        return this.tuilesPartie.containsKey(new Point(tuile.getX() + 1, tuile.getY() - 1));
    }

    public boolean isSud(ModelTuile tuile) {
        return this.tuilesPartie.containsKey(new Point(tuile.getX() + 2, tuile.getY()));
    }

    public boolean isSudEst(ModelTuile tuile) {
        return this.tuilesPartie.containsKey(new Point(tuile.getX() + 1, tuile.getY() + 1));
    }

    // Il faut éventuellement verifier si la tuile demandee existe avec un isPosition ou alors le mettre dans cette methode.
    public ModelTuile getNordOuest(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() - 1, tuile.getY() - 1));
    }

    public ModelTuile getNord(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() - 2, tuile.getY()));
    }

    public ModelTuile getNordEst(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() - 1, tuile.getY() + 1));
    }

    public ModelTuile getSudOuest(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() + 1, tuile.getY() - 1));
    }

    public ModelTuile getSud(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() + 2, tuile.getY()));
    }

    public ModelTuile getSudEst(ModelTuile tuile) {
        return this.tuilesPartie.get(new Point(tuile.getX() + 1, tuile.getY() + 1));
    }

    public Map<Point, ModelTuile> getTuilesPartie() {
        return this.tuilesPartie;
    }
}
