package models.entities.tower;

import models.entities.projectile.Dynamic;
import models.entities.projectile.Projectile;
import models.environment.Location;
import models.environment.World;

public class KiloBitDefender extends Tower {

    long startTime;
    int spawnTime;
    int number;

    public KiloBitDefender(int range, Location position, int upgradePrice, int price, int maxCons, int consumption, World env) {
        super(range, position, upgradePrice, price, maxCons, consumption, env);
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
            Projectile projectile = new Dynamic(this, this.getTarget(), 40, 5);
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
