package fr.iutfbleau.SAE31_2024_LTA.miseEnForme;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.util.Objects;

public class StyleComponent {

    public static Color getButtonColor() {
        return new Color(60, 60, 60);
    }

    public static Color getPopupColor() {
        return new Color(30, 30, 30, 95);
    }

    public static Color getPanelColor() {
        return new Color(193, 193, 193, 89);
    }

    public static Color getTextFieldColor(){
        return new Color(207, 207, 207);
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

    public static JButton setStyleButtonInGame(JButton button, int fontSize) {
        button.setBackground(new Color(237, 237, 237, 255));
        button.setForeground(new Color(45, 47, 55));
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setBorder(BorderFactory.createLineBorder(new Color(57, 61, 89), 2));
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
        slider.setBackground(new Color(45, 45, 45, 0));
        slider.setForeground(Color.WHITE);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.addMouseListener(new BrighterHoverJComponent(slider));
        slider.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(213, 213, 213, 255), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        slider.setOpaque(false);
        return slider;
    }

    public static JLabel setStyleLabel(JLabel label, int fontSize) {
        label.setForeground(new Color(0x232323));
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        return label;
    }

    public static JLabel setStyleLabelWhite(JLabel label, int fontSize) {
        label = setStyleLabel(label, fontSize);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createLineBorder(new Color(228, 228, 228, 0), 2));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(213, 213, 213, 255), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        label.setOpaque(false);
        return label;
    }

    public static JLabel setStyleLabelSucces(JLabel label, int fontSize) {
        label.setForeground(new Color(22, 76, 0));
        label.setOpaque(false);
        label.setBackground(new Color(0, 81, 4, 102));
        label.setSize(600,50);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        label.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90), 2));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 90, 90), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        return label;
    }

    public static JLabel setStyleLabelErreur(JLabel label, int fontSize) {
        label.setForeground(new Color(97, 0, 0));
        label.setOpaque(false);
        label.setBackground(new Color(122, 0, 0, 111));
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        label.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90), 2));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 90, 90), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        return label;
    }

    public static JLabel setStyleLabelScore(JLabel label, int fontSize) {
        label.setForeground(new Color(67, 67, 67));
        label.setBackground(new Color(0, 14, 90, 0));
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        label.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90), 2));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 90, 90), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        label.setOpaque(true);
        return label;
    }

    public static JTextField setStyleTextField(JTextField textField, int fontSize){
        textField.setFont(new Font("Arial", Font.PLAIN, fontSize));
        textField.setForeground(Color.GRAY);
        textField.setBackground(getTextFieldColor());
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(getTextFieldColor(), 1, true),
                BorderFactory.createEmptyBorder(7, 7, 7, 7)
        ));
        textField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        return textField;
    }

    public static JComboBox setStyleComboBox(JComboBox comboBox, int fontSize, JPanel vue){
        comboBox.setFont(new Font("Arial", Font.BOLD, fontSize));//24 en général
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(getTextFieldColor(), 1, true),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        comboBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                vue.repaint();
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                vue.repaint();
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                vue.repaint();
            }
        });

        return comboBox;
    }

    public static JLabel setStyleImageTitre(){
        ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(StyleComponent.class.getResource("/Images/Titre.png")));
        Image image = logoIcon.getImage();
        Image resizedImage = image.getScaledInstance(180, 110, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return logoLabel;
    }
}