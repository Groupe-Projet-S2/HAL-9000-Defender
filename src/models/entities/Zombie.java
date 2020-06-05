package models.entities;

import models.environment.Location;
import models.environment.Tile;

public class Zombie extends Virus {

    private int damage;
    public Zombie(Location location, Tile tile) {
        super(35, location, tile, 2, 1);
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
                ((Tower) target).setConsumption(((Tower) target).getConsumption() + damage);
                System.out.println(((Tower) target).getConsumption());
            }
        }
    }
}
