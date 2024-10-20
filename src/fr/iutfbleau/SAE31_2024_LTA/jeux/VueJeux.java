package fr.iutfbleau.SAE31_2024_LTA.jeux;

import javax.swing.*;
import java.awt.*;

public class VueJeux extends JLayeredPane {

    private final ModelJeux modelJeux;
    private int offsetX = 0;
    private int offsetY = 0;

    // Taille des tuiles
    private final int tuileSize = 50;
    //decalage vertical entre deux lignes de tuiles
    private final int hexHeight =  tuileSize-7;

    private final ModelTuile[] tuilePreview;

    private boolean end = false;

    VueInfoPanel infoPanel;//HUD

    ControllerMouseWheelDecalage controllerMouseWheelDecalage;

    public VueJeux( ModelJeux modelJeux) {
        setLayout(null);
        new Controller2D(this);
        this.modelJeux = modelJeux;

        tuilePreview = new ModelTuile[modelJeux.getListTuiles().size()];

        this.addMouseWheelListener(new ControllerMouseWheelDecalage(modelJeux.getModelPrincipale()));

        showPlayerInfo();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

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

                    if (!tuile.isButton()){
                        tuile.createVueTuile(x, y, tuileSize);
                        this.add(tuile.getVueTuile(), Integer.valueOf(0));
                        this.updatePreviewTuile();
                    }else if (!modelJeux.getListTuiles().isEmpty()){
                        tuile.createVueTuile(x, y, tuileSize/2);
                        this.add(tuile.getVueTuile(), Integer.valueOf(0));
                        tuile.getVueTuile().addMouseListener(new ControllerPoseTuile(modelJeux, tuile));
                    }
                }else if (tuile != null && tuile.getVueTuile() != null) {

                    if (!tuile.isButton()){

                        tuile.getVueTuile().updateTuile(x, y, tuileSize);

                    }else if (!modelJeux.getListTuiles().isEmpty()){
                        tuile.getVueTuile().updateTuile(x, y, tuileSize/2);
                    }
                }
            }
        }
        if (modelJeux.getListTuiles().isEmpty() && !end){
            end = true;
            modelJeux.createEndView();
            modelJeux.getVueScoreScreen().setBounds(getWidth()-400, 100, 350, 600);
            this.add(modelJeux.getVueScoreScreen(), Integer.valueOf(1));
        }
    }

    public void updateOffsets(int deltaX, int deltaY) {
        offsetX += deltaX;
        offsetY += deltaY;
        repaint();
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
        repaint();
    }

    public void updatePreviewTuile(){

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
            repaint();
            return modelHoveredPreviewed;
        }
        return null;
    }

    public void unsetPreviewOnButton(ModelTuile modelHoveredPreview) {
        if (modelHoveredPreview != null) {
            this.remove(modelHoveredPreview.getVueTuile());

        }
        this.repaint();
    }

    public void showPlayerInfo() {
        infoPanel = new VueInfoPanel(modelJeux);

        infoPanel.setBounds(30,30,550,50);
        this.add(infoPanel, Integer.valueOf(2));
    }

    public void updatePlayerInfo(){
        infoPanel.getCurrentScore().setText("Score : "+modelJeux.getScore()+" Points");
    }
}
