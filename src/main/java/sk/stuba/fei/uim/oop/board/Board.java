package sk.stuba.fei.uim.oop.board;


import lombok.Getter;
import sk.stuba.fei.uim.oop.tile.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Board extends JPanel{
    @Getter
    private Tile[][] board;
    private ArrayList<Node> path;
    @Getter
    private Node startNode;
    @Getter
    private Node endNode;
    private HashSet<Node> visited;
    private int size;

    public Board(int size){
        this.size = size;
        this.initializeStartEndNode();
        this.visited = new HashSet<>();
        this.visited.add(startNode);
        this.path = new ArrayList<>();
        this.findPath(this.startNode, this.endNode, this.visited,this.path);
        this.initializeBoard(this.size);
        this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.setBackground(Color.LIGHT_GRAY);
    }
    private void initializeBoard(int size){
        this.board = new Tile[size][size];
        this.setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new Tile();
            }
        }
        for (int i = 1; i < path.size()-1; i++){
            if(path.get(i - 1).getY() == path.get(i + 1).getY() || path.get(i - 1).getX() == path.get(i + 1).getX()){
                this.board[path.get(i).getY()][path.get(i).getX()] = new StraightPipe();
            } else {
                this.board[path.get(i).getY()][path.get(i).getX()] = new BentPipe();
            }
        }
        this.board[this.startNode.getY()][this.startNode.getX()] = new StartEndPipe();
        this.board[this.endNode.getY()][this.endNode.getX()] = new StartEndPipe();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.add(this.board[i][j]);
            }
        }
    }
    private boolean findPath(Node current, Node end, HashSet<Node> visited, ArrayList<Node> path) {
        visited.add(current);
        if (current.equals(end)) {
            path.add(current);
            return true;
        }
        ArrayList<Node> unvisitedNeighbors = getUnvisitedNeighbors(current, visited);
        Collections.shuffle(unvisitedNeighbors);
        for (Node neighbor : unvisitedNeighbors) {
            ArrayList<Node> pathCopy = new ArrayList<>(path);
            boolean foundPath = findPath(neighbor, end, visited, pathCopy);
            if (foundPath) {
                path.clear();
                path.addAll(pathCopy);
                path.add(0, current);
                return true;
            }
        }
        return false;
    }

    private ArrayList<Node> getUnvisitedNeighbors (Node node, HashSet<Node> visited){
        ArrayList<Node> unvisitedNeighbors = new ArrayList<>();
        node.addNeighbours(this.size);
        int neighboursX;
        int neighboursY;
        for (Direction neighbor : node.getNeighbours()) {
            neighboursX = node.getX() + neighbor.getX();
            neighboursY = node.getY() + neighbor.getY();
            boolean isVisited = false;
            for (Node visitedNode : visited) {
                if (neighboursX == visitedNode.getX() && neighboursY == visitedNode.getY()) {
                    isVisited = true;
                    break;
                }
            }
            if (!isVisited) {
                unvisitedNeighbors.add(new Node(neighboursX, neighboursY));
            }
        }
        return unvisitedNeighbors;
    }
    public boolean putWater(){
        int currentX = this.getStartNode().getX();
        int currentY = 0;
        Tile current = board[currentY][currentX];
        current.setConnected(true);

        int nextX = currentX+current.getDirection1().getX();
        int nextY = currentY+current.getDirection1().getY();
        Tile next = null;
        if (nextX < 0 || nextY < 0 || nextX >= this.size || nextY >= this.size){
            next = null;
        } else {
            next = board[nextY][nextX];
        }
        while (next != null){
            if (nextX + next.getDirection1().getX() == currentX && nextY + next.getDirection1().getY() == currentY){
                next.setConnected(true);
                currentX = nextX;
                currentY = nextY;
                current = board[currentY][currentX];
                nextX = currentX + current.getDirection2().getX();
                nextY = currentY + current.getDirection2().getY();
            } else if (nextX + next.getDirection2().getX() == currentX && nextY + next.getDirection2().getY() == currentY){
                next.setConnected(true);
                currentX = nextX;
                currentY = nextY;
                current = board[currentY][currentX];
                nextX = currentX + current.getDirection1().getX();
                nextY = currentY + current.getDirection1().getY();
            } else {
                break;
            }
            if (nextX < 0 || nextY < 0 || nextX >= this.size || nextY >= this.size){
                next = null;
            } else {
                next = board[nextY][nextX];
            }
            if (current == next){
                if (next == this.board[endNode.getY()][endNode.getX()] || current == this.board[endNode.getY()][endNode.getX()]){
                    return true;
                }
                return false;
            }

        }
        return false;
    }

    private void initializeStartEndNode() {
        int startPosition = getRandomPosition(this.size);
        this.startNode = new Node(startPosition, 0);
        int finishPosition = getRandomPosition(this.size);
        this.endNode = new Node(finishPosition, this.size - 1);
    }

    private int getRandomPosition(int size){
        return (int) (Math.random() * size);
    }
}
