package models;

import java.util.ArrayList;

public abstract class Projectile extends Entity {

    protected Location destination;
    protected Vector direction;
    private Node sender;
    private int damage;

    public Projectile(Location location, Location destination, Vector direction, Node sender, int damage, int range) {
        super(range, location);
        this.destination = destination;
        this.direction = direction;
        this.sender = sender;
        this.damage = damage;
    }

    public Projectile(Location location, Node sender, int damage, int range) {
        super(range, location);
        this.sender = sender;
        this.damage = damage;
    }

    public abstract void move();

    public abstract void hit(ArrayList<Virus> inRangeVirus);

    public Location getDestination() {
        return this.destination;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public int getDamage() {
        return this.damage;
    }

    public Entity getSender() {
        return this.sender;
    }

    public ArrayList<Virus> getInRangeVirus() {
        return this.sender.getInRangeVirus();
    }

    public void explode(Virus virus) {
        virus.setCurrentHP(virus.getCurrentHP()-this.getDamage());
    }
}