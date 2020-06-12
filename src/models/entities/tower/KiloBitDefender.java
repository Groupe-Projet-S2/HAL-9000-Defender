package models.entities.tower;

import models.entities.projectile.Dynamic;
import models.entities.projectile.Projectile;
import models.entities.projectile.Static;
import models.environment.Location;
import models.environment.World;

public class KiloBitDefender extends Tower {

    long startTime;
    int spawnTime;
    int number;

    public KiloBitDefender(Location location, World env) {
        super(100, location, 100, 50, 3000, 500, env);
        startTime = System.currentTimeMillis();
        spawnTime = 500;
        number = 4;
    }

    @Override
    public void act() {
        long current = System.currentTimeMillis();
        if (current - startTime>= spawningTime && number> 0 && this.hasTarget()) {
            startTime = current;
            number--;
            Projectile projectile = new Dynamic(this, this.getTarget(), 40, 10, env);
            env.addToList(projectile);
        }
        else if (number == 0 && current - startTime >= reloadingTime) {
            number = 4;
        }
    }
}
