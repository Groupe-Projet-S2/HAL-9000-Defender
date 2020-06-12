package models.entities.virus;

import models.entities.Entity;
import models.entities.tower.Tower;
import models.entities.tower.Firewall;
import models.entities.tower.Tower;
import models.environment.Location;
import models.environment.Tile;
import models.environment.Vector;
import models.environment.World;

import java.util.HashSet;
import java.util.Set;

public abstract class Virus extends Entity {

    protected int sizeW, sizeH, maxHP, currentHP = 100;
    protected Tile current;
    private Vector direction;
    private int speed;
    protected Set<Entity> targets;
    private int virusID;
    private Tile spawnTile;
    protected World world;

    public Virus(int range, Location location, Tile tile, int speed, int virusID, World world) {
        super(range, location);
        this.world = world;
        this.virusID = virusID;
        this.direction = new Vector();
        this.current = tile;
        this.spawnTile = tile;
        this.speed = speed;
        this.targets = new HashSet<>();
        this.sizeH = 16;
        this.sizeW = 16;
    }

    public int getVirusID() {
        return virusID;
    }

    public boolean isAlive(){
        return currentHP>0;
    }

    private void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void detection() {
        for (Tower tower : world.getNodeList()) {
            if (this.isInRange(tower))
                addTarget(tower);
            else if (targets.contains(tower))
                removeTarget(tower);
        }
    }

    public World getWorld() {
        return world;
    }

    public Tile getCurrent() {
        return current;
    }

    public static boolean isAnAdware(Virus virus) { return (virus instanceof Adware); }

    public static boolean isARansomware(Virus virus) { return (virus instanceof Ransomware); }

    public Tile getSpawnTile() { return this.spawnTile; }

    public void setCurrent(Tile tile) { this.current = tile; }

    public boolean detection() {
        for (int j = environment.getNodeList().size()-1; j>=0; j--) {
            if (isInRange(environment.getNodeList().get(j))) {
                return(Tower.isAFirewall(environment.getNodeList().get(j)));
            }
        }
        return false;
    }

    public void move() {

        Tile parent = current.getParent();

        if (!detection()) {
            if (this.getPosition().getRow() == current.getPos().getRow() * Tile.SIZE + (Tile.SIZE / 2) && this.getPosition().getCol() == current.getPos().getCol() * Tile.SIZE + (Tile.SIZE / 2)) {

                if (this.getPosition().getRow() < parent.getPos().getRow() * Tile.SIZE + (Tile.SIZE / 2))
                    direction.setRow(1);
                else if (this.getPosition().getRow() > parent.getPos().getRow() * Tile.SIZE + (Tile.SIZE / 2))
                    direction.setRow(-1);
                else
                    direction.setRow(0);

                if (this.getPosition().getCol() < parent.getPos().getCol() * Tile.SIZE + (Tile.SIZE / 2))
                    direction.setCol(1);
                else if (this.getPosition().getCol() > parent.getPos().getCol() * Tile.SIZE + (Tile.SIZE / 2))
                    direction.setCol(-1);
                else
                    direction.setCol(0);

                current = parent;
            }
            this.getPosition().setRow(this.getPosition().getRow() + (int) direction.getRow() * speed);
            this.getPosition().setCol(this.getPosition().getCol() + (int) direction.getCol() * speed);
        }
    }

    public void addTarget(Entity entity) {
        if (this.isInRange(entity))
            targets.add(entity);
    }

    public void removeTarget(Entity entity) {
        targets.remove(entity);
    }

    public abstract void act();

    public void die() {
        world.getVirusList().remove(this);
    }

    public void hit(int damage) {
        if ((currentHP -= damage) < 0) die();
    }
}
