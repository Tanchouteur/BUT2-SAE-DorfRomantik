package fr.iutfbleau.SAE31_2024_LTA.menu;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

public class ModelMenu {

    private final ModelPrincipale modelPrincipale;
    private VueMenu vueMenu;

    public ModelMenu(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;

        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getModelMediaLoader().getMenuMusicClip(), true);

        createVueMenu();
    }

    public VueMenu getVueMenu() {
        return vueMenu;
    }

    public void createVueMenu() {
        vueMenu = new VueMenu(modelPrincipale);
        modelPrincipale.getVuePrincipale().getPrincipaleLayeredPane().getMainPanel().add(vueMenu, "menu");
        modelPrincipale.getVuePrincipale().getPrincipaleLayeredPane().getMainCardLayout().show(modelPrincipale.getVuePrincipale().getPrincipaleLayeredPane().getMainPanel(), "menu");
        modelPrincipale.createPartieJouer();
        if (modelPrincipale.getConfigManager().isTuto()){
            modelPrincipale.getControllerPopup().showTutoDialog();
        }
    }
}
