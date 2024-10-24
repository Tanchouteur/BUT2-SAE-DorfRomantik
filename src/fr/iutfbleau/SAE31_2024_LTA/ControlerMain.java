package fr.iutfbleau.SAE31_2024_LTA;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ControlerMain {

    private static volatile boolean isLoading = true;
    private static ModelPrincipale modelPrincipale;

    public static void main(String[] args) {
        LoadingFrame loadingFrame = new LoadingFrame();
        new Thread(() -> {
            modelPrincipale = new ModelPrincipale();
            isLoading = false;
        }).start(); // On lance le chargement du modèle en parallèle

        while (isLoading) {
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        loadingFrame.dispose();
        modelPrincipale.getVuePrincipale().addWindowListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                modelPrincipale.getVuePrincipale().updateSize();
            }
        });
    }
}