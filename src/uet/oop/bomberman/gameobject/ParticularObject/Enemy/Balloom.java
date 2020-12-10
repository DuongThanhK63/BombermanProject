package uet.oop.bomberman.gameobject.ParticularObject.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.effect.Animation;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.gameobject.GameObject;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.gameobject.ParticularObject.Enemy.ai.AILow;

public class Balloom extends Enemy {
    Animation balloomLeftAnim, balloomRightAnim;
    GameObject balloomLeft, balloomRight;
    private int dir = 0;
    private int timeToChange = 32;

    public Balloom(int x, int y, Image image, GameWorld gameWorld) {
        super(x, y, image, gameWorld);
        balloomLeftAnim = CacheDataLoader.getInstance().getAnimation("balloom_left");
        balloomRightAnim = CacheDataLoader.getInstance().getAnimation("balloom_right");
        balloomLeft = CacheDataLoader.getInstance().getObject("balloom_left");
        balloomRight = CacheDataLoader.getInstance().getObject("balloom_right");
        ai = new AILow();
    }

    public Balloom(Image image) {
        super(image);
    }

    @Override
    public void draw(GraphicsContext gc) {
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
                setSpeedX(1);
                setX(getX() + getSpeedX());
                setDirection(RIGHT_DIR);
                break;
            case 1:
                setSpeedX(-1);
                setX(getX() + getSpeedX());
                setDirection(LEFT_DIR);
                break;

            case 2:
                setSpeedY(1);
                setY(getY() + getSpeedY());
                setDirection(DOWN_DIR);
                break;

            case 3:
                setSpeedY(-1);
                setY(getY() + getSpeedY());
                setDirection(UP_DIR);
                break;
        }



    }
}
