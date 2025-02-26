package org.example.projectdemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SelectBoard extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        Text txt = new Text("Select Game Board");
        txt.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        txt.setFill(Color.LAVENDERBLUSH);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.MIDNIGHTBLUE);
        txt.setEffect(dropShadow);

        StackPane paneText = new StackPane(txt);
        paneText.setPrefHeight(80);
        paneText.setAlignment(Pos.CENTER);
        paneText.setStyle("-fx-background-color: #5447bd");
        root.setTop(paneText);

        HBox paneBtn = new HBox();
        paneBtn.setPadding(new Insets(5, 5, 5, 5));
        paneBtn.setSpacing(5);
        paneBtn.setAlignment(Pos.CENTER);

        RadioButton rbtn1 = new RadioButton("5x5");
        rbtn1.setTextFill(Color.DARKSLATEBLUE);
        RadioButton rbtn2 = new RadioButton("11x11");
        rbtn2.setTextFill(Color.DARKSLATEBLUE);
        RadioButton rbtn3 = new RadioButton("17x17");
        rbtn3.setTextFill(Color.DARKSLATEBLUE);
        paneBtn.getChildren().addAll(rbtn1, rbtn2, rbtn3);

        ToggleGroup group = new ToggleGroup();
        rbtn1.setToggleGroup(group);
        rbtn2.setToggleGroup(group);
        rbtn3.setToggleGroup(group);

        rbtn1.setOnAction(event -> {
            if(rbtn1.isSelected()){
                new Board(5, 5).start(new Stage());
                stage.close();
            }
        });
        rbtn2.setOnAction(event -> {
            if(rbtn2.isSelected()){
                new Board(11, 11).start(new Stage());
                stage.close();
            }
        });
        rbtn3.setOnAction(event -> {
            if(rbtn3.isSelected()){
                new Board(17, 17).start(new Stage());
                stage.close();
            }
        });

        VBox vbox = new VBox(paneBtn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #b7bde7");
        root.setCenter(vbox); //HBox VBox' ın içine yerleştik, VBox 'ıda BorderPane' in tam ortasına yerleştirerek butonların ekranın tam ortasında olması sağlandı

        Scene scene = new Scene(root, 325, 300);
        stage.setScene(scene);
        stage.setTitle("Select Board");
        stage.show();
    }
}




