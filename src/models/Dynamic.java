package models;

import java.util.ArrayList;

public class Dynamic extends Projectile {

    private Virus target;

    public Dynamic(Location location, Location destination, Vector direction, Tower sender, Virus target, int damage, int range) {
        super(location, destination, direction, sender, damage, range);
        this.target = target;
    }

    @Override
    public void move() {
        if (!this.isInRange(target)) {
            if(this.location.getRow()!=target.getLocation().getRow()) {
                if(this.location.getRow()<target.getLocation().getRow()) {
                    this.direction.setRow(1);
                }
                else {
                    this.direction.setRow(-1);
                }
            }
            if(this.location.getCol()!=target.getLocation().getCol()) {
                if(this.location.getCol()<target.getLocation().getCol()) {
                    this.direction.setCol(1);
                }
                else {
                    this.direction.setCol(-1);
                }
            }
            this.location.setRow(this.location.getRow()+this.direction.getRow());
            this.location.setCol(this.location.getCol()+this.direction.getCol());
        }
    }

    @Override
    public void hit(ArrayList<Virus> inRangeVirus) {
        if (this.isInRange(target)) {
            this.explode(target);
        }
    }
}

