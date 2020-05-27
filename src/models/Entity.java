package models;

public class Entity {

    private int range;
    protected Location location;
    private Location target;
    private String id;
    public static int count = 0;

    public Entity(int range, Location location) {
        this.range=range;
        this.location=location;
        this.id="E"+count;
        count++;
    }

    public Location getLocation() {
        return this.location;
    }
    void setLocation(int x, int y) {
        this.location.setCol(x);
        this.location.setRow(y);
    }

    public static boolean isVirus(Entity ent){
        return (ent instanceof Virus);
    }

    public static boolean isNode(Entity ent){
        return (ent instanceof Tower);
    }

    public static boolean isProjectile(Entity ent){
        return (ent instanceof Projectile);
    }

    public String getId(){
        return this.id;
    }

    public int getRange(){
        return this.range;
    }

    public boolean isInRange(Virus virus){
        int deltaX = this.getLocation().getCol() - Math.max(virus.getLocation().getCol(), Math.min(this.getLocation().getCol(),virus.getLocation().getCol()+virus.getSizeW()));
        int deltaY = this.getLocation().getRow() - Math.max(virus.getLocation().getRow(), Math.min(this.getLocation().getRow(),virus.getLocation().getRow()+virus.getSizeH()));

        return (Math.pow(deltaX,2) + Math.pow(deltaY,2)) < Math.pow(this.getRange(),2);
    }

}
