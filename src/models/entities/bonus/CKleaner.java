package models.entities.bonus;

import models.environment.World;

public class CKleaner implements Bonus {

    private World environment;
    private boolean active;
    private int price;

    public CKleaner(World env) {
        this.environment = env;
        this.active = true;
        this.price = 300;
    }

    @Override
    public void act() {
        environment.getVirusList().clear();
        if (environment.getVirusList().isEmpty()) {
            active = false;
            environment.getBonusList().remove(this);
        }
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public int getPrice() {
        return price;
    }


}
