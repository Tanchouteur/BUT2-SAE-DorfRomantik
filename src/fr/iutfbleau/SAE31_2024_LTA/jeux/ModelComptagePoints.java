package fr.iutfbleau.SAE31_2024_LTA.jeux;
import java.util.ArrayList;

public class ModelComptagePoints {

    // fonction qui vérifie si la couleur de la tuile est égal à la couleur de la tuile de la tuile dans une direction
    public static boolean correspond(ModelTuile tuile, ModelTuile comparaison, int direction)  {
        if (tuile.getComposition()[direction] == comparaison.getComposition()[(direction+3)%6]) {
            return true;
        }
        return false;
    }

    // fonction qui retourne la poche de la tuile comparaison qui a la couleur de la tuile tuile dans une direction
    public static ModelPoche comparaisonPoche(ModelTuile tuile, ModelTuile comparaison, int direction) {
        int couleur = tuile.getComposition()[direction];
        if (comparaison.getPoche()[0] == comparaison.getPoche()[1]) {
            return comparaison.getPoche()[0];
        }
        else {
            if (comparaison.getPoche()[0].getCouleur() ==couleur) {
                return comparaison.getPoche()[0];
            }
            else {
                return comparaison.getPoche()[1];
            }
        }
    }
    // fonction qui enlève des points au compteur
    public static int enleverPoint (int point, ModelPoche poche) {
        System.out.println("taille poche : " + poche.getTuiles().size());
        System.out.println("point avant enlevement: " + point);
        point -= (poche.getTuiles().size()* poche.getTuiles().size());
        System.out.println("point après enlevement: " + point);
        return point;
    }

    // fonction qui ajoute des points au compteur
    public static int ajoutPoint(int point, ModelPoche poche) {
        System.out.println("taille poche : " + poche.getTuiles().size());
        System.out.println("point avant ajout: " + point);
        point+= (poche.getTuiles().size() * poche.getTuiles().size()) ;
        System.out.println("point après ajout: " + point);
        return point;
    }

    // cette fonction retourne une liste de boolean
    public static boolean[] correspondVoisins(ModelTuile tuile, ModelTuile[] voisin) {
        boolean[] correspond = new boolean[voisin.length];
        for (int i = 0; i < voisin.length; i++) {
            if (voisin[i]!=null) {


                if (correspond(tuile, voisin[i], i)) {
                    correspond[i] = true;
                } else {
                    correspond[i] = false;
                }
            }
        }
        return correspond;
    }

    public static int changementPoche(ArrayList<ModelPoche> couleur, ModelTuile tuile, int points, ModelPoche poche ) {

        for (int i = 0; i < couleur.size(); i++) {
            ModelPoche changementPoche= couleur.get(i);
            for (int j = 0;j<changementPoche.getTuiles().size(); j++) {
                if (changementPoche.getTuiles().get(j).getPoche()[0].getCouleur()==tuile.getIndexcouleur1()) {
                    changementPoche.getTuiles().get(j).setPoche1(poche);
                    poche.addTuile(changementPoche.getTuiles().get(j));
                }
                if (changementPoche.getTuiles().get(j).getPoche()[1].getCouleur()==tuile.getIndexcouleur1()) {
                    changementPoche.getTuiles().get(j).setPoche2(poche);
                    poche.addTuile(changementPoche.getTuiles().get(j));
                }

            }
        }
        points = ajoutPoint(points,tuile.getPoche()[0]);
        return points;
    }



