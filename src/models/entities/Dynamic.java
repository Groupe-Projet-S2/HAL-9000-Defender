package models.entities;

import models.environment.Location;
import models.environment.Vector;

import java.util.ArrayList;

public class Dynamic extends Projectile {

    private Virus target;

    private final static float THRUST_AMOUNT = 3.3f;
    private final static float MISSILE_THRUST_AMOUNT = 6.3F;
    private final Location origin;


    public Dynamic(Location location, Tower sender, Virus target, int damage, int range) {
        super(location, target.getLocation(), new Vector(0, 0), sender, damage, range);
        this.target = target;
        this.origin = location;
    }

    @Override
    public void move() {

        double x = destination.getCol()-location.getCol();
        double y = destination.getRow()-location.getRow();
        double coef = Math.min(Math.abs(x),Math.abs(y));

        x = Math.round(x/coef);
        y = Math.round(y/coef);

        direction.setRow((int)y);
        direction.setCol((int)x);

        location.setRow(location.getRow()+direction.getRow());
        location.setCol(location.getCol()+direction.getCol());
    }

    @Override
    public void hit(ArrayList<Virus> inRangeVirus) {
        if (this.isInRange(target)) {
            this.explode(target);
        }
    }
}

