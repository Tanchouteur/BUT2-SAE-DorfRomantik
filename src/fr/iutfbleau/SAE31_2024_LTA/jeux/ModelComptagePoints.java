package fr.iutfbleau.SAE31_2024_LTA.jeux;
import java.util.ArrayList;

public class ModelComptagePoints {

    public static boolean correspond(ModelTuile tuile, ModelTuile comparaison, int direction)  {
        if (tuile.getComposition()[direction] == comparaison.getComposition()[(direction+3)%6]) {
            return true;
        }
        return false;
    }
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
    public static int enleverPoint (int point, ModelPoche poche) {
        System.out.println(poche.getLength());
        point -= poche.getLength()* poche.getLength();
        return point;
    }
    public static int ajoutPoint(int point, ModelPoche poche) {
        System.out.println(poche.getLength());
        point+= poche.getLength()* poche.getLength() ;
        return point;
    }
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

    public static int updatePoche(int points, ModelTuile tuile, ModelTuile[] voisin) {
        boolean[] correspond = correspondVoisins(tuile, voisin);
        ArrayList<ModelPoche> couleur1 = new ArrayList<>();
        ArrayList<ModelPoche> couleur2 = new ArrayList<>();

        for (int i = 0; i < voisin.length; i++) {
            if (correspond[i]) {
                ModelPoche poche= comparaisonPoche(tuile, voisin[i], i);
                if (tuile.getIndexcouleur1()!=tuile.getIndexcouleur2()) {
                    if (tuile.getIndexcouleur1() == poche.getCouleur()) {
                        couleur1.add(poche);
                        points=enleverPoint(points, poche);

                    }
                    if (tuile.getIndexcouleur2() == poche.getCouleur()) {
                        couleur2.add(poche);
                        points = enleverPoint(points, poche);

                    }
                }
                else {
                    if (tuile.getIndexcouleur1() == poche.getCouleur()) {
                        couleur1.add(poche);
                        couleur2.add(poche);
                        points = enleverPoint(points, poche);

                    }
                }

            }
        }
        if (couleur1.isEmpty()) {
            tuile.setPoche1(new ModelPoche(tuile.getIndexcouleur1(), tuile));
        }
        if (couleur2.isEmpty()) {
            tuile.setPoche2(new ModelPoche(tuile.getIndexcouleur2(), tuile));
        }
        if (tuile.getIndexcouleur1()!= tuile.getIndexcouleur2()) {

            if (couleur1.size() > 1) {
                for (int i = 1; i < couleur1.size(); i++) {

                }
            }

            if (couleur2.size() > 1) {
                for (int i = 1; i < couleur2.size(); i++) {

                }
            }
        }
        else {
            tuile.setPoche1(couleur1.get(0));
            tuile.setPoche2(couleur2.get(0));
            for (int i = 1; i < couleur1.size(); i++) {

            }
        }
        if (tuile.getIndexcouleur1() != tuile.getIndexcouleur2()) {
            points += ajoutPoint(points, tuile.getPoche()[0]);
            System.out.println(points);
            points += ajoutPoint(points, tuile.getPoche()[1]);
            System.out.println(points);
        }
        else {
            points = ajoutPoint(points, tuile.getPoche()[0]);
            System.out.println(points);
        }
        return points;

    }



}
