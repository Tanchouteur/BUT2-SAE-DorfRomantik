package fr.iutfbleau.SAE31_2024_LTA.jeux;

import javax.swing.*;
import java.awt.*;

public class VueTuile extends JComponent {

    ModelTuile modelTuile;

    Polygon polygon;

    private final int[] xPoints;
    private final int[] yPoints;

    private int centerX, centerY;

    VueTuile(ModelTuile modelTuile, int centerX, int centerY, int radius) {
        this.modelTuile = modelTuile;

        this.xPoints = new int[6];
        this.yPoints = new int[6];

        polygon = createHexagon(radius, radius, radius);

        this.setBounds(centerX-radius, centerY-radius, radius*2, radius*2);
    }

    private Polygon createHexagon(int centerX, int centerY, int radius) {

        for (int i = 0; i < 6; i++) {
            xPoints[i] = (int) (centerX + radius * Math.cos(i * Math.PI / 3));
            yPoints[i] = (int) (centerY + radius * Math.sin(i * Math.PI / 3));
        }

        this.centerX = (xPoints[0] + xPoints[1] + xPoints[2] +
                xPoints[3] + xPoints[4] + xPoints[5]) / 6;

        this.centerY = (yPoints[0] + yPoints[1] + yPoints[2] +
                yPoints[3] + yPoints[4] + yPoints[5]) / 6;

        return new Polygon(xPoints, yPoints, 6);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color[] composition = modelTuile.getComposition();
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
