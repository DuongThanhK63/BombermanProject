package com.uet.gameobject.enemy;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;
import com.uet.gameobject.managerobjects.AILow;
import com.uet.gameobject.managerobjects.GameWorld;

import java.awt.*;
import java.util.Hashtable;

public class Balloom extends Enemy {

    private int [][] phys_map = CacheDataLoader.getInstance().getPhysicalmap();
    private int tileSize;

    private Animation BalloomLeft = new Animation();
    private Animation BalloomDown = new Animation();
    private Animation BalloomRight = new Animation();
    private Animation BalloomUp = new Animation();
    private Animation BalloomDie = new Animation();

    private Hashtable<String, Long> timeMoving = new Hashtable<String, Long>();
    private String[] movingType = new String[4];
    private int movingIndex = 0;
    private long lastMovingTime;

    public Balloom(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld, 32, 32);
        this.setPositionX(posX);
        this.setPositionY(posY);
        this.tileSize = 32;

        setTeamType(ENEMY_TEAM);
        setState(ALIVE);

        BalloomRight = CacheDataLoader.getInstance().getAnimation("balloomright");
        BalloomLeft = CacheDataLoader.getInstance().getAnimation("balloomleft");

        BalloomUp = CacheDataLoader.getInstance().getAnimation("balloomright");
        BalloomDown = CacheDataLoader.getInstance().getAnimation("balloomleft");

        BalloomDie = CacheDataLoader.getInstance().getAnimation("balloomdie");
        ai = new AILow();


    }

    int x = 0;
    int a = 0;

    @Override
    public void Update() {
        super.Update();
        x ++;
        while (!canmove(a)) {
            a = ai.calculateDirection();
        }
        switch (a) {
            case 0:
                setSpeedX(1);
                setDirection(RIGHT_DIR);
                setPositionX(getPositionX() + getSpeedX());
                break;
            case 1:
                setSpeedX(-1);
                setDirection(LEFT_DIR);
                setPositionX(getPositionX()  + getSpeedX());
                break;

            case 2:
                setSpeedY(1);
                setPositionY(getPositionY()  + getSpeedY());
                break;

            case 3:
                setSpeedY(-1);
                setPositionY(getPositionY()  + getSpeedY());
                break;
        }

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();

        rect.x = (int)getPositionX() - 10;
        rect.y = (int)getPositionY() - 10;
        rect.width = 32;
        rect.height = 32;

        return rect;
    }

    @Override
    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.GRAY);
//        switch (getState()) {
//            case ALIVE:
        //if (getState() == ALIVE && (System.nanoTime() / 10000000) % 2 != 1) {
        //    System.out.println("Flash..");
        //       } else {
        if (getSpeedX() > 0) {
            BalloomRight.Update(System.nanoTime());
            BalloomRight.draw((int) getPositionX() , (int) getPositionY(), g2d);
            if (BalloomRight.getCurrentFrame() == 1) BalloomRight.setIgnoreFrame(0);

        } else if (getSpeedX() < 0) {
            BalloomLeft.Update(System.nanoTime());
            BalloomLeft.draw((int) (getPositionX()), (int) getPositionY(), g2d);
            if (BalloomLeft.getCurrentFrame() == 1) BalloomLeft.setIgnoreFrame(0);

        } else if (getSpeedY() < 0) {
            BalloomUp.Update(System.nanoTime());
            BalloomUp.draw((int) (getPositionX()), (int) getPositionY(), g2d);
            if (BalloomUp.getCurrentFrame() == 1) BalloomUp.setIgnoreFrame(0);

        } else if (getSpeedY() > 0) {
            BalloomDown.Update(System.nanoTime());
            BalloomDown.draw((int) (getPositionX()), (int) getPositionY(), g2d);
            if (BalloomDown.getCurrentFrame() == 1) BalloomDown.setIgnoreFrame(0);

//            case DEATH:
//                BalloomDie.Update(System.nanoTime());
//                BalloomDie.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
//                // if (BalloomDie.getCurrentFrame() == 1) BalloomDie.setIgnoreFrame(0);
//                break;

    }


  //  public void attack () {
//        double x = getGameWorld().player.getPositionX() - (int)getGameWorld().camera.getPositionX();
//        double y = getGameWorld().player.getPositionY();
//        if(!isAttacking){
//            BombAttack bomb = new BombAttack(x,y,getGameWorld());
//            getGameWorld().bombList.addObject(bomb);
//            bomb.attack();
//        }
//        isAttacking = true;
//        lastAttackTime = System.nanoTime();
    }
    public boolean canmove(int direction) {
        switch (direction) {
            case 0://phai
                if (getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null) {
                    return false;
                }
            case 1://trai
                if (getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null) {
                    return false;
                }
            case 2://xuong
                if (getGameWorld().physicalMap.haveCollisionWithDownWall(getBoundForCollisionWithMap()) != null) {
                    return false;
                }
            case 3://len
                if (getGameWorld().physicalMap.haveCollisionWithTopWall(getBoundForCollisionWithMap()) != null) {
                    return false;
                }
        }
        return true;
    }


}