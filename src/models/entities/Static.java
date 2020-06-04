package models.entities;

import models.environment.Location;
import models.environment.Vector;

import java.util.ArrayList;

public class Static extends Projectile {

    public Static(Location location, Location destination, Vector direction, Tower sender, int damage, int range) {
        super(location, destination, direction, sender, damage, range);
    }

    private int isInTheWay() {
        for (int i = 0 ; i < this.getInRangeVirus().size() ; i++) {
            if (this.isInRange(this.getInRangeVirus().get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void move() {
        /*if (!this.location.match(this.destination) && this.isInTheWay()==-1) {
            if(this.location.getRow()!=this.destination.getRow()) {
                if(this.location.getRow()<this.destination.getRow()) {
                    this.direction.setRow(1);
                }
                else {
                    this.direction.setRow(-1);
                }
            }
            if(this.location.getCol()!=this.destination.getCol()) {
                if(this.location.getCol()<this.destination.getCol()) {
                    this.direction.setCol(1);
                }
                else {
                    this.direction.setCol(-1);
                }
            }
            this.location.setRow(this.location.getRow()+this.direction.getRow());
            this.location.setCol(this.location.getCol()+this.direction.getCol());
        }*/

        double x = destination.getRow()-location.getRow();
        double y = destination.getCol()-location.getCol();
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
        if (this.isInTheWay()!=-1) {
            this.explode(inRangeVirus.get(this.isInTheWay()));
        }
    }
}
