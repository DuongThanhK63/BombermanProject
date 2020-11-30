package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;

import java.awt.*;

public class Brick extends ParticularObject {

    private Animation brickAnim = new Animation();
    private int tileSize;

    public Brick(double positionX, double positionY, GameWorld gameWorld, double width, double height) {
        super(positionX, positionY, gameWorld, width, height);
        this.tileSize = 60;
        brickAnim = CacheDataLoader.getInstance().getAnimation("brick");
    }


    @Override
    public void draw(Graphics2D g2d) {

        brickAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()),
                (int)(getPositionY() - getGameWorld().camera.getPositionY()), g2d);
     }

    @Override
    public void attack() {

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return null;
    }
}
