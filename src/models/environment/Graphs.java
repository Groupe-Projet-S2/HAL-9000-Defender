package models.environment;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Graphs {

    /**
     * Creates a chained list between the tiles to represent a BFS tree.
     * Starting from the destination, we iterate over the available tiles (that is, a tile on which the entities can move) and for each adjacent tile, set its parent to the current tile.
     * To do so, we check the direct neighbors of the tile and check their availability and if they've been iterated over already.
     * In the end, every tile has a parent, which makes it easier for the entities to know where to go.
     * @param tileMap
     * @param start
     * @param end
     */
    public static LinkedHashMap<Tile,Tile> bfs(TileMap tileMap, Tile start, Tile end) {
        Tile temp = null; // The current tile
        LinkedList<Tile> file = new LinkedList<>();
        LinkedHashMap<Tile,Tile> path = new LinkedHashMap<>();
        file.addFirst(end);
        end.setParent(end);
        path.put(end,end);

        Collection<Tile> neighbors;

        do {
            if(!file.isEmpty()) {
                temp = file.removeLast();
                neighbors = TileMap.getAvailableNeighbors(tileMap, temp);

                for (Tile tile : neighbors) {
                    file.addFirst(tile);
                    tile.setParent(temp);
                    path.put(tile, temp);
                }
            }
        } while (temp != start);

        return path;
    }
}
