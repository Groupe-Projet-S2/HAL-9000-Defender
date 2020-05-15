package models;

public class Entity {

    private int range;
    private Location location;
    private Location target;
    private String id;
    public static int count = 0;

    public Entity(int range, Location location) {
        this.range=range;
        this.location=location;
        this.id="E"+count;
        count++;
    }

    Location getLocation() {
        return this.location;
    }

    void setLocation(int x, int y) {
        this.location.setX(x);
        this.location.setY(y);
    }

    public String getId(){
        return this.id;
    }

    public int getRange(){
        return this.range;
    }

}
