package com.example.apro1_22z_pro_sdscmk;

public class Data {
    private static final Data instance = new Data();
    public static Data getInstance(){
        return instance;
    }
    private Data(){}
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    private String difficulty;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    private String algorithm;
}
