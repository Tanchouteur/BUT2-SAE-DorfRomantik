package fr.iutfbleau.SAE31_2024_LTA.partieJouer;

import fr.iutfbleau.SAE31_2024_LTA.Bdd.BddPartieJouer;
import fr.iutfbleau.SAE31_2024_LTA.Bdd.PlayerNameFilter;
import fr.iutfbleau.SAE31_2024_LTA.Bdd.ScoreComparator;
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
                    .sorted(new ScoreComparator())
                    .collect(Collectors.toList());
            return listParties;
        }
        return null;
    }

    public List<BddPartieJouer> getFilteredParties(String playerName) {
        if (modelPrincipale.getBdd().updateBdd()) {
            return modelPrincipale.getBdd().getAllPartieJouer().stream()
                    .filter(new PlayerNameFilter(playerName))
                    .sorted(new ScoreComparator())
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
