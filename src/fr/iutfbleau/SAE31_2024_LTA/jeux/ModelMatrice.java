package fr.iutfbleau.SAE31_2024_LTA.jeux;

public class ModelMatrice {

    private final ModelTuile[][] listTuilesPosee;
    private final ModelJeux modelJeux;
    private final ModelListePoche modelListePoche;

    public ModelMatrice(ModelJeux modelJeux) {
        this.listTuilesPosee = new ModelTuile[101][101];
        this.modelJeux = modelJeux;
        this.modelListePoche = new ModelListePoche(modelJeux);

    }

    public void poseeTuile(int x,int y){
        ModelTuile tuile = modelJeux.getListTuiles().getFirst();
        this.listTuilesPosee[x][y] = tuile;
        this.listTuilesPosee[x][y].setCoordonner(x, y);


        boolean use1=false;
        boolean use2=false;
        if (this.getNordOuest(tuile) && (!use1 || !use2)) {
            System.out.println("ici1");
            if (tuile.getIndexcouleur1()==this.listTuilesPosee[x-1][y-1].getComposition()[3] ||
                    tuile.getIndexcouleur2()==this.listTuilesPosee[x-1][y-1].getComposition()[3]) {
                System.out.println("la1");
                int couleur = this.listTuilesPosee[x-1][y-1].getComposition()[3];
                for (int i=0;i< modelListePoche.getNbPoches();i++) {
                    if (couleur==modelListePoche.getListePoche()[i].getCouleur()) {
                        System.out.println("couleur1");
                        for (int j=0;j<modelListePoche.getListePoche()[i].getLength();j++) {
                            if (this.listTuilesPosee[x-1][y-1]==modelListePoche.getListePoche()[i].getTuiles()[j] && (!use1 || !use2)) {
                                if (tuile.getIndexcouleur1()==couleur && !use1) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use1 = true;
                                    if (tuile.getIndexcouleur2()==tuile.getIndexcouleur1()) {
                                        use2 = true;
                                    }
                                }
                                if (tuile.getIndexcouleur2()==couleur && !use2) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use2 = true;
                                }
                            }
                        }
                    }
                }
            }

        }
        if (this.getNord(tuile) && (!use1 || !use2)) {
            System.out.println("ici2");
            if (tuile.getIndexcouleur1()==this.listTuilesPosee[x-2][y].getComposition()[4] ||
                    tuile.getIndexcouleur2()==this.listTuilesPosee[x-2][y].getComposition()[4]) {
                System.out.println("la2");
                int couleur = this.listTuilesPosee[x-2][y].getComposition()[4];
                for (int i=0;i< modelListePoche.getNbPoches();i++) {
                    if (couleur==modelListePoche.getListePoche()[i].getCouleur()) {
                        System.out.println("couleur2");
                        for (int j=0;j<modelListePoche.getListePoche()[i].getLength();j++) {
                            if (this.listTuilesPosee[x-2][y]==modelListePoche.getListePoche()[i].getTuiles()[j] && (!use1 || !use2)) {
                                if (tuile.getIndexcouleur1()==couleur && !use1) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use1 = true;
                                    if (tuile.getIndexcouleur2()==tuile.getIndexcouleur1()) {
                                        use2 = true;
                                    }
                                }
                                if (tuile.getIndexcouleur2()==couleur && !use2) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use2 = true;
                                }
                            }
                        }
                    }
                }
            }

        }

        if (this.getNordEst(tuile) && (!use1 || !use2)) {
            System.out.println("ici3");
            if (tuile.getIndexcouleur1()==this.listTuilesPosee[x-1][y+1].getComposition()[5] ||
                    tuile.getIndexcouleur2()==this.listTuilesPosee[x-1][y+1].getComposition()[5]) {
                System.out.println("la3");
                int couleur = this.listTuilesPosee[x-1][y+1].getComposition()[5];
                for (int i=0;i< modelListePoche.getNbPoches();i++) {
                    if (couleur==modelListePoche.getListePoche()[i].getCouleur()) {
                        System.out.println("couleur3");
                        for (int j=0;j<modelListePoche.getListePoche()[i].getLength();j++) {
                            if (this.listTuilesPosee[x-1][y+1]==modelListePoche.getListePoche()[i].getTuiles()[j] && (!use1 || !use2)) {
                                System.out.println("poche3");
                                if (tuile.getIndexcouleur1()==couleur && !use1) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use1 = true;
                                    System.out.println("points3");
                                    if (tuile.getIndexcouleur2()==tuile.getIndexcouleur1()) {
                                        use2 = true;
                                    }
                                }
                                if (tuile.getIndexcouleur2()==couleur && !use2) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use2 = true;
                                    System.out.println("points3");
                                }
                            }
                        }
                    }
                }
            }

        }
        if (this.getSudEst(tuile) && (!use1 || !use2)) {
            System.out.println("ici4");
            if (tuile.getIndexcouleur1()==this.listTuilesPosee[x+1][y+1].getComposition()[0] ||
                    tuile.getIndexcouleur2()==this.listTuilesPosee[x+1][y+1].getComposition()[0]) {
                System.out.println("la4");
                int couleur = this.listTuilesPosee[x+1][y+1].getComposition()[0];
                for (int i=0;i< modelListePoche.getNbPoches();i++) {
                    if (couleur==modelListePoche.getListePoche()[i].getCouleur()) {
                        System.out.println("couleur4");
                        for (int j=0;j<modelListePoche.getListePoche()[i].getLength();j++) {
                            if (this.listTuilesPosee[x+1][y+1]==modelListePoche.getListePoche()[i].getTuiles()[j] && (!use1 || !use2)) {
                                if (tuile.getIndexcouleur1()==couleur && !use1) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use1 = true;
                                    if (tuile.getIndexcouleur2()==tuile.getIndexcouleur1()) {
                                        use2 = true;
                                    }
                                }
                                if (tuile.getIndexcouleur2()==couleur && !use2) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use2 = true;
                                }
                            }
                        }
                    }
                }
            }

        }
        if (this.getSud(tuile) && (!use1 || !use2)) {
            System.out.println("ici5");
            if (tuile.getIndexcouleur1()==this.listTuilesPosee[x+2][y].getComposition()[1] ||
                    tuile.getIndexcouleur2()==this.listTuilesPosee[x+2][y].getComposition()[1]) {
                System.out.println("la5");
                int couleur = this.listTuilesPosee[x+2][y].getComposition()[1];
                for (int i=0;i< modelListePoche.getNbPoches();i++) {
                    if (couleur==modelListePoche.getListePoche()[i].getCouleur()) {
                        System.out.println("couleur5");
                        for (int j=0;j<modelListePoche.getListePoche()[i].getLength();j++) {
                            if (this.listTuilesPosee[x+2][y]==modelListePoche.getListePoche()[i].getTuiles()[j] && (!use1 || !use2)) {
                                if (tuile.getIndexcouleur1()==couleur && !use1) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use1 = true;
                                    if (tuile.getIndexcouleur2()==tuile.getIndexcouleur1()) {
                                        use2 = true;
                                    }
                                }
                                if (tuile.getIndexcouleur2()==couleur && !use2) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use2 = true;
                                }
                            }
                        }
                    }
                }
            }

        }
        if (this.getSudOuest(tuile) && (!use1 || !use2)) {
            System.out.println("ici6");
            if (tuile.getIndexcouleur1()==this.listTuilesPosee[x+1][y-1].getComposition()[2] || tuile.getIndexcouleur2()==this.listTuilesPosee[x+1][y-1].getComposition()[2]) {
                System.out.println("la6");
                int couleur = this.listTuilesPosee[x+1][y-1].getComposition()[2];
                for (int i=0;i< modelListePoche.getNbPoches();i++) {
                    if (couleur==modelListePoche.getListePoche()[i].getCouleur()) {
                        System.out.println("couleur6");
                        for (int j=0;j<modelListePoche.getListePoche()[i].getLength();j++) {
                            if (this.listTuilesPosee[x+1][y-1]==modelListePoche.getListePoche()[i].getTuiles()[j] && (!use1 || !use2)) {
                                if (tuile.getIndexcouleur1()==couleur && !use1) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use1 = true;
                                    if (tuile.getIndexcouleur2()==tuile.getIndexcouleur1()) {
                                        use2 = true;
                                    }
                                }
                                if (tuile.getIndexcouleur2()==couleur && !use2) {
                                    modelListePoche.addTuilePoche(modelListePoche.getListePoche()[i], tuile);
                                    use2 = true;
                                }
                            }
                        }
                    }
                }
            }

        }else {
            if (tuile.getIndexcouleur1()==tuile.getIndexcouleur2() && !use1) {
                modelListePoche.addListePoche(tuile.getIndexcouleur1());
                System.out.println("rien1");
            }
            else {
                if (!use1) {
                    modelListePoche.addListePoche(tuile.getIndexcouleur1());
                    System.out.println("rien2");
                }
                if (!use2) {
                    modelListePoche.addListePoche(tuile.getIndexcouleur2());
                    System.out.println("rien3");
                }
            }
        }
        modelJeux.setScore(modelJeux.getScore()+1);
        modelJeux.getListTuiles().removeFirst();

        modelJeux.getVueJeux().updatePlayerInfo();
        modelJeux.getVueJeux().setDirty();
        //modelJeux.getVueJeux().repaint();
    }

    public void poseeButton(int x,int y, ModelTuile tuile){
        this.listTuilesPosee[x][y] = tuile;
        this.listTuilesPosee[x][y].setCoordonner(x, y);
    }

    public void deleteButton(int x,int y){
        this.listTuilesPosee[x][y] = null;
    }

    public boolean getNordOuest(ModelTuile tuile){
        int x = tuile.getX();
        int y = tuile.getY();
        if(this.listTuilesPosee[x-1][y-1] == null){
            return false;
        }
        return true;
    }

    public boolean getNord(ModelTuile tuile){
        int x = tuile.getX();
        int y = tuile.getY();
        if(this.listTuilesPosee[x-2][y] == null){
            return false;
        }
        return true;
    }

    public boolean getNordEst(ModelTuile tuile){
        int x = tuile.getX();
        int y = tuile.getY();
        if(this.listTuilesPosee[x-1][y+1] == null){
            return false;
        }
        return true;
    }

    public boolean getSudOuest(ModelTuile tuile){
        int x = tuile.getX();
        int y = tuile.getY();
        if(this.listTuilesPosee[x+1][y-1] == null){
            return false;
        }
        return true;
    }

    public boolean getSud(ModelTuile tuile){
        int x = tuile.getX();
        int y = tuile.getY();
        if(this.listTuilesPosee[x+2][y] == null){
            return false;
        }
        return true;
    }

    public boolean getSudEst(ModelTuile tuile){
        int x = tuile.getX();
        int y = tuile.getY();
        if(this.listTuilesPosee[x+1][y+1] == null){
            return false;
        }
        return true;
    }

    public ModelTuile[][] getListTuilesPosee() {
        return this.listTuilesPosee;
    }
}
