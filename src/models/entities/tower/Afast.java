package models.entities.tower;

import models.entities.projectile.Projectile;
import models.entities.projectile.Static;
import models.environment.Location;
import models.environment.World;

public class Afast extends Tower {

    long startTime;
    int spawnTime;
    int number;

    public Afast(int range, Location location, int upgradePrice, int price, int maxCons, int consumption, World env) {
        super(range, location, upgradePrice, price, maxCons, consumption, env);
        this.startTime = System.currentTimeMillis();
        this.spawnTime = 1000;
        this.number = 4;
    }

    @Override
    public void act() {
        System.out.println(position);
        long current = System.currentTimeMillis();
        if (current >= startTime + spawnTime && number> 0 && this.hasTarget()) {
            startTime = current;
            number--;
            Projectile projectile = new Static(new Location(this.getPosition().getRow()+10, this.getPosition().getCol()), this.getTarget().getPosition(), this, this.projectileDamages, 5);
            env.addToList(projectile);
        }
        else if (number == 0) {
            consumption++;
        }
        else if (consumption == maxCons) {
            number=4;
            consumption = 0;
        }
    }
}
