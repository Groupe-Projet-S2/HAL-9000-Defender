package models.entities.tower;

import models.entities.projectile.Projectile;
import models.entities.projectile.Static;
import models.environment.Location;
import models.environment.World;

public class Afast extends Tower {

    long startTime;
    int spawnTime;
    int number;

    public Afast(Location location, World env) {
        super(100, location, 100, 50, 1000, 500, env);
        startTime = System.currentTimeMillis();
        spawnTime = 1000;
        number = 4;
    }

    @Override
    public void act() {
        System.out.println(position);
        long current = System.currentTimeMillis();
        if (current - startTime >= spawningTime && number> 0 && this.hasTarget()) {
            startTime = current;
            number--;
            Projectile projectile = new Static(new Location(this.getPosition().getRow()+10, this.getPosition().getCol()), this.getTarget().getPosition(), this, 40, 5, env);
            env.addToList(projectile);
        }
        else if (number == 0 && current - startTime>= reloadingTime) {
            number = 4;
        }
    }
}
