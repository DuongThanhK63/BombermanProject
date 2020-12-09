package uet.oop.bomberman.entities.ParticularObject.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.entities.ParticularObject.Enemy.ai.AI;
import uet.oop.bomberman.entities.ParticularObject.ParticularObject;

import java.awt.*;

public class Enemy extends ParticularObject {
    protected AI ai;
    public Enemy(int x, int y, Image image, GameWorld gameWorld) {
        super(x, y, image);
        setGameWorld(gameWorld);
        setTeamType(ENEMY_TEAM);
    }
    public Enemy(Image image) {
        super(image);
    }

    @Override
    public void update() {
        if (getDirection() == RIGHT_DIR &&
                getGameWorld().getMap().haveCollisionWithRightWall(getBoundForCollisionWithEnemy()) != null) {
            Rectangle rectRightWall = getGameWorld().getMap().haveCollisionWithRightWall(getBoundForCollisionWithEnemy());
            setX(rectRightWall.x - 32);

        }
        if (getDirection() == LEFT_DIR &&
                getGameWorld().getMap().haveCollisionWithLeftWall(getBoundForCollisionWithEnemy()) != null) {
            Rectangle rectLeftWall = getGameWorld().getMap().haveCollisionWithLeftWall(getBoundForCollisionWithEnemy());
            setX(rectLeftWall.x + 32);
        }
        if (getDirection() == UP_DIR &&
                getGameWorld().getMap().haveCollisionWithTop(getBoundForCollisionWithEnemy()) != null) {
            Rectangle rectLeftWall = getGameWorld().getMap().haveCollisionWithTop(getBoundForCollisionWithEnemy());
            setY(rectLeftWall.y + 32);
        }
        if (getDirection() == DOWN_DIR &&
                getGameWorld().getMap().haveCollisionWithLand(getBoundForCollisionWithEnemy()) != null) {
            Rectangle rectLeftWall = getGameWorld().getMap().haveCollisionWithLand(getBoundForCollisionWithEnemy());
            setY(rectLeftWall.y - 32);
        }
    }
}
