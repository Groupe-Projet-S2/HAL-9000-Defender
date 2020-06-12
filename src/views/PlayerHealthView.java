package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerHealthView {

    public PlayerHealthView(){}

    public static Rectangle drawBackground(){
        Rectangle background = new Rectangle();
        background.setTranslateX(8);
        background.setTranslateY(-55);
        background.setWidth(250);
        background.setHeight(20);
        background.setFill(Color.web("#aaaaaa"));
        return background;
    }

    public static Rectangle drawHeat(){
        Rectangle heat = new Rectangle();
        heat.setId("heat");
        heat.setTranslateX(10);
        heat.setTranslateY(-53);
        heat.setWidth(0);
        heat.setHeight(16);
        heat.setFill(Color.BLACK);
        return heat;
    }
}
