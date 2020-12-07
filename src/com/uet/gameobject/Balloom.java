package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Hashtable;

public class Balloom extends Enemy{

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

    public Balloom(GameWorld gameWorld) {
        super(gameWorld, 48, 48);
        this.tileSize = 48;

        setTeamType(ENEMY_TEAM);
        setState(ALIVE);

        BalloomRight = CacheDataLoader.getInstance().getAnimation("balloomright");
        BalloomLeft = CacheDataLoader.getInstance().getAnimation("balloomleft");

        BalloomUp = CacheDataLoader.getInstance().getAnimation("balloomright");
        BalloomDown = CacheDataLoader.getInstance().getAnimation("balloomleft");

        BalloomDie = CacheDataLoader.getInstance().getAnimation("balloomdie");

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
        moving();

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

        setPositionX(getSpeedX() + getSpeedX());
        setPositionY(getSpeedY() + getSpeedY());


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


        g2d.setColor(Color.GRAY);
        Camera camera = getGameWorld().camera;

        for (int i = 0; i < phys_map.length; i++) {
            for (int j = 0; j < phys_map[0].length; j++) {
                switch (phys_map[i][j]) {
                    case 9:
//        switch (getState()) {
//            case ALIVE:
                        //if (getState() == ALIVE && (System.nanoTime() / 10000000) % 2 != 1) {
                        //    System.out.println("Flash..");
                        //       } else {
                        //if (getSpeedX() > 0 && getDirection() == RIGHT_DIR) {
                        int a = (int) getPositionX()  + j * tileSize - (int) getGameWorld().camera.getPositionX();
                            System.out.println(a);
                            BalloomRight.Update(System.nanoTime());
                        g2d.drawRect((int) getPositionX() + j * tileSize - (int) getGameWorld().camera.getPositionX(),
                                (int) getPositionY() + i * tileSize , tileSize, tileSize);
                        BalloomRight.draw((int) getPositionX()  + j * tileSize + 24 - (int) getGameWorld().camera.getPositionX(),
                                    (int) getPositionY() + i * tileSize + 24 , g2d);
                            //if (BalloomRight.getCurrentFrame() == 1) BalloomRight.setIgnoreFrame(0);
                           // moving();
//                        } else if (getSpeedX() < 0 && getDirection() == LEFT_DIR) {
//                            BalloomLeft.Update(System.nanoTime());
//                            BalloomLeft.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
//                            if (BalloomLeft.getCurrentFrame() == 1) BalloomLeft.setIgnoreFrame(0);
//                          //  moving();
//                        }
//                        if (getSpeedY() < 0 && getDirection() == UP_DIR) {
//                            BalloomUp.Update(System.nanoTime());
//                            BalloomUp.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
//                            if (BalloomUp.getCurrentFrame() == 1) BalloomUp.setIgnoreFrame(0);
//                           // moving();
//                        } else if (getSpeedY() > 0 && getDirection() == DOWN_DIR) {
//                            BalloomDown.Update(System.nanoTime());
//                            BalloomDown.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
//                            if (BalloomDown.getCurrentFrame() == 1) BalloomDown.setIgnoreFrame(0);
                           // moving();
                        }
//                    if(getSpeedY() == 0 && getSpeedX() == 0){
//                        if (getDirection() == RIGHT_DIR) {
//                            BalloomRight.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
//                        } else if (getDirection() == LEFT_DIR) {
//                            BalloomLeft.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
//                        } else if (getDirection() == UP_DIR) {
//                            BalloomUp.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
//                        } else {
//                            BalloomDie.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
//                        }
//                    }
                  //  break;
                        //}
//            case DEATH:
//                BalloomDie.Update(System.nanoTime());
//                BalloomDie.draw((int) (getPositionX() - getGameWorld().camera.getPositionX()), (int) getPositionY(), g2d);
//                // if (BalloomDie.getCurrentFrame() == 1) BalloomDie.setIgnoreFrame(0);
//                break;

                }
         //   }
        }



    }

    @Override
    public void moving() {
        if (System.currentTimeMillis() - lastMovingTime > timeMoving.get(movingType[movingIndex])) {
            lastMovingTime = System.currentTimeMillis();

            movingIndex++;
            if (movingIndex >= movingType.length) {
                movingIndex = 0;
            }

            switch (movingType[movingIndex]) {
                case "RIGHT":
                    setSpeedX(2);
                    break;

                case "LEFT":
                    setSpeedX(-2);
                    break;

                case "UP":
                    setSpeedY(-2);
                    break;

                case "DOWN":
                    setSpeedY(2);
                    break;
            }


        }
    }

        @Override
        public void attack () {
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
    }