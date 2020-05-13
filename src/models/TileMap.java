package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TileMap {
    private int cols;
    private int rows;
    private int size;
    private ArrayList<Tile> tiles;

    public TileMap(int cols, int rows, int size) {
        this.cols = cols;
        this.rows = rows;
        this.size = size;
        this.tiles = new ArrayList<>();
    }

    /**
     * getTile
     * Returns the Tile that matches given coordinates.
     * @param x column
     * @param y row
     * @return a Tile
     */
    public Tile getTile(int x, int y) {
        Location loc = new Location(x, y);
        for (Tile tile : tiles)
            if (tile.getPos().match(loc))
                return tile;
        return null;
    }

    /**
     * compose
     * Creates the TileSet based on the given CSV map.
     * Each value is a number used to determine what sprite will be rendered for the created Tile.
     */
    public void compose() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("src/utils/mapFlat.csv"));
            String row;
            int line = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                for (int col = 0; col < data.length; col++) {
                    this.tiles.add(new Tile(line, col, Integer.parseInt(data[col])));
                }
                line++;
            }

            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
