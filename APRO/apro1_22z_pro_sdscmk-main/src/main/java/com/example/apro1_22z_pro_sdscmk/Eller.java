package com.example.apro1_22z_pro_sdscmk;

import java.util.Objects;
import java.util.Random;

class Eller {
    private char[][] maze;
    public Eller(int s) {
        int w = s * 2 + 1;
        int s1 = s * 2 + 1;
        String[][] tab = ChangeWallsToX(generator(w, s1), w, s1);
        maze = new char[w][s1];
        for (int i = 0; i < w; i++) {
            for (int y = 0; y < s1; y++) {
                this.maze[i][y] = tab[i][y].charAt(0);
            }
        }

    }

    private String[][] generator(int W, int S) {
        String[][] lab = new String[W][S];
        for (int i = 0; i < S; i++) {
            lab[0][i] = "_";
        }
        int x = 1;
        for (int i = 1; i < S - 1; i += 2) {
            lab[1][i] = String.valueOf(x);
            lab[1][i + 1] = "|";
            x++;
        }
        lab[1][0] = "|";
        lab[1][S - 1] = "|";
        for (int i = 1; i < S - 2; i += 2) {
            int num = randomInt();
            if (num == 1) {
                lab[1][i + 2] = lab[1][i];
                lab[1][i + 1] = " ";
            }
        }
        for (int y = 2; y < W - 1; y += 2) {
            for (int i = 0; i < S - 1; i += 2) {
                lab[y][i] = "|";
                lab[y][i + 1] = "-";
            }
            lab[y][S - 1] = "|";
            for (int i = 1; i < S; i += 2) {
                int num = randomInt();
                if (num == 1) {
                    lab[y + 1][i] = lab[y - 1][i];
                    lab[y][i] = " ";
                } else {
                    lab[y + 1][i] = "X";
                }
                lab[y + 1][i + 1] = "|";
            }
            lab[y + 1][0] = "|";
            lab[y + 1][S - 1] = "|";
            boolean check;
            int temp = 1;
            while (temp < S - 2) {
                check = false;
                int pom = temp;
                for (int i = temp; i < S - 2; i += 2) {
                    if (!Objects.equals(lab[y - 1][i], lab[y - 1][i + 2])) {
                        temp = i;
                        break;
                    }
                    if (i == S - 4) {
                        temp = i + 2;
                    }
                }
                for (int i = pom; i < temp + 1; i += 2) {
                    if (Objects.equals(lab[y + 1][i], lab[y - 1][i])) {
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    if (pom == temp) {
                        lab[y + 1][temp] = lab[y - 1][temp];
                        lab[y][temp] = " ";
                    } else {
                        while (!check) {
                            for (int i = pom; i < temp + 1; i += 2) {
                                int num = randomInt();
                                if (num == 1) {
                                    lab[y + 1][i] = lab[y - 1][i];
                                    lab[y][i] = " ";
                                    lab[y + 1][i + 1] = "|";
                                    check = true;
                                } else {
                                    lab[y + 1][i] = "X";
                                    lab[y + 1][i + 1] = "|";
                                }
                            }
                        }
                    }
                }
                temp += 2;
            }
            if (!Objects.equals(lab[y - 1][S - 2], lab[y - 1][S - 4])) {
                if (!Objects.equals(lab[y - 1][S - 2], lab[y + 1][S - 2])) {
                    lab[y + 1][S - 2] = lab[y - 1][S - 2];
                    lab[y][S - 2] = " ";
                }
            }
            int max = 0;
            int pom;
            for (int i = 1; i < S - 1; i += 2) {
                if (!Objects.equals(lab[y + 1][i], "X")) {
                    pom = Integer.parseInt(lab[y + 1][i]);
                    if (pom > max) {
                        max = Integer.parseInt(lab[y + 1][i]);
                    }
                }
            }
            for (int i = 1; i < S - 1; i += 2) {
                if (Objects.equals(lab[y + 1][i], "X")) {
                    lab[y + 1][i] = String.valueOf(max + 1);
                    max++;
                }
            }
            for (int i = 1; i < S - 2; i += 2) {
                int num = randomInt();
                if (num == 1) {
                    lab[y + 1][i + 2] = lab[y + 1][i];
                    lab[y + 1][i + 1] = " ";
                }
            }
        }
        for (int i = 0; i < S; i++) {
            lab[W - 1][i] = "¯";
        }
        for (int i = 1; i < W - 1; i += 2) {
            for (int y = 1; y < S - 1; y += 2) {
                lab[i][y] = " ";
            }
        }
        for (int i = 1; i < S - 2; i++) {
            lab[W - 2][i] = " ";
        }
        return lab;
    }

    private int randomInt() {
        Random rand = new Random();
        return rand.nextInt((1) + 1);
    }

    private String[][] ChangeWallsToX(String[][] Temp, int W, int S) {
        String[][] temp = new String[W][S];
        for (int i = 0; i < W; i++) {
            System.arraycopy(Temp[i], 0, temp[i], 0, S);
        }
        for (int i = 0; i < W; i++) {
            for (int y = 0; y < S; y++) {
                if (Objects.equals(temp[i][y], "T") || Objects.equals(temp[i][y], "X") || Objects.equals(temp[i][y], "S") || Objects.equals(temp[i][y], "*")) {
                    temp[i][y] = " ";
                }
            }
        }
        for (int i = 0; i < W; i++) {
            for (int y = 0; y < S; y++) {
                if (Objects.equals(temp[i][y], "|") || Objects.equals(temp[i][y], "-") || Objects.equals(temp[i][y], "_") || Objects.equals(temp[i][y], "¯")) {
                    temp[i][y] = "X";
                }
            }
        }
        return temp;
    }

    public char[][] getMaze() {
        return maze;
    }
}
