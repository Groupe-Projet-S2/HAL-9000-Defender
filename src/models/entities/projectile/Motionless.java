package models.entities.projectile;

import models.entities.tower.Tower;
import models.entities.virus.Virus;
import models.environment.Location;

import java.util.ArrayList;

public class Motionless extends Projectile {

    public Motionless(Location location, Tower sender, int damage, int range) {
        super(location, sender, damage, range);
    }

    @Override
    public void move() {
    }

    @Override
    public void hit(ArrayList<Virus> inRangeVirus) {
        for (int i = 0 ; i < this.getInRangeVirus().size() ; i++) {
            if (this.isInRange(inRangeVirus.get(i))) {
                this.explode(inRangeVirus.get(i));
            }
        }
    }

    @Override
    public boolean isOnTarget() {
        return false;
    }
}
