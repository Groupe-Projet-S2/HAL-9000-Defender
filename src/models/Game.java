package models;

import controllers.MapController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Game {

    private int number;
    private IntegerProperty difficulty;
    private IntegerProperty waveNumber;
    private World env;
    private long startTime;
    private long spawnTime;

    public Game() {
        this.env = new World();
        difficulty = new SimpleIntegerProperty(1);
        waveNumber = new SimpleIntegerProperty(1);
        number = difficulty.getValue() * 5;
        startTime = System.currentTimeMillis();
        spawnTime = 1000;
    }

    public World getWorld(){
        return env;
    }

    public void enemySet(int virus) {
        switch (virus) {
            case 1:
                env.addToList(new Virus(5, new Location(6 * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE / 2), MapController.tileMap.getTile(6, 0)));
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }

    public void update() {
        for (Virus virus : env.getVirusList()) {
            virus.move();
        }
        for (Tower tower : env.getNodeList()) {
            tower.act();
        }

        long current = System.currentTimeMillis();
        if (current >= startTime + spawnTime && number > 0) {
            enemySet(decider());
            startTime = current;
            number--;
        }
        else{
            waveNumber.add(1);
            if (waveNumber.getValue() % 2 == 0)
                difficulty.add(1);
            number = difficulty.getValue() * 5;
            if (env.getVirusList().isEmpty())
            try {
                Thread.sleep(5000);
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
