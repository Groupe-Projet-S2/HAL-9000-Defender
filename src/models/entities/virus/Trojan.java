package models.entities.virus;

import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class Trojan extends Zombie {

    public Trojan(Location location, Tile tile, World world) {
        super(location, tile, world);
    }

    @Override
    void die() {
        this.getWorld().addToList(new Zombie(position, getCurrent(), getWorld()));
        this.getWorld().addToList(new Zombie(position, getCurrent(), getWorld()));
    }
}
