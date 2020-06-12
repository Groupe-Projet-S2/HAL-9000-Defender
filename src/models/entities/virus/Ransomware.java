package models.entities.virus;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class Ransomware extends Virus {

    private Location target;
    private BooleanProperty triggered;

    public Ransomware(Location location, Tile tile, Location target, World world) {
        super(35, location, tile, 2, 3, world);
        this.target = target;
        this.triggered = new SimpleBooleanProperty(false);
    }

    public BooleanProperty isTriggered() {
        return triggered;
    }

    public void close() {
        if (world.debit(10)){
            triggered.set(false);
            die();
        }
        else if (world.getVirusList().isEmpty()){
            triggered.set(false);
        }
    }

    @Override
    public void move() {
        if (! getCurrent().getPos().match(target)) super.move();
    }

    @Override
    public void act() {
        if (!world.isAdblock()) {
            if (getCurrent().getPos().match(target) && !triggered.getValue()) {
                triggered.set(true);
            }
        }
    }
}
