package models;

import controllers.MapController;

public class Wave {

    private long startTime, current;
    private float spawnTime;
    private int enemyNb;
    private Virus type;
    private World env;

    public Wave(World env){
        this.env = env;
        startTime = System.currentTimeMillis();
        spawnTime = 1000;
    }

    public void init(int spawnTime, int nb, Virus type){
        this.startTime = System.currentTimeMillis();
        this.spawnTime = spawnTime;
        this.enemyNb = nb;
        this.type = type;
    }

    public void setType(Virus type) {
        this.type = type;
    }

    public int getEnemyNb() {
        return enemyNb;
    }

    public void spawn() {
        current = System.currentTimeMillis();
        if (current >= startTime + spawnTime){
            env.addToList(new Virus(5, new Location(6 * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE / 2), MapController.tileMap.getTile(6, 0)));
            startTime = current;
            enemyNb--;
        }
    }

}
