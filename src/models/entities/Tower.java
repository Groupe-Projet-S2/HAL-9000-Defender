package models.entities;

import models.environment.Location;
import models.environment.World;

import java.util.ArrayList;

public abstract class Tower extends Entity {

    private int upgradePrice;
    protected int maxCons;
    protected int consumption;
    private boolean active;
    private Virus target;
    private ArrayList<Virus> inRangeVirus;
    protected World env;

    public Tower(int range, Location location, int upgradePrice, int maxCons, int consumption, World env) {
        super(range, location);
        this.upgradePrice = upgradePrice;
        this.maxCons = maxCons;
        this.consumption = consumption;
        this.inRangeVirus = new ArrayList<Virus>();
        this.active = true;
        this.env = env;
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

    public void setTarget(Virus target) {
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

    public Virus getTarget() {
        return target;
    }

    public boolean hasTarget(){
        return target != null;
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

    public abstract void act();

    public void detection() {
        for (int j = env.getVirusList().size()-1; j>=0; j--){  // Browse the virus list
            // Virus j is in range of node i and not yet in its range list so we add it to the list
            if (isInRange(env.getVirusList().get(j)) && !getInRangeVirus().contains(env.getVirusList().get(j))) {
                addRangedVirus(env.getVirusList().get(j));
            }
            // Virus j is not in range of node i and is in its range list so we remove it from the list
            else if (!isInRange(env.getVirusList().get(j)) && getInRangeVirus().contains(env.getVirusList().get(j))) {
                delRangedVirus(env.getVirusList().get(j));
            }
            if (!env.getVirusList().get(j).isAlive())
                env.getVirusList().remove(j);
        }
        // Setting node target
        if(!getInRangeVirus().isEmpty()) {
            setTarget(getInRangeVirus().get(0));
        }
        else {
            setTarget(null);
        }
    }

}
