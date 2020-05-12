package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import models.SpriteSheet;
import models.Tile;
import models.TileMap;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class MapController {

    private final int SIZE = 32;

    @FXML
    private GridPane grid;

    private SpriteSheet tileSet;

    private TileMap tileMap;

    @FXML void initialize() {
        grid.setAlignment(Pos.CENTER);
        tileMap = new TileMap(SIZE, SIZE, SIZE * SIZE);
        tileMap.compose();
        tileSet = new SpriteSheet("src/utils/tileset.png", SIZE);
        draw();
    }

    /**
     * draw method
     *
     * Draws the map.
     * It iterates over the rows and columns of the tile set to add TilePanes to the GridPane.
     * One tile is 32x32.
     * Every tile gets its corresponding sprite as a background.
     */
    private void draw() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {

                TilePane tilePane = new TilePane();
                tilePane.setAlignment(Pos.CENTER);
                tilePane.setPrefWidth(32);
                tilePane.setPrefHeight(32);
                Tile tile = tileMap.getTile(i, j);
                int ID = tile.getType();
                tilePane.setId("" + ID);

                Image image = tileSet.getSprite(ID).getImage();

                Background bg = new Background(new BackgroundImage(image,NO_REPEAT, NO_REPEAT, DEFAULT, BackgroundSize.DEFAULT));
                tilePane.setBackground(bg);

                grid.add(tilePane, j, i);
            }
    }

}
