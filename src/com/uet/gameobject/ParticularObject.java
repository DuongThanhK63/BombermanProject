package com.uet.gameobject;

import com.uet.effect.Animation;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class ParticularObject extends GameObject{

    public static final int LEAGUE_TEAM = 1;
    public static final int ENEMY_TEAM = 2;
    public static final int BOMB_TEAM = 3;

    public static final int LEFT_DIR = 0;
    public static final int RIGHT_DIR = 1;
    public static final int UP_DIR = 2;
    public static final int DOWN_DIR = 3;

    public static final int ALIVE = 0;
    public static final int GETDAMGE = 1;
    public static final int IMMORTAL = 3;
    public static final int DEATH = 2;
    public static final int FEY = 4;
    private int state = ALIVE;
    private int lives;

    private double width;
    private double height;
    private double speedX;
    private double speedY;
    private int blood;

    private int damage;

    private int direction;

    private int teamType;


    private long startTimeImmortal;
    private long timeImmortal;

    public ParticularObject(double positionX, double positionY, GameWorld gameWorld, double width, double height) {
        super(positionX, positionY, gameWorld);
        setWidth(width);
        setHeight(height);
    }
    public ParticularObject(GameWorld gameWorld, double width, double height){
        super(gameWorld);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public static int getLeagueTeam() {
        return LEAGUE_TEAM;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTeamType() {
        return teamType;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    public long getStartTimeImmortal() {
        return startTimeImmortal;
    }

    public void setStartTimeImmortal(long startTimeImmortal) {
        this.startTimeImmortal = startTimeImmortal;
    }

    public long getTimeImmortal() {
        return timeImmortal;
    }

    public void setTimeImmortal(long timeImmortal) {
        this.timeImmortal = timeImmortal;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public void getDame(int damageTaken){
        setBlood(getBlood() - damageTaken);
        state = GETDAMGE;
        getDamageCallBack();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void Update() {
        switch (state){
            case ALIVE:
                ParticularObject object = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
                if(object != null){
                    if(object.getDamage() > 0){
                        getDame(object.getDamage());
                        System.out.println(this.getLives());
                    }
                }
                break;
            case GETDAMGE:
                if(this.getBlood() <= 0){
                    setState(FEY);
                } else {
                    setState(ALIVE);
                }
                break;
            case FEY:
                setLives(getLives() - 1);
                if(getLives() > 0){
                    setState(ALIVE);
                } else {
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            setState(DEATH);
                        }
                    };
                    long delay = 1000L;
                    Timer timer = new Timer("Timer");
                    timer.schedule(timerTask, delay);

                    break;
                }
//            case IMMORTAL:
//                TimerTask timerTask = new TimerTask() {
//                    @Override
//                    public void run() {
//                        setState(FEY);
//                    }
//                };
//                long delay = 2000L;
//                Timer timer = new Timer("Timer");
//                timer.schedule(timerTask, delay);
//                break;
            case DEATH:
                break;
        }
    }

    public abstract void draw(Graphics2D g2d);

    public abstract void attack();

    public boolean isObjectOutOfCameraView(){
        if(getPositionX() - getGameWorld().camera.getPositionX() > getGameWorld().camera.getWidthView() ||
        getPositionX() - getGameWorld().camera.getPositionX() < -75 ){
            return true;
        } else {
            return false;
        }
    }

    public Rectangle getBoundForCollisionWithMap(){
        Rectangle bound = new Rectangle();
        bound.x = (int)(getPositionX() - getWidth() / 2);
        bound.y = (int)(getPositionY() - getHeight() / 2);
        bound.width = (int)getWidth();
        bound.height = (int)getHeight();
        return bound;
    }

    public void drawBoundForCollisionWithMap(Graphics2D g2d){
        Rectangle rect = getBoundForCollisionWithMap();
        g2d.setColor(Color.RED);
        g2d.drawRect(rect.x - (int) getGameWorld().camera.getPositionX(),rect.y,rect.width,rect.height);
    }

    public void drawBoundForCollisionWithEnemy(Graphics2D g2d){
        Rectangle rect = getBoundForCollisionWithMap();
        g2d.setColor(Color.RED);
    }

    public abstract Rectangle getBoundForCollisionWithEnemy();

    public void getDamageCallBack(){};
}
