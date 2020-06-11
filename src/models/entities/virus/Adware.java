package models.entities.virus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import models.entities.Entity;
import models.entities.tower.Tower;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;
import views.AlertBox;

public class Adware extends Virus {

    private ObservableList<VBox> popUps;
    private AlertBox alertBox;
    private int cooldown;
    public Adware(Location location, Tile tile, World env) {
        super(35, location, tile, 2, 2, env);
        this.popUps = FXCollections.observableArrayList();
        this.alertBox = new AlertBox(this);
    }

    public ObservableList<VBox> getPopUps() {
        return this.popUps;
    }

    public void close(VBox popUp) {
        popUps.remove(popUp);
    }

    public void setCooldown() { this.cooldown = 10000; }

    @Override
    public void move() {
        if (targets.isEmpty()) super.move();
    }

    @Override
    public void act() {
        if (cooldown != 0) {
            cooldown--;
        }
        else {
            for (Entity target : targets) {
                if (target.isActive()) {
                    ((Tower) target).setActive(false);
                    for (int i = popUps.size(); i < 5; i++) {
                        popUps.add(alertBox.popUp(new Location(target.getPosition().getRow() + i * 20, target.getPosition().getCol() + i * 20)));
                    }
                }
            }
        }
    }
}
