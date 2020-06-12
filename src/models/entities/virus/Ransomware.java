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
    private int cooldown;

    public Ransomware(Location location, Tile tile, Location target, World env) {
        super(35, location, tile, 2, 3, env);
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

    public void setCooldown() { this.cooldown = 10000; }

    @Override
    public void move() {
            if (!position.match(target))
                super.move();
    }

    @Override
    public void act() {
        if (cooldown != 0) {
            cooldown--;
        }
        else {
            if (position.match(target) && !triggered) {
                triggered = true;
                AlertBox box = new AlertBox(this);
                VBox ransom = box.ransom();
                ransoms.add(ransom);
            }
        }
    }
}
