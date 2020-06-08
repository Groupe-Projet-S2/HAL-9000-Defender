package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import models.entities.*;
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

    public Game(TileMap tileMap, int row, int col) {
        this.env = new World();
        this.tileMap = tileMap;
        difficulty = new SimpleIntegerProperty(1);
        waveNumber = new SimpleIntegerProperty(1);
        number = difficulty.getValue() * 5;
        startTime = System.currentTimeMillis();
        spawnTime = 1000;
        this.end = tileMap.getTile(row, col);
    }

    public World getWorld(){
        return env;
    }

    public void enemySet(int virus) {
        Location loc = new Location(6 * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE / 2);
        switch (virus) {
            case 1:
                env.addToList(new Zombie(loc, tileMap.getTile(6, 0)));
                break;
            case 2:
                env.addToList(new Adware(loc, tileMap.getTile(6, 0)));
                break;
            case 3:
                env.addToList(new Ransomware(loc, tileMap.getTile(6, 0), new Location(5, 23)));
                break;
            case 4:
                //env.addToList(new Worm(loc, tileMap.getTile(6, 0)));
                break;
            case 5:
                env.addToList(new Trojan(loc, tileMap.getTile(6, 0)));
                break;
            case 6:
                break;
        }
    }

    public void update() {
        for (Virus virus : env.getVirusList()) {
            virus.move();
            int x = virus.getLocation().getCol() / 32;
            int y = virus.getLocation().getRow() / 32;
            if (end.getPos().getRow() == y && end.getPos().getCol() == x)
                env.getEntities().remove(virus);
        }
        for (Tower tower : env.getNodeList()) {
            //tower.act();
        }

        long current = System.currentTimeMillis();
        if (current >= startTime + spawnTime && number > 0) {
            enemySet(decider());
            startTime = current;
            number--;
        }
        else if (number == 0 && env.getVirusList().isEmpty()) {
            waveNumber.add(1);
            if (waveNumber.getValue() % 2 == 0)
                difficulty.add(1);
            number = difficulty.getValue() * 5;
            if (env.getVirusList().isEmpty())
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
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