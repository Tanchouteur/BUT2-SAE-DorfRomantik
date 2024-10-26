package fr.iutfbleau.SAE31_2024_LTA;

import fr.iutfbleau.SAE31_2024_LTA.loading.LoadingFrame;
import fr.iutfbleau.SAE31_2024_LTA.loading.ModelLoader;
import fr.iutfbleau.SAE31_2024_LTA.loading.WindowStateHandler;

public class ControlerMain {

    private static volatile boolean isLoading = true;
    private static ModelPrincipale modelPrincipale;

    public static void main(String[] args) {
        LoadingFrame loadingFrame = new LoadingFrame();
        new Thread(new ModelLoader()).start();

        while (isLoading) {
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        loadingFrame.dispose();
        modelPrincipale.getVuePrincipale().addWindowListener(new WindowStateHandler());
    }

    public static synchronized void setModelPrincipale(ModelPrincipale model) {
        modelPrincipale = model;
    }

    public static synchronized ModelPrincipale getModelPrincipale() {
        return modelPrincipale;
    }

    public static synchronized void setIsLoading(boolean loading) {
        isLoading = loading;
    }

    public static synchronized boolean isIsLoading() {
        return isLoading;
    }
}
