package fr.iutfbleau.SAE31_2024_LTA.jeux;

import java.util.ArrayList;


// cette méthode permet de compter les points de la partie en cours
public class ModelComptagePoints {

    // Fonction qui enlève des points au compteur
    private static int enleverPoint(int points, ModelPoche poche) {
        return points - (poche.getTuiles().size() * poche.getTuiles().size());
    }

    // Fonction qui ajoute des points au compteur
    private static int ajouterPoint(int points, ModelPoche poche) {
        return points + (poche.getTuiles().size() * poche.getTuiles().size());
    }

    // cette fonction permet d'uptade les points après qu'une nouvelle tuile soit posé
    public static int updatePoints(int points, ModelTuile tuile, ModelTuile[] voisin) {
        boolean[] correspond = ModelMatrice.correspondVoisins(tuile, voisin);
        ArrayList<ModelPoche> couleur1 = new ArrayList<>();
        ArrayList<ModelPoche> couleur2 = new ArrayList<>();

        for (int i = 0; i < voisin.length; i++) {
            if (correspond[i]) {
                ModelPoche poche = ModelPoche.comparaisonPoche(tuile, voisin[i], i);
                boolean use = (tuile.getIndexcouleur1() != tuile.getIndexcouleur2()) &&
                        (tuile.getIndexcouleur1() == poche.getCouleur() ||
                                tuile.getIndexcouleur2() == poche.getCouleur());

                if (use) {
                    if (tuile.getIndexcouleur1() == poche.getCouleur() && !couleur1.contains(poche)) {
                        points = enleverPoint(points, poche);
                        couleur1.add(poche);
                    }
                    if (tuile.getIndexcouleur2() == poche.getCouleur() && !couleur2.contains(poche)) {
                        points = enleverPoint(points, poche);
                        couleur2.add(poche);
                    }
                } else if (tuile.getIndexcouleur1() == tuile.getIndexcouleur2()) {
                    if (!couleur1.contains(poche)) {
                        points = enleverPoint(points, poche);
                        couleur1.add(poche);
                        couleur2.add(poche);
                    }
                }
            }
        }

        // Gestion des nouvelles poches
        if (couleur1.isEmpty() && (tuile.getIndexcouleur1() != tuile.getIndexcouleur2())) {
            ModelPoche nouvellePoche = new ModelPoche(tuile.getIndexcouleur1(), tuile);
            tuile.setPoche1(nouvellePoche);
            points = ajouterPoint(points, nouvellePoche);
        }

        if (couleur2.isEmpty() && (tuile.getIndexcouleur1() != tuile.getIndexcouleur2())) {
            ModelPoche nouvellePoche = new ModelPoche(tuile.getIndexcouleur2(), tuile);
            tuile.setPoche2(nouvellePoche);
            points = ajouterPoint(points, nouvellePoche);
        }

        if (couleur1.isEmpty() && tuile.getIndexcouleur1() == tuile.getIndexcouleur2()) {
            ModelPoche nouvellePoche = new ModelPoche(tuile.getIndexcouleur1(), tuile);
            tuile.setPoche1(nouvellePoche);
            tuile.setPoche2(nouvellePoche);
            points = ajouterPoint(points, nouvellePoche);
        }

        if (tuile.getIndexcouleur1() != tuile.getIndexcouleur2()) {
            if (couleur1.size() > 1) {
                ModelPoche poche = new ModelPoche(tuile.getIndexcouleur1(), tuile);
                tuile.setPoche1(poche);
                ModelPoche.changementPoche(couleur1, poche, tuile.getIndexcouleur1());
                points = ajouterPoint(points, tuile.getPoche()[0]);
            }
            if (couleur2.size() > 1) {
                ModelPoche poche = new ModelPoche(tuile.getIndexcouleur2(), tuile);
                tuile.setPoche2(poche);
                ModelPoche.changementPoche(couleur2, poche, tuile.getIndexcouleur2());
                points = ajouterPoint(points, tuile.getPoche()[1]);
            }
            if (couleur1.size() == 1) {
                tuile.setPoche1(couleur1.get(0));
                couleur1.get(0).addTuile(tuile);
                points = ajouterPoint(points, couleur1.get(0));
            }
            if (couleur2.size() == 1) {
                tuile.setPoche2(couleur2.get(0));
                couleur2.get(0).addTuile(tuile);
                points = ajouterPoint(points, couleur2.get(0));
            }
        } else {
            if (couleur1.size() > 1) {
                ModelPoche poche = new ModelPoche(tuile.getIndexcouleur1(), tuile);
                tuile.setPoche1(poche);
                tuile.setPoche2(poche);
                ModelPoche.changementPoche(couleur1, poche, tuile.getIndexcouleur1());
                points = ajouterPoint(points, tuile.getPoche()[0]);
            }
            if (couleur1.size() == 1) {
                tuile.setPoche1(couleur1.get(0));
                tuile.setPoche2(couleur2.get(0));
                couleur1.get(0).addTuile(tuile);
                points = ajouterPoint(points, couleur1.get(0));
            }
        }
        return points;
    }
    // cette fonction va enlevé les points quand on enlève une tuile
    public static int UndoPoints(int points, ModelTuile tuile, ModelMatrice matrice) {
        ModelPoche poche1 = tuile.getPoche()[0];
        ModelPoche poche2 = tuile.getPoche()[1];

        points = enleverPoint(points, poche1);
        poche1.removeTuile(tuile);
        if (poche1 != poche2) {
            points = enleverPoint(points, poche2);
            poche2.removeTuile(tuile);
        }

        while (!poche1.getTuiles().isEmpty()) {
            points = ajouterPoint(points, ModelPoche.createNouvellePoche(poche1, matrice, tuile));
        }

        while (!poche2.getTuiles().isEmpty()) {
            points = ajouterPoint(points, ModelPoche.createNouvellePoche(poche2, matrice, tuile));
        }

        tuile.setPoche(null, null);
        return points;
    }
}
