package fr.iutfbleau.SAE31_2024_LTA.Model;

import fr.iutfbleau.SAE31_2024_LTA.Vue.VueMenu;

public class ModelMenu {

    private final VueMenu vueMenu;

    ModelMenu(ModelPrincipale modelPrincipale) {
        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getMediaPlayerManager().getMenuMusicClip(), true);
        vueMenu = new VueMenu(modelPrincipale);
        modelPrincipale.getVuePrincipale().add(vueMenu, "menu");
    }

    public VueMenu getVueMenu() {
        return vueMenu;
    }
}
