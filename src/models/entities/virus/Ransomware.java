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
    public Ransomware(Location location, Tile tile, Location target, World world) {
        super(0, location, tile, 2, 3, world);
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

    @Override
    public void move() {
        if (! getCurrent().getPos().match(target)) super.move();
    }

    @Override
    public void act() {
        if (getCurrent().getPos().match(target) && ! triggered.getValue()) {
            triggered.set(true);
        }
    }
}
