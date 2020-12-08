package com.uet.gameobject;

import java.awt.*;
import java.util.Random;

public abstract class Enemy extends ParticularObject{



    public Enemy(GameWorld gameWorld, double width, double height) {
        super(gameWorld,width,height);
        setState(ALIVE);
    }



    @Override
    public void Update() {
        super.Update();

        if(getState() == ALIVE){

            setPositionX(getPositionX() + getSpeedX());
            setPositionY(getPositionY() + getSpeedY());

            if(getDirection() == LEFT_DIR &&
                    getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null){
                Rectangle recLeftWall = getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                setPositionX(recLeftWall.x + recLeftWall.getWidth() + getWidth() / 2);
            }
            if(getDirection() == RIGHT_DIR &&
                    getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null){
                Rectangle recRightWall = getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                setPositionX(recRightWall.x - getWidth() / 2);
            }
            if(getDirection() == UP_DIR &&
                    getGameWorld().physicalMap.haveCollisionWithTopWall(getBoundForCollisionWithMap()) != null){
                Rectangle recTopWall = getGameWorld().physicalMap.haveCollisionWithTopWall(getBoundForCollisionWithMap());
                setPositionY(recTopWall.y + recTopWall.getHeight() + getHeight() / 2);
            }
            if(getDirection() == DOWN_DIR &&
                    getGameWorld().physicalMap.haveCollisionWithDownWall(getBoundForCollisionWithMap()) != null) {
                Rectangle recDownWall = getGameWorld().physicalMap.haveCollisionWithDownWall(getBoundForCollisionWithMap());
                setPositionY(recDownWall.y - getHeight() / 2);
            }

        }
    }


    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return null;
    }

    @Override
    public Rectangle getBoundForCollisionWithMap() {
        Rectangle bound = new Rectangle();
        bound.x = (int)(getPositionX() - getWidth() / 2);
        bound.y = (int)(getPositionY() - getHeight() / 2);
        bound.width = (int)getWidth();
        bound.height = (int)getHeight();
        return bound;
    }
    public abstract void moving();

}
