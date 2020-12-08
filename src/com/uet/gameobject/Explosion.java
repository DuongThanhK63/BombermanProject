package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;

import java.awt.*;

public class Explosion extends ParticularObject{

    private BombAttack bombAttack;
    private Animation centreAnim;
    private Animation fireLeftAnim, fireRightAnim,fireUpAnim,fireDownAnim;


    public Explosion(double positionX, double positionY, GameWorld gameWorld, double width, double height) {
        super(positionX, positionY, gameWorld, width, height);
        this.bombAttack = new BombAttack(positionX, positionY, gameWorld);
        centreAnim = CacheDataLoader.getInstance().getAnimation("firecentre");
        fireDownAnim = CacheDataLoader.getInstance().getAnimation("firedown1");
        fireLeftAnim = CacheDataLoader.getInstance().getAnimation("fireleft1");
        fireRightAnim = CacheDataLoader.getInstance().getAnimation("fireright1");
        fireUpAnim = CacheDataLoader.getInstance().getAnimation("fireup1");
    }

    public int getBombAttackState() {
        return bombAttack.getState();
    }

    @Override
    public void draw(Graphics2D g2d) {
        int a = (int)this.bombAttack.getPositionX();
        int b = (int)this.bombAttack.getPositionY();
        centreAnim.Update(System.nanoTime());
        centreAnim.draw(a,b,g2d);
        fireLeftAnim.Update(System.nanoTime());
        fireLeftAnim.draw(a-32,b,g2d);
        fireRightAnim.Update(System.nanoTime());
        fireRightAnim.draw(a+32,b,g2d);
        fireUpAnim.Update(System.nanoTime());
        fireUpAnim.draw(a,b-32,g2d);
        fireDownAnim.Update(System.nanoTime());
        fireDownAnim.draw(a,b+32,g2d);

    }

    @Override
    public void attack() {

    }

    @Override
    public void Update() {
        super.Update();
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return null;
    }
}
