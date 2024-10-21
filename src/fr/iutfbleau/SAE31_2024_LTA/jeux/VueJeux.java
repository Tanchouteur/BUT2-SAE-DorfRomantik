package fr.iutfbleau.SAE31_2024_LTA.jeux;

import javax.swing.*;
import java.awt.*;

public class VueJeux extends JLayeredPane {

    private final ModelJeux modelJeux;
     int offsetX = 0;
     int offsetY = 0;

    // Taille des tuiles
    private final int tuileSize = 50;
    //decalage vertical entre deux lignes de tuiles
    private final int hexHeight =  tuileSize-7;

    private final ModelTuile[] tuilePreview;

    private boolean end = false;

    JPanel infoPanel;//HUD
    private JLabel currentScore;

    private boolean dirty = false;

    public VueJeux( ModelJeux modelJeux) {
        setLayout(null);
        new Controller2D(this);
        this.modelJeux = modelJeux;

        tuilePreview = new ModelTuile[modelJeux.getListTuiles().size()];
        createPlayerInfo();

        this.addMouseWheelListener(new ControllerMouseWheelDecalage(modelJeux.getModelPrincipale()));

        createPlayerInfo();
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                if (end) {
                    modelJeux.getVueScoreScreen().setBounds(getWidth() - 400, 100, 350, 600);
                }else {
                updatePreviewTuileList();
                }
            }
        });
        dirty = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.dirty) {
            ModelTuile[][] listeTuilesPosee = modelJeux.getModelMatrice().getListTuilesPosee();

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            int tuileCentreRow = 50;
            int tuileCentreCol = 50;

            int initialOffsetX = centerX - (3 * tuileSize / 2) * tuileCentreCol;
            int initialOffsetY = centerY - hexHeight * tuileCentreRow;

            int totalOffsetX = initialOffsetX + offsetX;
            int totalOffsetY = initialOffsetY + offsetY;

            for (int row = 0; row < listeTuilesPosee.length; row++) {
                for (int col = 0; col < listeTuilesPosee[row].length; col++) {

                    ModelTuile tuile = listeTuilesPosee[row][col];

                    int x = totalOffsetX + col * (3 * tuileSize / 2);
                    int y = totalOffsetY + row * hexHeight;

                    if (tuile != null && tuile.getVueTuile() == null) {

                        if (!tuile.isButton()) {
                            tuile.createVueTuile(x, y, tuileSize);
                            this.add(tuile.getVueTuile(), Integer.valueOf(0));
                            this.updatePreviewTuileList();
                        } else if (!modelJeux.getListTuiles().isEmpty()) {
                            tuile.createVueTuile(x, y, tuileSize / 2);
                            this.add(tuile.getVueTuile(), Integer.valueOf(0));
                            tuile.getVueTuile().addMouseListener(new ControllerPoseTuile(modelJeux, tuile));
                        }

                    } else if (tuile != null && tuile.getVueTuile() != null) {

                        if (!tuile.isButton()) {
                            tuile.getVueTuile().updateTuile(x, y, tuileSize);
                        } else if (!modelJeux.getListTuiles().isEmpty()) {
                            tuile.getVueTuile().updateTuile(x, y, tuileSize / 2);
                        }
                    }
                }
            }
            if (modelJeux.getListTuiles().isEmpty() && !end) {
                end = true;
                modelJeux.setUndo(false);
                modelJeux.setUndoActivate(false);
                this.updatePreviewTuileList();
                modelJeux.createEndView();
                modelJeux.getVueScoreScreen().setBounds(getWidth() - 400, 100, 350, 600);
                deletePlayerInfo();
                this.add(modelJeux.getVueScoreScreen(), Integer.valueOf(1));

            }
        }
    }

    public void updateOffsets(int deltaX, int deltaY) {
        offsetX += deltaX;
        offsetY += deltaY;
        //repaint();
    }

    public void updateTuile(VueTuile btnCliked){
        for (int row = 0; row < modelJeux.getModelMatrice().getListTuilesPosee().length; row++) {
            for (int col = 0; col < modelJeux.getModelMatrice().getListTuilesPosee()[row].length; col++) {

                ModelTuile tuile = modelJeux.getModelMatrice().getListTuilesPosee()[row][col];

                if (tuile != null && tuile.getVueTuile() != null && tuile.isButton()) {
                    this.remove(tuile.getVueTuile());
                    if (btnCliked != null) {
                        this.remove(btnCliked);
                    }
                    tuile.deleteVueTuile();
                }
            }
        }
        this.dirty = true;
    }

    public void updatePreviewTuileList(){

        if (!modelJeux.getListTuiles().isEmpty()) {
            for (int row = modelJeux.getListTuiles().size() - 1; row >= 0; row--) {

                if (tuilePreview[row] != null) {
                    this.remove(tuilePreview[row].getVueTuile());
                }

                tuilePreview[row] = new ModelTuile(modelJeux.getListTuiles().get(row).getSeed(), false);
                tuilePreview[row].setComposition(modelJeux.getListTuiles().get(row).getComposition());

                int centerX = 60;
                int centerY = getHeight() - (5 * (modelJeux.getListTuiles().size() - row) + 15);

                tuilePreview[row].createVueTuile(centerX, centerY, 50);
                add(tuilePreview[row].getVueTuile(), Integer.valueOf(modelJeux.getListTuiles().size() - row));
            }
        }else{

            for (ModelTuile tuile : tuilePreview) {
                if (tuile != null) {
                    this.remove(tuile.getVueTuile());
                }
            }
            modelJeux.deleteButtons();
            modelJeux.createEndView();
            add(modelJeux.getVueScoreScreen(),Integer.valueOf(2));
        }
        this.dirty = true;
        repaint();
    }

    public ModelTuile setPreviewOnButton(VueTuile btnHovered) {
        if (btnHovered.getModelTuile().isButton() && !modelJeux.getListTuiles().isEmpty()) {
            ModelTuile modelHoveredPreviewed = new ModelTuile(modelJeux.getListTuiles().getFirst().getSeed(), true);
            modelHoveredPreviewed.setComposition(modelJeux.getListTuiles().getFirst().getComposition());
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            int initialOffsetX = centerX - (3 * tuileSize / 2) * 50;
            int initialOffsetY = centerY - hexHeight * 50;

            int totalOffsetX = initialOffsetX + offsetX;
            int totalOffsetY = initialOffsetY + offsetY;

            int x = totalOffsetX + btnHovered.getModelTuile().getY() * (3 * tuileSize / 2);
            int y = totalOffsetY + btnHovered.getModelTuile().getX() * hexHeight;

            modelHoveredPreviewed.createVueTuile(x, y, 45);

            add(modelHoveredPreviewed.getVueTuile(), Integer.valueOf(1));
            this.dirty = true;
            return modelHoveredPreviewed;
        }
        return null;
    }

    public void unsetPreviewOnButton(ModelTuile modelHoveredPreview) {
        if (modelHoveredPreview != null) {
            this.remove(modelHoveredPreview.getVueTuile());

        }
        this.dirty = true;
        repaint();
    }

    public void createPlayerInfo() {
        infoPanel = new JPanel();
        Color greyColor = new Color(44, 44, 44, 255);
        Font buttonMenuFont = new Font("Arial", Font.BOLD, 18);
        Font inputMenuFont = new Font("Arial", Font.BOLD, 24);

        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(new Color(112, 112, 112, 181));

        JLabel playerNameLabel = new JLabel(modelJeux.getModelPrincipale().getPlayerName());
        playerNameLabel.setFont(inputMenuFont);
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setBackground(greyColor);
        playerNameLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(7, 7, 7, 7)
        ));

        JLabel bestScoreLabel = new JLabel("Ton record : "+modelJeux.getModelPrincipale().getModelPartieJouer().getVuePartieJouer().getControllerSearchPartieJouer().searchPartieOfPlayer(modelJeux.getModelPrincipale().getPlayerName(),modelJeux.getModelPrincipale().getSelectedSeed())+" Points");
        bestScoreLabel.setFont(buttonMenuFont);
        bestScoreLabel.setBackground(greyColor);
        bestScoreLabel.setForeground(Color.WHITE);
        bestScoreLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(7, 10, 7, 10)
        ));


        currentScore = new JLabel("Score : "+modelJeux.getScore()+" Points");
        currentScore.setFont(buttonMenuFont);
        currentScore.setBackground(greyColor);
        currentScore.setForeground(Color.WHITE);
        currentScore.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(greyColor, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        infoPanel.add(playerNameLabel,BorderLayout.WEST);
        infoPanel.add(bestScoreLabel,BorderLayout.CENTER);
        infoPanel.add(currentScore,BorderLayout.EAST);
        infoPanel.setBounds(30,30,550,50);
        this.add(infoPanel, Integer.valueOf(2));
    }

    public void updatePlayerInfo(){
        deletePlayerInfo();
        createPlayerInfo();
    }

    public void deletePlayerInfo() {
        this.remove(infoPanel);
        this.dirty = true;
    }

    public void setDirty(){
        this.dirty = true;
    }

    public void unsetDirty(){
        this.dirty = false;
    }

    public boolean isEnd() {
        return this.end;
    }
}
