package models;

import java.util.ArrayList;

public class Dynamic extends Projectile {

    private Entity target;

    public Dynamic(Location location, Location destination, Vector direction, Node sender, Entity target, int damage, int range) {
        super(location, destination, direction, sender, damage, range);
        this.target = target;
    }

    public boolean locationMatch() {
        return this.getLocation().match(this.target.getLocation());
    }

    public void update() {

    }

    @Override
    public void hit(ArrayList<Virus> inRangeVirus) {

    }
}
