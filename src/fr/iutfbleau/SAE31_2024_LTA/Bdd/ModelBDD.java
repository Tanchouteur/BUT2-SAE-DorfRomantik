package fr.iutfbleau.SAE31_2024_LTA.Bdd;

import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.popup.PopupBd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelBDD {

    private Connection db;
    private List<BddListeTuiles> listeTuiles;
    private List<BddPartieJouer> partieJouees;
    private final VuePrincipale vuePrincipale;
    private boolean connected = false;
    private boolean inConnexion = false;
    private boolean gameSaved = false;

    public ModelBDD(VuePrincipale vuePrincipale) {
        this.vuePrincipale = vuePrincipale;
        connexionBdd();
    }

    public void connexionBdd(){
        new Thread(() -> {
            String message = "Connexion bd...";
            PopupBd popupBd = null;
            try {// Connexion à la base de données

                inConnexion = true;
                popupBd = new PopupBd(message,true);

                this.db = DriverManager.getConnection(
                    "jdbc:mariadb://dwarves.iut-fbleau.fr:3306/tanchou",
                    "tanchou", "MotdepasseUpec77**");

            } catch (SQLException e) {
                System.err.println("Erreur de connexion BDD");
            }
            if (db != null) {
                connected = true;
            }
            vuePrincipale.getPrincipaleLayeredPane().remove(popupBd);
            inConnexion = false;

            if (connected) {
                message="Connection à la base de donnée réussi";
            }else {
                message="Connection à la base de donnée échouer";
            }

            popupBd = new PopupBd(message,connected);
            vuePrincipale.getPrincipaleLayeredPane().add(popupBd,Integer.valueOf(3));

            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {

            }
            vuePrincipale.getPrincipaleLayeredPane().remove(popupBd);
            vuePrincipale.getPrincipaleLayeredPane().repaint();
        }).start();
    }

    public boolean updateBdd(){
        if (!connected) {
            connexionBdd();
        }
        if (db!=null) {
            if (inConnexion){
                while (inConnexion){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }

    public List<BddListeTuiles> getAllListe() {
        if (db!=null) {
            List<BddListeTuiles> seeds = new ArrayList<>();
            String query = "SELECT id, seed, BestScore FROM ListeTuiles";

            try (Statement stmt = db.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int seed = rs.getInt("seed");
                    Integer bestScore = rs.getObject("BestScore", Integer.class);

                    seeds.add(new BddListeTuiles(id, seed, bestScore));
                }

            } catch (SQLException e) {
                System.err.println("Erreur de preparation SQL methode getAllListe() - " + e.getMessage());
            }
            return seeds;
        }
        return null;
    }

    public List<BddPartieJouer> getAllPartieJouer() {
        if (db!=null) {
            List<BddPartieJouer> parties = new ArrayList<>();
            String query = "SELECT id, PlayerName, Score, ListeTuile FROM PartieJouer";

            try (Statement stmt = db.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String playerName = rs.getString("PlayerName");
                    int score = rs.getInt("Score");
                    int listeTuileId = rs.getInt("ListeTuile");

                    BddListeTuiles listeTuile = getListeTuileById(listeTuileId);//comme sa on refait la FK dans la table

                    parties.add(new BddPartieJouer(id, playerName, score, listeTuile));
                }

            } catch (SQLException e) {
                System.err.println("Erreur de preparation SQL methode getAllPartieJouer() - " + e.getMessage());
            }
            return parties;
        }
        return null;
    }

    public BddListeTuiles getListeTuileById(int id) throws SQLException {
        if (db!=null) {
            if (inConnexion){
                while (inConnexion){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            PreparedStatement ps = db.prepareStatement("SELECT * FROM ListeTuiles WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            BddListeTuiles listeTuile = null;
            if (rs.next()) {
                int seed = rs.getInt("seed");
                int bestScore = rs.getInt("BestScore");
                listeTuile = new BddListeTuiles(id, seed, bestScore);
            }

            rs.close();
            ps.close();

            return listeTuile;
        }
        return null;
    }

    public Integer getBestScoreSeed(int seed) {
        if (db == null) {
            return null;
        }
        List<BddListeTuiles> listeTuiles = getAllListe();
        for (BddListeTuiles tuile : listeTuiles) {

            if (tuile.getSeed() == seed) {
                return tuile.getBestScore();
            }
        }
        return null;
    }

    public boolean saveGame(String playerName, int score, int listeTuileId) throws SQLException {
        if (gameSaved) {
            return true;
        }
        if (db == null) {
            return false;
        }

        PreparedStatement ps = db.prepareStatement("INSERT INTO tanchou.PartieJouer (PlayerName, Score, ListeTuile) VALUES (?, ?, ?)");
        ps.setString(1, playerName);
        ps.setInt(2, score);
        ps.setInt(3, listeTuileId);
        ps.executeQuery();
        gameSaved = true;
        return true;
    }

    public boolean isInConnexion() {
        return inConnexion;
    }
}
