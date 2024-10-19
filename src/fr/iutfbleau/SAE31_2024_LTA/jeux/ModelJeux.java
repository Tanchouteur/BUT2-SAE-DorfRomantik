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


        modelMatrice.poseeTuile(50, 50, listTuiles.getFirst()); // Pose de la tuile centrale
        listTuiles.removeFirst();

        modelMatrice.poseeTuile(49, 49, listTuiles.getFirst()); // Tuile en haut à gauche (ligne impaire, décalée)
        listTuiles.removeFirst();

        modelMatrice.poseeTuile(49, 51, listTuiles.getFirst()); // Tuile en haut à droite (ligne impaire, décalée)
        listTuiles.removeFirst();

        modelMatrice.poseeTuile(51, 49, listTuiles.getFirst()); // Tuile en bas à gauche
        listTuiles.removeFirst();

        modelMatrice.poseeTuile(51, 51, listTuiles.getFirst()); // Tuile en bas à droite
        listTuiles.removeFirst();

        modelMatrice.poseeTuile(52, 50, listTuiles.getFirst()); // Tuile encore en dessous
        listTuiles.removeFirst();

        modelMatrice.poseeTuile(48, 50, listTuiles.getFirst()); // Tuile encore en dessous
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
