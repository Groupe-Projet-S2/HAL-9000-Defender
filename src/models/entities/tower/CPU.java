package models.entities.tower;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import models.environment.Location;
import models.environment.World;

public class CPU extends Tower implements Damagable {

    private IntegerProperty overHeatedRate;

    public CPU(Location pos, World env) {
        super(25,pos,env);
        this.overHeatedRate = new SimpleIntegerProperty(60);
    }

    public int getOverHeatedRate() {
        return overHeatedRate.getValue();
    }

    public void setOverHeatedRate(int rate) {
        this.overHeatedRate.setValue(rate);
    }

    public IntegerProperty getOverHeatedRateProperty() {
        return overHeatedRate;
    }

    @Override
    public void act() {
    }
    @Override
    public int getPrice() {return 0;}
    @Override
    public void getDamaged(int a) {
        overHeatedRate.set(overHeatedRate.getValue()+a);
    }

    @Override
    public boolean isAlive() {
        return overHeatedRate.getValue()<100;
    }

    @Override
    public int getHp() {
        return overHeatedRate.getValue();
    }
}