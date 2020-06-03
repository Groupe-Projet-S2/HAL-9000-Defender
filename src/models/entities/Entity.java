package models.entities;

import models.environment.Location;

public class Entity {

    private int range;
    protected Location location;
    private Location target;
    private boolean isActive;
    private int sizeW, sizeH;
    private String id;
    public static int count = 0;

    public Entity(int range, Location location) {
        this.range=range;
        this.location=location;
        this.id="E"+count;
        this.isActive = true;
        sizeW = 10;
        sizeH = 10;
        count++;
    }

    public int getSizeW(){
        return sizeW;
    }
    public int getSizeH(){
        return sizeH;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public Location getLocation() {
        return this.location;
    }

    void setLocation(int x, int y) {
        this.location.setCol(x);
        this.location.setRow(y);
    }

    public static boolean isVirus(Entity ent){
        return (ent instanceof Virus);
    }

    public static boolean isNode(Entity ent){
        return (ent instanceof Tower);
    }

    public static boolean isProjectile(Entity ent){
        return (ent instanceof Projectile);
    }

    public String getId(){
        return this.id;
    }

    public int getRange(){
        return this.range;
    }

    public boolean isInRange(Entity entity){
        int deltaX = this.getLocation().getCol() - Math.max(entity.getLocation().getCol(), Math.min(this.getLocation().getCol(),entity.getLocation().getCol()+entity.getSizeW()));
        int deltaY = this.getLocation().getRow() - Math.max(entity.getLocation().getRow(), Math.min(this.getLocation().getRow(),entity.getLocation().getRow()+entity.getSizeH()));

        return (Math.pow(deltaX,2) + Math.pow(deltaY,2)) < Math.pow(this.getRange(),2);
    }

}
