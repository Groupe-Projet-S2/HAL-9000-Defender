package models.environment;

import controllers.listeners.PopUpsListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.image.ImageView;
import models.entities.projectile.Projectile;
import models.entities.Entity;
import models.entities.tower.Tower;
import models.entities.virus.*;

public class World {

    private ObservableList<Tower> towers;
    private ObservableList<Virus> viruses;
    private ObservableList<Projectile> projectiles;
    private ObservableMap<Location, Virus> boxes;

    private ImageView selectedNodePreview;
    private Location selectedNodeLocation;
    private int selectedNode;

    private TileMap tileMap;
    public World(TileMap tileMap) {
        this.towers = FXCollections.observableArrayList();
        this.viruses = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        this.boxes = FXCollections.observableHashMap();
        this.tileMap = tileMap;
    }

    public void addToList(Entity e) {
        if (Entity.isNode(e))
            towers.add((Tower) e);
        else if (Entity.isProjectile(e))
            projectiles.add((Projectile) e);
        else if (Entity.isVirus(e))
            viruses.add((Virus) e);
    }

    public ImageView getSelectedNodePreview() {
        return selectedNodePreview;
    }

    public void setSelectedNodePreview(ImageView selectedNodePreview) {
        this.selectedNodePreview = selectedNodePreview;
    }

    public int getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(int selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Location getSelectedNodeLocation() {
        return selectedNodeLocation;
    }

    public void setSelectedNodeLocation(Location selectedNodeLocation) {
        this.selectedNodeLocation = selectedNodeLocation;
    }

    public ObservableMap<Location, Virus> getBoxes() {
        return boxes;
    }

    public ObservableList<Tower> getNodeList(){
        return towers;
    }

    public ObservableList<Virus> getVirusList() {
        return viruses;
    }

    public ObservableList<Projectile> getProjectileList(){
        return projectiles;
    }

    public Virus getVirus(String id){
        for (Virus virus : viruses){
            if (virus.getId().equals(id))
                return virus;
        }
        return null;
    }

    public Tower getNode(String id){
        for (Tower tower : towers){
            if (tower.getId().equals(id))
                return tower;
        }
        return null;
    }

    public Projectile getProjectile(String id){
        for (Projectile projectile : projectiles){
            if (projectile.getId().equals(id))
                return projectile;
        }
        return null;
    }

   /* void removeEntity(Entity entity, ObservableList<Entity> liste) {
        liste.remove(entity);
    }*/

    public void enemySet(int virus) {
        Location loc = new Location(6 * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE / 2);
        switch (virus) {
            case 1:
                addToList(new Zombie(loc, tileMap.getTile(6, 0), this));
                break;
            case 2:
                Adware adware = new Adware(loc, tileMap.getTile(6, 0), this);
                adware.getPopUps().addListener((ListChangeListener<? super Location>) c -> {
                    while (c.next()) {
                        if (c.wasAdded())
                            for (Location location : c.getAddedSubList())
                                boxes.put(location, adware);
                        if (c.wasRemoved())
                            for (Location location : c.getRemoved()) {
                                boxes.remove(location);
                                System.out.println("Removed " + location);
                            }
                    }
                });
                addToList(adware);
                break;
            case 3:
                addToList(new Ransomware(loc, tileMap.getTile(6, 0), new Location(5, 23), this));
                break;
            case 4:
                addToList(new Worm(loc, tileMap.getTile(6, 0), this));
                break;
            case 5:
                addToList(new Trojan(loc, tileMap.getTile(6, 0), this));
                break;
            case 6:
                break;
        }
    }

    public void nextRound() {
        for (Virus virus:getVirusList()) {
            virus.move();
            virus.detection();
            virus.act();
        }
        for (Tower tower:getNodeList()) {
            tower.detection();
            tower.act();
        }
        for (Projectile projectile : getProjectileList()) {
            projectile.move();
        }
    }
}
