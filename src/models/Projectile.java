package models;

public class Projectile {

    private Location location;
    private Location destination;
    private Location direction;
    private Entity sender;
    private int damage;

    public Projectile(Location location, Location destination, Location direction, Entity sender, int damage) {
        this.location = location;
        this.destination = destination;
        this.direction = direction;
        this.sender = sender;
        this.damage = damage;
    }

    /*public boolean inRange() {

    }

    public void hit() {

    }*/

    public Location getLocation() {
        return this.location;
    }
}
