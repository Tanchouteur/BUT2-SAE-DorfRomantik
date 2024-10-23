package fr.iutfbleau.SAE31_2024_LTA.animator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animator {

    // Méthode pour faire un fade
    public static void fadeIn(JComponent component, int duration) {
        Timer timer = new Timer(duration / 20, new ActionListener() {
            float alpha = 0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.05f;
                if (alpha >= 1f) {
                    alpha = 1f;
                    ((Timer) e.getSource()).stop();
                }
                component.setOpaque(true);
                component.repaint();
            }
        });
        timer.start();
    }

    // Méthode pour faire un fade
    public static void fadeOut(JComponent component, int duration) {
        Timer timer = new Timer(duration / 20, new ActionListener() {
            float alpha = 1f;

            @Override
            public void actionPerformed(ActionEvent e) {
                alpha -= 0.05f;
                if (alpha <= 0f) {
                    alpha = 0f;
                    ((Timer) e.getSource()).stop();
                    component.setVisible(false);
                }
                component.setOpaque(true);
                component.repaint();
            }
        });
        timer.start();
    }

    public static void moveTo(JPanel panel, int startX, int startY, int endX, int endY, int duration, boolean amortie) {
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

    private static double easeInOut(double t) {
        return t < 0.5 ? 4 * t * t * t : 1 - Math.pow(-2 * t + 2, 3) / 2;
    }
}
