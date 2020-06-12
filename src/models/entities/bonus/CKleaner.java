package models.entities.bonus;

import models.environment.World;

public class CKleaner implements Bonus {

    private final World environment;
    private boolean active;
    public static int price = 300;

    public CKleaner(World env) {
        this.environment = env;
        this.active = true;
    }

    @Override
    public void act() {
        environment.killCountProperty().setValue(environment.killCountProperty().getValue()+environment.getVirusList().size());
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
