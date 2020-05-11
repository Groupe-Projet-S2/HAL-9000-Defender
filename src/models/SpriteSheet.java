package models;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;

public class SpriteSheet {

    private static ImageView spriteSheet;

    public SpriteSheet() {
        try {
            spriteSheet = new ImageView(new Image(new FileInputStream("src/utils/tileset.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ImageView getSpriteSheet() {
        return spriteSheet;
    }

    public ImageView crop(int col, int row, int height, int width) {
        ImageView image = new ImageView(spriteSheet.getImage());
        image.setViewport(new Rectangle2D(col * 32, row * 32, width, height));
        return image;
    }
}
