package sk.stuba.fei.uim.oop.board;

import java.util.ArrayList;
import java.util.Collections;

import lombok.Getter;

public class Node {
    @Getter
    private final int x;
    @Getter
    private final int y;
    @Getter
    private ArrayList<Direction> neighbours;


    public Node(int x, int y){
        this.x = x;
        this.y = y;
        this.neighbours = new ArrayList<>();
    }
    public void addNeighbours(int size){
        if (this.x != 0){
            neighbours.add(Direction.LEFT);
        }
        if (this.x != (size-1)){
            neighbours.add(Direction.RIGHT);
        }
        if (this.y != 0){
            neighbours.add(Direction.UP);
        }
        if (this.y != (size-1)){
            neighbours.add(Direction.DOWN);
        }
        Collections.shuffle(neighbours);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        Node other = (Node) obj;
        return (this.getX() == other.getX() && this.getY() == other.getY());
    }

}
