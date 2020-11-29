package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;

import java.awt.*;

public class BombAttack extends Bomb{

    private Animation bombAnim = new Animation();
    public static int timeToExplosion = 2000 * 10000000;
    public static int disappearTime = 2 * 1000000;

    public BombAttack(double positionX, double positionY, GameWorld gameWorld) {
        super(positionX, positionY, gameWorld, 30, 30, 10);
        bombAnim = CacheDataLoader.getInstance().getAnimation("bomdie");
    }

    @Override
    public void draw(Graphics2D g2d) {
        bombAnim.draw((int)getGameWorld().player.getPositionX() - (int)getGameWorld().camera.getPositionX(),
                (int)getGameWorld().player.getPositionY() - (int)getGameWorld().camera.getPositionY(),g2d);
//        drawBoundForCollisionWithMap(g2d);

    }

    @Override
    public void attack() {


    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }


}
