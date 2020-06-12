package models.entities.tower;
import models.environment.Location;
import models.environment.World;

public class IVG extends Tower{

    public static int price = 50;
    private long startTime;
    private final int spawnTime;
    private int number;

    public IVG(Location location, World env) {
        super(50, location, 100, 1000, 500, env);
        startTime = System.currentTimeMillis();
        spawnTime = 500;
        number = 4;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void act() {
        long current = System.currentTimeMillis();
        if (current - startTime >= spawnTime && number > 0 && this.hasTarget()) {
            startTime = current;
            number--;
            this.getTarget().die();
        }
        else if (number == 0 && current - startTime >= reloadingTime) {
            number = 4;
        }
    }
}
