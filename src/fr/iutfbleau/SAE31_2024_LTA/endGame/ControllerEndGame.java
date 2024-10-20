package fr.iutfbleau.SAE31_2024_LTA.endGame;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

public class ControllerEndGame {
    ModelPrincipale modelPrincipale;
    public ControllerEndGame(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
    }

    public void end() {
        modelPrincipale.getVuePrincipale().getVueScoreScreen().addElements();
        modelPrincipale.getVuePrincipale().getCardLayout().show(modelPrincipale.getVuePrincipale().getFramePane(), "score");
    }
}
