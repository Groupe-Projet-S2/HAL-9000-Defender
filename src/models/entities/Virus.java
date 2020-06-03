package models.entities;

import models.environment.Location;
import models.environment.Tile;
import models.environment.Vector;

import java.util.HashSet;
import java.util.Set;

public abstract class Virus extends Entity {

    private int sizeW, sizeH, maxHP, currentHP = 0;
    private Tile current;
    private Vector direction;
    private int speed;
    public Set<Entity> targets;

    public Virus(int range, Location location, Tile tile, int speed) {
        super(range, location);
        this.direction = new Vector();
        this.current = tile;
        this.speed = speed;
        this.targets = new HashSet<>();
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

    public void move() {

        Tile parent = current.getParent();

        if (this.getLocation().getRow() == current.getPos().getRow()*Tile.SIZE+(Tile.SIZE/2) && this.getLocation().getCol() == current.getPos().getCol()*Tile.SIZE+(Tile.SIZE/2)) {
            if (this.getLocation().getRow() < parent.getPos().getRow() * Tile.SIZE + (Tile.SIZE / 2))
                direction.setRow(1);
            else if (this.getLocation().getRow() > parent.getPos().getRow() * Tile.SIZE + (Tile.SIZE / 2))
                direction.setRow(-1);
            else
                direction.setRow(0);

            if (this.getLocation().getCol() < parent.getPos().getCol() * Tile.SIZE + (Tile.SIZE / 2))
                direction.setCol(1);
            else if (this.getLocation().getCol() > parent.getPos().getCol() * Tile.SIZE + (Tile.SIZE / 2))
                direction.setCol(-1);
            else
                direction.setCol(0);

            current = parent;
        }

        this.getLocation().setRow(this.getLocation().getRow() + direction.getRow() * speed);
        this.getLocation().setCol(this.getLocation().getCol() + direction.getCol() * speed);
    }

    public void addTarget(Entity entity) {
        if (this.isInRange(entity))
            targets.add(entity);
    }

    public void removeTarget(Entity entity) {
        targets.remove(entity);
    }

    public abstract void act();

    void die() {}

}
