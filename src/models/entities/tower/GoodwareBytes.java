package models.entities.tower;

import models.environment.Location;
import models.environment.World;

public class GoodwareBytes extends Tower{

    public static int price = 50;
    private long startTime;
    private int spawnTime;
    private int number;

    public GoodwareBytes(Location location, World env) {
        super(100, location, 100, 1000, 500, env);
        startTime = System.currentTimeMillis();
        spawnTime = 500;
        number = 4;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void act() {
        /*long current = System.currentTimeMillis();
        if (current >= startTime + spawnTime && number > 0 && this.hasTarget()) {
            startTime = current;
            number--;
            Location nearestLoc = getNearestTileLoc();
            Projectile projectile = new Motionless(new Location(nearestLoc.getRow()*Tile.SIZE+(Tile.SIZE/2), nearestLoc.getCol()*Tile.SIZE+(Tile.SIZE/2)), this, 40, 5, env);
            env.addToList(projectile);
        }
        else if (number == 0 && current - startTime >= reloadingTime) {
            number = 4;
        }*/
    }
}
