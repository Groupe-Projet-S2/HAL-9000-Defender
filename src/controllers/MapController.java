package controllers;

import controllers.listeners.HostileBoxesListener;
import controllers.listeners.ProjectileListener;
import controllers.listeners.TowerListener;
import controllers.listeners.VirusListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

public class MapController {

    private static boolean inPause;
    private Timeline gameloop;

    @FXML BorderPane root;

    @FXML
    private Label kills;

    @FXML
    private ImageView imageAfast;

    @FXML
    private ImageView imageGb;

    @FXML
    private ImageView imageIVG;

    @FXML
    private ImageView imageKbd;

    @FXML
    private ImageView firewall;

    @FXML
    private ImageView adblock;

    @FXML
    private ImageView sudvpn;

    @FXML
    private ImageView ckleaner;

    @FXML
    private Label heat;

    @FXML
    private TilePane grid;

    @FXML
    private Pane world;

    @FXML
    private Label bitcoins;

    @FXML
    private Label round;

    private TileMap tileMap;

    private Game game;
    private World env;


    @FXML
    void initialize() {
        world.getChildren().add(map());
        world.getChildren().add(selectScreen());
        add_menu();
    }

    private void add_menu() {
        AudioClip defaultSound = new AudioClip(this.getClass().getResource("/utils/wake-up.mp3").toString());
        defaultSound.setVolume(0.05);
        defaultSound.play();
        HashMap<Integer, AudioClip> files = new HashMap<>();
        files.put(1, defaultSound);
        files.put(2, new AudioClip(this.getClass().getResource("/utils/step-to-space.mp3").toString()));
        AudioMenu test = new AudioMenu(files);
        MenuBar audio = test.create();
        root.setTop(audio);

    }

    public void start(int level){
        inPause = false;
        world.getChildren().remove(world.lookup("#start"));
        world.getChildren().remove(world.lookup("#map"));
        shopDescription();
        grid.setAlignment(Pos.CENTER);
        tileMap = new TileMap(level);
        tileMap.compose();
        // columns
        int COLS = 25;
        grid.setPrefColumns(COLS);
        // rows
        int ROWS = 25;
        grid.setPrefRows(ROWS);
        SpriteSheet tileSet = new SpriteSheet("src/utils/tileset32.png");
        LinkedHashMap<Tile, Tile> path = Graphs.bfs(tileMap, tileMap.getTile(6, 0), tileMap.getTile(5, 24));

        MapView.draw(grid, COLS, ROWS, tileMap);

        game = new Game(tileMap, 5, 24, path);

        env = game.getWorld();

        bitcoins.textProperty().bind(env.moneyProperty().asString());
        round.textProperty().bind(game.getWaveNumber().asString());
        kills.textProperty().bind(env.killCountProperty().asString());

        env.getVirusList().addListener(new VirusListener(world));
        env.getNodeList().addListener(new TowerListener(world, env));
        env.getProjectileList().addListener(new ProjectileListener(world));
        env.getHostileBoxes().addListener(new HostileBoxesListener(world));

        env.addToList(game.getCpu());
        heat.prefWidthProperty().bind(game.getCpu().getOverHeatedRateProperty().multiply(1.5));

        // Starts the loop
        initLoop();
        gameloop.play();
    }

    private void shopDescription(){
        Tooltip.install(imageAfast,TooltipView.assignateTooltip(1));
        Tooltip.install(imageGb,TooltipView.assignateTooltip(2));
        Tooltip.install(imageIVG,TooltipView.assignateTooltip(3));
        Tooltip.install(imageKbd,TooltipView.assignateTooltip(4));
        Tooltip.install(firewall,TooltipView.assignateTooltip(5));
        Tooltip.install(adblock,TooltipView.assignateTooltip(6));
        Tooltip.install(sudvpn,TooltipView.assignateTooltip(7));
        Tooltip.install(ckleaner,TooltipView.assignateTooltip(8));
    }


