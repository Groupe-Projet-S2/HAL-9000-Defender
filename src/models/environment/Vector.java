package models.environment;

public class Vector {

    private double row, col;

    public Vector(){}

    public Vector(double row, double col) {
        this.row = row;
        this.col = col;
    }

    public void setRow(double row) {
        this.row = row;
    }

    public void setCol(double col) {
        this.col = col;
    }

    public double getRow() {
        return this.row;
    }

    public double getCol() {
        return this.col;
    }

}
