package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import javax.swing.*;
import java.awt.*;

public class VueJeux extends JLayeredPane {

    private Graphics2D g2d;

    private final ModelJeux modelJeux;
    private int offsetX = 0;
    private int offsetY = 0;

    // Taille des tuiles
    private final int tuileSize = 50;

    //decalage vertical entre deux lignes de tuiles
    private final int hexHeight =  tuileSize-7;

    private ModelTuile[][] listeTuilesPosee;

    private ModelTuile tuilePreview;



    public VueJeux(ModelPrincipale modelPrincipale, ModelJeux modelJeux) {
        setLayout(null);
        new Controller2D(this);
        this.modelJeux = modelJeux;

    }

    @Override
    protected void paintComponent(Graphics g) {
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

                int x = totalOffsetX + col * (3 * tuileSize / 2);
                int y = totalOffsetY + row * hexHeight;

                if (tuile != null && tuile.getVueTuile() == null) {

                    if (!tuile.isButton()){
                        tuile.createVueTuile(x, y, tuileSize);
                        this.add(tuile.getVueTuile(), Integer.valueOf(0));
                    }else {
                        tuile.createVueTuile(x, y, tuileSize/2);

                        this.add(tuile.getVueTuile(), Integer.valueOf(0));
                        tuile.getVueTuile().addMouseListener(new ControllerPoseTuile(modelJeux, tuile));
                    }
                }else if (tuile != null && tuile.getVueTuile() != null) {

                    if (!tuile.isButton()){

                        tuile.getVueTuile().updateTuile(x, y, tuileSize);

                    }else {
                        tuile.getVueTuile().updateTuile(x, y, tuileSize/2);
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

    public void updatePreviewTuile(){

        if (tuilePreview != null){
            remove(tuilePreview.getVueTuile());
        }
        tuilePreview = new ModelTuile(modelJeux.getListTuiles().getFirst().getSeed());
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int[] xPoint = new int[6];
        int[] yPoint = new int[6];

        for (int i = 0; i < 6; i++) {
            xPoint[i] = (int) (centerX + 60 * Math.cos(i * Math.PI / 3));
            yPoint[i] = (int) (centerY + 60 * Math.sin(i * Math.PI / 3));
        }

        Polygon polygon = new Polygon(xPoint, yPoint, 6);

        Color[] composition = tuilePreview.getComposition();
        for (int i = 0; i < 6; i++) {
            int[] xPoints = {
                    polygon.xpoints[i],
                    polygon.xpoints[(i + 1) % 6],
                    centerX
            };
            int[] yPoints = {
                    polygon.ypoints[i],
                    polygon.ypoints[(i + 1) % 6],
                    centerY
            };

            g2d.setColor(composition[i]);
            g2d.fillPolygon(xPoints, yPoints, 3);
        }

        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(polygon);

    }
}