    /*
    Tick method
    This is where we code what will happen during a tick. It will happen at a certain number of times per framerate (ideally 60).
    */

    private void tick() {
        if (!inPause) {
            for (Tower tower : env.getNodeList()) {
                world.lookup("#T" + tower.getId()).toFront();
                if (Tower.isACPU(tower) && !((Damagable)tower).isAlive()) {
                    world.getChildren().add(end());
                    inPause = true;
                }
            }
            env.getHostileBoxes().forEach((k, v) -> world.lookup("#P" + k).toFront());
            env.nextRound();
            game.update();

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
        if(event.getButton() == MouseButton.PRIMARY) {
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
                        tower = new Firewall(loc);
                        break;
                }
                assert tower != null;
                if (env.getMoney() - tower.getPrice() >= 0) {
                    env.debit(tower.getPrice());
                    if ((!tile.isPath() && !Tower.isAFirewall(tower)) || (tile.isPath() && Tower.isAFirewall(tower))) {
                        env.addToList(tower);
                    }
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

    public void nextWaveChange(){
        if (!game.nextWave)
            game.changeNextWave();
    }

    public void setInPause(){
        inPause = !inPause;
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

    public static VBox end() {
        VBox layout = new VBox();

        FileInputStream file = null;
        try{
            file = new FileInputStream("src/utils/gameover.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert file != null;
        BackgroundImage myBI= new BackgroundImage(new Image(file,800,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(800);
        layout.setPrefWidth(800);
        layout.setBackground(new Background(myBI));
        return layout;
    }

    public VBox selectScreen(){
        VBox layout = new VBox();

        FileInputStream file = null;
        try{
            file = new FileInputStream("src/utils/select.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert file != null;
        BackgroundImage myBI= new BackgroundImage(new Image(file,800,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        layout.setId("start");
        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(800);
        layout.setPrefWidth(800);
        layout.setBackground(new Background(myBI));

        Button start = new Button();
        start.setTranslateX(-261);
        start.setTranslateY(20);
        start.setPrefWidth(100);
        start.setPrefHeight(100);
        start.setOnAction(e -> start(1));
        start.styleProperty().setValue("-fx-opacity: 0.2");

        Button mapchange = new Button();
        mapchange.setTranslateX(-261);
        mapchange.setTranslateY(32);
        mapchange.setPrefWidth(100);
        mapchange.setPrefHeight(100);
        mapchange.setOnAction(e ->world.getChildren().remove(world.lookup("#start")));
        mapchange.styleProperty().setValue("-fx-opacity: 0.2");

        Button quit = new Button();
        quit.setTranslateX(81);
        quit.setTranslateY(-180);
        quit.setPrefWidth(100);
        quit.setPrefHeight(100);
        quit.styleProperty().setValue("-fx-opacity: 0.2");
        quit.setOnAction(e -> System.exit(0));

        layout.getChildren().addAll(start, mapchange,quit);
        return layout;
    }

    public VBox map(){
        VBox layout = new VBox();
        layout.setId("map");
        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(800);
        layout.setPrefWidth(800);
        layout.styleProperty().setValue("-fx-background-color: #70B5FF");

        Button map1 = new Button("MAP 1");
        map1.setTranslateX(-261);
        map1.setTranslateY(40);
        map1.setPrefWidth(100);
        map1.setPrefHeight(100);
        map1.setOnAction(e -> start(1));
        map1.styleProperty().setValue("-fx-opacity: 0.2");

        Button map2 = new Button("MAP 2");
        map2.setTranslateX(-261);
        map2.setTranslateY(100);
        map2.setPrefWidth(100);
        map2.setPrefHeight(100);
        map2.styleProperty().setValue("-fx-opacity: 0.2");
        //map2.setOnAction(e -> start(2));
        map2.setOnAction(e -> start(1));

        layout.getChildren().addAll(map1, map2);
        return layout;
    }
}
