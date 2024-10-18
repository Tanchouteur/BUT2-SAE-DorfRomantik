package fr.iutfbleau.SAE31_2024_LTA.Model;

import fr.iutfbleau.SAE31_2024_LTA.Model.Bdd.BddPartieJouer;
import fr.iutfbleau.SAE31_2024_LTA.Model.Bdd.ModelBDD;
import fr.iutfbleau.SAE31_2024_LTA.Vue.VuePartieJouer;

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
        return modelPrincipale.getBdd().getPartieJouer().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                .collect(Collectors.toList());
    }

    public List<BddPartieJouer> getFilteredParties(String playerName) {
        return modelPrincipale.getBdd().getPartieJouer().stream()
                .filter(partie -> partie.getPlayerName().equalsIgnoreCase(playerName))
                .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                .collect(Collectors.toList());
    }

    private void createVue(){
        vuePartieJouer = new VuePartieJouer(modelPrincipale);
        modelPrincipale.getVuePrincipale().add(vuePartieJouer, "partieJouer");
    }

    public VuePartieJouer getVuePartieJouer() {
        return vuePartieJouer;
    }
}
