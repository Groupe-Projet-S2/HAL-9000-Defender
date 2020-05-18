package models;

public class Motionless extends Projectile {

    private int range;

    public Motionless(Location location, Location destination, Location direction, Entity sender, int range, int damage) {
        super(location, destination, direction, sender, damage);
        this.range = range;
    }





}
