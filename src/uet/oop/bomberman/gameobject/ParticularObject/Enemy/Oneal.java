package uet.oop.bomberman.gameobject.ParticularObject.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.effect.Animation;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.gameobject.GameObject;
import uet.oop.bomberman.gameobject.ParticularObject.Enemy.ai.AIMedium;
import uet.oop.bomberman.gameobject.ParticularObject.ParticularObject;

public class Oneal extends Enemy{

    Animation onealLeftAnim, onealRightAnim, onealDieAnim;
    GameObject onealLeft, onealRight;
    private int dir;
    private int timeToChange = 32;

    public Oneal(int x, int y, Image image, GameWorld gameWorld) {
        super(x, y, image, gameWorld);
        ai = new AIMedium(getGameWorld().getPlayer(),this);
        dir = ai.calculateDirection();
        setState(ALIVE);
        onealLeftAnim = CacheDataLoader.getInstance().getAnimation("oneal_left");
        onealRightAnim = CacheDataLoader.getInstance().getAnimation("oneal_right");
        onealDieAnim = CacheDataLoader.getInstance().getAnimation("oneal_die");
        onealLeft = CacheDataLoader.getInstance().getObject("oneal_left");
        onealRight = CacheDataLoader.getInstance().getObject("oneal_right");
    }

    public Oneal(Image image) {
        super(image);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (getState() == ALIVE) {
            switch (dir){
                case 0:
                case 2:
                    if(getSpeedX() > 0 || getSpeedY() < 0){
                        onealRightAnim.Update(System.nanoTime());
                        onealRightAnim.draw(gc,getX(),getY());
                    } else {
                        onealLeftAnim.Update(System.nanoTime());
                        onealLeftAnim.draw(gc,getX(),getY());
                    }
                    break;
                case 1:
                case 3:
                    if(getSpeedX() < 0 || getSpeedY() > 0){
                        onealLeftAnim.Update(System.nanoTime());
                        onealLeftAnim.draw(gc,getX(),getY());
                    } else {
                        onealRightAnim.Update(System.nanoTime());
                        onealRightAnim.draw(gc,getX(),getY());
                    }
                    break;
            }
        } else if(getState() == DEATH){
            onealDieAnim.Update(System.nanoTime());
            onealDieAnim.draw(gc,getX(),getY());
        }
    }

    @Override
    public void update() {
        if(getState() == ALIVE){
            super.update();
            timeToChange--;
            if (timeToChange == 0) {
                dir = ai.calculateDirection();
                timeToChange = 32;
            }
            if(this.getX() < getGameWorld().getPlayer().getX()){
                setDirection(RIGHT_DIR);
            }
            if(this.getX() > getGameWorld().getPlayer().getX()){
                setDirection(LEFT_DIR);
            }
            if(this.getY() < getGameWorld().getPlayer().getY()){
                setDirection(DOWN_DIR);
            }
            if(this.getY() > getGameWorld().getPlayer().getY()){
                setDirection(UP_DIR);
            }

            switch (getDirection()) {
                case 1:
                    setSpeedX(1);
                    setX(getX() + getSpeedX());
                    setDirection(RIGHT_DIR);
                    break;
                case 3:
                    setSpeedX(-1);
                    setX(getX() + getSpeedX());
                    setDirection(LEFT_DIR);
                    break;

                case 2:
                    setSpeedY(1);
                    setY(getY() + getSpeedY());
                    setDirection(DOWN_DIR);
                    break;

                case 0:
                    setSpeedY(-1);
                    setY(getY() + getSpeedY());
                    setDirection(UP_DIR);

                    break;
            }
        }
    }
}
