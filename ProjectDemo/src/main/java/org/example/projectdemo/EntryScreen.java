package org.example.projectdemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EntryScreen extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        Text txt = new Text("Welcome Java Hex Game");
        txt.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        txt.setFill(Color.LAVENDERBLUSH);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.MIDNIGHTBLUE);
        txt.setEffect(dropShadow);

        StackPane paneText = new StackPane(txt);
        paneText.setPrefHeight(80);
        paneText.setPadding(new Insets(25, 25, 25, 25));
        paneText.setAlignment(Pos.TOP_CENTER);
        paneText.setStyle("-fx-background-color: #5447bd");
        root.setTop(paneText);

        Text txt1 = new Text("Rengi kırmızı olan oyuncunun amacı soldan sağa");
        txt1.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 11));
        txt1.setFill(Color.MIDNIGHTBLUE);
        Text txt2 = new Text("Rengi mavi olan oyuncunun amacı yukarıdan");
        txt2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 11));
        txt2.setFill(Color.MIDNIGHTBLUE);
        Text txt3 = new Text("aşağıya bir yol oluşturmaktır. Bol Şans :))");
        txt3.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 11));
        txt3.setFill(Color.MIDNIGHTBLUE);

        VBox txtPane = new VBox();
        txtPane.setAlignment(Pos.CENTER);
        txtPane.setPadding(new Insets(10, 10, 10, 10));
        txtPane.setSpacing(5);
        txtPane.setStyle("-fx-background-color: b7bde7");
        txtPane.getChildren().addAll(txt1, txt2, txt3);
        root.setCenter(txtPane);

        VBox paneButton = new VBox();
        Button btn1 = new Button("Start");
        btn1 = new Button("Start");
        btn1.setStyle("-fx-border-color: darkblue");
        Button btn2 = new Button("Exit");
        btn2.setStyle("-fx-border-color: darkblue");
        btn1.setMinSize(90, 40);
        btn1.setMaxSize(90, 40);
        btn1.setPrefSize(90, 40);

        btn2.setMinSize(90, 40);
        btn2.setMaxSize(90, 40);
        btn2.setPrefSize(90, 40);

        paneButton.setSpacing(10);
        paneButton.setPadding(new Insets(10, 10, 10, 10));
        paneButton.getChildren().addAll(btn1, btn2);
        paneButton.setAlignment(Pos.BOTTOM_CENTER);
        paneButton.setStyle("-fx-background-color: #b7bde7");

        btn1.setOnAction(event -> {
            try {
                new SelectBoard().start(new Stage());
                stage.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        btn2.setOnAction(event -> stage.close());

        root.setBottom(paneButton);

        Scene scene = new Scene(root, 325, 300);
        stage.setScene(scene);
        stage.setTitle("Entry Screen");
        stage.show();
    }
}