package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;

import java.awt.*;

public class Player extends Human{


    public static final int tileSize = 60;

    private Animation runLeftAnim = new Animation();
    private Animation runDownAnim = new Animation();
    private Animation runRightAnim = new Animation();
    private Animation runUpAnim = new Animation();


    private long lastAttackTime;
    private boolean isAttacking = false;

    public Player(double positionX, double positionY, GameWorld gameWorld) {
        super(positionX, positionY, gameWorld, 40, 40, 20);

        setTeamType(LEAGUE_TEAM);

        setTimeImmortal(2000*1000000);

        runRightAnim = CacheDataLoader.getInstance().getAnimation("manright");
        runLeftAnim = CacheDataLoader.getInstance().getAnimation("manleft");

        runUpAnim = CacheDataLoader.getInstance().getAnimation("manup");
        runDownAnim = CacheDataLoader.getInstance().getAnimation("mandown");


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

        rect.x = (int)getPositionX() - 22;
        rect.y = (int)getPositionY() - 40;
        rect.width = 44;
        rect.height = 80;

        return rect;
    }

    @Override
    public void draw(Graphics2D g2d) {

        switch (getState()) {
            case ALIVE:
            case IMMORTAL:
                if (getState() == IMMORTAL && (System.nanoTime() / 10000000) % 2 != 1) {
                    System.out.println("Flash..");
                } else {
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
                }
            case DEATH:
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
        if(!isAttacking){
            double x = getGameWorld().player.getPositionX();
            double y = getGameWorld().player.getPositionY();
            placeBomb(x,y);

            }
//            bomb.attack();
            isAttacking = true;
            lastAttackTime = System.nanoTime();
    }

    public void placeBomb(double x, double y){
        BombAttack bomb = new BombAttack(x,y,getGameWorld());
        getGameWorld().bomb.addObject(bomb);
    }
    @Override
    public void getDamageCallBack() {

    }

}
