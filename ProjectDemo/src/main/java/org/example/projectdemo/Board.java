package org.example.projectdemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Board extends Application {
    private Gameplay gameplay;
    private int rows;
    private int cols;
    private GameAlgorithm gameAlgorithm;
    private Label kazananLbl;
    private Label siraLbl = new Label("Sıra Kırmızıda");
    private Button swapBtn = new Button("Swap");
    private int secilenSatir;
    private int secilenSutun;

    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
    }
    private Polygon hexagonCiz(double centerX, double centerY, double radius, int row, int col) {
        Polygon hexagon = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            hexagon.setRotate(90);
            hexagon.getPoints().addAll(x, y);

        }
        hexagon.setStroke(Color.BLUEVIOLET);
        hexagon.setFill(Color.LAVENDER);

        hexagon.setOnMouseClicked(event -> {
            secilenSatir = row;
            secilenSutun = col;
            gameplay.altigenRenginiDegistir(hexagon);
            gameAlgorithm.oyunKontrol();
            if (gameAlgorithm.kazandiMi()) {
                String winner = gameAlgorithm.kazananiBul();
                LabelGuncelle(winner);
                gameAlgorithm.hexagonTiklamaOzelligiKaldir();
                siraLbl.setVisible(false);
            }
        });

        return hexagon;
    }

    private void hexagonYerleştir(AnchorPane root, double radius, double hexWidth, double hexHeight, Polygon[][] hexagons) {
        //Altıgenlerin başlangıç konumlarının biraz aşağı ve sola kaydırmak için offset ekledik
        double xOffset = 100;
        double yOffset = 100;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double x = col * hexWidth + (row * (hexWidth / 2)) + xOffset;
                double y = row * hexHeight * 0.75 + yOffset;

                Polygon hexagon = hexagonCiz(x + Math.sqrt(3) * radius, y + radius, radius, row, col);
                root.getChildren().add(hexagon);

                //Altıgenlerin koordinatlarını Polygon tipinde olan hexagon matrisine kaydediyoruz
                hexagons[row][col] = hexagon;
            }
        }
    }

    private void LabelGuncelle(String winner) {
        kazananLbl.setText("Kazanan: " + winner);
        if(winner.equals("Kırmızı"))
            kazananLbl.setTextFill(Color.CRIMSON.brighter());
        else
            kazananLbl.setTextFill(Color.ROYALBLUE);
    }

    @Override
    public void start(Stage stage) {
        AnchorPane root = new AnchorPane();

        double radius = 25;
        if(rows == 17){
            radius = 19;
        }
        double sceneWidth = 1050;
        double sceneHeight = 700;

        if(rows == 5){
            sceneWidth = 550;
            sceneHeight = 400;
        }
        else if(rows == 11){
            sceneWidth = 950;
            sceneHeight = 650;
        }

        double hexHeight = 2 * radius;
        double hexWidth = Math.sqrt(3) * radius;


        Polygon[][] hexagons = new Polygon[rows][cols];
        hexagonYerleştir(root, radius, hexWidth, hexHeight, hexagons);

        siraLbl.setTextFill(Color.CRIMSON.brighter());
        siraLbl.setStyle("-fx-font-size: 20px;");
        siraLbl.setLayoutX(10);
        siraLbl.setLayoutY(10);
        root.getChildren().add(siraLbl);

        swapBtn.setTextFill(Color.ROYALBLUE);
        swapBtn.setStyle("-fx-font-size: 20px;");
        swapBtn.setLayoutX(sceneWidth - 500);

        if(rows == 11)
            swapBtn.setLayoutX(sceneWidth - 900);
        else if(rows == 17)
            swapBtn.setLayoutX(sceneWidth - 1000);

        swapBtn.setLayoutY(sceneHeight - 80);
        root.getChildren().add(swapBtn);

        Swap swap = new Swap(hexagons);
        gameplay = new Gameplay(siraLbl, swapBtn, swap, this);
        gameplay.clickSwapHandler();

        kazananLbl = new Label("");
        kazananLbl.setStyle("-fx-font-size: 20px;");
        kazananLbl.setLayoutX(10);
        kazananLbl.setLayoutY(40);
        root.getChildren().add(kazananLbl);

        gameAlgorithm = new GameAlgorithm(hexagons, rows, cols);

        root.setStyle("-fx-background-color: #b7bde7");
        Scene scene = new Scene(root, sceneWidth, sceneHeight);

        stage.setTitle("Hexagon Board");
        stage.setScene(scene);
        stage.show();
    }
    public int getSecilenSatir() {
        return secilenSatir;
    }

    public int getSecilenSutun() {
        return secilenSutun;
    }
}



