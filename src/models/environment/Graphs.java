package models.environment;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Collection;
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
    public static void bfs(TileMap tileMap, Tile start, Tile end) {
        Tile temp = null; // The current tile
        LinkedList<Tile> file = new LinkedList<>();
        file.addFirst(end);
        end.setParent(end);

        Collection<Tile> neighbors;

        do {
            if(!file.isEmpty()) {
                temp = file.removeLast();
                neighbors = TileMap.getAvailableNeighbors(tileMap, temp);

                for (Tile tile : neighbors) {
                    file.addFirst(tile);
                    tile.setParent(temp);
                }
            }
        } while (temp != start);

    }
}
