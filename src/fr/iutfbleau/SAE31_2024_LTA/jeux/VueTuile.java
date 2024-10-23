package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class VueTuile extends JComponent {

    private final ModelTuile modelTuile;

    private Polygon polygon;

    private final int[] xPoints;
    private final int[] yPoints;

    private int centerX, centerY;

    VueTuile(ModelTuile modelTuile, int centerX, int centerY, int radius) {
        this.modelTuile = modelTuile;

        this.xPoints = new int[6];
        this.yPoints = new int[6];

        updateTuile( centerX, centerY, radius);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public ModelTuile getModelTuile() {
        return modelTuile;
    }

    private Polygon createHexagon(int radius) {

        for (int i = 0; i < 6; i++) {
            xPoints[i] = (int) (radius + radius * Math.cos(i * Math.PI / 3));
            yPoints[i] = (int) (radius + radius * Math.sin(i * Math.PI / 3));
        }

        this.centerX = (xPoints[0] + xPoints[1] + xPoints[2] +
                xPoints[3] + xPoints[4] + xPoints[5]) / 6;

        this.centerY = (yPoints[0] + yPoints[1] + yPoints[2] +
                yPoints[3] + yPoints[4] + yPoints[5]) / 6;

        return new Polygon(xPoints, yPoints, 6);
    }

    public void updateTuile(int centerX, int centerY, int radius) {
        polygon = createHexagon(radius);
        this.setBounds(centerX-radius, centerY-radius, radius*2, radius*2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (modelTuile.isSuivante()){
            g2d.scale(1, 0.5);
            g2d.setColor(new Color(0x333333));
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[1], polygon.ypoints[1]);
            g2d.drawLine(polygon.xpoints[1], polygon.ypoints[1], polygon.xpoints[2], polygon.ypoints[2]);
            g2d.drawLine(polygon.xpoints[2], polygon.ypoints[2], polygon.xpoints[3], polygon.ypoints[3]);
        }

        if (!modelTuile.isButton()) {
            int[] composition = modelTuile.getComposition();
            Color[] colorPalette = new Color[6];

            if (!modelTuile.isPreview()) {
                colorPalette[0] = new Color(30, 142, 216);
                colorPalette[1] = new Color(119, 119, 119);
                colorPalette[2] = new Color(235, 222, 33);
                colorPalette[3] = new Color(119, 198, 119);
                colorPalette[4] = new Color(20, 119, 69);

                //dessiné les tuiles avec leurs compositions
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

                    g2d.setColor(colorPalette[composition[i]]);
                    g2d.fillPolygon(xPoints, yPoints, 3);
                }

                g2d.setColor(new Color(64, 64, 64, 226));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawPolygon(polygon);
            } else {
                g2d.scale(1, 0.5); //effet 3D
                g2d.setColor(new Color(0x333333));
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[1], polygon.ypoints[1]);
                g2d.drawLine(polygon.xpoints[1], polygon.ypoints[1], polygon.xpoints[2], polygon.ypoints[2]);
                g2d.drawLine(polygon.xpoints[2], polygon.ypoints[2], polygon.xpoints[3], polygon.ypoints[3]);

                g2d.setColor(new Color(124, 124, 124));
                g2d.fillPolygon(polygon);
            }

            g2d.setColor(new Color(51, 51, 51));
            g2d.drawPolygon(polygon);
            if (modelTuile.isOnBoard()) {
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[1], polygon.ypoints[1]);
                g2d.drawLine(polygon.xpoints[1], polygon.ypoints[1], polygon.xpoints[2], polygon.ypoints[2]);
                g2d.drawLine(polygon.xpoints[2], polygon.ypoints[2], polygon.xpoints[3], polygon.ypoints[3]);
            }
        } else {
                        g2d.setColor(new Color(151, 151, 151));
            g2d.fillPolygon(polygon);
        }
    }
}
