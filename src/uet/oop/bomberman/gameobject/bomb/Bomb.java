package uet.oop.bomberman.gameobject.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.effect.Animation;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.GameWorld;

import uet.oop.bomberman.gameobject.GameObject;
import uet.oop.bomberman.gameobject.LayeredEntity;
import uet.oop.bomberman.gameobject.ParticularObject.Human.Player;
import uet.oop.bomberman.gameobject.StaticObject.Wall;
import uet.oop.bomberman.gameobject.StaticObject.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends GameObject {

    private Animation bomb;
    protected double timeToExplode = 120; //2 seconds
    public int timeAfter = 20; //time to explosions disapear
    protected Flame[] flames;
    protected boolean exploded = false;

    public Bomb(int x, int y, Image img, GameWorld gameWorld, int a) {
        super(x, y, img, a);
        setGameWorld(gameWorld);
        bomb = CacheDataLoader.getInstance().getAnimation("bomb");
//        if (gameWorld.getObjectManager().getEntityAt(x, y) instanceof LayeredEntity) {
//            LayeredEntity tmp = (LayeredEntity) gameWorld.getObjectManager().getEntityAt(x, y);
//            gameWorld.getObjectManager().addEntity(y, x,
//                    new LayeredEntity(x, y, this
//                            , new Grass(x, y, Sprite.grass.getFxImage()), tmp.getTopEntity()));
//        } else {
//            gameWorld.getObjectManager().addEntity(y, x,
//                    new LayeredEntity(x, y, this, new Grass(x, y, Sprite.grass.getFxImage())));
//        }
    }

    @Override
    public void update() {
        if (timeToExplode > 0)
            timeToExplode--;
        else {
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
        exploded = true;
        int leftRadius = 0, rightRadius = 0, upRadius = 0, downRadius = 0;
        for (int i = getX() + 1; i <= getX() + Game.getBombRadius(); i++) {
            if (getGameWorld().getObjectManager().getObjectAt(getY(), i) instanceof LayeredEntity) {
                LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager().getObjectAt(getY(), i);
                if (tmp.getTopEntity() instanceof Brick) {
                    Brick b = (Brick) tmp.getTopEntity();
                    b.destroy();
                    break;
                } else if (tmp.getBelowTopEntity() instanceof Bomb) {
                    Bomb b = (Bomb) tmp.getBelowTopEntity();
                    if (!b.exploded) b.explode();
                    break;
                }
            }
            if (!(getGameWorld().getObjectManager().getObjectAt(getY(), i) instanceof Wall))
                rightRadius++;
            else break;
        }
        for (int i = getX() - 1; i >= getX() - Game.getBombRadius(); i--) {
            if (getGameWorld().getObjectManager().getObjectAt(getY(), i) instanceof LayeredEntity) {
                LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager().getObjectAt(getY(), i);
                if (tmp.getTopEntity() instanceof Brick) {
                    Brick b = (Brick) tmp.getTopEntity();
                    b.destroy();
                    break;
                } else if (tmp.getBelowTopEntity() instanceof Bomb) {
                    Bomb b = (Bomb) tmp.getBelowTopEntity();
                    if (!b.exploded) b.explode();
                    break;
                }
            }
            if (!(getGameWorld().getObjectManager().getObjectAt(getY(), i) instanceof Wall))
                leftRadius++;
            else break;
        }
        for (int i = getY() + 1; i <= getY() + Game.getBombRadius(); i++) {
            if (getGameWorld().getObjectManager().getObjectAt(i, getX()) instanceof LayeredEntity) {
                LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager().getObjectAt(i, getX());
                if (tmp.getTopEntity() instanceof Brick) {
                    Brick b = (Brick) tmp.getTopEntity();
                    b.destroy();
                    break;
                } else if (tmp.getBelowTopEntity() instanceof Bomb) {
                    Bomb b = (Bomb) tmp.getBelowTopEntity();
                    if (!b.exploded) b.explode();
                    break;
                }
            }
            if (!(getGameWorld().getObjectManager().getObjectAt( i, getX()) instanceof Wall))
                downRadius++;
            else break;
        }
        for (int i = getY() - 1; i >= getY() - Game.getBombRadius(); i--) {
            if (getGameWorld().getObjectManager().getObjectAt(i, getX()) instanceof LayeredEntity) {
                LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager().getObjectAt(i, getX());
                if (tmp.getTopEntity() instanceof Brick) {
                    Brick b = (Brick) tmp.getTopEntity();
                    b.destroy();
                    break;
                } else if (tmp.getBelowTopEntity() instanceof Bomb) {
                    Bomb b = (Bomb) tmp.getBelowTopEntity();
                    if (!b.exploded) b.explode();
                    break;
                }
            }
            if (!(getGameWorld().getObjectManager().getObjectAt(i, getX()) instanceof Wall))
                upRadius++;
            else break;
        }
        // TODO: xử lý khi Character đứng tại vị trí Bomb

        // TODO: tạo các Flame
        flames = new Flame[4];
        flames[0] = new Flame((int) this.getX(), (int) this.getY(), 0, upRadius, getGameWorld());
        flames[1] = new Flame((int) this.getX(), (int) this.getY(), 1, rightRadius, getGameWorld());
        flames[2] = new Flame((int) this.getX(), (int) this.getY(), 2, downRadius, getGameWorld());
        flames[3] = new Flame((int) this.getX(), (int) this.getY(), 3, leftRadius, getGameWorld());
//        getGameWorld().getObjectManager().addObject(flames[0]);
//        getGameWorld().getObjectManager().addObject(flames[1]);
//        getGameWorld().getObjectManager().addObject(flames[2]);
//        getGameWorld().getObjectManager().addObject(flames[3]);
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
//    public boolean collide(Entity e)
//    {
//        if (e instanceof FlameSegment || e instanceof Flame)
//        {
//            this.explode();
//            return true;
//        }
//        else if (e instanceof Enemy)
//        {
//            if (e instanceof Ghost) return false;
//            else return true;
//        }
//        else if (!this._allowedToPassThru && e instanceof Bomber)
//            return true;
//        return false;
//    }


    //    }
//
    public Bomb(Image image) {
        super(image);
    }
//
////    public Bomb(int xUnit, int yUnit, GameWorld gameWorld) {
////        super(xUnit, yUnit, gameWorld);
////    }
//
//    protected void explosion() {
////        _allowedToPassThru = true;
//        exploded = true;
//
////        Mob a = _board.getMobAt(_x, _y);
////        if(a != null)  {
////            a.kill();
////        }
//
//        _flames = new Flame[4];
//
//        for (int i = 0; i < _flames.length; i++) {
//            _flames[i] = new DirectionalExplosion((int)_x, (int)_y, i, Game.getBombRadius(), _board);
//        }
//    }
//    @Override
//    public void update() {
//        if(timeToExplode > 0)
//            timeToExplode--;
//        else {
//            if(!exploded)
//                explosion();
//        }
//
//    }
//    public void draw(GraphicsContext gc) {
//        bomb.Update(System.nanoTime());
//        bomb.draw(gc, getX(), getY());
//    }
//
}
