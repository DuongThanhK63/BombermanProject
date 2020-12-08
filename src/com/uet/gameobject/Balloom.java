package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Hashtable;

public class Balloom extends Enemy{

    private int [][] phys_map = CacheDataLoader.getInstance().getPhysicalmap();
    private int tileSize;

    private Animation balloomLeft = new Animation();
    private Animation balloomDown = new Animation();
    private Animation balloomRight = new Animation();
    private Animation balloomUp = new Animation();
    private Animation balloomDie = new Animation();

    private Hashtable<String, Long> timeMoving = new Hashtable<String, Long>();
    private String[] movingType = new String[4];
    private int movingIndex = 0;
    private long lastMovingTime;

    public Balloom(double positionX, double positionY, GameWorld gameWorld) {
        super(positionX,positionY,gameWorld,48,48);
        this.tileSize = 48;
        setBlood(10);
        setDamage(10);

        balloomRight = CacheDataLoader.getInstance().getAnimation("balloomright");
        balloomLeft = CacheDataLoader.getInstance().getAnimation("balloomleft");
        balloomUp = CacheDataLoader.getInstance().getAnimation("balloomright");
        balloomDown = CacheDataLoader.getInstance().getAnimation("balloomleft");
        balloomDie = CacheDataLoader.getInstance().getAnimation("balloomdie");

        movingType[0] = "RIGHT";
        movingType[1] = "LEFT";
        movingType[2] = "UP";
        movingType[3] = "DOWN";

        timeMoving.put("RIGHT", new Long(1000));
        timeMoving.put("LEFT", new Long(1000));
        timeMoving.put("UP", new Long(1000));
        timeMoving.put("DOWN", new Long(1000));
    }

    @Override
    public void Update() {
        super.Update();

        setPositionX(getPositionX() + getSpeedX());
        setPositionY(getPositionY() + getSpeedY());

        if(movingType[movingIndex].equals("RIGHT")){
            setDirection(RIGHT_DIR);
            if(getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null){
                setSpeedX(-2);
            }
        }
        if(movingType[movingIndex].equals("LEFT")){
            setDirection(LEFT_DIR);
            if(getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null){
                setSpeedX(2);
            }
        }
        if(movingType[movingIndex].equals("UP")){
            setDirection(UP_DIR);
            if(getGameWorld().physicalMap.haveCollisionWithTopWall(getBoundForCollisionWithMap()) != null){
                setSpeedY(-2);
            }
        }
        if(movingType[movingIndex].equals("DOWN")){
            setDirection(DOWN_DIR);
            if(getGameWorld().physicalMap.haveCollisionWithDownWall(getBoundForCollisionWithMap()) != null){
                setSpeedY(2);
            }
        }

        attack();

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
                    int a = (int) getPositionX() - (int) getGameWorld().camera.getPositionX();
                    System.out.println(a);
                    balloomRight.Update(System.nanoTime());
                    g2d.drawRect((int) getPositionX() - (int) getGameWorld().camera.getPositionX(),
                            (int) getPositionY(), tileSize, tileSize);
                    balloomRight.draw((int) getPositionX() - (int) getGameWorld().camera.getPositionX(),
                            (int) getPositionY(), g2d);
                    if (balloomRight.getCurrentFrame() == 1) balloomRight.setIgnoreFrame(0);

                } else if (getSpeedX() < 0 && getDirection() == LEFT_DIR) {
                    balloomLeft.Update(System.nanoTime());
                    balloomLeft.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                    if (balloomLeft.getCurrentFrame() == 1) balloomLeft.setIgnoreFrame(0);

                }
                if (getSpeedY() < 0 && getDirection() == UP_DIR) {
                    balloomUp.Update(System.nanoTime());
                    balloomUp.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                    if (balloomUp.getCurrentFrame() == 1) balloomUp.setIgnoreFrame(0);

                } else if (getSpeedY() > 0 && getDirection() == DOWN_DIR) {
                    balloomDown.Update(System.nanoTime());
                    balloomDown.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                    if (balloomDown.getCurrentFrame() == 1) balloomDown.setIgnoreFrame(0);
                }
                if(getSpeedY() == 0 && getSpeedX() == 0) {
                        if (getDirection() == RIGHT_DIR) {
                            balloomRight.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        } else if (getDirection() == LEFT_DIR) {
                            balloomLeft.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        } else if (getDirection() == UP_DIR) {
                            balloomUp.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        } else {
                            balloomDie.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
                        }
                    }
                      break;
//            case DEATH:
//                BalloomDie.Update(System.nanoTime());
//                BalloomDie.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
//                // if (BalloomDie.getCurrentFrame() == 1) BalloomDie.setIgnoreFrame(0);
//                break;
                }

    }

    @Override
    public void attack () {
//        if (System.currentTimeMillis() - lastMovingTime > timeMoving.get(movingType[movingIndex])) {
//            lastMovingTime = System.currentTimeMillis();
//            movingIndex++;
//            if (movingIndex >= movingType.length) {
//                movingIndex = 0;
//            }
//            switch (movingType[movingIndex]) {
//                case "RIGHT":
//                    setSpeedX(1);
//                    break;
//                case "LEFT":
//                    setSpeedX(-1);
//                    break;
//                case "UP":
//                    setSpeedY(-1);
//                    break;
//                case "DOWN":
//                    setSpeedY(1);
//                    break;
//            }
//        }
    }
}