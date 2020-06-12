package models.entities.bonus;

import models.environment.Tile;
import models.environment.World;

public class SudVPN implements Bonus {

    private final World environment;
    private final int cooldown;
    private int index;
    private final int numberOfVirus;
    private boolean active;
    private long current;
    public static int price = 250;

    public SudVPN(World env) {
        this.environment = env;
        this.cooldown = 500;
        this.index = 0;
        this.active = true;
        this.current = System.currentTimeMillis();
        this.numberOfVirus = environment.getVirusList().size();
    }

    public boolean isActive() { return this.active; }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void act() {
        if (active) {
            Tile tile = environment.getVirusList().get(0).getSpawnTile();
            int row = tile.getPos().getRow() * Tile.SIZE + Tile.SIZE / 2;
            int col = tile.getPos().getCol() * Tile.SIZE + Tile.SIZE / 2;

            if (System.currentTimeMillis() - current >= cooldown) {
                current = System.currentTimeMillis();
                environment.getVirusList().get(index).getPosition().setRow(row);
                environment.getVirusList().get(index).getPosition().setCol(col);
                environment.getVirusList().get(index).setCurrent(tile);
                index++;
            }
            if (index == numberOfVirus) {
                active = false;
                environment.getBonusList().remove(this);
            }
        }
    }
}
