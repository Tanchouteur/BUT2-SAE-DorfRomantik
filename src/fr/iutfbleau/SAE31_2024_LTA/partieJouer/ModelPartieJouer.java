package fr.iutfbleau.SAE31_2024_LTA.partieJouer;

import fr.iutfbleau.SAE31_2024_LTA.Bdd.BddPartieJouer;
import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import java.util.List;
import java.util.stream.Collectors;

public class ModelPartieJouer {
    private final ModelPrincipale modelPrincipale;

    private VuePartieJouer vuePartieJouer;

    public ModelPartieJouer(ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
        createVue();
    }

    public List<BddPartieJouer> getAllParties() {
        if (modelPrincipale.getBdd().updateBdd()) {
            List<BddPartieJouer> listParties = modelPrincipale.getBdd().getAllPartieJouer().stream()
                    .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                    .collect(Collectors.toList());
            return listParties;
        }
        return null;
    }

    public List<BddPartieJouer> getFilteredParties(String playerName) {
        if (modelPrincipale.getBdd().updateBdd()) {
            return modelPrincipale.getBdd().getAllPartieJouer().stream()
                    .filter(partie -> partie.getPlayerName().equalsIgnoreCase(playerName))
                    .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    private void createVue(){
        vuePartieJouer = new VuePartieJouer(modelPrincipale, this);
        modelPrincipale.getVuePrincipale().getPrincipaleLayeredPane().getMainPanel().add(vuePartieJouer, "partieJouer");
    }

    public VuePartieJouer getVuePartieJouer() {
        return vuePartieJouer;
    }
}
