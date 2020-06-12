package models.environment;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.image.ImageView;
import models.entities.bonus.Bonus;
import models.entities.projectile.Projectile;
import models.entities.Entity;
import models.entities.tower.Damagable;
import models.entities.tower.Tower;
import models.entities.virus.*;
import models.entities.virus.listeners.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class World {

    private final ObservableList<Tower> towers;
    private final ObservableList<Virus> viruses;
    private final ObservableList<Projectile> projectiles;
    private final ObservableMap<String, Virus> hostileBoxes;
    private final boolean hasRansom;
    private boolean adblock;

    private final ObservableList<Bonus> bonuses;
    private ImageView selectedNodePreview;
    private Location selectedNodeLocation;
    private int selectedNode;
    private final TileMap tileMap;
    private final IntegerProperty money;
    private final IntegerProperty killCount;
    private final LinkedHashMap<Tile,Tile> path;

    public World(TileMap tileMap, LinkedHashMap<Tile,Tile> path) {
        this.towers = FXCollections.observableArrayList();
        this.viruses = FXCollections.observableArrayList();
        this.bonuses = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        this.hostileBoxes = FXCollections.observableHashMap();
        this.tileMap = tileMap;
        this.hasRansom = false;
        this.money = new SimpleIntegerProperty(5000);
        this.killCount = new SimpleIntegerProperty(0);
        this.adblock = false;
        this.path = path;
    }

    public HashMap<Tile, Tile> getPath() {
        return path;
    }

    public boolean isAdblock() {
        return adblock;
    }

    public int getMoney() {
        return money.get();
    }

    public IntegerProperty moneyProperty() {
        return money;
    }

    public boolean debit(int a) {
        if (money.getValue() >= a){
            money.set(money.getValue()-a);
            return true;
        }
        return false;
    }

    public void credit(int a){
        money.set(money.getValue() + Math.max(0,a));
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

    public IntegerProperty killCountProperty() {
        return killCount;
    }

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

    public void setAdblock(boolean adblock) {
        this.adblock = adblock;
    }

    public void nextRound() {
        for (int i = getVirusList().size()-1; i>=0; i--) {
            if (!getVirusList().get(i).isAlive()){
                getVirusList().remove(getVirusList().get(i));
                killCount.setValue(killCount.getValue()+1);
                credit(20);
            }else {
                getVirusList().get(i).move();
                getVirusList().get(i).detection();
                getVirusList().get(i).act();
            }
        }
        for (int i = getNodeList().size()-1; i>=0; i--) {
            if (getNodeList().get(i).isActive()) {
                if (!Tower.isAFirewall(getNodeList().get(i)))
                    getNodeList().get(i).detection();
                getNodeList().get(i).act();
            }
            if (Tower.isAFirewall(getNodeList().get(i))) {
                if (!((Damagable)getNodeList().get(i)).isAlive()){
                    getNodeList().remove(i);
                }
            }
        }


        for (int i = getProjectileList().size()-1; i>=0; i--) {
            projectiles.get(i).move();
            if (projectiles.get(i).isOnTarget()){
                projectiles.get(i).explode();
                projectiles.remove(projectiles.get(i));
            }
        }

        for (int k = getBonusList().size()-1; k>=0; k--) {
            if (getBonusList().get(k).isActive()) {
                getBonusList().get(k).act();
            }
        }
    }
}
