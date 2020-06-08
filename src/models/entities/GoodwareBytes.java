package models.entities;

import models.environment.Location;
import models.environment.World;

public class GoodwareBytes extends Tower {

    long startTime;
    int spawnTime;
    int number;

    public GoodwareBytes(int range, Location location, int upgradePrice, int price, int maxCons, int consumption, World env) {
        super(range, location, upgradePrice, price, maxCons, consumption, env);
        startTime = System.currentTimeMillis();
        spawnTime = 500;
        number = 4;
    }

    @Override
    public void act() {
        long current = System.currentTimeMillis();
        if (current >= startTime + spawnTime && number > 0 && this.hasTarget()) {
            startTime = current;
            number--;
            Projectile projectile = new Motionless(this.location, this, 40, 5);
            env.addToList(projectile);
        }
        else if (number == 0) {
            consumption++;
        }
        else if (consumption == maxCons) {
            number = 4;
            consumption = 0;
        }
    }
}
