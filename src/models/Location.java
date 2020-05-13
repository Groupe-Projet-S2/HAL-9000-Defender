package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Location {
    private IntegerProperty x, y;

    public Location(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public void setX(int x) {
        this.x.setValue(x);
    }

    public void setY(int y) {
        this.y.setValue(y);
    }

    public int getX() {
        return this.x.getValue();
    }

    public int getY() {
        return this.y.getValue();
    }

    public IntegerProperty getXProperty() {
        return x;
    }

    public IntegerProperty getYProperty() {
        return y;
    }

    public boolean match(Location location) {
        return this.x.getValue() == location.getX() && this.y.getValue() == location.getY();
    }
}
