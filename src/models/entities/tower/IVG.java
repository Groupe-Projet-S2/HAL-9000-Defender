package models.entities.tower;

import models.entities.projectile.Motionless;
import models.entities.projectile.Projectile;
import models.environment.Location;
import models.environment.World;

public class IVG extends Tower {

    private long startTime;
    private int spawnTime;
    private int number;

    public IVG(Location location, World env) {
        super(50, location, 100, 50, 1000, 500, env);
        startTime = System.currentTimeMillis();
        spawnTime = 500;
        number = 4;
    }

    @Override
    public void act() {
        long current = System.currentTimeMillis();
        if (current - startTime >= spawnTime && number > 0 && this.hasTarget()) {
            startTime = current;
            number--;
            this.getTarget().die();
        }
        else if (number == 0 && current - startTime >= reloadingTime) {
            number = 4;
        }
    }
}
