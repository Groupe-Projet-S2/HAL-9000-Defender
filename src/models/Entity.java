package models;

public class Entity {

    private int range;
    private Location location;
    private Location target;

    public Entity(int range, Location location, Location target) {
        this.range=range;
        this.location=location;
        this.target=target;
    }

    Location getLocation() {
        return this.location;
    }

    void setLocation(int x, int y) {
        this.location.setX(x);
        this.location.setY(y);
    }

}
