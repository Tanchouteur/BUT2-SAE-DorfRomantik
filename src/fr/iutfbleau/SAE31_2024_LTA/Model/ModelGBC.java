package fr.iutfbleau.SAE31_2024_LTA.Model;

import java.awt.*;

public class ModelGBC extends GridBagConstraints {

    public GridBagConstraints setCoordonner(int q, int r) {
        int gridx = q;
        int gridy = r;

        if (gridy % 2 != 0) {
            gridx += 1;
        }

        this.gridx = gridx;
        this.gridy = gridy;

        this.gridwidth = 3;
        this.gridheight = 2;

        this.anchor = GridBagConstraints.CENTER;
        this.fill = GridBagConstraints.BOTH;

        this.weightx = 0.1;
        this.weighty = 0.1;

        return this;
    }
}
