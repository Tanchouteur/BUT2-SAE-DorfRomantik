package fr.iutfbleau.SAE31_2024_LTA.Bdd;

import fr.iutfbleau.SAE31_2024_LTA.popup.PopupBd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BddConnectionTask implements Runnable {
    private final ModelBDD modelBDD;


    BddConnectionTask(ModelBDD modelBDD) {
        this.modelBDD = modelBDD;
    }

    @Override
    public void run() {
        String message = "Connexion bd...";
        PopupBd popupBd = null;
        Connection db = null;
        try {
            modelBDD.setInConnexion(true);
            popupBd = new PopupBd(message, true);

            db = DriverManager.getConnection(
                    "jdbc:mariadb://dwarves.iut-fbleau.fr:3306/tanchou",
                    "tanchou", "MotdepasseUpec77**");

        } catch (SQLException e) {
            modelBDD.setConnected(false); //connected = false;
            System.err.println("Erreur de connexion BDD");
        }

        modelBDD.setDb(db);

        if (db != null) {
            modelBDD.setConnected(true);
        }
        modelBDD.removePopup(popupBd);
        modelBDD.setInConnexion(false);
        if (modelBDD.isConnected()) {
            message = "Connection à la base de donnée réussi";
        } else {
            message = "Connection à la base de donnée échouer";
        }

        popupBd = new PopupBd(message, modelBDD.isConnected());
        modelBDD.addPopup(popupBd);


        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
        modelBDD.removePopup(popupBd);
    }
}