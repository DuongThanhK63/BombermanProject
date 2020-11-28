package com.uet.gameobject;

import com.uet.effect.Animation;

import java.awt.*;

public class BombAttack extends Bomb{

    private Animation forwardBombAnim, backAnim, upAnim, downAnim;
    private Animation bombAnim;
    public static int timeToExplosion = 2000 * 10000000;
    public static int disappearTime = 2 * 1000000;

    public BombAttack(double positionX, double positionY, GameWorld gameWorld) {
        super(positionX, positionY, gameWorld, 40, 40, 10);
    }

    @Override
    public void draw(Graphics2D g2d) {
//        bombAnim.draw((int)getGameWorld().player.getPositionX() - (int)getGameWorld().camera.getPositionX(),
//                (int)getGameWorld().player.getPositionY() - (int)getGameWorld().camera.getPositionY(),g2d);
        drawBoundForCollisionWithMap(g2d);

    }

    @Override
    public void attack() {

        Update();


    }

    @Override
    public void Update() {

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

//    @Override
//    public void getDamageCallBack() {
//
//    }
}
