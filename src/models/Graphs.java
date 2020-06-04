package models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

public class Graphs {

    /**
     * Creates a chained list between the tiles to represent a BFS tree.
     * Starting from the destination, we iterate over the available tiles (that is, a tile on which the entities can move) and for each adjacent tile, set its parent to the current tile.
     * To do so, we check the direct neighbors of the tile and check their availability and if they've been iterated over already.
     * In the end, every tile has a parent, which makes it easier for the entities to know where to go.
     * @param world
     * @param tileMap
     * @param start
     * @param end
     */
    public static void bfs(Pane world, TileMap tileMap, Tile start, Tile end) {
        Tile temp = null; // The current tile
        //Stack<Tile> stack = new Stack<>();
        //stack.add(end);
        LinkedList<Tile> file = new LinkedList<Tile>();
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
            /*if (stack.size() > 0) {
                //temp = stack.pop();
                neighbors = TileMap.getAvailableNeighbors(tileMap, temp);
                //stack.addAll(neighbors);

                for (Tile tile : neighbors) {
                    //stack.add(tile);
                    tile.setParent(temp);
                }
            }*/
        } while (temp != start);




        for (Tile tile : tileMap.getTiles()) {
            if (tile.hasParent()) {
                Circle p = new Circle(tile.getPos().getCol() * Tile.SIZE + Tile.SIZE / 2.0, tile.getPos().getRow() * Tile.SIZE + Tile.SIZE / 2.0, 3, Color.BLUE);
                Location parent = tile.getParent().getPos();
                world.getChildren().add(p);
                world.getChildren().add(new Line(p.getCenterX(), p.getCenterY(), parent.getCol() * Tile.SIZE + Tile.SIZE / 2.0, parent.getRow() * Tile.SIZE + Tile.SIZE / 2.0));
            }
        }
    }
}