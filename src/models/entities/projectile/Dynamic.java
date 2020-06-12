package models.entities.projectile;

import models.entities.tower.Tower;
import models.entities.virus.Virus;
import models.environment.Location;
import models.environment.Vector;

import java.util.ArrayList;

public class Dynamic extends Projectile {

    private Virus target;

    public Dynamic(Tower sender, Virus target, int damage, int range) {
        super(new Location(sender.getPosition().getRow()+10, sender.getPosition().getCol()), target.getPosition(), sender, damage, range);
        this.target = target;
        speed = 3;
    }

    @Override
    public void move() {
        double x = target.getPosition().getCol() - position.getCol();
        double y = target.getPosition().getRow() - position.getRow();
        double coef = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
        //double coef = Math.min(Math.abs(x), Math.abs(y));

        x = Math.round(x/coef);
        y = Math.round(y/coef);

        direction.setRow(y);
        direction.setCol(x);

        position.setRow(position.getRow() + (int) direction.getRow()*speed);
        position.setCol(position.getCol() + (int) direction.getCol()*speed);
    }

    @Override
    public void hit(ArrayList<Virus> inRangeVirus) {
        if (this.isInRange(target)) {
            this.act();
        }
    }

    @Override
    public boolean isOnTarget() {
        return this.isInRange(target);
    }
}

