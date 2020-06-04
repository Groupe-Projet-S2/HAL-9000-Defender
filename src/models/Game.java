package models;

import controllers.MapController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Game {

    private Wave wave;
    private IntegerProperty number, difficulty, waveNumber;
    private World env;
    private long startTime;
    private long spawnTime;

    public Game() {
        this.env = new World();
        difficulty = new SimpleIntegerProperty(1);
        waveNumber = new SimpleIntegerProperty(1);
        number = new SimpleIntegerProperty(difficulty.getValue() * 5);
        this.wave = new Wave(env);
        wave.init(1000, 5, new Virus(5,new Location(6 * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE/2), MapController.tileMap.getTile(6,0)));
        startTime = System.currentTimeMillis();
        spawnTime = 1000;
    }

    public World getWorld(){
        return env;
    }

    public void waveDef(int virus) {
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
        if (current >= startTime + spawnTime && number.getValue() > 0) {
            waveDef(1);
            startTime = current;
            number.setValue(number.getValue()-1);
        }
        /*
        else{
            waveNumber++;
            if (waveNumber % 2 == 0)
                difficulty++;
            number = difficulty * 5;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
            }
        }*/
    }
}
