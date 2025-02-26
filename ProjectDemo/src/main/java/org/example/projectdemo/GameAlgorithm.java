package org.example.projectdemo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class GameAlgorithm {
    private Polygon[][] hexagons;
    private int rows;
    private int cols;
    private boolean oyunKazanani;
    private String kazanan;

    public GameAlgorithm(Polygon[][] hexagons, int rows, int cols) {
        this.hexagons = hexagons;
        this.rows = rows;
        this.cols = cols;
        this.oyunKazanani = false;
        this.kazanan = "";
    }

    public void oyunKontrol() {
        boolean[][] varildiMi = new boolean[rows][cols];

        //Bu döngü ile kırmızı oyuncunun soldan sağa kazandığını kontrol ediyoruz
        for (int i = 0; i < rows; i++) {
            if (kirmiziMi(hexagons[i][0]) && Algoritma(i, 0, Color.CRIMSON.brighter(), varildiMi)) {
                oyunKazanani = true;
                kazanan = "Kırmızı";
                return;
            }
        }

        //Bu döngü ile mavi oyuncunun yukardan aşağıya kazandığını kontrol ediyoruz
        for (int j = 0; j < cols; j++) {
            if (maviMi(hexagons[0][j]) && Algoritma(0, j, Color.ROYALBLUE, varildiMi)) {
                oyunKazanani = true;
                kazanan = "Mavi";
                return;
            }
        }
    }

    private boolean Algoritma(int row, int col, Color color, boolean[][] varildiMi) {
    //Row, Col değerleri paralelkenarın(oyun tahtası) dışında olup olmadığını kontrol ediyoruz
        if (row < 0 ||  col < 0 || row >= rows || col >= cols) {
            return false;
        }
        //Belirtilen indise daha önce varıldıysa ya da altıgen farklı renkse bu if statement ile geri dönüyoruz
        if (varildiMi[row][col] || !hexagons[row][col].getFill().equals(color)){
            return false;
        }
        //Kırmızı oyuncu için paralelkenarın sağına, mavi için paralelkenarın aşağı kısmına ulaşıp ulaşmadığı kontrol edilir
        if ((color.equals(Color.CRIMSON.brighter()) && col == cols - 1) || (color.equals(Color.ROYALBLUE) && row == rows - 1)) {
            return true;
        }

        varildiMi[row][col] = true;

        //Yukarı yönü
        int yeniSatir = row - 1;
        int yeniSutun = col;
        if (Algoritma(yeniSatir, yeniSutun, color, varildiMi)) {
            return true;
        }

        //Sağ-yukarı yönü
        yeniSatir = row - 1;
        yeniSutun = col + 1;
        if (Algoritma(yeniSatir, yeniSutun, color, varildiMi)) {
            return true;
        }

        //Sağ yönü
        yeniSatir = row;
        yeniSutun = col + 1;
        if (Algoritma(yeniSatir, yeniSutun, color, varildiMi)) {
            return true;
        }

        //Sol yönü
        yeniSatir = row;
        yeniSutun = col - 1;
        if (Algoritma(yeniSatir, yeniSutun, color, varildiMi)) {
            return true;
        }

        //Sol-aşağı yönü
        yeniSatir = row + 1;
        yeniSutun = col - 1;
        if (Algoritma(yeniSatir, yeniSutun, color, varildiMi)) {
            return true;
        }

        //Aşağı yönü
        yeniSatir = row + 1;
        yeniSutun = col;
        if (Algoritma(yeniSatir, yeniSutun, color, varildiMi)) {
            return true;
        }
        return false;
    }

    private boolean kirmiziMi(Polygon hexagon) {
        return hexagon != null && hexagon.getFill().equals(Color.CRIMSON.brighter());
    }

    private boolean maviMi(Polygon hexagon) {
        return hexagon != null && hexagon.getFill().equals(Color.ROYALBLUE);
    }

    public boolean kazandiMi() {
        return oyunKazanani;
    }

    public String kazananiBul() {
        return kazanan;
    }


    public void hexagonTiklamaOzelligiKaldir() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                hexagons[i][j].setOnMouseClicked(null);
            }
        }
    }
}







