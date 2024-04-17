package sk.stuba.fei.uim.oop.tile;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

import sk.stuba.fei.uim.oop.board.Direction;
import sk.stuba.fei.uim.oop.board.State;
public class Tile  extends JPanel {

    @Setter @Getter
    protected boolean highlight;
    @Setter @Getter
    protected State pipeState;
    @Setter @Getter
    protected boolean isConnected;
    @Getter
    protected Direction direction1;
    @Getter
    protected Direction direction2;

    public Tile(){
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.WHITE);
        this.pipeState = State.EMPTY;
        this.isConnected = false;
        this.direction1 = Direction.NODIREC;
        this.direction2 = Direction.NODIREC;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.highlight) {
            this.setBackground(Color.YELLOW);
            this.highlight = false;
        } else {
            this.setBackground(Color.WHITE);
        }
    }
}
