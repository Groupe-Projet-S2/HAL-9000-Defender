package models.entities.virus;

import models.entities.Entity;
import models.entities.tower.Tower;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class Worm extends Virus {

    private boolean triggered;
    public Worm(Location location, Tile tile, World world) {
        super(50, location, tile, 1, 4, world);
        this.triggered = false;
    }

    @Override
    public void hit(int damage) {
        super.hit(damage);
        if (! triggered) {
            for (Entity tower : targets)
                ((Tower) tower).disable();
            triggered = false;
        }
    }

    @Override
    public void act() {
        if (targets.isEmpty()) triggered = false;
    }
}
