package uet.oop.bomberman.gameobject.ParticularObject.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.effect.Animation;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.gameobject.GameObject;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.gameobject.LayeredEntity;
import uet.oop.bomberman.gameobject.ParticularObject.Enemy.ai.AILow;
import uet.oop.bomberman.gameobject.bomb.Bomb;

public class Balloom extends Enemy {
    Animation balloomLeftAnim, balloomRightAnim, balloomDieAnim;
    GameObject balloomLeft, balloomRight;
    private int dir = 0;
    private int timeToChange = 32;

    public Balloom(int x, int y, Image image, GameWorld gameWorld) {
        super(x, y, image, gameWorld);
        balloomLeftAnim = CacheDataLoader.getInstance().getAnimation("balloom_left");
        balloomRightAnim = CacheDataLoader.getInstance().getAnimation("balloom_right");
        balloomDieAnim = CacheDataLoader.getInstance().getAnimation("balloom_dead");
        balloomLeft = CacheDataLoader.getInstance().getObject("balloom_left");
        balloomRight = CacheDataLoader.getInstance().getObject("balloom_right");
        setState(ALIVE);
        ai = new AILow();
    }

    public Balloom(Image image) {
        super(image);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (getState() == ALIVE) {
            if (getSpeedX() > 0) {
                balloomRightAnim.Update(System.nanoTime());
                balloomRightAnim.draw(gc, getX(), getY());
            } else if (getSpeedX() < 0) {
                balloomLeftAnim.Update(System.nanoTime());
                balloomLeftAnim.draw(gc, getX(), getY());
            } else if (getSpeedY() > 0) {
                balloomLeftAnim.Update(System.nanoTime());
                balloomLeftAnim.draw(gc, getX(), getY());
            } else if (getSpeedY() < 0) {
                balloomRightAnim.Update(System.nanoTime());
                balloomRightAnim.draw(gc, getX(), getY());
            } else {
                if (getDirection() == RIGHT_DIR) {
                    balloomRight.render(gc, getX(), getY());
                } else if (getDirection() == LEFT_DIR) {
                    balloomLeft.render(gc, getX(), getY());
                } else if (getDirection() == UP_DIR) {
                    balloomLeft.render(gc, getX(), getY());
                } else if (getDirection() == DOWN_DIR) {
                    balloomRight.render(gc, getX(), getY());
                }
            }
        } else {
            balloomDieAnim.Update(System.nanoTime());
            balloomDieAnim.draw(gc,getX(),getY());

        }
    }



    @Override
    public void update() {
        super.update();
        timeToChange--;
        if (timeToChange == 0) {
            dir = ai.calculateDirection();;
            timeToChange = 32;
        }
        switch (dir) {
            case 0:
                if(!(getGameWorld().getObjectManager().getObjectAt(getGameWorld().getPlayer().getY() / 32, getGameWorld().getPlayer().getX() / 32) instanceof LayeredEntity)){
                        setSpeedX(1);
                        setX(getX() + getSpeedX());
                        setDirection(RIGHT_DIR);
                }
                break;
            case 1:
                if(!(getGameWorld().getObjectManager().getObjectAt(getGameWorld().getPlayer().getY() / 32, getGameWorld().getPlayer().getX() / 32) instanceof LayeredEntity)){
                    setSpeedX(-1);
                    setX(getX() + getSpeedX());
                    setDirection(LEFT_DIR);
                }
                break;

            case 2:
                if(!(getGameWorld().getObjectManager().getObjectAt(getGameWorld().getPlayer().getY() / 32, getGameWorld().getPlayer().getX() / 32) instanceof LayeredEntity)){
                    setSpeedY(1);
                    setY(getY() + getSpeedY());
                    setDirection(DOWN_DIR);
                }
                break;

            case 3:
                if(!(getGameWorld().getObjectManager().getObjectAt(getGameWorld().getPlayer().getY() / 32, getGameWorld().getPlayer().getX() / 32) instanceof LayeredEntity)){
                    setSpeedY(-1);
                    setY(getY() + getSpeedY());
                    setDirection(UP_DIR);
                }
                break;
        }



    }
}
