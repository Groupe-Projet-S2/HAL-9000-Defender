package models.entities.bonus;

import models.environment.World;

public class Flush implements Bonus {

    private World environment;
    private boolean active;

    public Flush(World env) {
        this.environment = env;
        this.active = true;
    }

    @Override
    public void act() {
        environment.getVirusList().clear();
        if (environment.getVirusList().isEmpty()) {
            active = false;
        }
    }

    @Override
    public boolean isActive() {
        return this.active;
    }


}
