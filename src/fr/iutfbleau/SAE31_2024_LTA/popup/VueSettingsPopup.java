package fr.iutfbleau.SAE31_2024_LTA.popup;

import fr.iutfbleau.SAE31_2024_LTA.ModelPrincipale;
import fr.iutfbleau.SAE31_2024_LTA.config.ConfigManager;
import fr.iutfbleau.SAE31_2024_LTA.menu.ControllerMenuCard;
import fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent;

import javax.swing.*;
import java.awt.*;

import static fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent.*;

public class VueSettingsPopup extends JPanel {
    private final JSlider musicVolumeSlider;
    private final JSlider effectsVolumeSlider;
    private final ModelPrincipale modelPrincipale;
    private boolean open = false;
    private JCheckBox AACheckBox;

    public VueSettingsPopup(ControllerPopup controllerPopup, ModelPrincipale modelPrincipale) {
        this.modelPrincipale = modelPrincipale;
        ConfigManager configManager = modelPrincipale.getConfigManager();
        setLayout(null);
        setOpaque(false);
        this.setSize(700,430);
        this.setBackground(StyleComponent.getPopupColor());
        this.updateVueSettings();

        JLabel settingsLabel = new JLabel("Paramètres");
        settingsLabel.setBounds((getWidth()-190)/2,20,190,50);
        add(setStyleLabelScore(settingsLabel,24));

        musicVolumeSlider = new JSlider(35, 100, configManager.getVolumeMusique());
        musicVolumeSlider.setBounds(250, 100, 350, 40);
        add(setStyleSlider(musicVolumeSlider));

        effectsVolumeSlider = new JSlider(35, 100, configManager.getVolumeEffet());
        effectsVolumeSlider.setBounds(250, 200, 350, 40);
        add(setStyleSlider(effectsVolumeSlider));

        JLabel musicLabel = new JLabel("Musique Volume:");
        musicLabel.setBounds(50, 100, 200, 40);
        add(setStyleLabel(musicLabel,19));

        JLabel effectsLabel = new JLabel("Effets Volume:");
        effectsLabel.setBounds(50, 200, 200, 40);
        add(setStyleLabel(effectsLabel,19));

        musicVolumeSlider.addChangeListener(new ControllerVolumeChange(configManager, 0,modelPrincipale));
        effectsVolumeSlider.addChangeListener(new ControllerVolumeChange(configManager, 1,modelPrincipale));

        AACheckBox = new JCheckBox("Anti-Aliasing", configManager.isAA());
        setStyleCheckBox(AACheckBox);
        AACheckBox.setBounds(20, this.getHeight()-140, 220, 50);
        AACheckBox.addActionListener(e -> onAntiAliasingChange(configManager));
        add(AACheckBox);

        JButton tutoButton = new JButton("Tutoriel");
        tutoButton.setBounds(20, 350, 200, 50);
        tutoButton.addActionListener(e -> onTuto(controllerPopup));
        add(setStyleButton(tutoButton,18));

        JButton resumeButton = new JButton("Resume");
        resumeButton.setBounds(240, 350, 200, 50);
        resumeButton.addActionListener(e -> onResume(controllerPopup));
        add(setStyleButton(resumeButton,18));

        JButton quitButton = new JButton("Menu");
        quitButton.setBounds(460, 350, 200, 50);
        quitButton.addActionListener(e -> onQuit(controllerPopup));
        add(setStyleButton(quitButton,18));
    }

    public void onAntiAliasingChange(ConfigManager configManager){
        configManager.setAA(AACheckBox.isSelected());
    }

    public void updateVueSettings(){
        if (!open){
            this.setBounds((modelPrincipale.getVuePrincipale().getWidth()-this.getWidth())/2,-this.getHeight(),getWidth(),getHeight());
        }else {
            this.setBounds((modelPrincipale.getVuePrincipale().getWidth()-this.getWidth())/2, (modelPrincipale.getVuePrincipale().getHeight()-this.getHeight())/2,getWidth(),getHeight());
        }
    }

    private void onTuto(ControllerPopup controllerPopup) {
        controllerPopup.closeSettings();
        controllerPopup.showTutoDialog(modelPrincipale.getConfigManager());
    }

    private void onResume(ControllerPopup controllerPopup) {
        controllerPopup.closeSettings();
    }

    private void onQuit(ControllerPopup controllerPopup) {
        ControllerMenuCard controllerMenuCard = new ControllerMenuCard(modelPrincipale);
        controllerPopup.closeSettings();
        controllerMenuCard.goMenu();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = new Color(45, 45, 45, 118);
        Color color2 = new Color(99, 99, 99,100);
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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}

