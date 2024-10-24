package fr.iutfbleau.SAE31_2024_LTA.jeux;
import java.util.ArrayList;

public class ModelPoche {
    private final int couleur;
    private final ArrayList<ModelTuile> tuiles;


    public ModelPoche(int couleur, ModelTuile tuile) {
        this.couleur = couleur;
        this.tuiles = new ArrayList<>();
        this.tuiles.add(tuile);


    }

    public ArrayList<ModelTuile> getTuiles() {
        return tuiles;
    }



    public void addTuile(ModelTuile m) {
        this.tuiles.add(m);

    }

    public int getCouleur() {
        return this.couleur;
    }

    public void removeTuile(ModelTuile m) {
        for (ModelTuile tuile : tuiles) {
            if (tuile.equals(m)) {
                tuiles.remove(tuile);

            }
        }
    }

}