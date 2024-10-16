package fr.iutfbleau.SAE31_2024_LTA.Model;

import fr.iutfbleau.SAE31_2024_LTA.Model.Bdd.BddPartieJouer;
import fr.iutfbleau.SAE31_2024_LTA.Model.Bdd.ModelBDD;

import java.util.List;
import java.util.stream.Collectors;

public class ModelPartieJouer {
    private final ModelBDD modelBDD;

    public ModelPartieJouer(ModelBDD modelBDD) {
        this.modelBDD = modelBDD;
    }

    public List<BddPartieJouer> getAllParties() {
        return modelBDD.getPartieJouer().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                .collect(Collectors.toList());
    }

    public List<BddPartieJouer> getFilteredParties(String playerName) {
        return modelBDD.getPartieJouer().stream()
                .filter(partie -> partie.getPlayerName().equalsIgnoreCase(playerName))
                .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                .collect(Collectors.toList());
    }
}
