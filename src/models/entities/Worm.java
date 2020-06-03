package models.entities;

import models.environment.Location;
import models.environment.Tile;

public class Worm extends Virus {

    private boolean triggered;
    public Worm(Location location, Tile tile) {
        super(0, location, tile, 1);
        this.triggered = false;
    }

    @Override
    public void act() {
        if (targets.isEmpty()) triggered = false;
        else if (! triggered) {
            triggered = true;
            for (Entity target : targets)
                if (Entity.isNode(target))
                    ((Tower) target).setActive(false);
            System.out.println("Defences are disabled !");
        }
    }
}
