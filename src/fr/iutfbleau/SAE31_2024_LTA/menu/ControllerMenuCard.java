package fr.iutfbleau.SAE31_2024_LTA.menu;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenuCard implements ActionListener {
    private final ModelPrincipale modelPrincipale;

    public ControllerMenuCard(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
    }

    public void goMenu(){
        VuePrincipale vuePrincipale = modelPrincipale.getVuePrincipale();

        modelPrincipale.getMediaPlayerManager().stopClip(modelPrincipale.getModelMediaLoader().getGameMusicClips());
        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getModelMediaLoader().getClicAudioClip(), false);
        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getModelMediaLoader().getMenuMusicClip(), true);

        vuePrincipale.getPrincipaleLayeredPane().getMainPanel().getCardLayout().show(vuePrincipale.getPrincipaleLayeredPane().getMainPanel(), "menu");

        vuePrincipale.setTitle("DorfJavatik - Menu");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        VuePrincipale vuePrincipale = modelPrincipale.getVuePrincipale();

        for (int i = 0; i < modelPrincipale.getModelMediaLoader().getGameMusicClips().size(); i++){
            modelPrincipale.getMediaPlayerManager().stopClip(modelPrincipale.getModelMediaLoader().getGameMusicClips().get(i));
        }

        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getModelMediaLoader().getClicAudioClip(), false);
        modelPrincipale.getMediaPlayerManager().startClip(modelPrincipale.getModelMediaLoader().getMenuMusicClip(), true);

        vuePrincipale.getPrincipaleLayeredPane().getMainPanel().getCardLayout().show(vuePrincipale.getPrincipaleLayeredPane().getMainPanel(), "menu");

        vuePrincipale.setTitle("DorfJavatik - Menu");
        vuePrincipale.getModelPrincipale().getBdd().setGameSaved(false);
    }
}
