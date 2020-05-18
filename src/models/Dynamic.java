package models;

public class Dynamic extends Projectile {

    private Entity target;

    public Dynamic(Location location, Location destination, Location direction, Entity sender, Entity target, int damage) {
        super(location, destination, direction, sender, damage);
        this.target = target;
    }

    public boolean locationMatch() {
        return this.getLocation()==this.target.getLocation();
    }

    public void update() {

    }
}
