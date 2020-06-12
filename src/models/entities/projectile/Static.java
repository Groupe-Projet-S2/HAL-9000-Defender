package models.entities.projectile;

import models.entities.tower.Tower;
import models.entities.virus.Virus;
import models.environment.Location;
import models.environment.World;

import java.util.ArrayList;

public class Static extends Projectile {

    public Static(Location location, Location destination, Tower sender, int damage, int range, World world) {
        super(location, destination, sender, damage, range, world);
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
        double x = destination.getCol()- position.getCol();
        double y = destination.getRow()- position.getRow();
        double coef = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));

        x = Math.round(x/coef);
        y = Math.round(y/coef);

        direction.setRow(y);
        direction.setCol(x);

        position.setRow(position.getRow()+(int)direction.getRow());
        position.setCol(position.getCol()+(int)direction.getCol());
    }

    @Override
    public void hit(ArrayList<Virus> inRangeVirus) {
        if (this.isInTheWay()!=-1) {
            this.act();
        }
    }
}
