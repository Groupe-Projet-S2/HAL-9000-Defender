package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import models.entities.*;
import models.environment.*;
import views.*;

import java.util.Date;

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

    World env;
    Virus virus;

    @FXML void initialize() {
        grid.setAlignment(Pos.CENTER);
        tileMap = new TileMap(COLS, ROWS);
        tileMap.compose();
        grid.setPrefColumns(COLS);
        grid.setPrefRows(ROWS);
        tileSet = new SpriteSheet("src/utils/tileset32.png");
        Graphs.bfs(world, tileMap, tileMap.getTile(6, 0), tileMap.getTile(5, 24));

        MapView.draw(grid, COLS, ROWS, tileMap);

        env = new World();

        //virus = new Adware(new Location(6 * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE/2), tileMap.getTile(6,0));

        env.getEntities().addListener((ListChangeListener<? super Entity>) c -> {
            while (c.next())
                if (c.wasAdded()) {
                    for (Entity a : c.getAddedSubList()) {
                        if (Entity.isVirus(a))
                            world.getChildren().add(VirusView.renderVirus((Virus) a));
                        else if (Entity.isNode(a))
                            world.getChildren().addAll(
                                    TowerView.drawRadius(50, a.getLocation(), a.getId()),
                                    TowerView.renderTower((Tower) a, placedNodeLoc, selectedNode.getImage())
                            );
                        else if (Entity.isProjectile(a)) {
                            world.getChildren().add(ProjectileView.renderProjectile((Projectile) a));
                        }
                    }
                }
        });

        env.addToList(virus);

        //env.addToList(new Dynamic(new Location(500, 500), null, virus, 10, 10));

        // Starts the loop
        initLoop();
        gameloop.play();
    }

    /*
    Tick method
    This is where we code what will happen during a tick. It will happen at a certain number of times per framerate (ideally 60).
    */
    private void tick() {
        updateView();
        env.nextRound();
    }

    private void updateView() {
        Line target;
        for (Tower tower : env.getNodeList()) {
            if (tower.hasTarget()) {
                if (world.lookup("#L"+ tower.getId()) == null)
                    world.getChildren().add(TowerView.drawTarget(tower.getLocation(), tower.getTarget().getLocation(), tower.getId()));
            }
            else if ((target = (Line) world.lookup("#L"+ tower.getId())) != null) {
                world.getChildren().remove(target);
            }
        }
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
        if (selectedNode != null) {
            placedNodeLoc = new Location((int) event.getY(), (int) event.getX());
            tower.getLocation().set(placedNodeLoc);
            env.addToList(tower);
        }
    }

    @FXML
    void setTowerOnAfast() {
        selectedNode = imageAfast;
        tower = new Afast(50, new Location(0, 0), 150, 50,150, 150, env);
    }

    @FXML
    void setTowerOnGb() {
        selectedNode = imageGb;
        tower = new GoodwareBytes(50, new Location(0, 0), 150, 50,150, 150, env);
    }

    @FXML
    void setTowerOnIVG() {
        selectedNode = imageIVG;
        tower = new IVG(50, new Location(0, 0), 150, 50,150, 150, env);
    }

    @FXML
    void setTowerOnKbd() {
        selectedNode = imageKbd;
        tower = new KiloBitDefender(50, new Location(0, 0), 150, 50,150, 150, env);
    }
}
