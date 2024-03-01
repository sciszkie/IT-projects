package com.example.apro1_22z_pro_sdscmk;

/**
 * Unit class represents a unit of the maze with four walls, including the north, south, east, and west walls.
 * Each unit has x and y positions and a boolean value indicating if it has been visited.
 */
public class Unit {
    /**
     * This method returns the x position of the unit.
     *
     * @return x position of unit
     */
    public int getX() {
        return x;
    }

    /**
     * This method returns the y position of the unit.
     *
     * @return y position of unit
     */
    public int getY() {
        return y;
    }

    /**
     * This method returns the boolean value indicating if the north wall of the unit is present or not.
     *
     * @return state of north wall
     */
    public int getIfWallN() {
        return ifWallN;
    }

    /**
     * This method sets the north wall of the unit.
     *
     * @param ifWallN boolean value indicating if the north wall of the unit is present or not.
     */
    public void setIfWallN(int ifWallN) {
        this.ifWallN = ifWallN;
    }

    /**
     * This method returns the boolean value indicating if the south wall of the unit is present or not.
     *
     * @return state of south wall
     */
    public int getIfWallS() {
        return ifWallS;
    }

    /**
     * This method sets the south wall of the unit.
     *
     * @param ifWallS boolean value indicating if the south wall of the unit is present or not.
     */
    public void setIfWallS(int ifWallS) {
        this.ifWallS = ifWallS;
    }

    /**
     * This method returns the boolean value indicating if the west wall of the unit is present or not.
     *
     * @return state of west wall
     */
    public int getIfWallW() {
        return ifWallW;
    }

    /**
     * This method sets the west wall of the unit.
     *
     * @param ifWallW boolean value indicating if the west wall of the unit is present or not.
     */
    public void setIfWallW(int ifWallW) {
        this.ifWallW = ifWallW;
    }

    /**
     * This method returns the boolean value indicating if the east wall of the unit is present or not.
     *
     * @return state of east wall
     */
    public int getIfWallE() {
        return ifWallE;
    }

    /**
     * This method sets the east wall of the unit.
     *
     * @param ifWallE boolean value indicating if the east wall of the unit is present or not.
     */
    public void setIfWallE(int ifWallE) {
        this.ifWallE = ifWallE;
    }

    /**
     * This method returns a boolean value indicating if the unit has been visited or not.
     *
     * @return visited boolean
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * This method sets the unit as visited.
     *
     * @param visited boolean value indicating if the unit has been visited or not.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * This is the constructor for the unit class that initializes the x and y positions of the unit.
     *
     * @param x x position of unit
     * @param y y position of unit
     */
    public Unit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private final int x, y;
    private int ifWallS = 1;
    private int ifWallW = 1;
    private int ifWallE = 1;
    private int ifWallN = 1;
    private boolean visited = false;
}
