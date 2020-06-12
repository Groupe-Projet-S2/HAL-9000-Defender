package models.entities.tower;

import models.entities.projectile.Motionless;
import models.entities.projectile.Projectile;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class GoodwareBytes extends Tower{

    public static int price = 50;
    private long startTime;
    private final int spawnTime;
    private int number;
    private double closestDist;
    private boolean hasProjectile;

    public GoodwareBytes(Location location, World env) {
        super(100, location, 100, 1000, 500, env);
        startTime = System.currentTimeMillis();
        spawnTime = 1000;
        number = 1;
        closestDist = 900;
        hasProjectile = false;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void clearProjectile() {
        hasProjectile = false;
    }

    @Override
    public void act() {
        long current = System.currentTimeMillis();
        if (current >= startTime + spawnTime && number > 0 && this.hasTarget()) {
            startTime = current;
            number--;
            if (! hasProjectile) {
                Location nearestLoc = position;
                Projectile projectile;
                for (Tile tile : env.getPath().keySet()) {
                    Location loc = new Location(tile.getPos().getRow() * Tile.SIZE + (Tile.SIZE / 2), tile.getPos().getCol() * Tile.SIZE + (Tile.SIZE / 2));
                    double temp = loc.centerDistance(new Location(position.getRow(), position.getCol()));
                    if (temp < closestDist) {
                        closestDist = temp;
                        nearestLoc = loc;
                    }
                }
                projectile = new Motionless(nearestLoc, this, 40, 5, env);
                env.addToList(projectile);
                hasProjectile = true;
            }
        }
        else if (number == 0 && current - startTime >= reloadingTime) {
            number = 1;
        }
    }
}
