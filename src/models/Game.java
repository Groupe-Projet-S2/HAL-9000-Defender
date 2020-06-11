package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import models.entities.tower.CPU;
import models.environment.Location;
import models.environment.Tile;
import models.environment.TileMap;
import models.environment.World;

public class Game {

    private int number;
    private IntegerProperty difficulty;
    private IntegerProperty waveNumber;
    private World env;
    private TileMap tileMap;
    private Tile end;
    private long startTime;
    private long spawnTime;
    public static int Money = 100;
    public boolean nextWave;
    public CPU cpu;

    public Game(TileMap tileMap, int row, int col) {
        this.env = new World(tileMap);
        this.tileMap = tileMap;
        difficulty = new SimpleIntegerProperty(1);
        waveNumber = new SimpleIntegerProperty(1);
        number = difficulty.getValue() * 5;
        startTime = System.currentTimeMillis();
        spawnTime = 1000;
        this.end = tileMap.getTile(row, col);
        nextWave = true;
        cpu = new CPU(new Location (end.getPos().getRow()*Tile.SIZE,end.getPos().getCol()*Tile.SIZE),env);
    }

    public World getWorld(){
        return env;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void changeNextWave(){
        nextWave = !nextWave;
    }

    public void update() {

        if (nextWave) {
            long current = System.currentTimeMillis();
            if (current >= startTime + spawnTime && number > 0) {
                env.enemySet(decider());
                startTime = current;
                number--;
            } else if (number == 0 && env.getVirusList().isEmpty()) {
                waveNumber.setValue(waveNumber.getValue()+1);
                if (waveNumber.getValue() % 1 == 0)
                    difficulty.setValue(difficulty.getValue()+1);
                number = difficulty.getValue() * 5;
                if (env.getVirusList().isEmpty())
                    changeNextWave();
            }
        }
    }

    public int decider(){
        double decider = Math.random();
        if (decider <= 0.5){
            return 1;
        }
        else if (decider <= 0.75){
            return 2;
        }
        else if (decider <= 0.90){
            return 3;
        }
        else{
            return 4;
        }
    }
}