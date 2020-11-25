package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;

import java.awt.*;
import java.util.UUID;

public class Player extends Human{


    public static final int tileSize = 50;
    private Animation runForwardAnim, runBackAnim, runUpAnim, runDownAnim;
    private Animation idleForwardAnim, idleBackAnim, idleUpAnim, idleDownAnim;


    public Player(double positionX, double positionY, GameWorld gameWorld) {
        super(positionX, positionY, gameWorld, 40, 40, 20);

        setTeamType(LEAGUE_TEAM);

        setTimeImmortal(2000*1000000);
        

    }

    @Override
    public void Update() {
        super.Update();


    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();

        rect.x = (int)getPositionX() - 22;
        rect.y = (int)getPositionY() - 40;
        rect.width = 44;
        rect.height = 80;

        return rect;
    }

    @Override
    public void draw(Graphics2D g2d) {

        switch (getState()){
            case ALIVE:
            case IMMORTAL:
               if(getState() == IMMORTAL && (System.nanoTime()/10000000) % 2 != 1){
                   System.out.println("Flash..");
               } else {
                   if(getSpeedX() > 0){
                       runForwardAnim.Update(System.nanoTime());
                       runForwardAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY() - (int) getGameWorld().camera.getPositionY(), g2d);
                       if(runForwardAnim.getCurrentFrame() == 1) runForwardAnim.setIgnoreFrame(0);
                   } else if(getSpeedX() < 0){
                       runBackAnim.Update(System.nanoTime());
                       runBackAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY() - (int) getGameWorld().camera.getPositionY(), g2d);
                       if(runBackAnim.getCurrentFrame() == 1) runBackAnim.setIgnoreFrame(0);
                   }
                   if(getSpeedY() < 0){
                       runUpAnim.Update(System.nanoTime());
                       runUpAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY() - (int) getGameWorld().camera.getPositionY(), g2d);
                       if(runUpAnim.getCurrentFrame() == 1) runUpAnim.setIgnoreFrame(0);
                   } else if(getSpeedY() > 0){
                       runDownAnim.Update(System.nanoTime());
                       runDownAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY() - (int) getGameWorld().camera.getPositionY(), g2d);
                       if(runDownAnim.getCurrentFrame() == 1) runDownAnim.setIgnoreFrame(0);
                   } else {
                       if(getDirection() == RIGHT_DIR){
                           idleForwardAnim.Update(System.nanoTime());
                           idleForwardAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY() - (int) getGameWorld().camera.getPositionY(), g2d);
                       } else if(getDirection() == LEFT_DIR){
                           idleBackAnim.Update(System.nanoTime());
                           idleBackAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY() - (int) getGameWorld().camera.getPositionY(), g2d);
                       } else if(getDirection() == UP_DIR){
                           idleUpAnim.Update(System.nanoTime());
                           idleUpAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY() - (int) getGameWorld().camera.getPositionY(), g2d);
                       } else {
                           idleDownAnim.Update(System.nanoTime());
                           idleDownAnim.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY() - (int) getGameWorld().camera.getPositionY(), g2d);
                       }
                   }
               }
               break;
            case GETDAMGE:
                break;
        }
    }

    @Override
    public void moving() {
        if(getDirection() == LEFT_DIR){
            setSpeedX(-1);
            setPositionX(getPositionX());
        } else if(getDirection() == RIGHT_DIR){
            setSpeedX(1);
        } else if(getDirection() == UP_DIR){
            setSpeedY(-1);
        } else if(getDirection() == DOWN_DIR){
            setSpeedY(1);
        }
    }

    @Override
    public void stopMoving() {
        setSpeedX(0);
        setSpeedY(0);
//        runBackAnim.reset();
//        runDownAnim.reset();
//        runForwardAnim.reset();
//        runUpAnim.reset();
    }

    @Override
    public void attack() {

    }

    @Override
    public void getDamageCallBack() {

    }
}
