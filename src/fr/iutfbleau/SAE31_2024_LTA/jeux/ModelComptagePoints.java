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
        System.out.println("taille poche : " + poche.getTuiles().size());
        System.out.println("point avant enlevement: " + point);
        point -= (poche.getTuiles().size()* poche.getTuiles().size());
        System.out.println("point après enlevement: " + point);
        return point;
    }
    public static int ajoutPoint(int point, ModelPoche poche) {
        System.out.println("taille poche : " + poche.getTuiles().size());
        System.out.println("point avant ajout: " + point);
        point+= (poche.getTuiles().size() * poche.getTuiles().size()) ;
        System.out.println("point après ajout: " + point);
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
        boolean use = false;
        for (int i = 0; i < voisin.length; i++) {
            if (correspond[i]) {
                ModelPoche poche= comparaisonPoche(tuile, voisin[i], i);
                if (tuile.getIndexcouleur1()!=tuile.getIndexcouleur2()) {
                    if (tuile.getIndexcouleur1() == poche.getCouleur()) {
                        for (int j = 0; j<couleur1.size();j++) {
                            if (poche == couleur1.get(j)) {
                                use = true;
                            }
                        }
                        if (!use) {
                            points=enleverPoint(points, poche);
                            couleur1.add(poche);
                        }

                        use = false;
                        //poche.addTuile(tuile);
                        System.out.println("taille poche couleur1: " + poche.getTuiles().size());


                    }
                    if (tuile.getIndexcouleur2() == poche.getCouleur()) {
                        for (int j = 0; j<couleur2.size();j++) {
                            if (poche == couleur2.get(j)) {
                                use = true;
                            }
                        }
                        if (!use) {
                            points=enleverPoint(points, poche);
                            couleur2.add(poche);
                        }

                        use = false;
                        //poche.addTuile(tuile);
                        System.out.println("taille poche couleur2: " + poche.getTuiles().size());


                    }
                }
                else {
                    if (tuile.getIndexcouleur1() == poche.getCouleur()) {
                        for (int j = 0; j<couleur1.size();j++) {
                            if (poche == couleur1.get(j)) {
                                use = true;
                            }
                        }
                        if (!use) {
                            points=enleverPoint(points, poche);
                            couleur1.add(poche);
                            couleur2.add(poche);
                        }

                        use = false;

                    }
                }

            }
        }
        if (couleur1.isEmpty() && (tuile.getIndexcouleur1()!=tuile.getIndexcouleur2())) {
            ModelPoche nouvellePoche1 = new ModelPoche(tuile.getIndexcouleur1(), tuile);
            tuile.setPoche1(nouvellePoche1);
            points = ajoutPoint(points, nouvellePoche1);
        }
        if (couleur2.isEmpty() && (tuile.getIndexcouleur1()!=tuile.getIndexcouleur2())) {
            ModelPoche nouvellePoche2 = new ModelPoche(tuile.getIndexcouleur2(), tuile);
            tuile.setPoche2(nouvellePoche2);
            points = ajoutPoint(points,nouvellePoche2);
        }
        if (couleur1.isEmpty() && tuile.getIndexcouleur1()==tuile.getIndexcouleur2()) {
            ModelPoche nouvellePoche3 = new ModelPoche(tuile.getIndexcouleur1(), tuile);
            tuile.setPoche1(nouvellePoche3);
            tuile.setPoche2(nouvellePoche3);
            points = ajoutPoint(points,nouvellePoche3);

        }
        if (tuile.getIndexcouleur1()!= tuile.getIndexcouleur2()) {

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
            }

            if (couleur2.size() > 1) {
                ModelPoche poche = new ModelPoche(tuile.getIndexcouleur2(), tuile);
                tuile.setPoche2(poche);
                for (int i = 0; i < couleur2.size(); i++) {
                    ModelPoche changementPoche= couleur2.get(i);
                    for (int j = 0;j<changementPoche.getTuiles().size(); j++) {
                        if (changementPoche.getTuiles().get(j).getPoche()[0].getCouleur()==tuile.getIndexcouleur2()) {
                            changementPoche.getTuiles().get(j).setPoche1(poche);
                            poche.addTuile(changementPoche.getTuiles().get(j));
                        }
                        if (changementPoche.getTuiles().get(j).getPoche()[1].getCouleur()==tuile.getIndexcouleur2()) {
                            changementPoche.getTuiles().get(j).setPoche2(poche);
                            poche.addTuile(changementPoche.getTuiles().get(j));
                        }

                    }
                }
                points = ajoutPoint(points,tuile.getPoche()[1]);
            }
            if (couleur1.size()==1) {
                tuile.setPoche1(couleur1.get(0));
                couleur1.get(0).addTuile(tuile);
                points = ajoutPoint(points,couleur1.get(0));
            }
            if (couleur2.size()==1) {
                tuile.setPoche2(couleur2.get(0));
                couleur2.get(0).addTuile(tuile);
                points = ajoutPoint(points,couleur2.get(0));
            }
        }
        else {
            if (couleur1.size()>1) {


                ModelPoche poche= new ModelPoche(tuile.getIndexcouleur1(), tuile);
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
            }

            if (couleur1.size()==1) {
                tuile.setPoche1(couleur1.get(0));
                tuile.setPoche2(couleur2.get(0));
                couleur1.get(0).addTuile(tuile);
                points = ajoutPoint(points,couleur1.get(0));
            }
        }

        return points;

    }



}
