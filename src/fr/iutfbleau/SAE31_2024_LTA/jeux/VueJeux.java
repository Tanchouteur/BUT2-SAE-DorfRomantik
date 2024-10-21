package fr.iutfbleau.SAE31_2024_LTA.jeux;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

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
        repaint();
    }

    private void createFirstTuile() {
        int x = (getWidth() / 2) - (3 * 50 / 2) * 50;
        int y = (getHeight() / 2) - 50 - 7 * 50;
        ModelTuile tuile = modelJeux.getListTuiles().getFirst();
        tuile.createVueTuile(x, y, tuileSize);
        add(tuile.getVueTuile());
        modelJeux.getModelMatrice().poseTuile(0, 0);
        this.addMouseListener(new ControllerPoseTuile(modelJeux, tuile));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        renderTuiles();
        if (modelJeux.getListTuiles().isEmpty() && !end) {
            endGame();
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


        for (Map.Entry<Point, ModelTuile> entry : tuiles.entrySet()) {
            ModelTuile tuile = entry.getValue();

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

    private void endGame() {
        end = true;
        modelJeux.setUndo(false);
        modelJeux.setUndoActivate(false);
        updatePreviewTuileList();
        modelJeux.createEndView();
        modelJeux.getVueScoreScreen().setBounds(getWidth() - 400, 100, 350, 600);
        deletePlayerInfo();
        add(modelJeux.getVueScoreScreen(), Integer.valueOf(1));
    } //Sa sa marche

    public void updateOffsets(int deltaX, int deltaY) {
        offsetX += deltaX;
        offsetY += deltaY;
    }

    public void updatePreviewTuileList() {
        for (int i = 0; i < tuilePreview.length; i++) {
            if (tuilePreview[i] != null) {
                remove(tuilePreview[i].getVueTuile());
            }
            if (i < modelJeux.getListTuiles().size()) {
                ModelTuile tuile = new ModelTuile(modelJeux.getListTuiles().get(i).getSeed(), false);
                tuile.setComposition(modelJeux.getListTuiles().get(i).getComposition());
                tuile.createVueTuile(60, getHeight() - (5 * (modelJeux.getListTuiles().size() - i) + 20), 50);
                tuilePreview[i] = tuile;
                add(tuilePreview[i].getVueTuile(), Integer.valueOf(modelJeux.getListTuiles().size() - i));
            }
        }
        modelJeux.deleteButtons();
        if (modelJeux.getListTuiles().isEmpty()) {
            modelJeux.createEndView();
            add(modelJeux.getVueScoreScreen(), Integer.valueOf(2));
        }
        repaint();
    }//Sa sa marche

    public ModelTuile setPreviewOnButton(VueTuile btnHovered) {
        if (btnHovered.getModelTuile().isButton() && !modelJeux.getListTuiles().isEmpty()) {

            ModelTuile previewTuile = new ModelTuile(modelJeux.getListTuiles().getFirst().getSeed(), true);
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

            repaint();

            return previewTuile;
        }
        return null;
    } //Sa sa marche

    public void unsetPreviewOnButton(ModelTuile previewTuile) {
        if (previewTuile != null) {
            remove(previewTuile.getVueTuile());
        }
        repaint();
    } //Sa sa marche

    public void createPlayerInfo() {
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(new Color(112, 112, 112, 181));
        infoPanel.setBounds(30, 30, 550, 50);

        JLabel playerNameLabel = createLabel(modelJeux.getModelPrincipale().getPlayerName(), 24);
        JLabel bestScoreLabel = createLabel("Ton record : " + modelJeux.getModelPrincipale().getModelPartieJouer()
                .getVuePartieJouer().getControllerSearchPartieJouer().searchPartieOfPlayer(
                        modelJeux.getModelPrincipale().getPlayerName(), modelJeux.getModelPrincipale().getSelectedSeed()) + " Points", 18);
        currentScore = createLabel("Score : " + modelJeux.getScore() + " Points", 18);

        infoPanel.add(playerNameLabel, BorderLayout.WEST);
        infoPanel.add(bestScoreLabel, BorderLayout.CENTER);
        infoPanel.add(currentScore, BorderLayout.EAST);
        add(infoPanel, Integer.valueOf(2));
    } //Sa sa marche

    private JLabel createLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(44, 44, 44, 255), 1, true),
                BorderFactory.createEmptyBorder(7, 7, 7, 7)
        ));
        return label;
    }

    public void updatePlayerInfo() {
        deletePlayerInfo();
        createPlayerInfo();
    }

    public void deletePlayerInfo() {
        remove(infoPanel);
        dirty = true;
    } //Sa je sais pas
}
