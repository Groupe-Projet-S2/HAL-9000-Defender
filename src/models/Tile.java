package models;

public class Tile {
    private Location pos;
    private int ID;

    public Tile(int x, int y, int ID) {
        this.pos = new Location(x, y);
        this.ID = ID;
    }

    public Location getPos() {
        return this.pos;
    }

    public int getID() {
        return ID;
    }

}
