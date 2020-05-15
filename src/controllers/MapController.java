package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import models.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class MapController {

    private Timeline gameloop;
    private ImageView imageTower;

    private final int SIZE = 32;

    @FXML
    private ImageView imageAfast;

    @FXML
    private ImageView imageGb;

    @FXML
    private ImageView imageKbd;

    @FXML
    private BorderPane pane;

    @FXML
    private ImageView imageIVG;

    @FXML
    private GridPane grid;

    @FXML
    private Pane world;

    private SpriteSheet tileSet;

    private TileMap tileMap;

    Circle dummy;
    World env;

    @FXML void initialize() {
        grid.setAlignment(Pos.CENTER);
        tileMap = new TileMap(SIZE, SIZE, SIZE * SIZE);
        tileMap.compose();
        tileSet = new SpriteSheet("src/utils/tileset.png", SIZE);
        draw();

        dummy = new Circle(16, 16 * 32, 8, Color.RED);
        world.getChildren().add(dummy);

        env = new World();

        // Starts the loop
        initLoop();
        gameloop.play();
    }

    /**
     * draw method
     *
     * Draws the map.
     * It iterates over the rows and columns of the tile set to add TilePanes to the GridPane.
     * One tile is 32x32.
     * Every tile gets its corresponding sprite as a background.
     */
    private void draw() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {

                TilePane tilePane = new TilePane();
                tilePane.setAlignment(Pos.CENTER);
                tilePane.setPrefWidth(32);
                tilePane.setPrefHeight(32);
                Tile tile = tileMap.getTile(i, j);
                int ID = tile.getType();
                tilePane.setId("" + ID);

                Image image = tileSet.getSprite(ID).getImage();

                Background bg = new Background(new BackgroundImage(image,NO_REPEAT, NO_REPEAT, DEFAULT, BackgroundSize.DEFAULT));
                tilePane.setBackground(bg);

                grid.add(tilePane, j, i);
            }
    }

    /*
    Tick method
    This is where we code what will happen during a tick. It will happen at a certain number of times per framerate (ideally 60).
    */
    private void tick() {
        double x = dummy.getCenterX();
        if (x + dummy.getRadius() < world.getWidth()) x++;
        dummy.setCenterX(x);
    }

    /*@FXML
    void createTower(ActionEvent event) {
        world.setOnMouseClicked(e -> System.out.println("test"));
        Color color= Color.WHITE;
        Circle c = new Circle(5, 2, 3, color);
        world.getChildren().add(c);
        System.out.println("test");
    }*/

    /**
     *  initLoop
     *  Create the event that will happen a certain number of time per seconds.
     */
    private void initLoop() {
        gameloop = new Timeline();
        gameloop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017), // FPS Number
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
        ImageView test = new ImageView();
        test.setX(event.getX());
        test.setY(event.getY());
        test.setFitHeight(imageTower.getFitHeight());
        test.setFitWidth(imageTower.getFitWidth());
        test.setImage(imageTower.getImage());
        Node tower = new Node(50,new Location((int)event.getX(),(int)event.getY()),150,150,150);
        env.addToList(tower);
        test.setId(tower.getId());
        world.getChildren().add(test);
        System.out.println(env.getNodeList());
    }

    @FXML
    void setTowerOnAfast(MouseEvent event) {
        imageTower = imageAfast;
    }

    @FXML
    void setTowerOnGb(MouseEvent event) {
        imageTower = imageGb;
    }

    @FXML
    void setTowerOnIVG(MouseEvent event) {
        imageTower = imageIVG;
    }

    @FXML
    void setTowerOnKbd(MouseEvent event) {
        imageTower = imageKbd;
    }


    /*
    public void createSprite(Entity ent){
        Circle r = new Circle(3);
        r.translateXProperty().bind(ent.getLocationX());
        r.translateYProperty().bind(ent.getLocationY());
        r.setFill(Color.web("#ffffff"));
        r.setId(ent.getId());
        world.getChildren().add(r);
    }
    */

}