    public static int updatePoche(int points, ModelTuile tuile, ModelTuile[] voisin) {
        boolean[] correspond = correspondVoisins(tuile, voisin);
        ArrayList<ModelPoche> couleur1 = new ArrayList<>();
        ArrayList<ModelPoche> couleur2 = new ArrayList<>();
        boolean use = false;

        System.out.println("Mise à jour de la poche : Points initiaux = " + points + ", Tuile = " + tuile);

        for (int i = 0; i < voisin.length; i++) {
            if (correspond[i]) {
                ModelPoche poche = comparaisonPoche(tuile, voisin[i], i);
                System.out.println("Correspondance trouvée avec le voisin " + voisin[i] + ", Poche = " + poche);

                if (tuile.getIndexcouleur1() != tuile.getIndexcouleur2()) {
                    if (tuile.getIndexcouleur1() == poche.getCouleur()) {
                        use = couleur1.contains(poche);
                        if (!use) {
                            points = enleverPoint(points, poche);
                            couleur1.add(poche);
                            System.out.println("Points après retrait (couleur1) : " + points + ", Taille de la poche couleur1 : " + poche.getTuiles().size());
                        }
                        use = false;
                    }
                    if (tuile.getIndexcouleur2() == poche.getCouleur()) {
                        use = couleur2.contains(poche);
                        if (!use) {
                            points = enleverPoint(points, poche);
                            couleur2.add(poche);
                            System.out.println("Points après retrait (couleur2) : " + points + ", Taille de la poche couleur2 : " + poche.getTuiles().size());
                        }
                        use = false;
                    }
                } else {
                    if (tuile.getIndexcouleur1() == poche.getCouleur()) {
                        use = couleur1.contains(poche);
                        if (!use) {
                            points = enleverPoint(points, poche);
                            couleur1.add(poche);
                            couleur2.add(poche);
                            System.out.println("Points après retrait (couleur1 et couleur2) : " + points);
                        }
                    }
                }
            }
        }

        // Gestion des nouvelles poches
        if (couleur1.isEmpty() && (tuile.getIndexcouleur1() != tuile.getIndexcouleur2())) {
            ModelPoche nouvellePoche1 = new ModelPoche(tuile.getIndexcouleur1(), tuile);
            tuile.setPoche1(nouvellePoche1);
            points = ajoutPoint(points, nouvellePoche1);
            System.out.println("Création de nouvelle poche couleur1 : " + nouvellePoche1 + ", Points après ajout : " + points);
        }

        if (couleur2.isEmpty() && (tuile.getIndexcouleur1() != tuile.getIndexcouleur2())) {
            ModelPoche nouvellePoche2 = new ModelPoche(tuile.getIndexcouleur2(), tuile);
            tuile.setPoche2(nouvellePoche2);
            points = ajoutPoint(points, nouvellePoche2);
            System.out.println("Création de nouvelle poche couleur2 : " + nouvellePoche2 + ", Points après ajout : " + points);
        }

        if (couleur1.isEmpty() && tuile.getIndexcouleur1() == tuile.getIndexcouleur2()) {
            ModelPoche nouvellePoche3 = new ModelPoche(tuile.getIndexcouleur1(), tuile);
            tuile.setPoche1(nouvellePoche3);
            tuile.setPoche2(nouvellePoche3);
            points = ajoutPoint(points, nouvellePoche3);
            System.out.println("Création de nouvelle poche couleur identique : " + nouvellePoche3 + ", Points après ajout : " + points);
        }

        if (tuile.getIndexcouleur1() != tuile.getIndexcouleur2()) {
            if (couleur1.size() > 1) {
                ModelPoche poche = new ModelPoche(tuile.getIndexcouleur1(), tuile);
                tuile.setPoche1(poche);
                for (int i = 0; i < couleur1.size(); i++) {
                    ModelPoche changementPoche= couleur1.get(i);
                    for (int j = 0;j<changementPoche.getTuiles().size(); j++) {
                        if (changementPoche.getTuiles().get(j).getPoche()[0].getCouleur()==tuile.getIndexcouleur1()) {
                            changementPoche.getTuiles().get(j).setPoche1(poche);
                            poche.addTuile(changementPoche.getTuiles().get(j));
                        }
                        if (changementPoche.getTuiles().get(j).getPoche()[1].getCouleur()==tuile.getIndexcouleur1()) {
                            changementPoche.getTuiles().get(j).setPoche2(poche);
                            poche.addTuile(changementPoche.getTuiles().get(j));
                        }

                    }
                }
                points = ajoutPoint(points,tuile.getPoche()[0]);
                System.out.println("Changement de poche couleur1, Points après changement : " + points);
            }
            if (couleur2.size() > 1) {
                ModelPoche poche = new ModelPoche(tuile.getIndexcouleur2(), tuile);
                tuile.setPoche2(poche);
                for (int i = 0; i < couleur2.size(); i++) {
                    ModelPoche changementPoche= couleur2.get(i);
                    for (int j = 0;j<changementPoche.getTuiles().size(); j++) {
                        if (changementPoche.getTuiles().get(j).getPoche()[0].getCouleur()==tuile.getIndexcouleur1()) {
                            changementPoche.getTuiles().get(j).setPoche1(poche);
                            poche.addTuile(changementPoche.getTuiles().get(j));
                        }
                        if (changementPoche.getTuiles().get(j).getPoche()[1].getCouleur()==tuile.getIndexcouleur1()) {
                            changementPoche.getTuiles().get(j).setPoche2(poche);
                            poche.addTuile(changementPoche.getTuiles().get(j));
                        }

                    }
                }
                points = ajoutPoint(points,tuile.getPoche()[1]);
                System.out.println("Changement de poche couleur2, Points après changement : " + points);
            }
            if (couleur1.size() == 1) {
                tuile.setPoche1(couleur1.get(0));
                couleur1.get(0).addTuile(tuile);
                points = ajoutPoint(points, couleur1.get(0));
                System.out.println("Ajout de la tuile à la poche couleur1, Points après ajout : " + points);
            }
            if (couleur2.size() == 1) {
                tuile.setPoche2(couleur2.get(0));
                couleur2.get(0).addTuile(tuile);
                points = ajoutPoint(points, couleur2.get(0));
                System.out.println("Ajout de la tuile à la poche couleur2, Points après ajout : " + points);
            }
        } else {
            if (couleur1.size() > 1) {
                ModelPoche poche = new ModelPoche(tuile.getIndexcouleur1(), tuile);
                tuile.setPoche1(poche);
                tuile.setPoche2(poche);
                for (int i = 0; i < couleur1.size(); i++) {
                    ModelPoche changementPoche= couleur1.get(i);
                    for (int j = 0;j<changementPoche.getTuiles().size(); j++) {
                        if (changementPoche.getTuiles().get(j).getPoche()[0].getCouleur()==tuile.getIndexcouleur1()) {
                            changementPoche.getTuiles().get(j).setPoche1(poche);
                            poche.addTuile(changementPoche.getTuiles().get(j));
                        }
                        if (changementPoche.getTuiles().get(j).getPoche()[1].getCouleur()==tuile.getIndexcouleur1()) {
                            changementPoche.getTuiles().get(j).setPoche2(poche);
                            poche.addTuile(changementPoche.getTuiles().get(j));
                        }

                    }
                }
                points = ajoutPoint(points,tuile.getPoche()[0]);
                System.out.println("Changement de poche (couleur identique), Points après changement : " + points);
            }
            if (couleur1.size() == 1) {
                tuile.setPoche1(couleur1.get(0));
                tuile.setPoche2(couleur2.get(0));
                couleur1.get(0).addTuile(tuile);
                points = ajoutPoint(points, couleur1.get(0));
                System.out.println("Ajout de la tuile à la poche identique, Points après ajout : " + points);
            }
        }
        return points;
    }




