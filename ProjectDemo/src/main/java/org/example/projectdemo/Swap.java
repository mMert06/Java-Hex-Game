package org.example.projectdemo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Swap {
    private Polygon[][] hexagons;

    public Swap(Polygon[][] hexagons){

        this.hexagons = hexagons;
    }
    public Polygon[][] swapOlustur(int row, int col){

        int yeniRow = col;
        int yeniCol = row;

        if(hexagons[row][col].getFill().equals(Color.CRIMSON.brighter())){
            hexagons[row][col].setFill(Color.LAVENDER);
            hexagons[yeniRow][yeniCol].setFill(Color.ROYALBLUE);
        } else {
            hexagons[yeniRow][yeniCol].setFill(Color.CRIMSON.brighter());
        }

        return hexagons;
    }
}


