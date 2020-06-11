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

    public AdBlock(World env) {
        this.environment = env;
        this.adwares = new ArrayList<>();
        this.ransomwares = new ArrayList<>();
        this.active = true;
    }

    private void setTargets() {
        for (int i = 0 ; i < environment.getVirusList().size() ; i++) {
            if (Virus.isAnAdware(environment.getVirusList().get(i)))
                adwares.add((Adware) environment.getVirusList().get(i));
            else if (Virus.isARansomware(environment.getVirusList().get(i)))
                ransomwares.add((Ransomware) environment.getVirusList().get(i));
        }
    }

    @Override
    public void act() {
        this.setTargets();
        for (int i = 0 ; i < adwares.size() ; i++) {
            adwares.get(i).setCooldown();
        }
        for (int i = 0 ; i < ransomwares.size() ; i++) {
            ransomwares.get(i).setCooldown();
        }
        this.active = false;
    }

    @Override
    public boolean isActive() { return this.active; }
}