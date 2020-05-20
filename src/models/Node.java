package models;

import java.util.ArrayList;

public class Node extends Entity {

    private int upgradePrice;
    private int maxCons;
    private int consumption;
    private boolean active;
    private Entity target;
    private ArrayList<Virus> inRangeVirus;

    public Node(int range, Location location, int upgradePrice, int maxCons, int consumption) {
        super(range, location);
        this.upgradePrice = upgradePrice;
        this.maxCons = maxCons;
        this.consumption = consumption;
        this.inRangeVirus = new ArrayList<Virus>();
    }

    public void setMaxCons(int maxCons) {
        this.maxCons = maxCons;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public void setUpgradePrice(int upgradePrice){
        this.upgradePrice = upgradePrice;
    }

    public ArrayList<Virus> getInRangeVirus() {
        return inRangeVirus;
    }

    public int getUpgradePrice() {
        return upgradePrice;
    }

    public int getMaxCons() {
        return maxCons;
    }

    public int getConsumption() {
        return consumption;
    }

    public boolean isActive() {
        return active;
    }

    public Entity getTarget() {
        return target;
    }

    public boolean hasTarget(){
        return target != null;
    }

    public boolean isInRange(Virus virus){
        int deltaX = this.getLocation().getCol() - Math.max(virus.getLocation().getCol(), Math.min(this.getLocation().getCol(),virus.getLocation().getCol()+virus.getSizeW()));
        int deltaY = this.getLocation().getRow() - Math.max(virus.getLocation().getRow(), Math.min(this.getLocation().getRow(),virus.getLocation().getRow()+virus.getSizeH()));

        return (Math.pow(deltaX,2) + Math.pow(deltaY,2)) < Math.pow(this.getRange(),2);
    }

    public void addRangedVirus(Virus virus){
        if (this.isInRange(virus))
            this.inRangeVirus.add(virus);
        else
            throw new Error("This virus is not in range");
    }

    public void delRangedVirus(Virus virus){
        if(this.inRangeVirus.contains(virus))
            this.inRangeVirus.remove(virus);
    }

}
