package models;

import javafx.beans.property.IntegerProperty;

public class Virus extends Entity {

    private int sizeW, sizeH, maxHP, currentHP;

    public Virus(int range, Location location) {
        super(range, location);
        sizeW = 5;
        sizeH = 5;
    }

    public int getSizeW(){
        return sizeW;
    }
    public int getSizeH(){
        return sizeH;
    }
    public boolean isAlive(){
        return currentHP>0;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    void move() {}

    void act() {}

    void die() {}

}
