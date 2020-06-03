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

        /*int x = destination.getCol();
        int y = destination.getRow();
        int xD = x - location.getCol();
        int yD = y - location.getRow();
        int distance = (int) Math.sqrt(yD * yD + (xD*xD));
        double ratio = 4.0/distance;
        int ySpeed = (int) ratio*yD;
        int xSpeed = (int) ratio*xD;
        System.out.println(distance);
        location.set(location.getRow() + distance / yD, location.getCol() + distance / xD);*/

        if(location.getRow()<destination.getRow()) {
            direction.setRow(1);
        }
        if (location.getRow() > destination.getRow()) {
            direction.setRow(-1);
        }
        if(location.getCol() < destination.getCol()) {
            direction.setCol(1);
        }
        if (location.getCol() > destination.getCol()) {
            direction.setCol(-1);
        }

        this.location.set(location.getRow()+direction.getRow() * 2, location.getCol()+direction.getCol() * 2);
        this.direction.setCol(0);
        this.direction.setRow(0);
    }

    @Override
    public void hit(ArrayList<Virus> inRangeVirus) {
        if (this.isInRange(target)) {
            this.explode(target);
        }
    }
}

