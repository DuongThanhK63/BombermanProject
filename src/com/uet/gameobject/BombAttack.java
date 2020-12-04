package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;

import java.awt.*;
import java.util.Iterator;
import  java.util.Timer;
import java.util.TimerTask;

public class BombAttack extends Bomb{

    private Animation bombAnim;
    private Animation centreExplosionAnim;
    private Animation upExplosionAnim;
    private Animation downExplosionAnim;
    private Animation leftExplosionAnim;
    private Animation rightExplosionAnim;
    private int numOfBomb = 1;
    public long timeToExplosion = 120;
    public int disappearTime = 2;

    public BombAttack(double positionX, double positionY, GameWorld gameWorld) {
        super(positionX, positionY, gameWorld, 30, 30, 10);
        bombAnim = CacheDataLoader.getInstance().getAnimation("bomdie");
        centreExplosionAnim = CacheDataLoader.getInstance().getAnimation("firecentre");


    }

    @Override
    public double getPositionX() {
        return super.getPositionX();
    }


    @Override
    public double getPositionY() {
        return super.getPositionY();
    }


    @Override
    public void draw(Graphics2D g2d) {

        switch (getState()){
            case ALIVE:
                bombAnim.Update(System.nanoTime());
                bombAnim.draw((int)getPositionX() - (int)getGameWorld().camera.getPositionX(),
                        (int)getPositionY(),g2d);
                break;
            case FEY:
//                TimerTask timerTask = new TimerTask() {
//                    @Override
//                    public void run() {
                Explosion explosion = new Explosion(getPositionX(),getPositionY(), getGameWorld(),50,50);
                explosion.draw(g2d);
//                    }
//                };
//                long delay = 2000L;
//                Timer timer = new Timer("Timer");
//                timer.schedule(timerTask, delay);
                TimerTask timerTask1 = new TimerTask() {
                @Override
                public void run() {
                    setState(DEATH);
                }
            };
                long delay1 = 2000L;
                Timer timer = new Timer("Timer");
                timer.schedule(timerTask1, delay1);
                break;
        }

    }

    @Override
    public void attack() {
        setState(ALIVE);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                setState(FEY);
            }
        };
        long delay = 2000L;
        Timer timer = new Timer("Timer");
        timer.schedule(timerTask, delay);

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

    public int getNumOfBomb() {
        return numOfBomb;
    }

}
