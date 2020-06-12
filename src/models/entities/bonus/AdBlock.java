package models.entities.bonus;
import models.environment.World;

public class AdBlock implements Bonus {

    private final World environment;
    private final boolean active;
    public static int price = 100;
    private final long current;

    public AdBlock(World env) {
        this.environment = env;
        this.active = true;
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