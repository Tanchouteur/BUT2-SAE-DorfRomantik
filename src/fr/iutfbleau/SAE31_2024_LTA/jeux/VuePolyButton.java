package fr.iutfbleau.SAE31_2024_LTA.jeux;

import javax.swing.*;
import java.awt.*;

public class VuePolyButton extends JComponent {
    int centerX, centerY, radius;
    Polygon polygon;

    int x;
    int y;

    public VuePolyButton(int centerX, int centerY, int radius, int x, int y) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.x = x;
        this.y = y;

        polygon = createHexagon(radius, radius, radius);

        this.setBounds(centerX-radius, centerY-radius, radius*2, radius*2);
    }

    private Polygon createHexagon(int centerX, int centerY, int radius) {
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        for (int i = 0; i < 6; i++) {
            xPoints[i] = (int) (centerX + radius * Math.cos(i * Math.PI / 3));
            yPoints[i] = (int) (centerY + radius * Math.sin(i * Math.PI / 3));
        }

        return new Polygon(xPoints, yPoints, 6);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g2d.fillPolygon(polygon);
    }
}

