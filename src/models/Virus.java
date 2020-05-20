package models;

import controllers.MapController;

public class Virus extends Entity {

    private int sizeW, sizeH, maxHP, currentHP = 0;
    private Tile current;
    private Vector direction;

    public Virus(int range, Location location, Tile tile) {
        super(range, location);
        direction = new Vector();
        sizeW = 10;
        sizeH = 10;
        current = tile;
    }

    public int getSizeW(){
        return sizeW;
    }
    public int getSizeH(){
        return sizeH;
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

    public void setCurrent(Tile current) {
        this.current = current;
    }

    public Tile getCurrent() {
        return current;
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

        this.getLocation().setRow(this.getLocation().getRow() + direction.getRow());
        this.getLocation().setCol(this.getLocation().getCol() + direction.getCol());
    }

    void act() {}

    void die() {}

}
