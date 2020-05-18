package models;

public class Static extends Projectile {

    private int range;

    public Static(Location location, Location destination, Location direction, Entity sender, int damage, int range) {
        super(location, destination, direction, sender, damage);
        this.range = range;
    }
}
