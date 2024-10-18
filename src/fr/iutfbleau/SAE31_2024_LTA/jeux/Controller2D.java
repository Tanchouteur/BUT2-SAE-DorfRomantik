package fr.iutfbleau.SAE31_2024_LTA.jeux;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller2D extends MouseAdapter {
    private final VueJeux vueJeux;
    private boolean dragging = false;
    private int startX;
    private int startY;

    public Controller2D(VueJeux vueJeux) {
        this.vueJeux = vueJeux;
        vueJeux.addMouseListener(this);
        vueJeux.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // Clic gauche
            dragging = true;
            startX = e.getX();
            startY = e.getY();
            System.out.println("Clic gauche enfoncé à: (" + startX + ", " + startY + ")");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            int currentX = e.getX();
            int currentY = e.getY();
            int deltaX = currentX - startX;
            int deltaY = currentY - startY;

            System.out.println("Déplacement: " + deltaX + ", " + deltaY);

            // Mettre à jour les offsets dans VueJeux
            vueJeux.updateOffsets(deltaX, deltaY);

            startX = currentX;
            startY = currentY; // Mettez à jour les coordonnées de départ
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // Clic gauche
            dragging = false;
            System.out.println("Clic gauche relâché");
        }
    }
}
