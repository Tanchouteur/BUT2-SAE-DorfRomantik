package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

public class VueJeux extends JPanel {

    private Graphics2D g2d;
    private final ModelJeux modelJeux;
    private int offsetX = 0;
    private int offsetY = 0;

    // Taille des tuiles
    private final int tuileSize = 50;

    //decalage vertical entre deux lignes de tuiles
    private final int hexHeight =  tuileSize-7;

    private ModelTuile[][] listeTuilesPosee;

    public VueJeux(ModelPrincipale modelPrincipale, ModelJeux modelJeux) {
        setLayout(null);
        new Controller2D(this);

        this.modelJeux = modelJeux;
    }

    @Override
    protected void paintComponent(Graphics g) {

        removeAll();

        super.paintComponent(g);
        g2d = (Graphics2D) g;
        listeTuilesPosee = modelJeux.getModelMatrice().getListTuilesPosee();

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int tuileCentreRow = 50;
        int tuileCentreCol = 50;

        int initialOffsetX = centerX - (3 * tuileSize / 2) * tuileCentreCol;
        int initialOffsetY = centerY - hexHeight * tuileCentreRow;

        int totalOffsetX = initialOffsetX + offsetX;
        int totalOffsetY = initialOffsetY + offsetY;

        for (int row = 0; row < listeTuilesPosee.length; row++) {
            for (int col = 0; col < listeTuilesPosee[row].length; col++) {

                ModelTuile tuile = listeTuilesPosee[row][col];

                if (tuile != null && tuile.getVueTuile() == null) {

                    int x = totalOffsetX + col * (3 * tuileSize / 2);
                    int y = totalOffsetY + row * hexHeight;

                    if (!tuile.isButton()){
                        tuile.createVueTuile(x, y, tuileSize);

                        this.add(tuile.getVueTuile());
                    }else {
                        tuile.createVueTuile(x, y, tuileSize/2);

                        this.add(tuile.getVueTuile());
                        tuile.getVueTuile().addMouseListener(new ControllerPoseTuile(modelJeux, tuile));
                    }
                }else if (tuile != null && tuile.getVueTuile() != null) {
                    int x = totalOffsetX + col * (3 * tuileSize / 2);
                    int y = totalOffsetY + row * hexHeight;

                    if (!tuile.isButton()){

                        tuile.createVueTuile(x, y, tuileSize);

                        this.add(tuile.getVueTuile());

                    }else {
                        tuile.createVueTuile(x, y, tuileSize/2);

                        this.add(tuile.getVueTuile());
                        tuile.getVueTuile().addMouseListener(new ControllerPoseTuile(modelJeux, tuile));
                    }
                }
            }
        }
    }

    public void updateOffsets(int deltaX, int deltaY) {
        offsetX += deltaX;
        offsetY += deltaY;
        repaint();
    }


}
