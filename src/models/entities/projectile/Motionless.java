package models.entities.projectile;

import models.entities.tower.GoodwareBytes;
import models.entities.tower.Tower;
import models.entities.virus.Virus;
import models.environment.Location;
import models.environment.World;

public class Motionless extends Projectile {

    private final GoodwareBytes sender;
    public Motionless(Location location, Tower sender, int damage, int range, World world) {
        super(location, sender, damage, range, world);
        this.sender = (GoodwareBytes) sender;
    }

    @Override
    public void move() {}

    @Override
    public boolean isOnTarget() {
        return ! getWorld().getVirusList().filtered(this::isInRange).isEmpty();
    }

    @Override
    public void explode() {
        for (Virus virus : getWorld().getVirusList()) {
            if (this.isInRange(virus)) {
                virus.setSpeed(virus.getSpeed() - 1);
                virus.hit(getDamage());
                if (virus.getSpeed() == 0) {
                    virus.die();
                }
            }
        }
        sender.clearProjectile();
    }
}
