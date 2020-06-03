package models.environment;

import javafx.scene.image.*;

import java.io.*;
import java.util.HashMap;

public class SpriteSheet {

    private ImageView spriteSheet;
    private double spriteSize;
    private HashMap<Integer, Image> sprites;

    public SpriteSheet(String url) {
        this.spriteSize = Tile.SIZE;
        try {
            this.spriteSheet = new ImageView(new Image(new FileInputStream(url)));
            this.sprites = splitSheet();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * splitSheet
     * Splits a sprite sheet into individual sprites.
     * @return the HashMap containing all the sprites for every possible ID.
     */
    private HashMap<Integer, Image> splitSheet() {
        HashMap<Integer, Image> list = new HashMap<>();
        // Calculates how many sprites per rows there are in the sheet.
        double cols = spriteSheet.getImage().getWidth() / this.spriteSize;
        double rows = spriteSheet.getImage().getHeight() / this.spriteSize;

        // For each sprite found, add it to the map with this id.
        int id = 0;
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                list.put(id++, crop(col, row, (int) this.spriteSize, (int) this.spriteSize));
        return list;
    }

    public Image getSprite(int ID) {
        return this.sprites.get(ID);
    }

    /**
     * crop
     * Writes a new image by copying the pixels of a given area in a sprite sheet.
     * @param col Column of the sprite in the sheet
     * @param row Row of the sprite in the sheet
     * @param height height of the desired image
     * @param width width of the desired image
     * @return A cropped image.
     */
    private Image crop(int col, int row, int height, int width) {
        Image image = this.spriteSheet.getImage();
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter;

        WritableImage writableImage = new WritableImage(width, height);

        pixelWriter = writableImage.getPixelWriter();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixelWriter.setColor(i, j, pixelReader.getColor(i + col * (int) spriteSize, j + row * (int) spriteSize));
            }
        }
        return writableImage;
    }
}
