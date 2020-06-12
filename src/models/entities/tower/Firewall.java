package models.entities.tower;
import models.environment.Location;

public class Firewall extends Tower implements Damagable{

    public static int price = 200;
    private int hp;

    public Firewall(Location location) {
        super(location);
        this.hp = 300;
    }

    @Override
    public int getPrice() {
        return price;
    }

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
}
