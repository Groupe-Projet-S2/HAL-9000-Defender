package models.entities.projectile;

import models.entities.Entity;
import models.entities.tower.Tower;
import models.entities.virus.Virus;
import models.environment.Location;
import models.environment.Vector;

import java.util.ArrayList;

public abstract class Projectile extends Entity {

    protected Location destination;
    protected Vector direction;
    private Tower sender;
    private int damage;

    public Projectile(Location location, Location destination, Tower sender, int damage, int range) {
        super(range, location);
        this.destination = destination;
        this.direction = new Vector();
        this.sender = sender;
        this.damage = damage;
    }

    public Projectile(Location location, Tower sender, int damage, int range) {
        super(range, location);
        this.sender = sender;
        this.damage = damage;
    }

    public abstract void move();

    public abstract void hit(ArrayList<Virus> inRangeVirus);

    public int getDamage() {
        return this.damage;
    }

    public ArrayList<Virus> getInRangeVirus() {
        return this.sender.getInRangeVirus();
    }

    public void explode(Virus virus) {
        virus.setCurrentHP(virus.getCurrentHP() - this.getDamage());
    }
}