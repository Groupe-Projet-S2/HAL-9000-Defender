package models;

public class Location {
    private int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean match(Location location) {
        return this.x == location.getX() && this.y == location.getY();
    }
}
