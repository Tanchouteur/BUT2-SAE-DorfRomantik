package fr.iutfbleau.SAE31_2024_LTA.loading;

import fr.iutfbleau.SAE31_2024_LTA.ControlerMain;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowStateHandler extends WindowAdapter {
    @Override
    public void windowStateChanged(WindowEvent e) {
        ControlerMain.getModelPrincipale().getVuePrincipale().updateSize();
    }
}
