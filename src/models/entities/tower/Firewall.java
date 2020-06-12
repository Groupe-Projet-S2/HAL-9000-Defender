package models.entities.tower;

import models.entities.Entity;
import models.environment.Location;
import models.environment.World;

public class Firewall extends Tower implements Damagable{

    private World environment;

    private int hp;

    public Firewall(Location location, World env) {
        super(0, location);
        this.environment = env;
        this.hp = 300;
        setPrice(200);
    }

    private boolean isItNovember1991() { return (hp==0); }

    @Override
    public void act() {
        hp -=1;
    }

    @Override
    public void getDamaged(int a) {
        this.hp -= a;
    }

    @Override
    public boolean isAlive() {
        return hp>0;
    }

    @Override
    public int getHp() {
        return hp;
    }
}
