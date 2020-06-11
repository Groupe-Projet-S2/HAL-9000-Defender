package models.entities.virus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;
import views.AlertBox;

public class Ransomware extends Virus {

    private Location target;
    private boolean triggered;
    private ObservableList<VBox> ransoms;
    public Ransomware(Location location, Tile tile, Location target, World world) {
        super(0, location, tile, 2, 3, world);
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
        if (! position.match(target)) super.move();
    }

    @Override
    public void act() {
        if (position.match(target) && ! triggered) {
            triggered = true;
            AlertBox box = new AlertBox();
            box.setVirus(this);
            VBox ransom = box.ransom();
            ransoms.add(ransom);
        }
    }
}
