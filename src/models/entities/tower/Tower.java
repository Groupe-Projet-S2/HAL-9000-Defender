package models.entities.tower;

import models.entities.Entity;
import models.entities.virus.Virus;
import models.environment.Location;
import models.environment.World;

import java.util.ArrayList;

public abstract class Tower extends Entity {

    private int upgradePrice;
    private int price;
    protected int reloadingTime;
    protected int spawningTime;
    private boolean active;
    private Virus target;
    private ArrayList<Virus> inRangeVirus;
    protected World env;
    protected int projectileDamages;

    public Tower(int range, Location position, int upgradePrice, int price, int reloadingTime, int spawningTime, World env) {
        super(range, position);
        this.upgradePrice = upgradePrice;
        this.reloadingTime = reloadingTime;
        this.spawningTime = spawningTime;
        this.inRangeVirus = new ArrayList<>();
        this.active = true;
        this.env = env;
        this.price = price;
        this.projectileDamages = 40;
    }

    public Tower(int range, Location location) {
        super(0, location);
    }

    public Tower(int range, Location pos, World env) {
        super(range, pos);
        this.env = env;
        this.inRangeVirus = new ArrayList<>();
        this.active = true;
    }

    public static boolean isAFirewall(Tower tower) {
        return (tower instanceof Firewall);
    }

    public void disable() {
        System.out.println("Disabled");
        setActive(false);
    }

    public void enable() {
        System.out.println("Enabled");
        setActive(true);
    }

    public void setReloadingTime(int reloadingTime) {
        this.reloadingTime = reloadingTime;
    }

    public void setProjectileDamages(int damage) { this.projectileDamages = damage; }

    public void setSpawningTime(int spawningTime) {
        this.spawningTime = spawningTime;
    }

    private void setActive(boolean active) {
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

    public int getReloadingTime() {
        return reloadingTime;
    }
    public int getProjectileDamages() { return projectileDamages; }

    public int getSpawningTime() {
        return spawningTime;
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

    public int getPrice(){
        return this.price;
    }

    public void addRangedVirus(Virus virus){
        if (this.isInRange(virus))
            this.inRangeVirus.add(virus);
        else
            throw new Error("This virus is not in range");
    }

    public void delRangedVirus(Virus virus){
        this.inRangeVirus.remove(virus);
    }

    public abstract void act();

    public void detection() {

        for (int j = env.getVirusList().size()-1; j>=0; j--) {  // Browse the virus list
            // Virus j is in range of node i and not yet in its range list so we add it to the list
            if (isInRange(env.getVirusList().get(j)) && !getInRangeVirus().contains(env.getVirusList().get(j))) {
                addRangedVirus(env.getVirusList().get(j));
            }
        }
        for (int i = getInRangeVirus().size()-1; i>=0; i--){
            if (!isInRange(getInRangeVirus().get(i))) {
                delRangedVirus(getInRangeVirus().get(i));

            }
            /*if (!env.getVirusList().get(j).isAlive()) {
                env.getEntities().remove(j);
            }*/
        }

        if (this instanceof CPU){
            for (int k = getInRangeVirus().size()-1; k>=0; k--){
                env.getVirusList().remove(getInRangeVirus().get(k));
                getInRangeVirus().get(k);
            }
        }

        // Setting node target
        if(!getInRangeVirus().isEmpty()) {
            target = getInRangeVirus().get(0);
        }
        else{
            target = null;

        }
    }

}