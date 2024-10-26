package fr.iutfbleau.SAE31_2024_LTA.jeux.controller;

import fr.iutfbleau.SAE31_2024_LTA.jeux.vue.VueJeux;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.Cursor.MOVE_CURSOR;

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

        if (e.getButton() == MouseEvent.BUTTON1) {
            dragging = true;
            startX = e.getX();
            startY = e.getY();
            vueJeux.setCursor(new Cursor(MOVE_CURSOR));
        }
        vueJeux.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            int currentX = e.getX();
            int currentY = e.getY();
            int deltaX = currentX - startX;
            int deltaY = currentY - startY;

            vueJeux.updateOffsets(deltaX, deltaY);

            startX = currentX;
            startY = currentY;
        }
        vueJeux.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            dragging = false;
            vueJeux.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
}