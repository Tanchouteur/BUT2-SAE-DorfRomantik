package fr.iutfbleau.SAE31_2024_LTA.jeux;

import fr.iutfbleau.SAE31_2024_LTA.layers.VuePrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List; // Importer la bonne classe List
import java.util.ArrayList; // Importer ArrayList
import java.util.Map; // Pour les Map

import static fr.iutfbleau.SAE31_2024_LTA.miseEnForme.StyleComponent.*;


public class VueJeux extends JLayeredPane {

    private final ModelJeux modelJeux;
    private int offsetX = 0, offsetY = 0;
    int tuileCentreRow = 0;
    int tuileCentreCol = 0;

    private final int tuileSize = 50, hexHeight = tuileSize - 7;
    private final ModelTuile[] tuilePreview;
    private boolean end = false, dirty = false;
    private JPanel infoPanel;
    private JLabel currentScore;

    private JButton centrer;

    public VueJeux(ModelJeux modelJeux) {
        setLayout(null);
        new Controller2D(this);
        this.modelJeux = modelJeux;
        tuilePreview = new ModelTuile[modelJeux.getListTuiles().size()];
        createPlayerInfo();
        addMouseWheelListener(new ControllerMouseWheelDecalage(modelJeux.getModelPrincipale()));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                if (end) {
                    modelJeux.getVueScoreScreen().setBounds(getWidth() - 400, 100, 350, 600);
                } else {
                    updatePreviewTuileList();
                }
            }
        });

        createFirstTuile();
        this.addMouseListener(new ControllerPoseTuile(modelJeux));
        repaint();
    }

    private void centrer() {
        offsetX = 0;
        offsetY = 0;
        repaint();
    }

    private void createFirstTuile() {
        int x = (getWidth() / 2) - (3 * 50 / 2) * 50;
        int y = (getHeight() / 2) - 50 - 7 * 50;
        ModelTuile tuile = modelJeux.getListTuiles().getFirst();
        tuile.createVueTuile(x, y, tuileSize);
        add(tuile.getVueTuile());
        modelJeux.getModelMatrice().poseTuile(0, 0);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        renderTuiles();
        if (modelJeux.getListTuiles().isEmpty() && !end) {
            endGame();
        }
        if (offsetX > modelJeux.getModelPrincipale().getVuePrincipale().getWidth()/2 || offsetY > modelJeux.getModelPrincipale().getVuePrincipale().getHeight()/2 || offsetX < -modelJeux.getModelPrincipale().getVuePrincipale().getWidth()/2 || offsetY < -modelJeux.getModelPrincipale().getVuePrincipale().getHeight()/2){
            if (infoPanel.getComponentCount() == 3) {
                centrer.setBounds((VuePrincipale.frameWidth / 2) - 200, 100, 200, 50);
                infoPanel.add(setStyleButtonInGame(centrer, 34));
            }
        }else if (infoPanel.getComponentCount() == 4) {
            infoPanel.remove(centrer);
        }
    }

    private void renderTuiles() {
        Map<Point, ModelTuile> tuiles = modelJeux.getModelMatrice().getTuilesPartie();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int initialOffsetX = centerX - (3 * tuileSize / 2) * tuileCentreCol;
        int initialOffsetY = centerY - hexHeight * tuileCentreRow;

        int totalOffsetX = initialOffsetX + offsetX;
        int totalOffsetY = initialOffsetY + offsetY;

        List<ModelTuile> tuilesToRender = new ArrayList<>();

        for (Map.Entry<Point, ModelTuile> entry : tuiles.entrySet()) {
            ModelTuile tuile = entry.getValue();
            if (tuile != null) {
                tuilesToRender.add(tuile);
            }
        }

        for (ModelTuile tuile : tuilesToRender) {
            int col = tuile.getX();
            int row = tuile.getY();

            int x = totalOffsetX + col * (3 * tuileSize / 2);
            int y = totalOffsetY + row * hexHeight;

            if (x + tuileSize > 0 && x < getWidth() && y + hexHeight > 0 && y < getHeight()) {
                updateTuileView(tuile, x, y);
            }
        }
    }

    private void updateTuileView(ModelTuile tuile, int x, int y) {
        if (tuile != null) {
            if (tuile.getVueTuile() == null) {
                if (!tuile.isButton()) {
                    tuile.createVueTuile(x, y, tuileSize);
                    this.add(tuile.getVueTuile(), Integer.valueOf(0));
                    this.updatePreviewTuileList();
                } else if (!modelJeux.getListTuiles().isEmpty()) {
                    tuile.createVueTuile(x, y, tuileSize / 2);
                    this.add(tuile.getVueTuile(), Integer.valueOf(0));
                    tuile.getVueTuile().addMouseListener(new ControllerPoseTuile(modelJeux));
                }
            } else {
                if (!tuile.isButton()) {
                    tuile.getVueTuile().updateTuile(x, y, tuileSize);
                } else if (!modelJeux.getListTuiles().isEmpty()) {
                    tuile.getVueTuile().updateTuile(x, y, tuileSize / 2);
                }
            }
        }
    }

    private void endGame() {
        end = true;
        modelJeux.deleteButtons();
        modelJeux.setUndo(false);
        modelJeux.setUndoActivate(false);
        updatePreviewTuileList();
        modelJeux.createEndView();
        modelJeux.getVueScoreScreen().setBounds(getWidth() - (modelJeux.getVueScoreScreen().getWidthSidebar()+20), (this.getHeight()-modelJeux.getVueScoreScreen().getHeightSidebar())/2, modelJeux.getVueScoreScreen().getWidthSidebar(), modelJeux.getVueScoreScreen().getHeightSidebar());
        deletePlayerInfo();
        add(modelJeux.getVueScoreScreen(), Integer.valueOf(1));
    }

    public void updateOffsets(int deltaX, int deltaY) {
        if (offsetX+deltaX < 3365 && offsetX+deltaX > -3365) {
            offsetX += deltaX;
        }
        if (offsetY+deltaY < 3365 && offsetY+deltaY > -3365) {
            offsetY += deltaY;
        }
    }

    public void updatePreviewTuileList() {
        for (int i = 0; i < tuilePreview.length; i++) {
            if (tuilePreview[i] != null) {
                remove(tuilePreview[i].getVueTuile());
            }
            if (i < modelJeux.getListTuiles().size()) {
                ModelTuile tuile;
                if (i == 0) {
                    tuile = new ModelTuile(modelJeux.getListTuiles().get(i).getSeed(), false,true);
                    tuile.setComposition(modelJeux.getListTuiles().get(i).getComposition());
                }else {
                    tuile = new ModelTuile(modelJeux.getListTuiles().get(i).getSeed(), true,false);
                }

                tuile.createVueTuile(60, getHeight() - (5 * (modelJeux.getListTuiles().size() - i) + 45), 50);
                tuilePreview[i] = tuile;
                add(tuilePreview[i].getVueTuile(), Integer.valueOf(modelJeux.getListTuiles().size() - i));
            }
        }
        if (modelJeux.getListTuiles().isEmpty()) {
            modelJeux.createEndView();
            add(modelJeux.getVueScoreScreen(), Integer.valueOf(2));
        }
        modelJeux.createButton();
        repaint();
    }

    public ModelTuile setPreviewOnButton(VueTuile btnHovered) {
        if (btnHovered.getModelTuile().isButton() && !modelJeux.getListTuiles().isEmpty()) {

            ModelTuile previewTuile = new ModelTuile(modelJeux.getListTuiles().getFirst().getSeed(), false,false);
            previewTuile.setComposition(modelJeux.getListTuiles().getFirst().getComposition());

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            int initialOffsetX = centerX - (3 * tuileSize / 2) * tuileCentreCol;
            int initialOffsetY = centerY - hexHeight * tuileCentreRow;

            int totalOffsetX = initialOffsetX + offsetX;
            int totalOffsetY = initialOffsetY + offsetY;

            int col = btnHovered.getModelTuile().getX();
            int row = btnHovered.getModelTuile().getY();

            int x = totalOffsetX + col * (3 * tuileSize / 2);
            int y = totalOffsetY + row * hexHeight;

            previewTuile.createVueTuile(x, y, (int) (tuileSize*0.8));
            add(previewTuile.getVueTuile(), Integer.valueOf(1));
            //this.remove(btnHovered);
            repaint();

            return previewTuile;
        }
        return null;
    }

    public void unsetPreviewOnButton(ModelTuile previewTuile) {
        if (previewTuile != null) {
            remove(previewTuile.getVueTuile());
        }
        repaint();
    }

    public void createPlayerInfo() {
        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBackground(new Color(40, 40, 40, 0));
        infoPanel.setBounds(0, 0, modelJeux.getModelPrincipale().getVuePrincipale().getWidth(), modelJeux.getModelPrincipale().getVuePrincipale().getHeight());
        infoPanel.setOpaque(false);
        JLabel playerNameLabel = new JLabel(modelJeux.getModelPrincipale().getPlayerName());
        playerNameLabel.setBounds(30,30,200,50);
        infoPanel.add(setStyleLabelScore(playerNameLabel,18));

        JLabel bestScoreLabel = new JLabel("Record " + modelJeux.getModelPrincipale().getModelPartieJouer()
                .getVuePartieJouer().getControllerSearchPartieJouer().searchPartieOfPlayer(
                        modelJeux.getModelPrincipale().getPlayerName(), modelJeux.getModelPrincipale().getSelectedSeed()) + " Points");
        bestScoreLabel.setBounds(250,30,250,50);
        infoPanel.add(setStyleLabelScore(bestScoreLabel,16));

        currentScore = new JLabel("Score " + modelJeux.getScore() + " Points");
        currentScore.setBounds(520,30,250,50);
        infoPanel.add(setStyleLabelScore(currentScore,18));

        centrer = new JButton("Centrer");
        centrer.addActionListener(e -> centrer());


        this.add(infoPanel, Integer.valueOf(2));
    }

    public void updatePlayerInfo() {
        deletePlayerInfo();
        createPlayerInfo();
    }

    public void deletePlayerInfo() {
        infoPanel.removeAll();
        remove(infoPanel);
    }
}
