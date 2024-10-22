package fr.iutfbleau.SAE31_2024_LTA;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ControlerMain {
    public static void main(String[] args) {
        ModelPrincipale jeux = new ModelPrincipale();
        jeux.getVuePrincipale().addWindowListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                jeux.getVuePrincipale().updateSize();
            }
        });
    }
    //main test
}