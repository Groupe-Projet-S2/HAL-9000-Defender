package models;

public class Tile {
    private Location pos;
    private int type;
    private int ID;

    private static int count = 0;

    public Tile(int x, int y, int type) {
        this.pos = new Location(x, y);
        this.type = type;
        this.ID = count++;
    }

    public int getID() {
        return ID;
    }

    public Location getPos() {
        return this.pos;
    }

    public int getType() {
        return type;
    }

}
