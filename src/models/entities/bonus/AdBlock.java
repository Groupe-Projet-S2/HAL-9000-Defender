package models.entities.bonus;

import models.entities.virus.Adware;
import models.entities.virus.Ransomware;
import models.entities.virus.Virus;
import models.environment.World;

import java.util.ArrayList;

public class AdBlock implements Bonus {

    private World environment;
    private ArrayList<Adware> adwares;
    private ArrayList<Ransomware> ransomwares;
    private boolean active;
    private int price;
    private long current;

    public AdBlock(World env) {
        this.environment = env;
        this.adwares = new ArrayList<>();
        this.ransomwares = new ArrayList<>();
        this.active = true;
        this.price = 100;
        this.current = System.currentTimeMillis();
        environment.setAdblock(true);
    }
    @Override
    public void act() {
        if (System.currentTimeMillis()-current>=10000 && environment.isAdblock()){
            environment.setAdblock(false);
            environment.getBonusList().remove(this);
        }
    }

    @Override
    public boolean isActive() { return this.active; }

    @Override
    public int getPrice() {
        return price;
    }
}