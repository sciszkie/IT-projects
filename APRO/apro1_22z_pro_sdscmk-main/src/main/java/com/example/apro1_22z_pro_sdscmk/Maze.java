package com.example.apro1_22z_pro_sdscmk;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * The Maze class generates maze using BackTracking and Eller classes and saves it in 2D character array.
 * Class randomly generates entrance, exit units and solve maze.
 */
public class Maze {
    private int size;
    private Pairr entrance;

    /**
     * This method returns the entrance position.
     *
     * @return entrance position
     */
    public Pairr getEntrance() {
        return entrance;
    }

    /**
     * This method returns the exit position.
     *
     * @return exit position
     */
    public Pairr getExit() {
        return exit;
    }

    /**
     * This method returns 2D array with solved maze.
     *
     * @return 2D array with solved maze
     */
    public char[][] getMazeTableWithPath() {
        char[][] copyMazeTableWithPath = new char[mazeTable.length][mazeTable.length];
        for (int y = 0; y < mazeTable.length; y++) {
            for (int x = 0; x < mazeTable[y].length; x++) {
                copyMazeTableWithPath[y][x] = mazeTableWithPath[y][x];
            }
        }
        return copyMazeTableWithPath;
    }

    private char[][] mazeTableWithPath;
    private Pairr exit;

    /**
     * This method returns 2D array with maze.
     *
     * @return 2D array with maze
     */
    public char[][] getMazeTable() {
        char[][] copyMazeTable = new char[mazeTable.length][mazeTable.length];
        for (int y = 0; y < mazeTable.length; y++) {
            for (int x = 0; x < mazeTable[y].length; x++) {
                copyMazeTable[y][x] = mazeTable[y][x];
            }
        }
        return copyMazeTable;
    }

    private char[][] mazeTable;

    private ArrayList<Pairr> onEdges = new ArrayList<>();

    /**
     * This is the constructor for generating maze using chosen size and algorithm.
     *
     * @param size      size of maze
     * @param indicator indicator determining which algorithm to use
     */
    public Maze(int size, char indicator) {
        this.size = size;
        mazeTable = new char[2 * size + 1][2 * size + 1];
        if (indicator == 'b') {
            BackTracking lab = new BackTracking(size);
            mazeTable = lab.getLabTable();
        }
        if (indicator == 'e') {
            Eller lab = new Eller(size);
            mazeTable = lab.getMaze();
        }
        fillOnEdges();
        entrance = findEnd();
        mazeTable[entrance.getA()][entrance.getB()] = 'E';
        exit = findEnd();
        mazeTable[exit.getA()][exit.getB()] = 'F';
        findPath();
    }

    /**
     * This is the constructor for uploading maze using 2D array.
     *
     * @param size     size of maze
     * @param labTable 2D array containing maze
     */
    public Maze(int size, char[][] labTable) {
        this.size = size;
        mazeTable = new char[2 * size + 1][2 * size + 1];
        for (int y = 0; y < mazeTable.length; y++) {
            for (int x = 0; x < mazeTable[y].length; x++) {
                mazeTable[y][x] = labTable[y][x];
                if (labTable[y][x] == 'E') entrance = new Pairr(y, x);
                else if (labTable[y][x] == 'F') exit = new Pairr(y, x);
            }
        }
        findPath();
    }

    /**
     * This method generates blank array in order to generate solved maze.
     */
    private void makeBlankMazeTableWithPath() {
        mazeTableWithPath = new char[mazeTable.length][mazeTable.length];
        for (int y = 0; y < mazeTable.length; y++) {
            for (int x = 0; x < mazeTable[y].length; x++) {
                mazeTableWithPath[y][x] = mazeTable[y][x];
            }
        }
    }

    /**
     * This method solves maze.
     */
    private void findPath() {
        Stack<Pairr> stak = new Stack<Pairr>();
        boolean[][] visited = new boolean[mazeTable.length][mazeTable.length];
        makeBlankMazeTableWithPath();
        int currentY = entrance.getA();
        int currentX = entrance.getB();
        visited[currentY][currentX] = true;
        while (currentY != exit.getA() || currentX != exit.getB()) {
            if (currentY != mazeTable.length - 1 && mazeTable[currentY + 1][currentX] != 'X'
                    && visited[currentY + 1][currentX] == false) {
                currentY++;
                stak.add(new Pairr(currentY, currentX));
                visited[currentY][currentX] = true;
                continue;
            } else if (currentX != mazeTable.length - 1 && mazeTable[currentY][currentX + 1] != 'X'
                    && visited[currentY][currentX + 1] == false) {
                currentX++;
                stak.add(new Pairr(currentY, currentX));
                visited[currentY][currentX] = true;
                continue;
            } else if (currentY != 0 && mazeTable[currentY - 1][currentX] != 'X'
                    && visited[currentY - 1][currentX] == false) {
                currentY--;
                stak.add(new Pairr(currentY, currentX));
                visited[currentY][currentX] = true;
                continue;
            } else if (currentX != 0 && mazeTable[currentY][currentX - 1] != 'X'
                    && visited[currentY][currentX - 1] == false) {
                currentX--;
                stak.add(new Pairr(currentY, currentX));
                visited[currentY][currentX] = true;
                continue;
            }
            stak.pop();
            currentY = stak.lastElement().getA();
            currentX = stak.lastElement().getB();
        }
        while (!stak.empty()) {
            mazeTableWithPath[stak.lastElement().getA()][stak.lastElement().getB()] = '.';
            stak.pop();
        }
        mazeTableWithPath[exit.getA()][exit.getB()] = 'F';
    }

    /**
     * This method generates base with possible entrance and exit cells.
     */
    private void fillOnEdges() {
        for (int y = 0; y < mazeTable.length; y++) {
            for (int x = 0; x < mazeTable[y].length; x++) {
                if (y == 0 && mazeTable[y + 1][x] == ' ') {
                    onEdges.add(new Pairr(y, x));
                }
                if (x == 0 && mazeTable[y][x + 1] == ' ') {
                    onEdges.add(new Pairr(y, x));
                }
                if (y == mazeTable.length - 1 && mazeTable[y - 1][x] == ' ') {
                    onEdges.add(new Pairr(y, x));
                }
                if (x == mazeTable.length - 1 && mazeTable[y][x - 1] == ' ') {
                    onEdges.add(new Pairr(y, x));
                }
            }
        }
    }

    /**
     * This method randomly finds entrance and exit cells.
     *
     * @return cell, that can be entrance or exit of maze
     */
    private Pairr findEnd() {
        Random rand = new Random();
        int index = rand.nextInt(onEdges.size());
        return onEdges.get(index);
    }
}
