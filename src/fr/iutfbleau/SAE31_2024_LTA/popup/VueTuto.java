package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VueTuto extends JPanel {
    private final JCheckBox showAtStartupCheckBox;

    VueTuto(ControllerPopup controllerPopup, ConfigManager configManager) {
        setLayout(null);
        setBackground(new Color(168, 168, 168));
        setSize(new Dimension(controllerPopup.getTutoDialog().getWidth(), controllerPopup.getTutoDialog().getHeight()));

        showAtStartupCheckBox = new JCheckBox("Montrer au démarrage", configManager.isTuto());
        styleCheckBox(showAtStartupCheckBox);
        showAtStartupCheckBox.setBounds(20, this.getHeight()-100, 220, 30);
        showAtStartupCheckBox.addActionListener(e -> onShowAtStartupChange(configManager));
        add(showAtStartupCheckBox);

        JButton resumeButton = new JButton("Resume");
        styleButton(resumeButton);
        resumeButton.setBounds(this.getWidth()-220, this.getHeight()-110, 180, 50);
        resumeButton.addActionListener(e -> onResume(controllerPopup));
        add(resumeButton);


    }
    private void styleButton(JButton button) {
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90), 2));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 90, 90), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(80, 80, 80));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(60, 60, 60));
            }
        });
    }

    private void styleCheckBox(JCheckBox checkBox) {
        checkBox.setBackground(new Color(60, 60, 60));
        checkBox.setForeground(Color.WHITE);
        checkBox.setFocusPainted(false);
        checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
        checkBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    private void onResume(ControllerPopup controllerPopup) {
        controllerPopup.closePopup(controllerPopup.getTutoDialog());
    }
    private void onShowAtStartupChange(ConfigManager configManager) {
        configManager.setTuto(showAtStartupCheckBox.isSelected());
    }
}
