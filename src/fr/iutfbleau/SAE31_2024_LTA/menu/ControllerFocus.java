package fr.iutfbleau.SAE31_2024_LTA.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ControllerFocus implements FocusListener {
    JPanel panel;
    public ControllerFocus(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField playerNameInput = (JTextField) e.getComponent();
            if (playerNameInput.getText().equals("Player Name...")) {
                playerNameInput.setText("");
                playerNameInput.setForeground(Color.BLACK);
            }
            panel.repaint();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField playerNameInput = (JTextField) e.getComponent();
            if (playerNameInput.getText().isEmpty()) {
                playerNameInput.setForeground(Color.GRAY);
                playerNameInput.setText("Player Name...");
            }
            panel.repaint();
        }
    }
}
