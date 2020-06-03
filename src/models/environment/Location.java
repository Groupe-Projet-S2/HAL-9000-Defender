package models.environment;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Location {
    private IntegerProperty row, col;

    public Location(int row, int col) {
        this.row = new SimpleIntegerProperty(row);
        this.col = new SimpleIntegerProperty(col);
    }

    public void set(int row, int col) {
        setRow(row);
        setCol(col);
    }

    public void setRow(int row) {
        this.row.setValue(row);
    }

    public void setCol(int col) {
        this.col.setValue(col);
    }

    public int getRow() {
        return this.row.getValue();
    }

    public int getCol() {
        return this.col.getValue();
    }

    public IntegerProperty getRowProperty() {
        return row;
    }

    public IntegerProperty getColProperty() {
        return col;
    }

    public boolean match(Location location) {
        return this.row.getValue() == location.getRow() && this.col.getValue() == location.getCol();
    }

    public String toString() {
        return "Row: " + row.getValue() + " - Col: " + col.getValue();
    }
}
