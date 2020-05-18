package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import models.SpriteSheet;
import models.Tile;
import models.TileMap;

public class MapView {
    /**
     * draw method
     *
     * Draws the map.
     * It iterates over the rows and columns of the tile set to add TilePanes to the GridPane.
     * One tile is 32x32.
     * Every tile gets its corresponding sprite as a background.
     */
    public static void draw(TilePane grid, int COLS, int ROWS, int SIZE, TileMap tileMap) {
        SpriteSheet tileSet = new SpriteSheet("src/utils/tileset32.png", SIZE);
        for (int i = 0; i < COLS; i++)
            for (int j = 0; j < ROWS; j++) {
                Tile tile = tileMap.getTile(i, j);
                int ID = tile.getType();
                ImageView sprite = new ImageView();
                Image image = tileSet.getSprite(ID);
                sprite.setImage(image);

                grid.getChildren().add(sprite);
            }
    }
}
