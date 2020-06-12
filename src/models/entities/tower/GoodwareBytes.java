package models.entities.tower;

import models.entities.projectile.Motionless;
import models.entities.projectile.Projectile;
import models.environment.Location;
import models.environment.World;

public class GoodwareBytes extends Tower {

    private long startTime;
    private int spawnTime;
    private int number;

    public GoodwareBytes(Location location, World env) {
        super(50, location, 100, 50, 1000, 500, env);
        startTime = System.currentTimeMillis();
        spawnTime = 500;
        number = 4;
    }

    @Override
    public void act() {
        System.out.println(position);
        long current = System.currentTimeMillis();
        if (current >= startTime + spawnTime && number > 0 && this.hasTarget()) {
            startTime = current;
            number--;
            Projectile projectile = new Motionless(new Location(this.getPosition().getRow()+10, this.getPosition().getCol()), this, 40, 5, env);
            env.addToList(projectile);
        }
        else if (number == 0 && current - startTime >= reloadingTime) {
            number = 4;
        }
    }
}
