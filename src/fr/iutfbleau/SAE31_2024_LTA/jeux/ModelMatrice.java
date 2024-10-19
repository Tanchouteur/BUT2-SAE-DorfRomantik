package fr.iutfbleau.SAE31_2024_LTA.jeux;

public class ModelMatrice {
    private final ModelTuile[][] listTuilesPosee;
    private ModelJeux modelJeux;
    public ModelMatrice(ModelJeux modelJeux) {
        this.listTuilesPosee = new ModelTuile[101][101];
        this.modelJeux = modelJeux;
    }

    public void poseeTuile(int x,int y){
        this.listTuilesPosee[x][y] = modelJeux.getListTuiles().getFirst();
        this.listTuilesPosee[x][y].setCoordonner(x, y);
        modelJeux.getListTuiles().removeFirst();
        modelJeux.getVueJeux().repaint();
    }

    public void poseeButton(int x,int y, ModelTuile tuile){
        this.listTuilesPosee[x][y] = tuile;
        this.listTuilesPosee[x][y].setCoordonner(x, y);
        modelJeux.getVueJeux().repaint();
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
