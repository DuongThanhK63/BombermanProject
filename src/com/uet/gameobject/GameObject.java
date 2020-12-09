package com.uet.gameobject;

import com.uet.effect.CacheDataLoader;

import java.awt.*;

public abstract class GameObject {

    private double positionX, positionY;
    private GameWorld gameWorld;
    public final int tileSize = 32;

    public GameObject(double positionX, double positionY, GameWorld gameWorld) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.gameWorld = gameWorld;
    }

    public GameObject(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        if ( positionX < 32) {
            this.positionX = 32;
        } else if ( positionX > 32*30) {
            this.positionX = 32*30;
        } else this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        if ( positionY < 32) {
            this.positionY = 32;
        }else if ( positionY > 32*12) {
            this.positionY = 32*12;
        } else this.positionY = positionY;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public abstract void Update();

    public int getTileSize() {
        return tileSize;
    }

    public Rectangle getBoundForCollisionWithMap(){
        Rectangle bound = new Rectangle();
        bound.x = (int) getPositionX();
        bound.y = (int) getPositionY();
        bound.width = 32;
        bound.height = 32;
        return bound;
    }
    public Rectangle getBoundForCollisionWithEnemy(){
        Rectangle bound = new Rectangle();
        bound.x = (int) getPositionX();
        bound.y = (int) getPositionY();
        bound.width = 32;
        bound.height = 32;
        return bound;
    }
}

