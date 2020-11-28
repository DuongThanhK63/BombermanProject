package com.uet.gameobject;

import java.awt.*;

public abstract class Human extends ParticularObject{


    public Human(double positionX, double positionY, GameWorld gameWorld, double width, double height, int blood) {
        super(positionX, positionY, gameWorld, width, height);
        setState(ALIVE);
    }

    @Override
    public void Update() {
        super.Update();

        if(getState() == ALIVE || getState() == IMMORTAL){

            setPositionX(getPositionX() + getSpeedX());
            setPositionY(getPositionY() + getSpeedY());

        }
    }

    @Override
    public void attack() {

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return null;
    }


    public abstract void moving();
    public abstract void stopMoving();
}
