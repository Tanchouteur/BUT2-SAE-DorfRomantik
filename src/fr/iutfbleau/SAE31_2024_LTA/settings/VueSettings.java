package fr.iutfbleau.SAE31_2024_LTA.settings;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.VuePrincipale;
import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;
import fr.iutfbleau.SAE31_2024_LTA.menu.ControllerMenuCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VueSettings extends JPanel {
    private final JSlider musicVolumeSlider;
    private final JSlider effectsVolumeSlider;
    private final ModelPrincipale modelPrincipale;

    public VueSettings(ControllerPopup controllerPopup, ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
        ConfigManager configManager = modelPrincipale.getConfigManager();
        setLayout(null);
        setBackground(new Color(30, 30, 30));

        musicVolumeSlider = new JSlider(35, 100, configManager.getVolumeMusique());
        musicVolumeSlider.setBounds(200, 100, 400, 40);
        styleSlider(musicVolumeSlider);
        add(musicVolumeSlider);

        effectsVolumeSlider = new JSlider(35, 100, configManager.getVolumeEffet());
        effectsVolumeSlider.setBounds(200, 200, 400, 40);
        styleSlider(effectsVolumeSlider);
        add(effectsVolumeSlider);

        JLabel musicLabel = new JLabel("Musique Volume:");
        styleLabel(musicLabel);
        musicLabel.setBounds(50, 100, 150, 40);
        add(musicLabel);

        JLabel effectsLabel = new JLabel("Effets Volume:");
        styleLabel(effectsLabel);
        effectsLabel.setBounds(50, 200, 150, 40);
        add(effectsLabel);

        musicVolumeSlider.addChangeListener(new ControllerVolumeChange(configManager, 0));
        effectsVolumeSlider.addChangeListener(new ControllerVolumeChange(configManager, 1));

        JButton resumeButton = new JButton("Resume");
        styleButton(resumeButton);
        resumeButton.setBounds(200, 350, 180, 50);
        resumeButton.addActionListener(e -> onResume(controllerPopup));
        add(resumeButton);

        JButton quitButton = new JButton("Quit");
        styleButton(quitButton);
        quitButton.setBounds(420, 350, 180, 50);
        quitButton.addActionListener(e -> onQuit(controllerPopup));
        add(quitButton);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    onResume(controllerPopup);
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    private void styleSlider(JSlider slider) {
        slider.setBackground(new Color(45, 45, 45));
        slider.setForeground(Color.WHITE);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
    }

    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
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

    private void onResume(ControllerPopup controllerPopup) {
        controllerPopup.closePopup();
    }

    private void onQuit(ControllerPopup controllerPopup) {
        ControllerMenuCard controllerMenuCard = new ControllerMenuCard(modelPrincipale);
        controllerPopup.closePopup();
        controllerMenuCard.goMenu();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = new Color(45, 45, 45);
        Color color2 = new Color(30, 30, 30);
        int width = getWidth();
        int height = getHeight();

        GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }

    public void setMusicVolume(int volume) {
        musicVolumeSlider.setValue(volume);
    }

    public void setEffectsVolume(int volume) {
        effectsVolumeSlider.setValue(volume);
    }

}

