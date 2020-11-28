package com.uet.gameobject;

import javax.swing.*;
import java.awt.*;

public abstract class Bomb extends ParticularObject {


    public Bomb(double positionX, double positionY, GameWorld gameWorld, double width, double height, int damage) {
        super(positionX, positionY, gameWorld, width, height);
        setDamage(damage);
    }

    public abstract void draw(Graphics2D g2d);

    public void Update(){
        super.Update();
        setPositionX(getPositionX());
        setPositionY(getPositionY());
//        ParticularObject object = getGameWorld().
    }
}
