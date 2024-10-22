package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;
import fr.iutfbleau.SAE31_2024_LTA.menu.ControllerMenuCard;
import fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent.*;

public class VueSettingsPopup extends JPanel {
    private final JSlider musicVolumeSlider;
    private final JSlider effectsVolumeSlider;
    private final ModelPrincipale modelPrincipale;

    public VueSettingsPopup(ControllerPopup controllerPopup, ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
        ConfigManager configManager = modelPrincipale.getConfigManager();
        setLayout(null);
        setBackground(StyleComponent.getPopupColor());

        musicVolumeSlider = new JSlider(35, 100, configManager.getVolumeMusique());
        musicVolumeSlider.setBounds(200, 100, 400, 40);
        add(setStyleSlider(musicVolumeSlider));

        effectsVolumeSlider = new JSlider(35, 100, configManager.getVolumeEffet());
        effectsVolumeSlider.setBounds(200, 200, 400, 40);
        add(setStyleSlider(effectsVolumeSlider));

        JLabel musicLabel = new JLabel("Musique Volume:");
        musicLabel.setBounds(50, 100, 150, 40);
        add(setStyleLabel(musicLabel,19));

        JLabel effectsLabel = new JLabel("Effets Volume:");
        effectsLabel.setBounds(50, 200, 150, 40);
        add(setStyleLabel(effectsLabel,19));

        musicVolumeSlider.addChangeListener(new ControllerVolumeChange(configManager, 0));
        effectsVolumeSlider.addChangeListener(new ControllerVolumeChange(configManager, 1));

        JButton tutoButton = new JButton("Tutoriel");
        tutoButton.setBounds(20, 350, 180, 50);
        tutoButton.addActionListener(e -> onTuto(controllerPopup));
        add(setStyleButton(tutoButton,18));

        JButton resumeButton = new JButton("Resume");
        resumeButton.setBounds(230, 350, 180, 50);
        resumeButton.addActionListener(e -> onResume(controllerPopup));
        add(setStyleButton(resumeButton,18));

        JButton quitButton = new JButton("Menu");
        quitButton.setBounds(450, 350, 180, 50);
        quitButton.addActionListener(e -> onQuit(controllerPopup));
        add(setStyleButton(quitButton,18));

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

    private void onTuto(ControllerPopup controllerPopup) {
        controllerPopup.closePopup(controllerPopup.getSettingsDialog());
        controllerPopup.showTutoDialog(modelPrincipale.getConfigManager());
    }

    private void onResume(ControllerPopup controllerPopup) {
        controllerPopup.closePopup(controllerPopup.getSettingsDialog());
    }

    private void onQuit(ControllerPopup controllerPopup) {
        ControllerMenuCard controllerMenuCard = new ControllerMenuCard(modelPrincipale);
        controllerPopup.closePopup(controllerPopup.getSettingsDialog());
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

