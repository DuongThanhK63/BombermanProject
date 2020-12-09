package com.uet.gameobject.otherobjects;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;
import com.uet.gameobject.managerobjects.GameWorld;
import com.uet.gameobject.managerobjects.ParticularObject;

import java.awt.*;

public class Brick extends ParticularObject {

    private Animation brickAnim;
    private Animation brickDieAnim;

    public Brick(double positionX, double positionY, GameWorld gameWorld, double width, double height) {
        super(positionX, positionY, gameWorld, width, height);
        setState(ALIVE);
        setDamage(0);
        setTeamType(ENEMY_TEAM);
        brickAnim = CacheDataLoader.getInstance().getAnimation("brick");
        brickDieAnim = CacheDataLoader.getInstance().getAnimation("brickdie");
    }


    @Override
    public void draw(Graphics2D g2d) {

        switch (getState()){
            case ALIVE:
                brickAnim.draw((int) (getPositionX()), (int)(getPositionY()), g2d);
                break;
            case DEATH:
                brickDieAnim.Update(System.nanoTime());
                brickDieAnim.draw((int) (getPositionX()), (int)(getPositionY()), g2d);
                if (brickDieAnim.getCurrentFrame() == 1) brickDieAnim.setIgnoreFrame(0);
        }
     }

    @Override
    public void attack() {

    }

    public void die(){

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }
}
