package com.uet.gameobject;

public abstract class GameObject {

    private double positionX, positionY;
    private GameWorld gameWorld;

    public GameObject(double positionX, double positionY, GameWorld gameWorld) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.gameWorld = gameWorld;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public abstract void Update();
}
