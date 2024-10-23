package fr.iutfbleau.SAE31_2024_LTA.jeux;

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
        boolean use1 = false;
        boolean use2 = false;
        for (int i = 0; i < voisin.length; i++) {
            if (correspond[i]) {
                ModelPoche poche= comparaisonPoche(tuile, voisin[i], i);
                if (tuile.getIndexcouleur1()!=tuile.getIndexcouleur2()) {
                    if (tuile.getIndexcouleur1() == poche.getCouleur() && use1 == false) {
                        use1 = true;
                        points=enleverPoint(points, poche);
                        tuile.setPoche1(poche);
                    }
                    if (tuile.getIndexcouleur2() == poche.getCouleur() && use2 == false) {
                        use2 = true;
                        points = enleverPoint(points, poche);
                        tuile.setPoche2(poche);
                    }
                }
                else {
                    if (!use1) {
                        use1 = true;
                        use2 = true;
                        points = enleverPoint(points, poche);
                        tuile.setPoche1(poche);
                        tuile.setPoche2(poche);
                    }
                }

            }
        }
        if (!use1) {
            tuile.setPoche1(new ModelPoche(tuile.getIndexcouleur1(), tuile));
        }
        if (!use2) {
            tuile.setPoche2(new ModelPoche(tuile.getIndexcouleur2(), tuile));
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
