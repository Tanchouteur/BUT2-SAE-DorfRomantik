package fr.iutfbleau.SAE31_2024_LTA.animator;

import fr.iutfbleau.SAE31_2024_LTA.jeux.ModelJeux;
import fr.iutfbleau.SAE31_2024_LTA.jeux.ModelMatrice;
import fr.iutfbleau.SAE31_2024_LTA.jeux.VueJeux;
import fr.iutfbleau.SAE31_2024_LTA.jeux.VueTuile;
import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animator {
    public static void moveTo(JComponent panel, int startX, int startY, int endX, int endY, int duration, boolean amortie) {
        Timer timer = new Timer(10, null);
        final long startTime = System.currentTimeMillis();
        panel.setLocation(startX, startY);
        timer.addActionListener(e -> {
            long elapsed = System.currentTimeMillis() - startTime;
            double progress = Math.min(1.0, (double) elapsed / duration);

            double easedProgress = progress;

            if (amortie) {
                easedProgress = easeInOut(progress);
            }

            int newX = (int) (startX + easedProgress * (endX - startX));
            int newY = (int) (startY + easedProgress * (endY - startY));

            panel.setBounds(newX, newY,panel.getWidth(),panel.getHeight());
            panel.repaint();
            if (progress >= 1.0) {
                timer.stop();
            }
        });
        timer.start();
    }

    public static void moveToTuile(VueTuile vueTuile, int startX, int startY, int duration, VueJeux vueJeux) {
        Timer timer = new Timer(10, null);
        final long startTime = System.currentTimeMillis();
        vueTuile.setLocation(startX, startY);
        timer.addActionListener(e -> {
            vueJeux.getModelJeux().setUndoActivate(false);
            long elapsed = System.currentTimeMillis() - startTime;
            double progress = Math.min(1.0, (double) elapsed / duration);

            double easedProgress = easeInOut(progress);

            int centerX = VuePrincipale.frameWidth / 2;
            int centerY =VuePrincipale.frameHeight / 2;

            int initialOffsetX = centerX;
            int initialOffsetY = centerY - 43;

            int totalOffsetX = initialOffsetX + vueJeux.getOffsetX();
            int totalOffsetY = initialOffsetY + vueJeux.getOffsetY();

            int col = vueTuile.getModelTuile().getX();
            int row = vueTuile.getModelTuile().getY();

            int x = (totalOffsetX + col * (3 * 50 / 2))-40;
            int y = (totalOffsetY + row * 43)-10;

            int newX = (int) (startX + easedProgress * (x - startX));
            int newY = (int) (startY + easedProgress * (y - startY));

            vueTuile.setBounds(newX, newY,vueTuile.getWidth(),vueTuile.getHeight());
            vueTuile.repaint();

            if (progress >= 1.0) {
                timer.stop();
                vueTuile.getModelTuile().setOnBoard(true);
                vueJeux.getModelJeux().setUndoActivate(true);
            }
        });
        timer.start();
    }

    private static double easeInOut(double t) {
        return t < 0.5 ? 4 * t * t * t : 1 - Math.pow(-2 * t + 2, 3) / 2;
    }
}
