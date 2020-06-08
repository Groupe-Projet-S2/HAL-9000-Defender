package models.environment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;

public class TileMap {
    private int cols;
    private int rows;
    private int size;
    private ArrayList<Tile> tiles;

    public TileMap(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.size = Tile.SIZE * Tile.SIZE;
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

    public static Collection<Tile> getAvailableNeighbors(TileMap map, Tile tile) {
        Collection<Tile> neighbors = new ArrayList<>();
        int row = tile.getPos().getRow();
        int col = tile.getPos().getCol();

        neighbors.add(map.getTile(row - 1, col));
        neighbors.add(map.getTile(row + 1, col));
        neighbors.add(map.getTile(row, col - 1));
        neighbors.add(map.getTile(row, col + 1));

        neighbors.removeIf(t -> t == null || t.hasParent() || !t.isPath());

        return neighbors;
    }
}
