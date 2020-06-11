package models.entities.virus;

import controllers.listeners.PopUpsListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.entities.Entity;
import models.entities.tower.Tower;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;
import views.AlertBox;

public class Adware extends Virus {

    private ObservableList<Location> popUps;
    public Adware(Location location, Tile tile, World world) {
        super(35, location, tile, 2, 2, world);
        this.popUps = FXCollections.observableArrayList();
    }

    public ObservableList<Location> getPopUps() {
        return this.popUps;
    }

    public void close(Location popUp) {
        System.out.println(popUp);
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
                for (int i = popUps.size(); i < 1; i++) {
                    popUps.add(new Location(target.getPosition().getRow() + i * 20, target.getPosition().getCol() + i * 20));
                }
            }
        }
    }
}
