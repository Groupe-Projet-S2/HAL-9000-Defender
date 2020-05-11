package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import models.SpriteSheet;
import models.Tile;
import models.TileMap;

public class MapController {

    private final int COLS = 31;
    private final int ROWS = 31;

    @FXML
    private GridPane grid;

    private SpriteSheet tileSet;

    private TileMap tileMap;

    @FXML void initialize() {
        grid.setAlignment(Pos.CENTER);
        tileMap = new TileMap(COLS, ROWS, COLS * ROWS);
        tileMap.compose();
        tileSet = new SpriteSheet();
        draw();
    }

    private void draw() {
        int setCol, setRow;
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++) {
                setCol = 0;
                setRow = 0;

                TilePane tilePane = new TilePane();
                tilePane.setPrefWidth(32);
                tilePane.setPrefHeight(32);
                Tile tile = tileMap.getTile(i, j);
                int ID = tile.getID();

                switch (ID) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        setCol = ID;
                        break;
                    case 5:
                        setRow = 1;
                        setCol = 0;
                        break;
                    case 6:
                        setRow = 1;
                        setCol = 1;
                        break;
                    case 7:
                        setRow = 1;
                        setCol = 2;
                        break;
                    case 8:
                        setRow = 1;
                        setCol = 3;
                        break;
                    case 9:
                        setRow = 1;
                        setCol = 4;
                        break;
                    case 10:
                        setRow = 2;
                        break;
                    case 11:
                        setRow = 2;
                        setCol = 1;
                        break;
                    case 12:
                        setRow = 2;
                        setCol = 2;
                        break;
                    case 13:
                        setRow = 2;
                        setCol = 3;
                        break;
                    case 14:
                        setRow = 2;
                        setCol = 4;
                        break;
                }

                System.out.println(setRow + " - " + setCol + " - " + ID);

                ImageView image = tileSet.crop(setCol, setRow, 32, 32);

                tilePane.getChildren().add(image);
                grid.add(tilePane, j, i);
            }
    }

}
