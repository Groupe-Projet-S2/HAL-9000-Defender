package models.entities.virus;

import models.entities.Entity;
import models.entities.tower.Firewall;
import models.entities.tower.Tower;
import models.environment.Location;
import models.environment.Tile;
import models.environment.Vector;
import models.environment.World;

import java.util.HashSet;
import java.util.Set;

public abstract class Virus extends Entity {

    private int sizeW, sizeH, maxHP, currentHP = 0;
    private Tile current;
    private Vector direction;
    private int speed;
    public Set<Entity> targets;
    private int virusID;
    private Tile spawnTile;
    private World environment;

    public Virus(int range, Location location, Tile tile, int speed, int virusID, World env) {
        super(range, location);
        this.virusID = virusID;
        this.direction = new Vector();
        this.current = tile;
        this.spawnTile = tile;
        this.speed = speed;
        this.targets = new HashSet<>();
        this.sizeH = 16;
        this.sizeW = 16;
        this.environment = env;
    }

    public int getVirusID() {
        return virusID;
    }

    public boolean isAlive(){
        return currentHP>0;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
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

    public void die() {}

}
