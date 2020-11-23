package com.uet.gameobject;

import java.awt.*;

public class Player {

    private double posX, posY;
    private double width;
    private double height;

    private double speedX;
    private double speedY;

    public static int dir_up, dir_down, dir_left, dir_right;
    private int direction;

    public void drawPlayer(Graphics2D graphics2D){

        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect((int)posX, (int)posY, (int)width, (int)height);
    }

    public void update(){
        setPosX(getPosX() + speedX);
        setPosY(getPosY() + speedY);
    }

    public Player(double posX, double posY, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