    public static int UndoPocheTuile(int points, ModelTuile tuile, ModelMatrice matrice) {
        ModelPoche poche1 = tuile.getPoche()[0];
        ModelPoche poche2 = tuile.getPoche()[1];
        if (poche2!=poche1) {

            points = enleverPoint(points, poche1);
            points = enleverPoint(points, poche2);


            poche1.removeTuile(tuile);
            poche2.removeTuile(tuile);
        }
        else {
            points = enleverPoint(points, poche1);
            poche1.removeTuile(tuile);
        }
        while (!poche1.getTuiles().isEmpty()) {
            System.out.println("rentre poche1");
            ModelPoche nouvellepoche = new ModelPoche(poche1.getCouleur(), poche1.getTuiles().get(0) );
            nouvellepoche = ModelPoche.createPocheVoisinProfondeur( poche1.getTuiles().get(0), nouvellepoche,poche1.getCouleur(), matrice);

            if (poche1.getTuiles().get(0).getPoche()[0].getCouleur() == poche1.getCouleur()) {
                poche1.getTuiles().get(0).setPoche1(nouvellepoche);
            }
            if (poche1.getTuiles().get(0).getPoche()[1].getCouleur() == poche1.getCouleur()) {
                poche1.getTuiles().get(0).setPoche2(nouvellepoche);
            }
            poche1.getTuiles().remove(poche1.getTuiles().get(0));
            for (int i = poche1.getTuiles().size()-1; i >= 0; i--) {
                if (nouvellepoche.getTuiles().contains(poche1.getTuiles().get(i))) {
                    if (poche1.getTuiles().get(i).getPoche()[0].getCouleur() == poche1.getCouleur()) {
                        poche1.getTuiles().get(i).setPoche1(nouvellepoche);
                    }
                    if (poche1.getTuiles().get(i).getPoche()[1].getCouleur() == poche1.getCouleur()) {
                        poche1.getTuiles().get(i).setPoche2(nouvellepoche);
                    }
                    poche1.getTuiles().remove(i);
                }
            }
            points = ajoutPoint(points, nouvellepoche);
            System.out.println("taille nouvelle poche"+nouvellepoche.getTuiles().size());

        }

        while (!poche2.getTuiles().isEmpty()) {
            System.out.println("rentre poche2");
            ModelPoche nouvellepoche = new ModelPoche(poche2.getCouleur(), poche2.getTuiles().get(0) );
            nouvellepoche = ModelPoche.createPocheVoisinProfondeur( poche2.getTuiles().get(0), nouvellepoche,poche2.getCouleur(), matrice);

            if (poche2.getTuiles().get(0).getPoche()[0].getCouleur() == poche2.getCouleur()) {
                poche2.getTuiles().get(0).setPoche1(nouvellepoche);
            }
            if (poche2.getTuiles().get(0).getPoche()[1].getCouleur() == poche2.getCouleur()) {
                poche2.getTuiles().get(0).setPoche2(nouvellepoche);
            }
            poche2.getTuiles().remove(poche2.getTuiles().get(0));
            for (int i = poche2.getTuiles().size()-1; i >= 0; i--) {
                if (nouvellepoche.getTuiles().contains(poche2.getTuiles().get(i))) {
                    if (poche2.getTuiles().get(i).getPoche()[0].getCouleur() == poche2.getCouleur()) {
                        poche2.getTuiles().get(i).setPoche1(nouvellepoche);
                    }
                    if (poche2.getTuiles().get(i).getPoche()[1].getCouleur() == poche2.getCouleur()) {
                        poche2.getTuiles().get(i).setPoche2(nouvellepoche);
                    }
                    poche2.getTuiles().remove(i);
                }
            }
            points = ajoutPoint(points, nouvellepoche);
            System.out.println("taille nouvelle poche"+nouvellepoche.getTuiles().size());
        }


        tuile.setPoche(null, null);
        System.out.println("point après undo"+points);
        return points;


    }







}
