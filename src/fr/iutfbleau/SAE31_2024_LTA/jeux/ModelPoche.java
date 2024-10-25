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
        this.tuiles.remove(m);


    }
    public static ModelPoche createPocheVoisinProfondeur(ModelTuile m, ModelPoche poche, int couleur, ModelMatrice matrice, ModelTuile tuileEnleve) {
        ArrayList<ModelTuile> visited = new ArrayList<>();
        visited.add(tuileEnleve);
        return createPocheVoisinProfondeur(m, poche, couleur, matrice, visited);
    }

    private static ModelPoche createPocheVoisinProfondeur(ModelTuile m, ModelPoche poche, int couleur, ModelMatrice matrice, ArrayList<ModelTuile> visited) {
        // Vérifie si la tuile a déjà été visitée
        if (visited.contains(m)) {
            System.out.println("Tuile déjà visitée : " + m); // Log pour les tuiles déjà visitées
            return poche; // Éviter les boucles infinies
        }

        // Marquer la tuile comme visitée
        visited.add(m);
        System.out.println("Visite de la tuile : " + m); // Log pour la tuile actuellement visitée

        ModelTuile[] voisin = matrice.getVoisins(m);
        boolean[] correspond = ModelComptagePoints.correspondVoisins(m, voisin);

        for (int i = 0; i < correspond.length; i++) {
            if (correspond[i]) {
                System.out.println("Correspondance trouvée avec le voisin : " + voisin[i]); // Log pour les voisins correspondants

                if (!poche.tuiles.contains(voisin[i]) && m.getComposition()[i] == poche.getCouleur()) {
                    poche.addTuile(voisin[i]);
                    System.out.println("Ajout de la tuile voisine : " + voisin[i]); // Log pour l'ajout d'une tuile

                    // Appel récursif avec la tuile voisine
                    return createPocheVoisinProfondeur(voisin[i], poche, couleur, matrice, visited);
                }
            }
        }

        return poche;

    }

}