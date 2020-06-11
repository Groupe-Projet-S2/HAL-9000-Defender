package models.entities.virus;

import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class Trojan extends Zombie {
    public Trojan(Location location, Tile tile, World env) {
        super(location, tile, env);
    }
}
