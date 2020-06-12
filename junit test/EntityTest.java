import models.Game;
import models.entities.projectile.Dynamic;
import models.entities.projectile.Projectile;
import models.entities.tower.*;
import models.entities.virus.*;
import models.environment.*;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class EntityTest {

    @Test
    public void IsInRangeTest() {

        /* There is 3 entites type
         * We're gonna test each type of entity when it detect one of the two others
         * Here whe initialize the classes that we need to create entites, so that concern the environment */

        TileMap tileMap = new TileMap(1);
        tileMap.compose();
        LinkedHashMap<Tile,Tile> path = Graphs.bfs(tileMap, tileMap.getTile(6, 0), tileMap.getTile(5, 24));
        Game game = new Game(tileMap, 5, 24,path);
        World world = game.getWorld();

        //Some classes that we'll need

        Tower tower1 = new Afast(new Location(15 * Tile.SIZE + Tile.SIZE / 2, 15* Tile.SIZE + Tile.SIZE / 2), world);
        world.addToList(tower1);
        Tower tower2 = new Afast(new Location(10 * Tile.SIZE + Tile.SIZE / 2, 15* Tile.SIZE + Tile.SIZE / 2), world);
        world.addToList(tower2);

        Virus virus1 = new Adware(new Location(6 * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE / 2), tileMap.getTile(6, 0), world);
        world.addToList(virus1);
        Virus virus2 = new Adware(new Location(15 * Tile.SIZE + Tile.SIZE / 2, 14 * Tile.SIZE + Tile.SIZE / 2), tileMap.getTile(23, 23), world);
        world.addToList(virus2);
        Virus virus3 = new Adware(new Location(15 * Tile.SIZE + Tile.SIZE / 2,  15* Tile.SIZE + Tile.SIZE / 2), tileMap.getTile(23, 23), world);
        world.addToList(virus3);

        Projectile projectileDynamic1 = new Dynamic(tower1, virus1, 0, 100, world);
        world.addToList(projectileDynamic1);


        //Testing Projectile range on Virus

        //1 - When virus isn't in the range
        assertFalse(projectileDynamic1.isInRange(virus1));

        //2 - When virus is in the range
        assertTrue(projectileDynamic1.isInRange(virus2));

        //3 - When virus is on the same position as the projectile
        virus3.getPosition().setRow(projectileDynamic1.getPosition().getRow());
        virus3.getPosition().setCol(projectileDynamic1.getPosition().getCol());
        assertTrue(projectileDynamic1.isInRange(virus3));

        //4 - When virus is on the same position as the projectile which have no range
        projectileDynamic1.setRange(0);
        assertFalse(projectileDynamic1.isInRange(virus3));


        //Testing Projectile range on Tower

        projectileDynamic1.setRange(50);

        //1 - When tower isn't in the range
        assertFalse(projectileDynamic1.isInRange(tower2));

        //2 - When tower is in the range;
        assertTrue(projectileDynamic1.isInRange(tower1));

        //3 - When tower is on the same position as the projectile
        projectileDynamic1.getPosition().setRow(projectileDynamic1.getPosition().getRow()-10);
        assertTrue(projectileDynamic1.isInRange(tower1));

        //4 - When tower is on the same position as the projectile which have no range
        projectileDynamic1.setRange(0);
        assertFalse(projectileDynamic1.isInRange(tower1));


        //Testing Tower range on Virus

        //1 - When virus isn't in the range
        assertFalse(tower1.isInRange(virus1));

        //2 - When virus is in the range
        assertTrue(tower1.isInRange(virus3));

        //3 - When virus is on the same position as the tower
        virus3.getPosition().setRow(tower1.getPosition().getRow());
        virus3.getPosition().setCol(tower1.getPosition().getCol());
        assertTrue(tower1.isInRange(virus3));

        //4 - When virus is on the same position as the tower which have no range
        tower1.setRange(0);
        assertFalse(tower1.isInRange(virus3));


        //Testing Tower range on Projectile

        tower1.setRange(50);

        //1 - When projectile isn't in the range
        assertFalse(tower2.isInRange(projectileDynamic1));

        //2 - When projectile is in the range
        projectileDynamic1.getPosition().setRow(tower1.getPosition().getRow()+20);
        projectileDynamic1.getPosition().setCol(tower1.getPosition().getCol()+20);
        assertTrue(tower1.isInRange(projectileDynamic1));

        //3 - When projectile is on the same position as the tower
        projectileDynamic1.getPosition().setRow(tower1.getPosition().getRow());
        projectileDynamic1.getPosition().setCol(tower1.getPosition().getCol());
        assertTrue(tower1.isInRange(projectileDynamic1));

        //4 - When projectile is on the same position as the tower which have no range
        tower2.setRange(0);
        assertFalse(tower2.isInRange(projectileDynamic1));


        //Testing Virus range on Projectile

        //1 - When projectile isn't in the range
        assertFalse(virus1.isInRange(projectileDynamic1));

        //2 - When projectile is in the range
        projectileDynamic1.getPosition().setRow(virus1.getPosition().getRow()+20);
        projectileDynamic1.getPosition().setCol(virus1.getPosition().getCol()+20);
        assertTrue(virus1.isInRange(projectileDynamic1));

        //3 - When projectile is on the same position as the virus
        projectileDynamic1.getPosition().setRow(virus1.getPosition().getRow());
        projectileDynamic1.getPosition().setCol(virus1.getPosition().getCol());
        assertTrue(virus1.isInRange(projectileDynamic1));

        //4 - When projectile is on the same position as the virus which have no range
        virus1.setRange(0);
        assertFalse(virus1.isInRange(projectileDynamic1));


        //Testing Virus range on Tower

        //1 - When tower isn't in the range
        assertFalse(virus1.isInRange(tower1));

        //2 - When tower is in the range
        assertTrue(virus3.isInRange(tower1));

        //3 - When tower is on the same position as the virus
        assertTrue(virus3.isInRange(tower1));

        //4 - When tower is on the same position as the virus which have no range
        virus3.setRange(0);
        assertFalse(virus3.isInRange(tower1));
    }
}