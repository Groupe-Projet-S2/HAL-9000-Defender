package models;

import java.util.ArrayList;

public abstract class Projectile extends Entity {

    private Location destination;
    private Vector direction;
    private Node sender;
    private int damage;

    public Projectile(Location location, Location destination, Vector direction, Node sender, int damage, int range) {
        super(range, location);
        this.destination = destination;
        this.direction = direction;
        this.sender = sender;
        this.damage = damage;
    }

    public Projectile(Location location, Node sender, int damage, int range) {
        super(range, location);
        this.sender = sender;
        this.damage = damage;
    }

    public abstract void hit(ArrayList<Virus> inRangeVirus);

    public Location getDestination() {
        return this.destination;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public int getDamage() {
        return this.damage;
    }

    public Entity getSender() {
        return this.sender;
    }

    public ArrayList<Virus> getInRangeVirus() {
        return this.sender.getInRangeVirus();
    }

    public boolean isInRange(Virus virus){
        int nearestX = Math.max(virus.getLocation().getCol(), Math.min(this.getLocation().getCol(),virus.getLocation().getCol()+virus.getSizeW()));
        int nearestY = Math.max(virus.getLocation().getRow(), Math.min(this.getLocation().getRow(),virus.getLocation().getRow()+virus.getSizeH()));

        int deltaX = this.getLocation().getCol() - Math.max(virus.getLocation().getCol(), Math.min(this.getLocation().getCol(),virus.getLocation().getCol()+virus.getSizeW()));
        int deltaY = this.getLocation().getRow() - Math.max(virus.getLocation().getRow(), Math.min(this.getLocation().getRow(),virus.getLocation().getRow()+virus.getSizeH()));

        return (Math.pow(deltaX,2) + Math.pow(deltaY,2)) < Math.pow(this.getRange(),2);
    }

    public void explode(Virus virus) {
        virus.setCurrentHP(virus.getCurrentHP()-this.getDamage());
    }
}