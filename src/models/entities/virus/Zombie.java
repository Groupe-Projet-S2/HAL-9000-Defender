package models.entities.virus;

import models.entities.Entity;
import models.entities.tower.Damagable;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class Zombie extends Virus {

    private final int damage;
    private long startTime;

    public Zombie(Location location, Tile tile, World world, int id) {
        super(35, location, tile, 2, id, world);
        this.startTime = System.currentTimeMillis();
        this.damage = 10;
    }

    public Zombie(Location location, Tile tile, World world) {
        super(35, location, tile, 2, 1, world);
        this.startTime = System.currentTimeMillis();
        this.damage = 10;
    }

    @Override
    public void act() {
        targets.removeIf(t -> !this.isInRange(t) || !(t instanceof Damagable) || !((Damagable)t).isAlive());
        for (Entity target : targets) {
            if (Entity.isNode(target) && System.currentTimeMillis()-startTime>=1000) {
                startTime = System.currentTimeMillis();
                ((Damagable) target).getDamaged(damage);
            }
        }
    }
}
