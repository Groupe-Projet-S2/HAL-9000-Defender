package models.entities.virus;

import models.entities.Entity;
import models.entities.tower.Tower;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class Zombie extends Virus {

    private int damage;

    public Zombie(Location location, Tile tile, World world, int id) {
        super(35, location, tile, 2, id, world);
        this.damage = 10;
    }

    public Zombie(Location location, Tile tile, World world) {
        super(35, location, tile, 2, 1, world);
        this.damage = 10;
    }

    @Override
    public void act() {
        targets.removeIf(t -> !this.isInRange(t));
        for (Entity target : targets) {
            if (Entity.isNode(target)) {
                ((Tower) target).setSpawningTime(((Tower) target).getSpawningTime() + damage);
            }
        }
    }
}
