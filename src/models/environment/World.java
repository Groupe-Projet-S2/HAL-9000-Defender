package models.environment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.image.ImageView;
import models.entities.bonus.Bonus;
import models.entities.projectile.Projectile;
import models.entities.Entity;
import models.entities.tower.Tower;
import models.entities.virus.*;
import models.entities.virus.listeners.*;

public class World {

    private ObservableList<Tower> towers;
    private ObservableList<Virus> viruses;
    private ObservableList<Projectile> projectiles;
    private ObservableMap<String, Virus> hostileBoxes;
    private boolean hasRansom;

    private ObservableList<Bonus> bonuses;
    private ImageView selectedNodePreview;
    private Location selectedNodeLocation;
    private int selectedNode;
    private TileMap tileMap;

    public World(TileMap tileMap) {
        this.towers = FXCollections.observableArrayList();
        this.viruses = FXCollections.observableArrayList();
        this.bonuses = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        this.hostileBoxes = FXCollections.observableHashMap();
        this.tileMap = tileMap;
        this.hasRansom = false;
    }

    public void addToList(Entity e) {
        if (Entity.isNode(e))
            towers.add((Tower) e);
        else if (Entity.isProjectile(e))
            projectiles.add((Projectile) e);
        else if (Entity.isVirus(e))
            viruses.add((Virus) e);
    }

    public void addBonus(Bonus bonus) {
        bonuses.add(bonus);
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

    public ObservableMap<String, Virus> getHostileBoxes() {
        return hostileBoxes;
    }

    public TileMap getTileMap() { return this.tileMap; }

    public ObservableList<Tower> getNodeList(){
        return towers;
    }
    public ObservableList<Virus> getVirusList() {
        return viruses;
    }

    public ObservableList<Projectile> getProjectileList(){
        return projectiles;
    }

    public ObservableList<Bonus> getBonusList() {
        return bonuses;
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
                adware.getPopUps().addListener(new AdwareListener(hostileBoxes, adware));
                addToList(adware);
                break;
            case 3:
                Ransomware ransomware = new Ransomware(loc, tileMap.getTile(6, 0), new Location(6, 2), this);
                ransomware.isTriggered().addListener(new RansomwareListener(hostileBoxes, hasRansom, ransomware));
                addToList(ransomware);
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
        for (int i = getVirusList().size()-1; i>=0; i--) {
            getVirusList().get(i).move();
            getVirusList().get(i).detection();
            getVirusList().get(i).act();
            if (getVirusList().get(i).isRemovable()){
                getVirusList().remove(this);
            }
        }
        for (Tower tower:getNodeList()) {
            if (tower.isActive()) {
                tower.detection();
                tower.act();
            }
        }
        for (int i = getProjectileList().size()-1; i>=0; i--) {
            projectiles.get(i).move();
            if (projectiles.get(i).isOnTarget()){
                projectiles.remove(projectiles.get(i));
            }
        }

        for (Bonus bonus : getBonusList()) {
            if (bonus.isActive()) {
                bonus.act();
            }
        }
    }
}
