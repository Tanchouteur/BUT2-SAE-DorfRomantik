package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;

import javax.swing.*;
import java.awt.*;

public class VueJeux extends JPanel {

    private Graphics2D g2d;

    private ModelTuile[] modelTuiles;
    private int offsetX = 0;
    private int offsetY = 0;

    public VueJeux(ModelPrincipale modelPrincipale) {
        setLayout(null);
        new Controller2D(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        ModelTuile tuile = new ModelTuile(10);
        tuile.setCoordinates(100 + offsetX, 100 + offsetY, 50);

        ModelTuile tuile1 = new ModelTuile(11);
        tuile1.setCoordinates(175 + offsetX, 150 + offsetY, 50);
        modelTuiles = new ModelTuile[] {tuile, tuile1};

        for (ModelTuile modelTuile : modelTuiles) {
            drawTuile(g2d, modelTuile);
        }
    }

    private void drawTuile(Graphics2D g2d, ModelTuile tuile) {
        Polygon tuilePoly = tuile.getPolygon();
        Color[] composition = tuile.getComposition();

        for (int i = 0; i < 6; i++) {
            g2d.setColor(composition[i]);
            g2d.fillPolygon(tuilePoly);
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
