package com.uet.gameobject;

import com.uet.effect.Animation;

import java.awt.*;

public class Explosion extends ParticularObject{

    private BombAttack bombAttack;


    public Explosion(double positionX, double positionY, GameWorld gameWorld, double width, double height, BombAttack bombAttack) {
        super(positionX, positionY, gameWorld, width, height);
        this.bombAttack = bombAttack;
    }

    public int getBombAttackState() {
        return bombAttack.getState();
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(getBombAttackState() == DEATH){

        }
    }

    @Override
    public void attack() {

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return null;
    }
}
