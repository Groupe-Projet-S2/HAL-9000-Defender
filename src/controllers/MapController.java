package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import models.*;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import views.MapView;
public class MapController {

    private Timeline gameloop;
    private ImageView imageTower;
    private Node tower;
    private final int COLS = 25; // columns
    private final int ROWS = 25; // rows

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

        virus = new Virus(5,new Location(6 * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE/2), tileMap.getTile(6,0));
        env.addToList(virus);
        Rectangle r = new Rectangle();
        r.setHeight(virus.getSizeH());
        r.setWidth(virus.getSizeW());
        r.setFill(Color.RED);
        r.setX(virus.getLocation().getCol()-virus.getSizeW()/2);
        r.setY(virus.getLocation().getRow()-virus.getSizeH()/2);
        r.setId("S"+virus.getId());
        world.getChildren().add(r);

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

    private void updateView(){
        /*if (!env.getNodeList().isEmpty() && env.getNodeList().get(0).getTarget()!=null) {
            line.setVisible(true);
            line.setStartX(env.getNodeList().get(0).getLocation().getCol());
            line.setStartY(env.getNodeList().get(0).getLocation().getRow());
            line.setEndX(env.getNodeList().get(0).getTarget().getLocation().getCol());
            line.setEndY(env.getNodeList().get(0).getTarget().getLocation().getRow());
        }
        else
            line.setVisible(false);*/

        Line target;
        for (Node node:env.getNodeList()){
            if (node.hasTarget()){
                if ((target = (Line)world.lookup("#L"+node.getId()))!=null){
                    target.setEndX(node.getTarget().getLocation().getCol());
                    target.setEndY(node.getTarget().getLocation().getRow());
                }
                else{
                    target = new Line();
                    target.setStartX(node.getLocation().getCol());
                    target.setStartY(node.getLocation().getRow());
                    target.setStrokeWidth(1);
                    target.setStroke(Color.YELLOW);
                    target.setEndX(node.getTarget().getLocation().getCol());
                    target.setEndY(node.getTarget().getLocation().getRow());
                    target.setId("L"+node.getId());
                    world.getChildren().add(target);
                }
            }
            else if ((target = (Line)world.lookup("#L"+node.getId()))!=null){
                world.getChildren().remove(target);
            }
        }

        for (Virus virus:env.getVirusList()) {
            ((Rectangle) world.lookup("#S" + virus.getId())).setX(virus.getLocation().getCol() - virus.getSizeW() / 2);
            ((Rectangle) world.lookup("#S" + virus.getId())).setY(virus.getLocation().getRow() - virus.getSizeH() / 2);
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
        if (imageTower != null) {
            tower = new Node(50, new Location((int) event.getY(), (int) event.getX()), 150, 150, 150);
            env.addToList(tower);

            Circle range = new Circle(tower.getRange());
            range.setStroke(Color.web("#000000"));
            range.setFill(Color.rgb(0, 0, 0, 0.25));
            range.setCenterX(tower.getLocation().getCol());
            range.setCenterY(tower.getLocation().getRow());
            range.setId("R" + tower.getId());
            world.getChildren().add(range);

            ImageView test = new ImageView();
            int col = (int) (event.getX() - imageTower.getFitWidth() / 2);
            int row = (int) (event.getY() - imageTower.getFitHeight() / 2);
            test.setX(col);
            test.setY(row);
            test.setFitHeight(imageTower.getFitHeight());
            test.setFitWidth(imageTower.getFitWidth());
            test.setImage(imageTower.getImage());
            test.setId(tower.getId());
            world.getChildren().add(test);
        }
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
}
