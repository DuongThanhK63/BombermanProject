package com.uet.gameobject;

import com.uet.effect.Animation;

import java.awt.*;

public abstract class ParticularObject extends GameObject{

    public static final int LEAGUE_TEAM = 1;
    public static final int ENEMY_TEAM = 2;

    public static final int LEFT_DIR = 1;
    public static final int RIGHT_DIR = 2;
    public static final int UP_DIR = 3;
    public static final int DOWN_DIR = 4;

    public static final int ALIVE = 0;
    public static final int GETDAMGE = 1;
    public static final int DEATH = 2;
    public static final int IMMORTAL = 3;
    private int state = ALIVE;

    private double width;
    private double height;
    private double speedX;
    private double speedY;
    private int blood;

    private int damage;

    private int direction;

    private int teamType;

    protected Animation deathAnimation, immortalAnimation;

    private long startTimeImmortal;
    private long timeImmortal;

    public ParticularObject(double positionX, double positionY, GameWorld gameWorld, double width, double height) {
        super(positionX, positionY, gameWorld);
        setWidth(width);
        setHeight(height);
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
                break;
            case GETDAMGE:
                if(immortalAnimation == null){
                    state = IMMORTAL;
                    startTimeImmortal = System.nanoTime();
                    if(getBlood() == 0){
                        state = DEATH;
                    }
                } else {
                    deathAnimation.Update(System.nanoTime());
                    if(deathAnimation.isLastFrame()){
                        deathAnimation.reset();
                        state = IMMORTAL;
                        if(getBlood() == 0){
                            state = DEATH;
                        }
                        startTimeImmortal = System.nanoTime();
                    }
                }
                break;
            case IMMORTAL:
                if(System.nanoTime() - startTimeImmortal > timeImmortal){
                    state = ALIVE;
                }
                break;
            case DEATH:
                break;
        }
    }

    public abstract void draw(Graphics2D g2d);

    public abstract void attack();

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
        g2d.setColor(Color.WHITE);
        g2d.drawRect(rect.x - (int) getGameWorld().camera.getPositionX(),rect.y - (int) getGameWorld().camera.getPositionY(),rect.width,rect.height);
    }

    public void drawBoundForCollisionWithEnemy(Graphics2D g2d){
        Rectangle rect = getBoundForCollisionWithMap();
        g2d.setColor(Color.RED);
    }

    public abstract Rectangle getBoundForCollisionWithEnemy();

    public abstract void getDamageCallBack();
}
