package models.entities;

import models.environment.Location;
import models.environment.Tile;

public class Ransomware extends Virus {

    private Location target;
    private boolean triggered;
    public Ransomware(Location location, Tile tile, Location target) {
        super(0, location, tile, 2);
        this.target = target;
        this.triggered = false;
    }

    @Override
    public void move() {
        if (! location.match(target)) super.move();
    }

    @Override
    public void act() {
        if (location.match(target) && ! triggered) {
            triggered = true;
            System.out.println("Hello");
        }
    }
}
