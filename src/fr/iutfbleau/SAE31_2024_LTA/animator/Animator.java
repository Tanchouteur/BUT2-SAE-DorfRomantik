package fr.iutfbleau.SAE31_2024_LTA.animator;

import fr.iutfbleau.SAE31_2024_LTA.jeux.VueJeux;
import fr.iutfbleau.SAE31_2024_LTA.jeux.VueTuile;
import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animator {

    public static void moveTo(JComponent panel, int startX, int startY, int endX, int endY, int duration, boolean amortie) {
        Timer timer = new Timer(10, null);
        final long startTime = System.currentTimeMillis();
        panel.setLocation(startX, startY);
        timer.addActionListener(new MoveToAction(panel, startX, startY, endX, endY, duration, amortie, timer, startTime));
        timer.start();
    }

    public static void moveToTuile(VueTuile vueTuile, int startX, int startY, int duration, VueJeux vueJeux) {
        Timer timer = new Timer(10, null);
        final long startTime = System.currentTimeMillis();
        vueTuile.setLocation(startX, startY);
        timer.addActionListener(new MoveToTuileAction(vueTuile, startX, startY, duration, vueJeux, timer, startTime));
        timer.start();
    }

    private static double easeInOut(double t) {
        return t < 0.5 ? 4 * t * t * t : 1 - Math.pow(-2 * t + 2, 3) / 2;
    }

    private static class MoveToAction implements ActionListener {
        private final JComponent panel;
        private final int startX, startY, endX, endY, duration;
        private final boolean amortie;
        private final Timer timer;
        private final long startTime;

        public MoveToAction(JComponent panel, int startX, int startY, int endX, int endY, int duration, boolean amortie, Timer timer, long startTime) {
            this.panel = panel;
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.duration = duration;
            this.amortie = amortie;
            this.timer = timer;
            this.startTime = startTime;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            long elapsed = System.currentTimeMillis() - startTime;
            double progress = Math.min(1.0, (double) elapsed / duration);
            double easedProgress = amortie ? easeInOut(progress) : progress;

            int newX = (int) (startX + easedProgress * (endX - startX));
            int newY = (int) (startY + easedProgress * (endY - startY));

            panel.setBounds(newX, newY, panel.getWidth(), panel.getHeight());
            panel.repaint();

            if (progress >= 1.0) {
                timer.stop();
            }
        }
    }

    private static class MoveToTuileAction implements ActionListener {
        private final VueTuile vueTuile;
        private final int startX, startY, duration;
        private final VueJeux vueJeux;
        private final Timer timer;
        private final long startTime;

        public MoveToTuileAction(VueTuile vueTuile, int startX, int startY, int duration, VueJeux vueJeux, Timer timer, long startTime) {
            this.vueTuile = vueTuile;
            this.startX = startX;
            this.startY = startY;
            this.duration = duration;
            this.vueJeux = vueJeux;
            this.timer = timer;
            this.startTime = startTime;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            vueJeux.getModelJeux().setUndoActivate(false);
            long elapsed = System.currentTimeMillis() - startTime;
            double progress = Math.min(1.0, (double) elapsed / duration);
            double easedProgress = easeInOut(progress);

            int centerX = VuePrincipale.frameWidth / 2;
            int centerY = VuePrincipale.frameHeight / 2;
            int initialOffsetX = centerX;
            int initialOffsetY = centerY - 43;
            int totalOffsetX = initialOffsetX + vueJeux.getOffsetX();
            int totalOffsetY = initialOffsetY + vueJeux.getOffsetY();

            int col = vueTuile.getModelTuile().getX();
            int row = vueTuile.getModelTuile().getY();

            int x = (totalOffsetX + col * (3 * 50 / 2)) - 40;
            int y = (totalOffsetY + row * 43) - 10;

            int newX = (int) (startX + easedProgress * (x - startX));
            int newY = (int) (startY + easedProgress * (y - startY));

            vueTuile.setBounds(newX, newY, vueTuile.getWidth(), vueTuile.getHeight());
            vueTuile.repaint();

            if (progress >= 1.0) {
                timer.stop();
                vueTuile.getModelTuile().setOnBoard(true);
                vueJeux.getModelJeux().setUndoActivate(true);
            }
        }
    }
}

