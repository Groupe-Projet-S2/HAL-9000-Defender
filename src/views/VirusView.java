package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.entities.virus.Virus;
import models.environment.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VirusView {
    public static ImageView renderVirus(Virus virus) {
        FileInputStream file = null;
        try {
            file = new FileInputStream("src/utils/virus" + virus.getVirusID() + ".png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert file != null;
        ImageView sprite = new ImageView(new Image(file));
        Location loc = virus.getPosition();
        sprite.setX(loc.getCol() - 8);
        sprite.setY(loc.getRow() - 8);

        loc.getRowProperty().addListener((obs, prev, next) -> sprite.setY(next.intValue() - virus.getSizeW() / 2.0));
        loc.getColProperty().addListener((obs, prev, next) -> sprite.setX(next.intValue() - virus.getSizeH() / 2.0));

        sprite.setId("S" + virus.getId());
        return sprite;
    }
}