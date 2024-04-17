package sk.stuba.fei.uim.oop.tile;

import sk.stuba.fei.uim.oop.board.Direction;
import sk.stuba.fei.uim.oop.board.State;

import javax.swing.*;
import java.awt.*;

public class StartEndPipe extends  Tile{

    public StartEndPipe(){

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.WHITE);
        this.pipeState = State.UP;
        this.direction1 = Direction.UP;
        this.direction2 = Direction.NODIREC;
        this.isConnected = false;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(this.getWidth()/3));
        g2d.drawOval((int) (0 + this.getWidth() * 0.35), (int) (0 + this.getHeight() * 0.35), (int) (this.getWidth()*0.3),(int) (this.getHeight()*0.3));
        switch (pipeState){
            case UP:
                g2d.drawLine(this.getWidth()/2,0,this.getWidth()/2,this.getHeight()/2);
                if (this.isConnected) {
                    g2d.setColor(Color.BLUE);
                    g2d.setStroke(new BasicStroke(this.getWidth() / 5));
                    g2d.drawLine(this.getWidth()/2,0,this.getWidth()/2,this.getHeight()/2);
                    this.isConnected = false;
                }
                this.direction1 = Direction.UP;
                break;
            case RIGHT:
                g2d.drawLine(this.getWidth()/2,this.getHeight()/2, this.getWidth(),this.getHeight()/2);
                if (this.isConnected) {
                    g2d.setColor(Color.BLUE);
                    g2d.setStroke(new BasicStroke(this.getWidth() / 5));
                    g2d.drawLine(this.getWidth()/2,this.getHeight()/2, this.getWidth(),this.getHeight()/2);
                    this.isConnected = false;
                }
                this.direction1 = Direction.RIGHT;
                break;
            case DOWN:
                g2d.drawLine(this.getWidth()/2,this.getHeight()/2,this.getWidth()/2,this.getHeight());
                if (this.isConnected) {
                    g2d.setColor(Color.BLUE);
                    g2d.setStroke(new BasicStroke(this.getWidth() / 5));
                    g2d.drawLine(this.getWidth()/2,this.getHeight()/2,this.getWidth()/2,this.getHeight());
                    this.isConnected = false;
                }
                this.direction1 = Direction.DOWN;
                break;
            case LEFT:
                g2d.drawLine(this.getWidth()/2,this.getHeight()/2, 0,this.getHeight()/2);
                if (this.isConnected) {
                    g2d.setColor(Color.BLUE);
                    g2d.setStroke(new BasicStroke(this.getWidth() / 5));
                    g2d.drawLine(this.getWidth()/2,this.getHeight()/2, 0,this.getHeight()/2);
                    this.isConnected = false;
                }
                this.direction1 = Direction.LEFT;
                break;
        }
        if (this.getY() > 400){
            g2d.setColor(Color.RED);
            if (isConnected){
                System.out.println("LAST IS CONNECTED");
            }
        } else {
            g2d.setColor(Color.GREEN);
            if (isConnected){
                System.out.println("FIRST IS CONNECTED");
            }
        }
        g2d.setStroke(new BasicStroke(this.getWidth()/5));
        g2d.drawOval((int) (0 + this.getWidth() * 0.4), (int) (0 + this.getHeight() * 0.4), (int) (this.getWidth()*0.2),(int) (this.getHeight()*0.2));
    }
}
