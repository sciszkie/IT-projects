package com.example.apro1_22z_pro_sdscmk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * LabiryntController class controls maze part of user interface.
 */
public class LabiryntController implements Initializable {
    private Circle circ = new Circle(8);
    private int size;
    private char indicator;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String path;
    Data data = Data.getInstance();
    private boolean gameMode, solveMode, generated;
    private char[][] labTable, labTableWithPath;
    private Pairr current, entrance, exit;
    @FXML
    GridPane maze = new GridPane();
    @FXML
    Button play;
    @FXML
    BorderPane bord;
    /**
     * This method saves maze in .txt files.
     *
     * @param event action on using button
     * @throws IOException
     */
    public void save(ActionEvent event) throws IOException {
        if (!generated) return;
        if (!solveMode) {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File selectedFile = fileChooser.showSaveDialog(stage);
            if (selectedFile != null) {
                path = selectedFile.getPath();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                    char[][] tablica = labTable;
                    writer.write(((Integer) size).toString());
                    writer.newLine();
                    for (int i = 0; i < tablica.length; i++) {
                        for (int j = 0; j < tablica[i].length; j++) {
                            writer.write(tablica[i][j]);
                        }
                        writer.newLine();
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        /*
        else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("templates/solution/labirynt.txt"))) {
                char[][] tablica = labTableWithPath;
                writer.write(((Integer) size).toString());
                writer.newLine();
                for (int i = 0; i < tablica.length; i++) {
                    for (int j = 0; j < tablica[i].length; j++) {
                        writer.write(tablica[i][j]);
                    }
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

 */
        }
    }

    /**
     * This method loads maze from .txt files.
     *
     * @param event
     * @throws IOException
     */
    public void load(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            path = selectedFile.getPath();
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                line = reader.readLine();
                size = Integer.parseInt(line);
                labTable = new char[2 * size + 1][2 * size + 1];
                for (int i = 0; i < 2 * size + 1; i++) {
                    line = reader.readLine();
                    for (int j = 0; j < 2 * size + 1; j++) {
                        labTable[i][j] = line.charAt(j);
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            generated = true;
            gameMode = false;
            clear();
            Maze lab = new Maze(size, labTable);
            entrance = lab.getEntrance();
            exit = lab.getExit();
            labTableWithPath = lab.getMazeTableWithPath();
            uploadMaze(labTable);
            stage = (Stage) (bord.getScene().getWindow());
            stage.sizeToScene();
        }
    }

    /**
     * This method sets size of maze and sets.
     *
     * @param difficulty difficulty of maze
     */
    public void setSize(String difficulty) {
        if (difficulty == "easy") {
            size = 10;
            path = "templates/maze/easy.txt";
        }
        if (difficulty == "medium") {
            size = 15;
            path = "templates/maze/medium.txt";
        }
        if (difficulty == "hard") {
            size = 20;
            path = "templates/maze/hard.txt";
        }
    }

    /**
     * This method sets algorithm used for generating.
     *
     * @param algorithm generating algorithm
     */
    public void setAlgorithm(String algorithm) {
        if (algorithm == "Eller's") {
            indicator = 'e';
        }
        if (algorithm == "BackTracking") {
            indicator = 'b';
        }
    }

    /**
     * This method sets position of player.
     *
     * @param a a position parameter
     * @param b b position parameter
     */
    private void move(int a, int b) {
        clear();
        uploadMaze(labTable);
        maze.add(circ, b, a);
        if (current.getB() == exit.getB() && current.getA() == exit.getA()) {
            gameMode = false;
            uploadMaze(labTable);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.show();
            alert.setHeaderText("Congratulations!");
            alert.setTitle("Congratulations!");
            alert.setContentText("You've won!");
        }
    }

    /**
     * This method changes look of scene.
     *
     * @param table new version to display
     */
    private void uploadMaze(char[][] table) {
        StackPane[][] rectangle = new StackPane[2 * size + 1][2 * size + 1];
        for (int y = 0; y < rectangle.length; y++) {
            for (int x = 0; x < rectangle[y].length; x++) {
                Rectangle rec = new Rectangle(16, 16);
                if (table[y][x] == ' ') rec.setFill(Color.WHITE);
                else if (table[y][x] == 'X') rec.setFill(Color.BLACK);
                else if (table[y][x] == 'E') rec.setFill(Color.BLUEVIOLET);
                else if (table[y][x] == 'F') rec.setFill(Color.BLUEVIOLET);
                else if (table[y][x] == '.') rec.setFill(Color.YELLOWGREEN);
                maze.add(rec, x, y);
            }
        }
    }

    /**
     * This method clears GridPane structure.
     */
    public void clear() {
        maze.getChildren().clear();
    }


    public void generate() {
        generated = true;
        gameMode = false;
        clear();
        Maze lab = new Maze(size, indicator);
        entrance = lab.getEntrance();
        exit = lab.getExit();
        labTable = lab.getMazeTable();
        labTableWithPath = lab.getMazeTableWithPath();
        uploadMaze(labTable);
    }


    public void goBack(ActionEvent e) throws IOException {
        root = FXMLLoader.load((getClass().getResource("tytulowa.fxml")));
        stage = (Stage) (play.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method displays solution.
     *
     * @param e action on using button
     */
    public void solve(ActionEvent e) {
        if (!generated) return;
        if (!gameMode) {
            if (!solveMode) {
                uploadMaze(labTableWithPath);
                solveMode = true;
            } else if (solveMode) {
                uploadMaze(labTable);
                solveMode = false;
            }
        }

    }

    /**
     * This method moves up.
     */
    public void moveUp() {
        if (current.getA() != 0 && labTable[current.getA() - 1][current.getB()] != 'X' && gameMode) {
            current.setA(current.getA() - 1);
            move(current.getA(), current.getB());
        }
    }

    /**
     * This method moves down.
     */
    public void moveDown() {
        if (current.getA() != labTable.length - 1 && labTable[current.getA() + 1][current.getB()] != 'X' && gameMode) {
            current.setA(current.getA() + 1);
            move(current.getA(), current.getB());
        }
    }

    /**
     * This method moves right.
     */
    public void moveRight() {
        if (current.getB() != labTable.length - 1 && labTable[current.getA()][current.getB() + 1] != 'X' && gameMode) {
            current.setB(current.getB() + 1);
            move(current.getA(), current.getB());
        }
    }

    /**
     * This method moves left.
     */
    public void moveLeft() {
        if (current.getB() != 0 && labTable[current.getA()][current.getB() - 1] != 'X' && gameMode) {
            current.setB(current.getB() - 1);
            move(current.getA(), current.getB());
        }
    }

    /**
     * This method starts game.
     *
     * @param e action on using button
     */
    public void play(ActionEvent e) {
        if (!generated) return;
        scene = play.getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    moveUp();
                    break;
                case S:
                    moveDown();
                    break;
                case A:
                    moveLeft();
                    break;
                case D:
                    moveRight();
                    break;
            }
        });
        if (!solveMode) {
            if (!gameMode) {
                gameMode = true;
                circ.setFill(Color.GREEN);
                maze.getChildren().clear();
                current = new Pairr(entrance.getA(), entrance.getB());
                clear();
                uploadMaze(labTable);
                maze.add(circ, current.getB(), current.getA());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAlgorithm(data.getAlgorithm());
        setSize(data.getDifficulty());

        generate();
    }
}
