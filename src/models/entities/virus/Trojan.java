package models.entities.virus;

import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class Trojan extends Zombie {

    private long current;
    private int cooldown;
    private Tile spawn;
    private int split;

    public Trojan(Location location, Tile tile, World world) {
        super(location, tile, world, 5);
        this.spawn = tile;
        this.current = System.currentTimeMillis();
        this.cooldown = 1500;
        this.split = 0;
    }

    @Override
    public void die() {}

    @Override
    public void act() {
        if (currentHP > 0)
            super.act();
        else {
            if (System.currentTimeMillis() - current >= cooldown && split < 2) {
                current = System.currentTimeMillis();
                world.addToList(new Zombie(new Location(6 * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE / 2), spawn, world));
                split++;
            } else if (split >= 2) super.die();
        }
    }
}
