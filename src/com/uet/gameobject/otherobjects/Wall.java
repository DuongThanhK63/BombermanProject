package com.uet.gameobject.otherobjects;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;
import com.uet.gameobject.managerobjects.GameWorld;
import com.uet.gameobject.managerobjects.ParticularObject;

import java.awt.*;

public class Wall extends ParticularObject {

    private Animation wallAnim;
    public Wall(double positionX, double positionY, GameWorld gameWorld, double width, double height) {
        super(positionX, positionY, gameWorld, width, height);
        setState(ALIVE);
        setDamage(0);
        wallAnim = CacheDataLoader.getInstance().getAnimation("wall");
    }

    @Override
    public void draw(Graphics2D g2d) {
        wallAnim.draw((int) (getPositionX()), (int)(getPositionY()), g2d);
    }

    @Override
    public void attack() {

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }
}
