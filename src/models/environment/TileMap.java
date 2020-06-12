package models.environment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;

public class TileMap {
    private final ArrayList<Tile> tiles;
    private final int level;

    public TileMap(int level) {
        this.tiles = new ArrayList<>();
        this.level = level;
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

    /**
     * compose
     * Creates the TileSet based on the given CSV map.
     * Each value is a number used to determine what sprite will be rendered for the created Tile.
     */
    public void compose() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("src/utils/map"+level+".csv"));
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

    static Collection<Tile> getAvailableNeighbors(TileMap map, Tile tile) {
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
