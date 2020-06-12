package models.entities.tower;

import models.entities.projectile.Projectile;
import models.entities.projectile.Static;
import models.environment.Location;
import models.environment.World;

public class Afast extends Tower {

    public static int price = 50;
    long startTime;
    int spawnTime;
    int number;

    public Afast(Location location, World env) {
        super(100, location, 100, 1000, 500, env);
        startTime = System.currentTimeMillis();
        spawnTime = 1000;
        number = 4;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void act() {
        long current = System.currentTimeMillis();
        if (current - startTime >= spawningTime && number> 0 && this.hasTarget()) {
            startTime = current;
            number--;
            Projectile projectile = new Static(new Location(this.getPosition().getRow()+10, this.getPosition().getCol()), this.getTarget().getPosition(), this, 40, 10, env);
            env.addToList(projectile);
        }
        else if (number == 0 && current - startTime>= reloadingTime) {
            number = 4;
        }
    }
}
