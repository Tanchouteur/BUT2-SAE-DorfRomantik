package fr.iutfbleau.SAE31_2024_LTA.miseEnForme;

import javax.swing.*;
import java.awt.*;

public class StyleComponent {

    public static Color getButtonColor() {
        return new Color(60, 60, 60);
    }

    public static Color getPopupColor() {
        return new Color(30, 30, 30);
    }

    public static Color getPanelColor() {
        return new Color(193, 193, 193, 89);
    }

    public static JButton setStyleButton(JButton button, int fontSize) {
        button.setBackground(getButtonColor());
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90), 2));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 90, 90), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new BrighterHoverJComponent(button));
        return button;
    }

    public static JCheckBox setStyleCheckBox(JCheckBox checkBox) {
        checkBox.setBackground(getButtonColor());
        checkBox.setForeground(Color.WHITE);
        checkBox.setFocusPainted(false);
        checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
        checkBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        checkBox.addMouseListener(new BrighterHoverJComponent(checkBox));
        return checkBox;
    }

    public static JSlider setStyleSlider(JSlider slider) {
        slider.setBackground(new Color(45, 45, 45));
        slider.setForeground(Color.WHITE);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.addMouseListener(new BrighterHoverJComponent(slider));
        return slider;
    }

    public static JLabel setStyleLabel(JLabel label, int fontSize) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        return label;
    }
}