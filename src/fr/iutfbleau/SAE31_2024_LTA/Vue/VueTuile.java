package fr.iutfbleau.SAE31_2024_LTA.Vue;

import javax.swing.*;
import java.awt.*;

public class VueTuile extends JPanel {

    private final Color[] composition;

    public VueTuile(Color[] composition){
        this.composition = composition;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2 - 10;

        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        for (int i = 0; i < 6; i++) {
            xPoints[i] = width / 2 + (int) (radius * Math.cos(i * Math.PI / 3));
            yPoints[i] = height / 2 + (int) (radius * Math.sin(i * Math.PI / 3));
        }

        //ajouter un gradient pour l'OA
        for (int i = 0; i < 6; i++) {
            // Dégradé léger sur chaque triangle
            GradientPaint gradient = new GradientPaint(
                    (float) getWidth() / 2, (float) getHeight() / 2, composition[i].brighter(),
                    xPoints[i], yPoints[i], composition[i].darker()
            );

            g2d.setPaint(gradient);

            int[] xTriangle = {getWidth() / 2, xPoints[i], xPoints[(i + 1) % 6]};
            int[] yTriangle = {getHeight() / 2, yPoints[i], yPoints[(i + 1) % 6]};
            g2d.fillPolygon(xTriangle, yTriangle, 3);
        }

        //contour pour chaque triangle
        for (int i = 0; i < 6; i++) {
            g2d.setColor(composition[i].darker().darker());
            int[] xTriangle = {getWidth() / 2, xPoints[i], xPoints[(i + 1) % 6]};
            int[] yTriangle = {getHeight() / 2, yPoints[i], yPoints[(i + 1) % 6]};
            g2d.drawPolygon(xTriangle, yTriangle, 3);
        }

        //dessiner le contour de l'hexagone
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawPolygon(xPoints, yPoints, 6);
    }
}
