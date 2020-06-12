package models.entities.virus;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;
import views.AlertBox;

public class Ransomware extends Virus {

    private Location target;
    private BooleanProperty triggered;
    private int cooldown;

    public Ransomware(Location location, Tile tile, Location target, World world) {
        super(35, location, tile, 2, 3, world);
        this.target = target;
        this.triggered = new SimpleBooleanProperty(false);
    }

    public BooleanProperty isTriggered() {
        return triggered;
    }

    public void close() {
        triggered.set(false);
        die();
    }

    public void setCooldown() { this.cooldown = 10000; }

    @Override
    public void move() {
        if (! getCurrent().getPos().match(target)) super.move();
    }

    @Override
    public void act() {
        if (cooldown != 0) {
            cooldown--;
        }
        else {
            if (getCurrent().getPos().match(target) && !triggered.getValue()) {
                triggered.set(true);
            }
        }
    }
}
