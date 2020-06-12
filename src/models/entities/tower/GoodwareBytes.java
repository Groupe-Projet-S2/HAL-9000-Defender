package models.entities.tower;

import models.entities.projectile.Motionless;
import models.entities.projectile.Projectile;
import models.environment.Location;
import models.environment.World;

public class GoodwareBytes extends Tower {

    long startTime;
    int number;

    public GoodwareBytes(Location location, World env) {
        super(50, location, 100, 50, 1000, 500, env);
        startTime = System.currentTimeMillis();
        number = 4;
    }

    @Override
    public void act() {
        long current = System.currentTimeMillis();
        if (current - startTime >= spawningTime && number > 0 && this.hasTarget()) {
            startTime = current;
            number--;
            Projectile projectile = new Motionless(new Location(this.getPosition().getRow()+10, this.getPosition().getCol()), this, 40, 5);
            env.addToList(projectile);
        }
        else if (number == 0 && current - startTime >= reloadingTime) {
            number = 4;
        }
    }
}
