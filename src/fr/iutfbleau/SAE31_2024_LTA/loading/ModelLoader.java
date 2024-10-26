package fr.iutfbleau.SAE31_2024_LTA.loading;

import fr.iutfbleau.SAE31_2024_LTA.ControlerMain;
import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

/**
 * The class ModelLoader
 */
public class ModelLoader implements Runnable {
    @Override
    public void run() {
        ControlerMain.setModelPrincipale(new ModelPrincipale());
        ControlerMain.setIsLoading(false);
    }
}

