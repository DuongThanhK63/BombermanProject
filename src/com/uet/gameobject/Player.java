package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Human{

    private Animation runLeftAnim;
    private Animation runDownAnim;
    private Animation runRightAnim;
    private Animation runUpAnim;
    private Animation dieAnim;

    private long lastAttackTime;
    private boolean isAttacking = false;

    public Player(double positionX, double positionY, GameWorld gameWorld) {
        super(positionX, positionY, gameWorld, 48, 48, 10);

        setTeamType(LEAGUE_TEAM);
        setDamage(0);
        setLives(3);

        runRightAnim = CacheDataLoader.getInstance().getAnimation("manright");
        runLeftAnim = CacheDataLoader.getInstance().getAnimation("manleft");

        runUpAnim = CacheDataLoader.getInstance().getAnimation("manup");
        runDownAnim = CacheDataLoader.getInstance().getAnimation("mandown");

        dieAnim = CacheDataLoader.getInstance().getAnimation("mandie");


    }

    @Override
    public void Update() {
        super.Update();
        if(isAttacking){
            if(System.nanoTime() - lastAttackTime > 500*1000000){
                isAttacking = false;
            }
        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();

        rect.x = (int)getPositionX() - 10;
        rect.y = (int)getPositionY() - 10;
        rect.width = 48;
        rect.height = 48;

        return rect;
    }

    @Override
    public void draw(Graphics2D g2d) {
        drawBoundForCollisionWithMap(g2d);
        switch (getState()) {
            case ALIVE:

                    if (getSpeedX() > 0 && getDirection() == RIGHT_DIR) {
                        runRightAnim.Update(System.nanoTime());
                        runRightAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        if (runRightAnim.getCurrentFrame() == 1) runRightAnim.setIgnoreFrame(0);
                    } else if (getSpeedX() < 0 && getDirection() == LEFT_DIR) {
                        runLeftAnim.Update(System.nanoTime());
                        runLeftAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        if (runLeftAnim.getCurrentFrame() == 1) runLeftAnim.setIgnoreFrame(0);
                    }
                    if (getSpeedY() < 0 && getDirection() == UP_DIR) {
                        runUpAnim.Update(System.nanoTime());
                        runUpAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        if (runUpAnim.getCurrentFrame() == 1) runUpAnim.setIgnoreFrame(0);
                    } else if (getSpeedY() > 0 && getDirection() == DOWN_DIR) {
                        runDownAnim.Update(System.nanoTime());
                        runDownAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        if (runDownAnim.getCurrentFrame() == 1) runDownAnim.setIgnoreFrame(0);
                    }
                    if(getSpeedY() == 0 && getSpeedX() == 0){
                        if (getDirection() == RIGHT_DIR) {
                            runRightAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        } else if (getDirection() == LEFT_DIR) {
                            runLeftAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        } else if (getDirection() == UP_DIR) {
                            runUpAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        } else {
                            runDownAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        }
                    }
                    break;

            case FEY:
                dieAnim.Update(System.nanoTime());
                dieAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                if (dieAnim.getCurrentFrame() == 1) dieAnim.setIgnoreFrame(0);
               break;

        }
    }
    @Override
    public void moving() {

        if(getDirection() == LEFT_DIR){
            setSpeedX(-2);
        } else if(getDirection() == RIGHT_DIR){
            setSpeedX(2);
        } else if(getDirection() == UP_DIR){
            setSpeedY(-2);
        } else if(getDirection() == DOWN_DIR){
            setSpeedY(2);
        }
    }

    @Override
    public void stopMoving() {
        setSpeedX(0);
        setSpeedY(0);
        runLeftAnim.reset();
        runDownAnim.reset();
        runRightAnim.reset();
        runUpAnim.reset();
    }

    @Override
    public void attack() {
        double x = getGameWorld().player.getPositionX() - (int)getGameWorld().camera.getPositionX();
        double y = getGameWorld().player.getPositionY();
        if(!isAttacking){
            BombAttack bomb = new BombAttack(x,y,getGameWorld());
            getGameWorld().bombList.addObject(bomb);
            bomb.attack();
        }
            isAttacking = true;
            lastAttackTime = System.nanoTime();
    }

    @Override
    public void getDamageCallBack() {

    }

    public boolean isAttacking() {
        return isAttacking;
    }


}
