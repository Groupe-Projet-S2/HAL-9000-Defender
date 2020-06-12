package controllers;

import controllers.listeners.HostileBoxesListener;
import controllers.listeners.ProjectileListener;
import controllers.listeners.TowerListener;
import controllers.listeners.VirusListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import models.Game;
import models.entities.bonus.AdBlock;
import models.entities.bonus.CKleaner;
import models.entities.bonus.SudVPN;
import models.entities.tower.*;
import models.environment.*;
import views.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MapController {

    private Timeline gameloop;
    private final int COLS = 25; // columns
    private final int ROWS = 25; // rows

    @FXML
    private ImageView imageAfast;

    @FXML
    private ImageView imageGb;

    @FXML
    private ImageView imageKbd;

    @FXML
    private ImageView imageIVG;

    @FXML
    private ImageView firewall;

    @FXML
    private TilePane grid;

    @FXML
    private Pane world;

    @FXML
    private Button nextWave;

    @FXML
    private Label bitcoins;

    @FXML
    private Label round;

    private SpriteSheet tileSet;

    private TileMap tileMap;

    private Game game;
    private World env;
    private LinkedHashMap<Tile,Tile> path;

    @FXML void initialize() {
        start();
    }

    private void start(){
        grid.setAlignment(Pos.CENTER);
        tileMap = new TileMap(COLS, ROWS);
        tileMap.compose();
        grid.setPrefColumns(COLS);
        grid.setPrefRows(ROWS);
        tileSet = new SpriteSheet("src/utils/tileset32.png");
        path = Graphs.bfs(tileMap, tileMap.getTile(6, 0), tileMap.getTile(5, 24));

        MapView.draw(grid, COLS, ROWS, tileMap);

        game = new Game(tileMap, 5, 24,path);

        env = game.getWorld();

        bitcoins.textProperty().bind(env.moneyProperty().asString());
        round.textProperty().bind(game.getWaveNumber().asString());

        env.getVirusList().addListener(new VirusListener(world));
        env.getNodeList().addListener(new TowerListener(world, env));
        env.getProjectileList().addListener(new ProjectileListener(world));
        env.getHostileBoxes().addListener(new HostileBoxesListener(world));

        env.addToList(game.getCpu());

        // Starts the loop
        initLoop();
        gameloop.play();
    }

    /*
    Tick method
    This is where we code what will happen during a tick. It will happen at a certain number of times per framerate (ideally 60).
    */
    private void tick() {
        for (Tower tower : env.getNodeList())
            world.lookup("#T" + tower.getId()).toFront();
        env.getHostileBoxes().forEach((k, v) -> world.lookup("#P" + k).toFront());
        env.nextRound();
        game.update();
    }

    /**
     *  initLoop
     *  Create the event that will happen a certain number of time per seconds.
     */
    private void initLoop() {
        gameloop = new Timeline();
        gameloop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.034), // FPS Number
                event -> tick()
        );

        gameloop.getKeyFrames().add(kf);
    }

    /**
     * onMouseClicked part
     * Manages the creation of towers on the map
     */

    @FXML
    public void createTower(MouseEvent event) {
        Tower tower = null;
        if (env.getSelectedNodePreview() != null) {
            double y = event.getY();
            double x = event.getX();
            int row = (int) y / Tile.SIZE, col = (int) x / Tile.SIZE;
            Tile tile = tileMap.getTile(row, col);
            Location loc = new Location(row * Tile.SIZE + Tile.SIZE / 2, col * Tile.SIZE + Tile.SIZE / 2);
            env.setSelectedNodeLocation(loc);

            switch (env.getSelectedNode()) {
                case 1:
                    tower = new Afast(loc, env);
                    break;
                case 2:
                    tower = new GoodwareBytes(loc, env);
                    break;
                case 3:
                    tower = new IVG(loc, env);
                    break;
                case 4:
                    tower = new KiloBitDefender(loc, env);
                    break;
                case 5:
                    tower = new Firewall(loc, env);
                    break;
            }
            if (env.getMoney() - tower.getPrice() >= 0) {
                env.debit(tower.getPrice());
                if ((!tile.isPath() && !Tower.isAFirewall(tower)) || (tile.isPath() && Tower.isAFirewall(tower))) {
                    env.addToList(tower);
                }
            }
        }
    }

    @FXML
    void setTowerOnAfast() {
        env.setSelectedNodePreview(imageAfast);
        env.setSelectedNode(1);
    }

    @FXML
    void setTowerOnGb() {
        env.setSelectedNodePreview(imageGb);
        env.setSelectedNode(2);
    }

    @FXML
    void setTowerOnIVG() {
        env.setSelectedNodePreview(imageIVG);
        env.setSelectedNode(3);
    }

    @FXML
    void setTowerOnKbd() {
        env.setSelectedNodePreview(imageKbd);
        env.setSelectedNode(4);
    }

    public void nextWaveChange(ActionEvent event){
        if (!game.nextWave)
            game.changeNextWave();
    }

    @FXML
    void setTowerOnFirewall() {
        env.setSelectedNodePreview(firewall);
        env.setSelectedNode(5);
    }

    @FXML
    void useAdblock() {
        AdBlock adBlock = new AdBlock(env);
        if (env.getMoney() - adBlock.getPrice() >= 0 && env.getBonusList().isEmpty()) {
            env.debit(adBlock.getPrice());
            env.addBonus(adBlock);
        }
    }

    @FXML
    void useSudVPN() {
        SudVPN sudVPN = new SudVPN(env);
        if (env.getMoney() - sudVPN.getPrice() >= 0 && env.getBonusList().isEmpty()) {
            env.debit(sudVPN.getPrice());
            env.addBonus(sudVPN);
        }
    }

    @FXML
    void useFlush() {
        CKleaner CKleaner = new CKleaner(env);
        if (env.getMoney() - CKleaner.getPrice() >= 0 && env.getBonusList().isEmpty()) {
            env.debit(CKleaner.getPrice());
            env.addBonus(CKleaner);
        }
    }
}
