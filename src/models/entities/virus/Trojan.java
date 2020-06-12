package models.entities.virus;

import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class Trojan extends Zombie {

    private Tile spawn;

    public Trojan(Location location, Tile tile, World world) {
        super(location, tile, world, 5);
        this.spawn = tile;
    }

    @Override
    public void die() {
        world.addToList(new Zombie(new Location(6 * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE / 2), spawn, world));
        world.addToList(new Zombie(new Location(6 * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE / 2), spawn, world));

    }

    @Override
    public void act() {
        if (this.isAlive())
            super.act();
    }
}
