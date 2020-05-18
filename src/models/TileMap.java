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
     * @param row row
     * @param col column
     * @return a Tile
     */
    public Tile getTile(int row, int col) {
        Location loc = new Location(row, col);
        for (Tile tile : tiles)
            if (tile.getPos().match(loc))
                return tile;
        return null;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    /**
     * compose
     * Creates the TileSet based on the given CSV map.
     * Each value is a number used to determine what sprite will be rendered for the created Tile.
     */
    public void compose() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("src/utils/map32.csv"));
            String line;
            int row = 0;
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                for (int col = 0; col < data.length; col++) {
                    Tile tile = new Tile(row, col, Integer.parseInt(data[col]));
                    this.tiles.add(tile);
                }
                row++;
            }

            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Tile getNeighbor(TileMap map, Tile tile, String dir) {
        int row = tile.getPos().getRow();
        int col = tile.getPos().getCol();
        switch (dir.toLowerCase()) {
            case "up": return map.getTile(row - 1, col);
            case "down": return map.getTile(row + 1, col);
            case "left": return map.getTile(row, col - 1);
            case "right": return map.getTile(row, col + 1);
            default: return null;
        }
    }
}
