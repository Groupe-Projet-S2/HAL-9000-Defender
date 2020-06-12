package models.entities.virus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import models.entities.Entity;
import models.entities.tower.Tower;
import models.environment.Location;
import models.environment.Tile;
import models.environment.World;

public class Adware extends Virus {

    private final ObservableMap<String, Virus> popUps;
    public Adware(Location location, Tile tile, World world) {
        super(35, location, tile, 2, 2, world);
        this.popUps = FXCollections.observableHashMap();
    }

    public ObservableMap<String, Virus> getPopUps() {
        return this.popUps;
    }

    public void close(String id) {
        popUps.remove(id);
        if (popUps.isEmpty()){
            for (Entity tower:targets){
                ((Tower)tower).enable();
            }
            this.die();
        }
    }

    @Override
    public void addTarget(Entity entity) {
        if (entity.isActive() || !Tower.isAFirewall((Tower)entity)) super.addTarget(entity);
    }

    @Override
    public void removeTarget(Entity entity) {
        ((Tower) entity).enable();
        super.removeTarget(entity);
    }

    @Override
    public void die() {
        for (Entity target:targets){
            this.removeTarget(target);
        }
        super.die();
    }

    @Override
    public void move() {
        if (targets.isEmpty()) super.move();
    }

    @Override
    public void act() {
        if (!world.isAdblock()) {
            for (Entity target : targets) {
                if (target.isActive()) {
                    ((Tower) target).disable();
                    for (int i = popUps.size(); i < 5; i++) {
                        popUps.put(getId() + i, this);
                    }
                }
            }
        }
    }
}
