package models.environment;

public class Tile {
    private final Location pos;
    private final int type;
    private final int ID;
    private Tile parent;
    private final boolean isAvailable;

    private static int count = 0;
    public final static int SIZE = 32; // tile's size

    public Tile(int row, int col, int type) {
        this.pos = new Location(row, col);
        this.type = type;
        this.ID = count++;
        this.parent = null;
        this.isAvailable = type != 5 && type != 7;
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    public boolean isPath() {
        return this.isAvailable;
    }

    public void setParent(Tile parent) {
        this.parent = parent;
    }

    public Tile getParent() {
        return this.parent;
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
