package sk.stuba.fei.uim.oop.gameLogic;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.board.State;
import sk.stuba.fei.uim.oop.tile.Tile;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_BOARD_SIZE = 8;
    private JFrame game;
    private Board currentBoard;
    @Getter
    private JLabel levelLabel;
    @Getter
    private JLabel boardSizeLabel;
    JButton buttonRestart;
    JButton buttonCheck;
    private int currentBoardSize;
    private int currentLevel;

    public GameLogic(JFrame mainGame,JButton buttonRestart, JButton buttonCheck) {
        this.game = mainGame;
        this.buttonRestart = buttonRestart;
        this.buttonCheck = buttonCheck;
        this.currentBoardSize = INITIAL_BOARD_SIZE;
        this.currentLevel = 1;
        this.initializeNewBoard(this.currentBoardSize);
        this.game.add(this.currentBoard);
        this.levelLabel = new JLabel();
        this.boardSizeLabel = new JLabel();
        this.updateLevelLabel();
        this.updateBoardSizeLabel();
    }

    private void updateBoardSizeLabel() {
        this.boardSizeLabel.setText("CURRENT BOARD SIZE: " + this.currentBoardSize + "x" + this.currentBoardSize);
        this.boardSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.game.revalidate();
        this.game.repaint();
    }

    private void updateLevelLabel(){
        this.levelLabel.setText("LEVEL: " + this.currentLevel);
        this.levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    private void initializeNewBoard(int size) {
        this.currentBoard = new Board(size);
        this.currentBoard.addMouseMotionListener(this);
        this.currentBoard.addMouseListener(this);
    }
    private void gameRestart(int level) {
        this.game.remove(this.currentBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.game.add(this.currentBoard);
        this.currentLevel = level;
        this.updateLevelLabel();
        this.game.revalidate();
        this.game.repaint();
    }
    private boolean checkPipesConnection(){
        return this.currentBoard.putWater();
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        this.currentBoardSize = ((JSlider) e.getSource()).getValue();
        this.updateBoardSizeLabel();
        this.gameRestart(1);
        this.game.setFocusable(true);
        this.game.requestFocus();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buttonRestart) {
            this.gameRestart(1);
        } else if (e.getSource() == this.buttonCheck) {
            if ( this.checkPipesConnection()){
                this.currentLevel++;
                this.updateLevelLabel();
                this.gameRestart(currentLevel);
            }
        }
        this.game.revalidate();
        this.game.repaint();
        this.game.setFocusable(true);
        this.game.requestFocus();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        Component current = this.currentBoard.getComponentAt(e.getX(), e.getY());
        this.currentBoard.repaint();
        if (!(current instanceof Tile)) {
            return;
        }
        ((Tile) current).setHighlight(true);
    }
    public void mouseClicked(MouseEvent e) {
        Component current = this.currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Tile)) {
            return;
        }
        switch (((Tile) current).getPipeState()){
            case UP:
                ((Tile) current).setPipeState(State.RIGHT);
                break;
            case RIGHT:
                ((Tile) current).setPipeState(State.DOWN);
                break;
            case DOWN:
                ((Tile) current).setPipeState(State.LEFT);
                break;
            case LEFT:
                ((Tile) current).setPipeState(State.UP);
                break;
            case VERTICAL:
                ((Tile) current).setPipeState(State.HORIZONTAL);
                break;
            case HORIZONTAL:
                ((Tile) current).setPipeState(State.VERTICAL);
                break;
        }
        ((Tile) current).setHighlight(true);
        current.revalidate();
        current.repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.gameRestart(1);
                break;
            case KeyEvent.VK_ENTER:
                if ( this.checkPipesConnection()){
                    this.currentLevel++;
                    this.updateLevelLabel();
                    this.gameRestart(currentLevel);
                }
                this.game.revalidate();
                this.game.repaint();
                break;
            case KeyEvent.VK_ESCAPE:
                this.game.dispose();
        }
    }
}