package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import javax.swing.*;
import java.awt.*;

public class VueJeux extends JPanel {

    private Graphics2D g2d;
    private final ModelJeux modelJeux;
    private int offsetX = 0;
    private int offsetY = 0;

    // Taille des tuiles (par exemple, la longueur d'un côté)
    private final int tuileSize = 50;
    // Décalage vertical entre deux tuiles
    private final int hexHeight = (int) (Math.sqrt(3) * tuileSize / 2);

    public VueJeux(ModelPrincipale modelPrincipale, ModelJeux modelJeux) {
        setLayout(null);
        new Controller2D(this);

        this.modelJeux = modelJeux;


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        ModelTuile[][] listeTuilesPosee = modelJeux.getModelMatrice().getListTuilesPosee();

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int tuileCentreRow = 50;
        int tuileCentreCol = 50;

        int initialOffsetX = centerX - (tuileCentreCol * (3 * tuileSize / 2));
        int initialOffsetY = centerY - (tuileCentreRow * hexHeight);

        int totalOffsetX = initialOffsetX + offsetX;
        int totalOffsetY = initialOffsetY + offsetY;

        for (int row = 0; row < listeTuilesPosee.length; row++) {
            for (int col = 0; col < listeTuilesPosee[row].length; col++) {

                ModelTuile tuile = listeTuilesPosee[row][col];

                if (tuile != null) { // Si la tuile existe

                    int x = totalOffsetX + col * (3 * tuileSize / 2);
                    int y = totalOffsetY + row * hexHeight;

                    //decaler les lignes impaires
                    if (row % 2 == 1) {
                        x += tuileSize * 3 / 4;
                    }

                    //definir les coordonnées pour la tuile
                    tuile.setCoordinates(x, y, tuileSize);

                    drawTuile(g2d, tuile);
                }
            }
        }
    }


    private void drawTuile(Graphics2D g2d, ModelTuile tuile) {
        Polygon tuilePoly = tuile.getPolygon();
        Color[] composition = tuile.getComposition();

        int centerX = (tuilePoly.xpoints[0] + tuilePoly.xpoints[1] + tuilePoly.xpoints[2] + tuilePoly.xpoints[3] + tuilePoly.xpoints[4] + tuilePoly.xpoints[5]) / 6;
        int centerY = (tuilePoly.ypoints[0] + tuilePoly.ypoints[1] + tuilePoly.ypoints[2] + tuilePoly.ypoints[3] + tuilePoly.ypoints[4] + tuilePoly.ypoints[5]) / 6;

        for (int i = 0; i < 6; i++) {
            int[] xPoints = {
                    tuilePoly.xpoints[i],
                    tuilePoly.xpoints[(i + 1) % 6],
                    centerX
            };
            int[] yPoints = {
                    tuilePoly.ypoints[i],
                    tuilePoly.ypoints[(i + 1) % 6],
                    centerY
            };

            g2d.setColor(composition[i]);
            g2d.fillPolygon(xPoints, yPoints, 3);
        }

        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(tuilePoly);
    }

    public void updateOffsets(int deltaX, int deltaY) {
        offsetX += deltaX;
        offsetY += deltaY;
        repaint();
    }
}
