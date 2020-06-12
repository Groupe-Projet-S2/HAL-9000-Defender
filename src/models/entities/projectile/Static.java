package models.entities.projectile;

import models.entities.tower.Tower;
import models.entities.virus.Virus;
import models.environment.Location;
import models.environment.World;

public class Static extends Projectile {

    private final Location reach;

    public Static(Location location, Location destination, Tower sender, int damage, int range, World world) {
        super(location, destination, sender, damage, range, world);
        reach = new Location(sender.getTarget().getPosition().getRow(), sender.getTarget().getPosition().getCol());
    }

    @Override
    public void move() {
        double x = reach.getCol()- position.getCol();
        double y = reach.getRow()- position.getRow();
        double coef = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));

        x = Math.round(x/coef);
        y = Math.round(y/coef);

        direction.setRow(y);
        direction.setCol(x);

        position.setRow(position.getRow()+(int)direction.getRow());
        position.setCol(position.getCol()+(int)direction.getCol());
    }

    @Override
    public boolean isOnTarget(){
        return getPosition().match(reach);
    }

    @Override
    public void explode() {
        for (Virus virus:getWorld().getVirusList()){
            if (this.isInRange(virus))
                virus.hit(getDamage());
        }
    }
}
