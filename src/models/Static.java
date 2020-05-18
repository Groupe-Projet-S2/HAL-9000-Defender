package models;

import java.util.ArrayList;

public class Static extends Projectile {

    public Static(Location location, Location destination, Vector direction, Node sender, int damage, int range) {
        super(location, destination, direction, sender, damage, range);
    }

    public void hit(ArrayList<Virus> inRangeVirus) {

        for (int i = 0; i < inRangeVirus.size(); i++) {
            if (this.isInRange(inRangeVirus.get(i))) {
                this.explode(inRangeVirus.get(i));
            }

        }
    }
}
