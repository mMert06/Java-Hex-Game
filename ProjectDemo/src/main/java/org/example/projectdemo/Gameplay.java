package org.example.projectdemo;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Gameplay {
    private int sira = 0;
    private Label siraLbl;
    private Button swapBtn;
    private Swap swap;
    private Board board;

    public Gameplay(Label siraLabel, Button swapBtn, Swap swap, Board board) {
        this.siraLbl = siraLabel;
        this.swapBtn = swapBtn;
        this.swap = swap;
        this.board = board;
    }

    public void clickSwapHandler(){
        swapBtn.setOnAction(event -> {
            if(sira == 1){
                int row = board.getSecilenSatir();
                int col = board.getSecilenSutun();
                swap.swapOlustur(row, col);
                siraLbl.setText("Swap gerçekleştirildi. Sıra Kırmızıda");
                siraLbl.setTextFill(Color.CRIMSON.brighter());
                sira++;
            }
        });
    }

    public void altigenRenginiDegistir(Polygon hexagons) {
        if (sira % 2 == 0) {
            if(!maviMi(hexagons) && !kirmiziMi(hexagons)){
                hexagons.setFill(Color.CRIMSON.brighter());
                siraLbl.setText("Sıra Mavide");
                siraLbl.setTextFill(Color.ROYALBLUE);
                sira++;
            }
        }
        else
        {
            if(!kirmiziMi(hexagons) && !maviMi(hexagons)){
                hexagons.setFill(Color.ROYALBLUE);
                siraLbl.setText("Sıra Kırmızıda");
                siraLbl.setTextFill(Color.CRIMSON.brighter());
                sira++;
            }
        }
    }
    private boolean kirmiziMi(Polygon hexagons) {
        return hexagons.getFill().equals(Color.CRIMSON.brighter());
    }
    private boolean maviMi(Polygon hexagons){
        return hexagons.getFill().equals(Color.ROYALBLUE);
    }
}




