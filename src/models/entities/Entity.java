package models.entities;

import models.entities.bonus.Bonus;
import models.entities.tower.Firewall;
import models.entities.projectile.Projectile;
import models.entities.tower.Tower;
import models.entities.virus.Virus;
import models.environment.Location;

public class Entity {

    private int range;
    protected Location position;
    private Location target;
    private boolean isActive;
    private int sizeW, sizeH;
    private String id;
    public static int count = 0;

    public Entity(int range, Location position) {
        this.range=range;
        this.position = position;
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

    public Location getPosition() {
        return this.position;
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

    public void setRange(int newRange) { this.range = newRange; }

    public boolean isInRange(Entity entity){
        int deltaX = this.getPosition().getCol() - Math.max(entity.getPosition().getCol(), Math.min(this.getPosition().getCol(),entity.getPosition().getCol()+entity.getSizeW()));
        int deltaY = this.getPosition().getRow() - Math.max(entity.getPosition().getRow(), Math.min(this.getPosition().getRow(),entity.getPosition().getRow()+entity.getSizeH()));

        return (Math.pow(deltaX,2) + Math.pow(deltaY,2)) < Math.pow(this.getRange(),2);
    }

}
