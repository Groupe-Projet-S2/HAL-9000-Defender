package models.entities.tower;

import models.entities.projectile.Motionless;
import models.entities.projectile.Projectile;
import models.environment.Location;
import models.environment.Tile;
import models.environment.TileMap;
import models.environment.World;

import java.util.Collection;

public class GoodwareBytes extends Tower {

    private long startTime;
    private int spawnTime;
    private int number;
    private Projectile projectile;

    public GoodwareBytes(Location location, World env) {
        super(100, location, 100, 50, 1000, 500, env);
        startTime = System.currentTimeMillis();
        spawnTime = 500;
        number = 4;
    }

    public Location getNearestTileLoc(){
       /* Tile loc = env.getTileMap().getTile(position.getRow()/Tile.SIZE,position.getCol()/Tile.SIZE);
        System.out.println(loc.getPos());
        Collection<Tile> neighbors = TileMap.getAvailableNeighbors(env.getTileMap(), loc);
        for (Tile tile:neighbors) {
            System.out.println(tile.getPos());
            if ((tile.getPos().getRow() - loc.getPos().getRow() == 1 && tile.getPos().getCol() - loc.getPos().getCol() == 0) || (tile.getPos().getRow() - loc.getPos().getRow() == 0 && tile.getPos().getCol() - loc.getPos().getCol() == 1)) {
                return tile.getPos();
            }
        }
        return loc.getPos();*/
    }

    @Override
    public void act() {
        long current = System.currentTimeMillis();
        if (current >= startTime + spawnTime && number > 0 && this.hasTarget()) {
            startTime = current;
            number--;
            Location nearestLoc = getNearestTileLoc();
            Projectile projectile = new Motionless(new Location(nearestLoc.getRow()*Tile.SIZE+(Tile.SIZE/2), nearestLoc.getCol()*Tile.SIZE+(Tile.SIZE/2)), this, 40, 5, env);
            env.addToList(projectile);
        }
        else if (number == 0 && current - startTime >= reloadingTime) {
            number = 4;
        }
    }
}
