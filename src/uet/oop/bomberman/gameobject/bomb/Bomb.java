package uet.oop.bomberman.gameobject.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.effect.Animation;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.GameWorld;

import uet.oop.bomberman.gameobject.GameObject;
import uet.oop.bomberman.gameobject.LayeredObject;
import uet.oop.bomberman.gameobject.StaticObject.Grass;
import uet.oop.bomberman.gameobject.StaticObject.Wall;
import uet.oop.bomberman.gameobject.StaticObject.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sounds.Sound;

import java.awt.*;

public class Bomb extends GameObject {

    private Animation bomb;
    protected double timeToExplode = 120; //2 seconds
    public int timeAfter = 20; //time to explosions disapear
    protected Flame[] flames;
    protected boolean exploded = false;
    public boolean allowedToPass = true;

    public Bomb(int x, int y, Image img, GameWorld gameWorld, int a) {
        super(x, y, img, a);
        setGameWorld(gameWorld);
        bomb = CacheDataLoader.getInstance().getAnimation("bomb");
        if (gameWorld.getObjectManager().getObjectAt(y, x) instanceof LayeredObject) {
            LayeredObject tmp = (LayeredObject) gameWorld.getObjectManager().getObjectAt(y, x);
            gameWorld.getObjectManager().addObject(y, x,
                    new LayeredObject(x, y, this
                            , new Grass(x, y, Sprite.grass.getFxImage()), tmp.getTopObject()));
        } else {
            gameWorld.getObjectManager().addObject(y, x,
                    new LayeredObject(x, y, this, new Grass(x, y, Sprite.grass.getFxImage())));
        }
    }

