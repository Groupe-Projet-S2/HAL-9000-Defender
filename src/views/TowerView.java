package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import models.entities.tower.Tower;
import models.environment.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TowerView {
    public static ImageView renderTower(Tower tower, Location location, Image view) {
        ImageView towerView = new ImageView(view);
        int col = location.getCol() - 15;
        int row = location.getRow() - 15;
        towerView.setX(col);
        towerView.setY(row);
        towerView.setFitHeight(30);
        towerView.setFitWidth(30);
        towerView.setId("T" + tower.getId());
        return towerView;
    }

    public static ImageView renderCPU(Tower tower, Location location) {
        FileInputStream file = null;
        try{
            file = new FileInputStream("src/utils/proco50x50.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(file);
        ImageView towerView = new ImageView(new Image(file));
        int col = location.getCol()-10;
        int row = location.getRow()-10;
        towerView.setX(col);
        towerView.setY(row);
        towerView.setFitHeight(50);
        towerView.setFitWidth(50);
        towerView.setId("T" + tower.getId());
        return towerView;
    }

    public static Circle drawRadius(int range, Location location, String id) {
        Circle radius = new Circle(range);
        radius.setStroke(Color.web("#000000"));
        radius.setFill(Color.rgb(0, 0, 0, 0.25));
        radius.setCenterX(location.getCol());
        radius.setCenterY(location.getRow());
        radius.setId("R" + id);
        return radius;
    }

    public static Line drawTarget(Location start, Location end, String id) {
        Line target = new Line();
        target.setStartX(start.getCol());
        target.setStartY(start.getRow());
        target.setStrokeWidth(1);
        target.setStroke(Color.YELLOW);
        target.setEndX(end.getCol());
        target.setEndY(end.getRow());

        end.getRowProperty().addListener((obs, prev, next) -> target.setEndY(next.intValue()));
        end.getColProperty().addListener((obs, prev, next) -> target.setEndX(next.intValue()));

        target.setId("L"+ id);
        return target;
    }
}
