package sk.stuba.fei.uim.oop.tile;


import sk.stuba.fei.uim.oop.board.Direction;
import sk.stuba.fei.uim.oop.board.State;

import javax.swing.*;
import java.awt.*;



public class StraightPipe extends Tile {

    public StraightPipe() {

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.WHITE);
        this.pipeState = State.VERTICAL;
        this.direction1 = Direction.UP;
        this.direction2 = Direction.DOWN;
        this.isConnected = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(this.getWidth()/3));
        switch (pipeState){
            case VERTICAL:
                g2d.drawLine(this.getWidth()/2,0,this.getWidth()/2,this.getHeight());
                if (this.isConnected) {
                    g2d.setColor(Color.BLUE);
                    g2d.setStroke(new BasicStroke(this.getWidth() / 5));
                    g2d.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
                    this.isConnected = false;
                }
                this.direction1 = Direction.UP;
                this.direction2 = Direction.DOWN;
                break;
            case HORIZONTAL:
                g2d.drawLine(0,this.getHeight()/2, this.getWidth(),this.getHeight()/2);
                if (this.isConnected) {
                    g2d.setColor(Color.BLUE);
                    g2d.setStroke(new BasicStroke(this.getWidth() / 5));
                    g2d.drawLine(0,this.getHeight()/2, this.getWidth(),this.getHeight()/2);
                    this.isConnected = false;
                }
                this.direction1 = Direction.RIGHT;
                this.direction2 = Direction.LEFT;
                break;
        }
    }

}