    @Override
    public void update() {
        if (timeToExplode > 0){
            timeToExplode--;
            int x1 = getGameWorld().getPlayer().getX();
            int y1 = getGameWorld().getPlayer().getY();
            if (Math.abs(x1 - x * 32) > 22 || Math.abs(y1 - y * 32) > 22) {
                allowedToPass = false;
            }
        } else {
            if (!exploded) {
                explode();
            } else {
                if (timeAfter > 0)
                    timeAfter--;
                else {
                    updateFlames();
                    remove();
                }
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (exploded) {
            img = Sprite.bomb_exploded2.getFxImage();
            renderFlames(gc);
            super.render(gc, getX() * 32, getY() * 32);

        } else {
            bomb.Update(System.nanoTime());
            bomb.draw(gc, getX() * 32, getY() * 32);
        }

    }

    public void renderFlames(GraphicsContext gc) {
//        for (int i = 0; i < flames.length; i++) {
//            flames[i].render(gc);
//        }
    }

    public void updateFlames() {
        for (int i = 0; i < flames.length; i++) {
            flames[i].update();
        }
    }

    /**
     * Xử lý Bomb nổ
     */
    protected void explode() {
        allowedToPass = true;
        exploded = true;
        int leftRadius = 0, rightRadius = 0, upRadius = 0, downRadius = 0;
        for (int i = getX() + 1; i <= getX() + Game.getBombRadius(); i++) {
            if (getGameWorld().getObjectManager().getObjectAt(getY(), i) instanceof LayeredObject) {
                LayeredObject tmp = (LayeredObject) getGameWorld().getObjectManager().getObjectAt(getY(), i);
                if (tmp.getTopObject() instanceof Brick) {
                    Brick b = (Brick) tmp.getTopObject();
                    b.destroy();
                    break;
                } else if (tmp.getBelowTopEntity() instanceof Bomb) {
                    Bomb b = (Bomb) tmp.getBelowTopEntity();
                    if (!b.exploded) {
                        b.explode();
                        b.timeAfter = -100;
                    }
                    break;
                }
            }
            if (!(getGameWorld().getObjectManager().getObjectAt(getY(), i) instanceof Wall))
                rightRadius++;
            else break;
        }
        for (int i = getX() - 1; i >= getX() - Game.getBombRadius(); i--) {
            if (getGameWorld().getObjectManager().getObjectAt(getY(), i) instanceof LayeredObject) {
                LayeredObject tmp = (LayeredObject) getGameWorld().getObjectManager().getObjectAt(getY(), i);
                if (tmp.getTopObject() instanceof Brick) {
                    Brick b = (Brick) tmp.getTopObject();
                    b.destroy();
                    break;
                } else if (tmp.getBelowTopEntity() instanceof Bomb) {
                    Bomb b = (Bomb) tmp.getBelowTopEntity();
                    if (!b.exploded) {
                        b.explode();
                        b.timeAfter = -100;
                    }
                    break;
                }
            }
            if (!(getGameWorld().getObjectManager().getObjectAt(getY(), i) instanceof Wall))
                leftRadius++;
            else break;
        }
        for (int i = getY() + 1; i <= getY() + Game.getBombRadius(); i++) {
            if (getGameWorld().getObjectManager().getObjectAt(i, getX()) instanceof LayeredObject) {
                LayeredObject tmp = (LayeredObject) getGameWorld().getObjectManager().getObjectAt(i, getX());
                if (tmp.getTopObject() instanceof Brick) {
                    Brick b = (Brick) tmp.getTopObject();
                    b.destroy();
                    break;
                } else if (tmp.getBelowTopEntity() instanceof Bomb) {
                    Bomb b = (Bomb) tmp.getBelowTopEntity();
                    if (!b.exploded) {
                        b.explode();
                        b.timeAfter = -100;
                    }
                    break;
                }
            }
            if (!(getGameWorld().getObjectManager().getObjectAt(i, getX()) instanceof Wall))
                downRadius++;
            else break;
        }
        for (int i = getY() - 1; i >= getY() - Game.getBombRadius(); i--) {
            if (getGameWorld().getObjectManager().getObjectAt(i, getX()) instanceof LayeredObject) {
                LayeredObject tmp = (LayeredObject) getGameWorld().getObjectManager().getObjectAt(i, getX());
                if (tmp.getTopObject() instanceof Brick) {
                    Brick b = (Brick) tmp.getTopObject();
                    b.destroy();
                    break;
                } else if (tmp.getBelowTopEntity() instanceof Bomb) {
                    Bomb b = (Bomb) tmp.getBelowTopEntity();
                    if (!b.exploded) {
                        b.explode();
                        b.timeAfter = -100;
                    }
                    break;
                }
            }
            if (!(getGameWorld().getObjectManager().getObjectAt(i, getX()) instanceof Wall))
                upRadius++;
            else break;
        }
        System.out.println(upRadius + " " + downRadius + " " + leftRadius + " "+rightRadius);
        // TODO: xử lý khi Character đứng tại vị trí Bomb

        // TODO: tạo các Flame
        flames = new Flame[4];
        flames[0] = new Flame((int) this.getX(), (int) this.getY(), 0, upRadius, getGameWorld());
        flames[1] = new Flame((int) this.getX(), (int) this.getY(), 1, rightRadius, getGameWorld());
        flames[2] = new Flame((int) this.getX(), (int) this.getY(), 2, downRadius, getGameWorld());
        flames[3] = new Flame((int) this.getX(), (int) this.getY(), 3, leftRadius, getGameWorld());
    }

    public FlameSegment flameAt(int x, int y)
    {
        if (!exploded)
            return null;

        for (int i = 0; i < flames.length; i++)
        {
            if (flames[i] == null)
                return null;
            FlameSegment e = flames[i].flameSegmentAt(x, y);
            if (e != null)
                return e;
        }

        return null;
    }
//
//    @Override
//    public boolean collide(GameObject gameObject) {
//
//        if(gameObject instanceof Player) {
//            double diffX = gameObject.getX();
//            double diffY = gameObject.getY();
//
//            if(!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) { // differences to see if the player has moved out of the bomb, tested values
//                allowedToPass = false;
//            }
//
//            return allowedToPass;
//    }
//
//        if(gameObject instanceof Flame) {
//            explode();
//            return true;
//        }
//
//        return false;
//    }


    //    }
//
    public Bomb(Image image) {
        super(image);
    }

}
