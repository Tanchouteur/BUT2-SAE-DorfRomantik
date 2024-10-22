package fr.iutfbleau.SAE31_2024_LTA;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent.setStyleButton;
import static fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent.setStyleImageTitre;

public class LoadingFrame extends JFrame {
    LoadingFrame(){
        this.setSize(400, 350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//faut surtout pas exit le programme
        this.setLayout(null);

        JButton cancelButton = new JButton("Annuler");
        cancelButton.setBounds(100,220,200,50);
        cancelButton.addActionListener(e->{
            System.exit(0);
        });
        this.add(setStyleButton(cancelButton,28));

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setBounds(20, this.getHeight()-70, 360,50);
        this.add(progressBar);

        this.setBackground(new Color(0x787878, false));
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        try {
            URL logoUrl = ControlerMain.class.getResource("/Images/logo.png");
            if (logoUrl != null) {
                this.setIconImage(ImageIO.read(logoUrl));
            } else {
                System.err.println("Logo non trouvé : /Images/Logo.png");
            }
        } catch (IOException e) {
            System.out.println("logo err : " + e);
        }
        JLabel titre = setStyleImageTitre();
        titre.setBounds(50,10,300,200);
        this.add(titre);
        this.repaint();
    }
}
