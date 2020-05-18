package models;

public class Static extends Projectile {

    private int range;

    public Static(Location location, Location destination, Location direction, Entity sender, int range) {
        super(location, destination, direction, sender);
        this.range = range;
    }
}
