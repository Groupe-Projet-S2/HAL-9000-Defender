package models.entities.virus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.entities.Entity;
import models.entities.tower.Tower;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class Adware extends Virus {

    private ObservableList<Location> popUps;
    public Adware(Location location, Tile tile, World world) {
        super(35, location, tile, 2, 2, world);
        this.popUps = FXCollections.observableArrayList();
    }

    public ObservableList<Location> getPopUps() {
        return this.popUps;
    }

    public void close(Location loc) {
        popUps.remove(loc);
    }

    @Override
    public void addTarget(Entity entity) {
        if (entity.isActive()) super.addTarget(entity);
    }

    @Override
    public void removeTarget(Entity entity) {
        ((Tower) entity).setActive(true);
        super.removeTarget(entity);
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
                    popUps.add(new Location(target.getPosition().getRow() + i * 100, target.getPosition().getCol() + i * 100));
                }
            }
        }
    }
}
