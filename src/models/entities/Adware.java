package models.entities;

import controllers.MapController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import models.environment.Location;
import models.environment.Tile;
import views.AlertBox;

import java.util.ArrayList;

public class Adware extends Virus {

    private ObservableList<VBox> popUps;
    private AlertBox alertBox;
    public Adware(Location location, Tile tile) {
        super(35, location, tile, 2);
        this.popUps = FXCollections.observableArrayList();
        this.alertBox = new AlertBox(this);
    }

    public ObservableList<VBox> getPopUps() {
        return this.popUps;
    }

    public void close(VBox popUp) {
        popUps.remove(popUp);
    }

    @Override
    public void move() {
        if (targets.isEmpty()) super.move();
    }

    @Override
    public void act() {
        for (Entity target : targets) {
            if (target.isActive()) {
                ((Tower) target).setActive(false);
                for (int i = popUps.size(); i < 5; i++) {
                    popUps.add(alertBox.popUp(new Location(target.getLocation().getRow() + i * 20, target.getLocation().getCol() + i * 20)));
                }
            }
        }
    }
}
