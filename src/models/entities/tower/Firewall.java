package models.entities.tower;

import models.entities.Entity;
import models.environment.Location;
import models.environment.World;

public class Firewall extends Tower {

    private World environment;

    private int hp;

    public Firewall(Location location, World env) {
        super(0, location);
        this.environment = env;
        this.hp = 1000;
    }

    private boolean isItNovember1991() { return (hp==0); }

    @Override
    public void act() {
    }
}
