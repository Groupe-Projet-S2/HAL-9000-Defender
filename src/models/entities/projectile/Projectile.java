package models.entities.projectile;

import models.entities.Entity;
import models.entities.tower.Tower;
import models.entities.virus.Virus;
import models.environment.Location;
import models.environment.Vector;
import models.environment.World;

import java.util.ArrayList;

public abstract class Projectile extends Entity {

    protected Location destination;
    protected Vector direction;
    private Tower sender;
    private int damage;
    private World world;

    public Projectile(Location location, Location destination, Tower sender, int damage, int range, World world) {
        super(range, location);
        this.destination = destination;
        this.direction = new Vector();
        this.sender = sender;
        this.damage = damage;
        this.world = world;
    }

    public Projectile(Location location, Tower sender, int damage, int range, World world) {
        super(range, location);
        this.sender = sender;
        this.damage = damage;
        this.world = world;
    }

    public abstract void move();

    public abstract void hit(ArrayList<Virus> inRangeVirus);

    public ArrayList<Virus> getInRangeVirus() {
        return this.sender.getInRangeVirus();
    }

    public void act() {
        if (! getInRangeVirus().isEmpty()) {
            getInRangeVirus().removeIf(v -> ! this.isInRange(v));
            getInRangeVirus().forEach(v -> v.hit(damage));
            die();
        } else die();
    }

    void die() {
        world.getProjectileList().remove(this);
    }
}