package com.example.apro1_22z_pro_sdscmk;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * The BackTracking class generates a maze using the backtracking algorithm.
 * Each unit in the maze has north, south, east, and west walls.
 * The maze is converted to a 2D array of characters.
 */
public class BackTracking {
    private int size;
    private Unit[] units;

    /**
     * This method returns 2D array of characters representing the maze.
     *
     * @return 2D array of characters representing the maze
     */
    public char[][] getLabTable() {
        return labTable;
    }

    private char[][] labTable;

    /**
     * This is the constructor for the maze class that create given size blank board,
     * generate maze and convert it to 2D character array
     *
     * @param size size of maze
     */
    public BackTracking(int size) {
        this.size = size;
        generateBlank(size);
        generateLabirynt(size);
        toTable();
    }

    /**
     * This method is looking for unit containing given parameters.
     *
     * @param x x position of unit
     * @param y y position of unit
     * @return unit on given position
     */
    private Unit getUnit(int x, int y) {
        for (int i = 0; i < size * size; i++) {
            if (units[i].getX() == x && units[i].getY() == y) return units[i];
        }
        return null;
    }

    /**
     * This method generates blank board in order to generate maze later.
     * @param size size of maze
     */
    private void generateBlank(int size) {
        int index = 0;
        units = new Unit[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                units[index] = new Unit(i, j);
                if (i == 0) units[index].setIfWallN(2);
                if (i == size - 1) units[index].setIfWallS(2);
                if (j == 0) units[index].setIfWallW(2);
                if (j == size - 1) units[index].setIfWallE(2);
                index++;
            }
        }
    }

    /**
     * This method converts 1D Unit array to 2D character array.
     */
    public void toTable() {
        labTable = new char[2 * size + 1][2 * size + 1];
        for (int i = 0; i < 2 * size + 1; i++) {
            labTable[0][i] = 'X';
        }
        int index = 0;
        for (int i = 1; i < 2 * size - 1; i++) {
            int igrek = 0;
            labTable[i][igrek] = 'X';
            igrek++;
            for (int j = 0; j < size; j++) {
                labTable[i][igrek] = ' ';
                igrek++;
                if (units[index].getIfWallE() != 0) labTable[i][igrek] = 'X';
                else labTable[i][igrek] = ' ';
                igrek++;
                index++;
            }
            index = index - size;
            i++;
            igrek = 0;
            labTable[i][igrek] = 'X';
            igrek++;
            for (int j = 0; j < size; j++) {
                if (units[index].getIfWallS() != 0) labTable[i][igrek] = 'X';
                else labTable[i][igrek] = ' ';
                igrek++;
                index++;
                labTable[i][igrek] = 'X';
                igrek++;
            }
        }
        int igrek = 0;
        labTable[2 * size - 1][igrek] = 'X';
        igrek++;
        for (int j = 0; j < size; j++) {
            labTable[2 * size - 1][igrek] = ' ';
            igrek++;
            if (units[index].getIfWallE() != 0) labTable[2 * size - 1][igrek] = 'X';
            else labTable[2 * size - 1][igrek] = ' ';
            igrek++;
            index++;
        }
        for (int i = 0; i < 2 * size + 1; i++) {
            labTable[2 * size][i] = 'X';
        }
    }

    /**
     * This method looks for unvisited neighbours of given unit.
     *
     * @param unit unit, whose neighbours are being considered
     * @return
     */
    public Unit findUnvisitedNeighbour(Unit unit) {
        ArrayList<Unit> lista = new ArrayList<Unit>();
        if (unit.getIfWallE() != 2) {
            if (!getUnit(unit.getX(), unit.getY() + 1).isVisited()) lista.add(getUnit(unit.getX(), unit.getY() + 1));
        }
        if (unit.getIfWallW() != 2) {
            if (!getUnit(unit.getX(), unit.getY() - 1).isVisited()) lista.add(getUnit(unit.getX(), unit.getY() - 1));
        }
        if (unit.getIfWallS() != 2) {
            if (!getUnit(unit.getX() + 1, unit.getY()).isVisited()) lista.add(getUnit(unit.getX() + 1, unit.getY()));
        }
        if (unit.getIfWallN() != 2) {
            if (!getUnit(unit.getX() - 1, unit.getY()).isVisited()) lista.add(getUnit(unit.getX() - 1, unit.getY()));
        }
        int length = lista.size();
        if (length == 0) return null;
        Random generator = new Random();
        int pick = generator.nextInt(length);
        return lista.get(pick);
    }

    /**
     * This method carves a tunnel between two units.
     *
     * @param a first unit
     * @param b second unit
     */
    private void carve(Unit a, Unit b) {
        if (a.getX() - b.getX() == 1) {
            a.setIfWallN(0);
            b.setIfWallS(0);
        } else if (a.getX() - b.getX() == -1) {
            a.setIfWallS(0);
            b.setIfWallN(0);
        } else if (a.getY() - b.getY() == -1) {
            a.setIfWallE(0);
            b.setIfWallW(0);
        } else if (a.getY() - b.getY() == 1) {
            a.setIfWallW(0);
            b.setIfWallE(0);
        }
    }

    /**
     * This method looks for starting position for backtracking algorithm.
     *
     * @return starting position of backtracking algorithm
     */
    private int findStart() {
        Random rand = new Random();
        return rand.nextInt(size);
    }

    /**
     * This method generates maze.
     *
     * @param size size of maze
     */
    private void generateLabirynt(int size) {
        Stack<Unit> stak = new Stack<Unit>();
        Unit current = getUnit(findStart(), findStart());
        current.setVisited(true);
        Unit previous = null;
        stak.add(current);
        while (!stak.empty()) {
            previous = current;
            if (findUnvisitedNeighbour(previous) != null) {
                current = findUnvisitedNeighbour(previous);
                carve(previous, current);
                stak.add(current);
                current.setVisited(true);
            } else {
                while (findUnvisitedNeighbour(current) == null && !stak.empty()) {
                    stak.pop();
                    if (!stak.empty()) current = stak.lastElement();
                }
            }
        }
    }

}
