package uet.oop.bomberman.entities.ParticularObject.Human;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.effect.Animation;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.entities.GameObject;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.StaticObject.Grass;
import uet.oop.bomberman.entities.StaticObject.Portal;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;


public class Player extends Human {

    private Animation playerRightAnim, playerLeftAnim, playerUpAnim, playerDownAnim, playerDeadAnim;
    private GameObject playerRight, playerLeft, playerUp, playerDown;
    private List<Bomb> bombs;

    public Player(int x, int y, Image img, GameWorld gameWorld) {
        super(x, y, img, gameWorld);
        setState(ALIVE);
        bombs = getGameWorld().getObjectManager().getBombs();
        playerLeftAnim = CacheDataLoader.getInstance().getAnimation("player_left");
        playerRightAnim = CacheDataLoader.getInstance().getAnimation("player_right");
        playerUpAnim = CacheDataLoader.getInstance().getAnimation("player_up");
        playerDownAnim = CacheDataLoader.getInstance().getAnimation("player_down");
        playerDeadAnim = CacheDataLoader.getInstance().getAnimation("player_dead");

        playerRight = CacheDataLoader.getInstance().getEntity("player_right");
        playerLeft = CacheDataLoader.getInstance().getEntity("player_left");
        playerUp = CacheDataLoader.getInstance().getEntity("player_up");
        playerDown = CacheDataLoader.getInstance().getEntity("player_down");
    }

    public Player(Image img) {
        super(img);
        setState(ALIVE);
    }

    public void draw(GraphicsContext gc) {
        if (getState() == ALIVE) {
            if (getSpeedX() > 0) {
                playerRightAnim.Update(System.nanoTime());
                playerRightAnim.draw(gc, getX(), getY());
            } else if (getSpeedX() < 0) {
                playerLeftAnim.Update(System.nanoTime());
                playerLeftAnim.draw(gc, getX(), getY());
            } else if (getSpeedY() > 0) {
                playerDownAnim.Update(System.nanoTime());
                playerDownAnim.draw(gc, getX(), getY());
            } else if (getSpeedY() < 0) {
                playerUpAnim.Update(System.nanoTime());
                playerUpAnim.draw(gc, getX(), getY());
            } else {
                if (getDirection() == RIGHT_DIR) {
                    playerRight.render(gc, getX(), getY());
                } else if (getDirection() == LEFT_DIR) {
                    playerLeft.render(gc, getX(), getY());
                } else if (getDirection() == UP_DIR) {
                    playerUp.render(gc, getX(), getY());
                } else if (getDirection() == DOWN_DIR) {
                    playerDown.render(gc, getX(), getY());
                }
            }
        } else {
            playerDeadAnim.Update(System.nanoTime());
            playerDeadAnim.draw(gc, getX(), getY());
        }
    }

    @Override
    public void update() {
        super.update();
        setY(getY() + getSpeedY());
        setX(getX() + getSpeedX());
        if (getDirection() == RIGHT_DIR &&
                getGameWorld().getMap().haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null) {
            Rectangle rect = getGameWorld().getMap().haveCollisionWithRightWall(getBoundForCollisionWithMap());
            if (rect.width < 32) {
                setY(rect.y + 32);
            } else if (rect.width > 32) {
                setY(rect.y - 32);
            }
            setX(rect.x - 22);
        }
        if (getDirection() == LEFT_DIR &&
                getGameWorld().getMap().haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null) {
            Rectangle rect = getGameWorld().getMap().haveCollisionWithLeftWall(getBoundForCollisionWithMap());
            if (rect.width < 32) {
                setY(rect.y + 32);
            } else if (rect.width > 32) {
                setY(rect.y - 32);
            }
            setX(rect.x + 32);
        }
        if (getDirection() == UP_DIR &&
                getGameWorld().getMap().haveCollisionWithTop(getBoundForCollisionWithMap()) != null) {
            Rectangle rect = getGameWorld().getMap().haveCollisionWithTop(getBoundForCollisionWithMap());
            if (rect.width < 32) {
                setX(rect.x - 22);
            } else if (rect.width > 32) {
                setX(rect.x + 32);
            }
            setY(rect.y + 32);
        }
        if (getDirection() == DOWN_DIR &&
                getGameWorld().getMap().haveCollisionWithLand(getBoundForCollisionWithMap()) != null) {
            Rectangle rect = getGameWorld().getMap().haveCollisionWithLand(getBoundForCollisionWithMap());
            if (rect.width < 32) {
                setX(rect.x - 22);
            } else if (rect.width > 32) {
                setX(rect.x + 32);
            }
            setY(rect.y - 32);
        }
//        clearBombs();
    }

//    public void detectPlaceBomb() {
//        if(Game.getBombRate() > 0 ) {
//            placeBomb(getX(),getY());
//            Game.setBombRate(-1);
//        }
//    }

//    public void placeBomb() {
//        if (Game.getBombRate() > 0) {
//            Bomb bomb;
//            System.out.println(getX() + " " + getY());
//            bomb = new Bomb(this.x,this.y, Sprite.bomb.getFxImage(), getGameWorld(), 1);
//            getGameWorld().getObjectManager().addBomb(bomb);
//            Game.setBombRate(-1);
//        }
//    }
//    private void clearBombs()
//    {
//        Iterator<Bomb> bs = bombs.iterator();
//
//       Bomb b;
//        while (bs.hasNext())
//        {
//            b = bs.next();
//            if (b.isRemoved())
//            {
//                bs.remove();
//                Game.setBombRate(1);
//                if (getGameWorld().getObjectManager().getEntityAt(b.getY() - 1, b.getX()) instanceof LayeredEntity)
//                {
//                    LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
//                            .getEntityAt(b.getY() - 1, b.getX());
//                    if (tmp.getTopEntity() instanceof Portal)
//                    {
//
//                   }
//               }
//                else
//                {
//                    getGameWorld().getObjectManager().addEntity(b.getY() - 1, b.getX()
//                            , new Grass(b.getX(), b.getY(), Sprite.grass.getFxImage()));
//                }
//            }
//        }
//
//    }


}
