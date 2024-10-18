package fr.iutfbleau.SAE31_2024_LTA.jeux;

public class ModelMatrice {
    private final ModelTuile[][] listTuilesPosee;
    
    public ModelMatrice(){
        this.listTuilesPosee = new ModelTuile[101][101];
    }

    public void poseeTuile(int x,int y, ModelTuile tuile){
        this.listTuilesPosee[x][y] = tuile;
        this.listTuilesPosee[x][y].setCoordonner(x, y);
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
        if(this.listTuilesPosee[x][y-2] == null){
            return false;
        }
        return true;
    }

    public boolean getNordEst(ModelTuile tuile){
        int x = tuile.getX();
        int y = tuile.getY();
        if(this.listTuilesPosee[x+1][y-1] == null){
            return false;
        }
        return true;
    }

    public boolean getSudOuest(ModelTuile tuile){
        int x = tuile.getX();
        int y = tuile.getY();
        if(this.listTuilesPosee[x-1][y+1] == null){
            return false;
        }
        return true;
    }

    public boolean getSud(ModelTuile tuile){
        int x = tuile.getX();
        int y = tuile.getY();
        if(this.listTuilesPosee[x][y+2] == null){
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
