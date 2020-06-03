package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.entities.Virus;
import models.environment.Location;

public class VirusView {
    public static Rectangle renderVirus(Virus virus) {
        Location loc = virus.getLocation();
        Rectangle r = new Rectangle();
        r.setHeight(virus.getSizeH());
        r.setWidth(virus.getSizeW());
        r.setFill(Color.RED);
        r.setX(loc.getCol() - virus.getSizeW() / 2.0);
        r.setY(loc.getRow() - virus.getSizeH() / 2.0);

        loc.getRowProperty().addListener((obs, prev, next) -> r.setY(next.intValue() - virus.getSizeW() / 2.0));
        loc.getColProperty().addListener((obs, prev, next) -> r.setX(next.intValue() - virus.getSizeH() / 2.0));

        r.setId("S" + virus.getId());
        return r;
    }
}