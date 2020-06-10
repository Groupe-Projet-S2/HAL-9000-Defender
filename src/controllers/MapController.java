package controllers;

import controllers.listeners.ProjectileListener;
import controllers.listeners.TowerListener;
import controllers.listeners.VirusListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import models.Game;
import models.entities.tower.Afast;
import models.entities.tower.GoodwareBytes;
import models.entities.tower.KiloBitDefender;
import models.entities.tower.Tower;
import models.environment.*;
import views.*;

import static views.TowerView.drawTarget;

public class MapController {

    private Timeline gameloop;
    private ImageView selectedNode;
    private Location placedNodeLoc;
    private Tower tower;
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
    private TilePane grid;

    @FXML
    private Pane world;

    private SpriteSheet tileSet;

    private TileMap tileMap;

    private Game game;
    private World env;

    @FXML void initialize() {
        grid.setAlignment(Pos.CENTER);
        tileMap = new TileMap(COLS, ROWS);
        tileMap.compose();
        grid.setPrefColumns(COLS);
        grid.setPrefRows(ROWS);
        tileSet = new SpriteSheet("src/utils/tileset32.png");
        Graphs.bfs(world, tileMap, tileMap.getTile(6, 0), tileMap.getTile(5, 24));

        MapView.draw(grid, COLS, ROWS, tileMap);

        game = new Game(tileMap, 5, 24);

        env = game.getWorld();

        env.getVirusList().addListener(new VirusListener(world));
        env.getNodeList().addListener(new TowerListener(world, env));
        env.getProjectileList().addListener(new ProjectileListener(world));

        // Starts the loop
        initLoop();
        gameloop.play();
    }

    /*
    Tick method
    This is where we code what will happen during a tick. It will happen at a certain number of times per framerate (ideally 60).
    */
    private void tick() {
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
                (event -> {tick();
                    //System.out.println("A tick has been run");
                })
        );
        gameloop.getKeyFrames().add(kf);
    }

    /**
     * onMouseClicked part
     * Manages the creation of towers on the map
     */

    @FXML
    public void createTower(MouseEvent event) {
        if (env.getSelectedNodePreview() != null) {
            double y = event.getY();
            double x = event.getX();
            int row = (int) y / Tile.SIZE, col = (int) x / Tile.SIZE;
            Tile tile = tileMap.getTile(row, col);
            Location loc = new Location(row * Tile.SIZE + Tile.SIZE / 2, col * Tile.SIZE + Tile.SIZE / 2);
            env.setSelectedNodeLocation(loc);

            switch (env.getSelectedNode()) {
                case 1 :
                    tower = new Afast(loc,env);
                    break;
                case 2 :
                    tower = new GoodwareBytes(loc,env);
                    break;
                case 4 :
                    tower = new KiloBitDefender(loc,env);
                    break;
            }

            if (! tile.isPath() && game.Money - tower.getPrice() >=0) {
                game.Money -= tower.getPrice();
                env.addToList(tower);
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
}
