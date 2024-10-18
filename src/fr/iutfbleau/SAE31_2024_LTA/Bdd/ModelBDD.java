package fr.iutfbleau.SAE31_2024_LTA.Bdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelBDD {

    private Connection db;
    private List<BddListeTuiles> listeTuiles; //chaque object des listes contients une ligne de la table
    private List<BddPartieJouer> partieJouees;

    public ModelBDD() {
        try {
            // Connexion à la base de données
            this.db = DriverManager.getConnection(
                    "jdbc:mariadb://dwarves.iut-fbleau.fr:3306/tanchou",
                    "tanchou", "MotdepasseUpec77**");


            this.listeTuiles = getAllListe();
            this.partieJouees = getAllPartieJouer();

        } catch (SQLException e) {
            System.err.println("Erreur de connexion BDD - " + e.getMessage());
        }
    }

    private List<BddListeTuiles> getAllListe() {
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

    private List<BddPartieJouer> getAllPartieJouer() {
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

    public BddListeTuiles getListeTuileById(int id) throws SQLException {
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

    public List<BddListeTuiles> getListeTuiles() {
        return this.listeTuiles;
    }


    public List<BddPartieJouer> getPartieJouer() {
        return this.partieJouees;
    }

    public Integer getBestScoreSeed(int seed) {
        for (BddListeTuiles tuile : listeTuiles) {
            if (tuile.getSeed() == seed) {
                return tuile.getBestScore();
            }
        }
        return null;
    }
}