package com.example.apro1_22z_pro_sdscmk;

/**
 * Pairr class contains two int values.
 */
public class Pairr {
    /**
     * This method sets a value.
     *
     * @param a a value
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * This method sets a value.
     *
     * @param b b value
     */
    public void setB(int b) {
        this.b = b;
    }

    /**
     * This method return a value.
     *
     * @return a value
     */
    public int getA() {
        return a;
    }

    /**
     * This method return b value.
     *
     * @return b value
     */
    public int getB() {
        return b;
    }

    private int a;
    private int b;

    /**
     * This is the constructor of Pairr class that initializes a and b values.
     *
     * @param a a value
     * @param b b value
     */
    public Pairr(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
