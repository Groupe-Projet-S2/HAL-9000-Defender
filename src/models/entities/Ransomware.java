package models.entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import models.environment.Location;
import models.environment.Tile;
import views.AlertBox;

public class Ransomware extends Virus {

    private Location target;
    private boolean triggered;
    private ObservableList<VBox> ransoms;
    public Ransomware(Location location, Tile tile, Location target) {
        super(0, location, tile, 2);
        this.target = target;
        this.triggered = false;
        this.ransoms = FXCollections.observableArrayList();
    }

    public ObservableList<VBox> getRansoms() {
        return this.ransoms;
    }

    public void close() {
        ransoms.clear();
    }

    @Override
    public void move() {
        if (! location.match(target)) super.move();
    }

    @Override
    public void act() {
        if (location.match(target) && ! triggered) {
            triggered = true;
            AlertBox box = new AlertBox(this);
            VBox ransom = box.ransom();
            ransoms.add(ransom);
        }
    }
}
