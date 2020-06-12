package models.entities.virus;

import models.entities.Entity;
import models.entities.tower.Tower;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class Zombie extends Virus {

    private int damage;
    public Zombie(Location location, Tile tile, World env) {
        super(35, location, tile, 2, 1, env);
        this.damage = 10;
    }
/*
    @Override
    public void move() {
        if ()
    }*/

    @Override
    public void act() {
        targets.removeIf(t -> !this.isInRange(t));
        for (Entity target : targets) {
            if (Entity.isNode(target)) {
                ((Tower) target).setSpawningTime(((Tower) target).getSpawningTime() + damage);
                System.out.println(((Tower) target).getSpawningTime());
            }
        }
    }
}
