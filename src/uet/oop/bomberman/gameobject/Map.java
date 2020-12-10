package uet.oop.bomberman.gameobject;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.gameobject.ParticularObject.Enemy.Balloom;
import uet.oop.bomberman.gameobject.ParticularObject.Human.Player;
import uet.oop.bomberman.gameobject.StaticObject.destroyable.Brick;
import uet.oop.bomberman.gameobject.StaticObject.Grass;
import uet.oop.bomberman.gameobject.StaticObject.Item.BombItem;
import uet.oop.bomberman.gameobject.StaticObject.Item.FlameItem;
import uet.oop.bomberman.gameobject.StaticObject.Item.SpeedItem;
import uet.oop.bomberman.gameobject.StaticObject.Portal;
import uet.oop.bomberman.gameobject.StaticObject.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Map extends GameObject {
    public int[][] map;
    private int tileSize;
    GameObject wall, brick, grass;

    public Map(int x, int y, GameWorld gameWorld) {
        super(x, y, gameWorld);
        this.tileSize = 32;
        map = CacheDataLoader.getInstance().getBackgroundMap();
    }

    public void setObjects() {
        int width = 31;
        int height = 13;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (map[y][x]) {
                    case '#': // Wall
                    {
                        getGameWorld().getObjectManager()
                                .addObject(y, x, new Wall(x + getX(), y + getY(), Sprite.wall.getFxImage()));
                        break;
                    }
                    case '*': // Brick
                    {
                        getGameWorld().getObjectManager().addObject(y, x,
                                new LayeredEntity(x + getX(), y + getY()
                                        , new Grass(x + getX(), y + getY(), Sprite.grass.getFxImage())
                                        , new Brick(x + getX(), y + getY(), Sprite.brick.getFxImage())));
                        break;
                    }
                    case 'x': // Portal
                    {
                        getGameWorld().getObjectManager().addObject(y, x, new LayeredEntity(x, y
                                , new Grass(x + getX(), y + getY(), Sprite.grass.getFxImage()),
                                new Portal(x + getX(), y + getY(), Sprite.portal.getFxImage(), getGameWorld())
                                , new Brick(x + getX(), y + getY(), Sprite.brick.getFxImage())));
                        break;
                    }
                    case 'p': // Bomber
                    {
                        getGameWorld().getObjectManager()
                                .addObject(new Player(x + getX(), y + getY()
                                        , Sprite.player_right.getFxImage(), getGameWorld()));

                        getGameWorld().getObjectManager().addObject(y, x
                                , new Grass(x + getX(), y + getY(), Sprite.grass.getFxImage()));
                        break;
                    }
                    case '1': // Balloon
                    {
                        getGameWorld().getObjectManager()
                                .addObject(new Balloom(x + getX(), y + getY()
                                        , Sprite.balloom_right1.getFxImage(), getGameWorld()));
                        getGameWorld().getObjectManager().addObject(y, x
                                , new Grass(x + getX(), y + getY(), Sprite.grass.getFxImage()));
                        break;
                    }
//                    case '2': // Oneal
//                    {
//                        _board.addCharacter(new Oneal(Coordinates.tileToPixel(x),
//                                Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//                        getGameWorld().getObjectManager().addEntity(y, x
//                                , new Grass(x + getX(), y + getY(), Sprite.grass.getFxImage()));
//                        break;
//                    }
                    case 'b': // Bomb item
                    {
                        getGameWorld().getObjectManager().addObject(y , x
                                , new LayeredEntity(x + getX(), y + getY(), new Grass(x + getX(), y + getY()
                                        , Sprite.grass.getFxImage()),
                                new BombItem(x + getX(), y + getY()
                                        , Sprite.powerup_bombs.getFxImage(), getGameWorld())
                                        , new Brick(x + getX(), y + getY(), Sprite.brick.getFxImage())));
                        break;
                    }
                    case 'f': // Flame item
                    {
                        getGameWorld().getObjectManager().addObject(y, x
                                ,  new LayeredEntity(x + getX(), y + getY()
                                        , new Grass(x + getX(), y + getY(), Sprite.grass.getFxImage()),
                                new FlameItem(x + getX(), y + getY()
                                        , Sprite.powerup_flames.getFxImage(), getGameWorld())
                                        , new Brick(x + getX(), y + getY(), Sprite.brick.getFxImage())));
                        break;
                    }
                    case 's': // Speed item
                    {
                        getGameWorld().getObjectManager().addObject(y, x
                                , new LayeredEntity(x + getX(), y + getY()
                                        , new Grass(x + getX(), y + getY(), Sprite.grass.getFxImage()),
                                new SpeedItem(x + getX(), y + getY(), Sprite.powerup_speed.getFxImage(), getGameWorld())
                                        , new Brick(x + getX(), y + getY(), Sprite.brick.getFxImage())));
                        break;
                    }
                    default: // Grass
                    {
                        getGameWorld().getObjectManager().addObject(y, x, new Grass(x + getX(), y + getY(), Sprite.grass.getFxImage()));
                    }
                }
            }
        }
    }


    @Override
    public void update() {

    }

    public void draw(GraphicsContext gc) {
        wall = CacheDataLoader.getInstance().getObject("wall");
        brick = CacheDataLoader.getInstance().getObject("brick");
        grass = CacheDataLoader.getInstance().getObject("grass");

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                if (map[i][j] == '#') {
                    wall.render(gc, (getX() + j) * tileSize, (getY() + i) * tileSize);
                } else if (map[i][j] == '*') {
                    brick.render(gc, (getX() + j) * tileSize, (getY() + i) * tileSize);
                } else if (map[i][j] == '1') {
                    grass.render(gc, (getX() + j) * tileSize, (getY() + i) * tileSize);
                } else {
                    grass.render(gc, (getX() + j) * tileSize, (getY() + i) * tileSize);
                }
    }

    public Rectangle haveCollisionWithLand(Rectangle rect) {

        int posX1 = rect.x / tileSize - getX();
        posX1 -= 2;
        int posX2 = (rect.x + rect.width) / tileSize - getX();
        posX2 += 2;

        int posY1 = (rect.y + rect.height) / tileSize - getY();

        if (posX1 < 0) posX1 = 0;

        if (posX2 >= map[0].length) posX2 = map[0].length - 1;
        for (int j = posY1; j < map.length; j++) {
            for (int i = posX1; i <= posX2; i++) {
                GameObject e = getGameWorld().getObjectManager().getObjectAt(j, i);
                if (e instanceof Wall
                        || (e instanceof LayeredEntity && ((LayeredEntity)e).getTopEntity() instanceof Brick)) {

                    Rectangle r = new Rectangle((getX() + i) * tileSize, (getY() + j) * tileSize, tileSize, tileSize);
                    if (rect.intersects(r)) {
                        if (rect.x > r.x) {
                            rect.x += 8;
                            if (!rect.intersects(r)) {
                                r.width = 35;
                            }
                        }
                        if (rect.x < r.x) {
                            rect.x -= 8;
                            if (!rect.intersects(r)) {
                                r.width = 30;
                            }
                        }
                        return r;
                    }
                }
            }
        }
        return null;

    }

    public Rectangle haveCollisionWithTop(Rectangle rect) {

        int posX1 = rect.x / tileSize - getX();
        posX1 -= 2;
        int posX2 = (rect.x + rect.width) / tileSize - getX();
        posX2 += 2;

        //int posY = (rect.y + rect.height)/tileSize;
        int posY = rect.y / tileSize - getY();

        if (posX1 < 0) posX1 = 0;

        if (posX2 >= map[0].length) posX2 = map[0].length - 1;

        for (int j = posY; j >= 0; j--) {
            for (int i = posX1; i <= posX2; i++) {
                GameObject e = getGameWorld().getObjectManager().getObjectAt(j, i);
                if (e instanceof Wall
                        || (e instanceof LayeredEntity && ((LayeredEntity)e).getTopEntity() instanceof Brick)) {
                    Rectangle r = new Rectangle((getX() + i) * tileSize, (getY() + j) * tileSize, tileSize, tileSize);
                    if (rect.intersects(r)) {
                        if (rect.x > r.x) {
                            rect.x += 8;
                            if (!rect.intersects(r)) {
                                r.width = 35;
                            }
                        }
                        if (rect.x < r.x) {
                            rect.x -= 8;
                            if (!rect.intersects(r)) {
                                r.width = 30;
                            }
                        }
                        return r;
                    }
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithRightWall(Rectangle rect) {


        int posY1 = rect.y / tileSize - getY();
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / tileSize - getY();
        posY2 += 2;

        int posX1 = (rect.x + rect.width) / tileSize - getX();
        int posX2 = posX1 + 3 - getX();
        if (posX2 >= map[0].length) posX2 = map[0].length - 1;

        if (posY1 < 0) posY1 = 0;
        if (posY2 >= map.length) posY2 = map.length - 1;


        for (int j = posX1; j <= posX2; j++) {
            for (int i = posY1; i <= posY2; i++) {
                GameObject e = getGameWorld().getObjectManager().getObjectAt(i, j);
                if (e instanceof Wall
                        || (e instanceof LayeredEntity && ((LayeredEntity)e).getTopEntity() instanceof Brick)) {
                    Rectangle r = new Rectangle((getX() + j) * tileSize, (getY() + i) * tileSize, tileSize, tileSize);
                    if (r.y < rect.y + rect.height - 1 && rect.intersects(r)) {
                        if (rect.y > r.y) {
                            rect.y += 8;
                            if (!rect.intersects(r)) {
                                r.width = 30;
                            }
                        }
                        if (rect.y < r.y) {
                            rect.y -= 8;
                            if (!rect.intersects(r)) {
                                r.width = 35;
                            }
                        }
                        return r;
                    }
                }
            }
        }
        return null;

    }

    public Rectangle haveCollisionWithLeftWall(Rectangle rect) {


        int posY1 = rect.y / tileSize - getY();
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / tileSize - getY();
        posY2 += 2;

        int posX1 = (rect.x + rect.width) / tileSize - getX();
        int posX2 = posX1 - 3 - getX();
        if (posX2 < 0) posX2 = 0;

        if (posY1 < 0) posY1 = 0;
        if (posY2 >= map.length) posY2 = map.length - 1;


        for (int j = posX1; j >= posX2; j--) {
            for (int i = posY1; i <= posY2; i++) {
                GameObject e = getGameWorld().getObjectManager().getObjectAt(i, j);
                if (e instanceof Wall
                        || (e instanceof LayeredEntity && ((LayeredEntity)e).getTopEntity() instanceof Brick)) {
                    Rectangle r = new Rectangle((getX() + j) * tileSize, (getY() + i) * tileSize, tileSize, tileSize);
                    if (r.y < rect.y + rect.height - 1 && rect.intersects(r)) {
                        if (rect.y > r.y) {
                            rect.y += 8;
                            if (!rect.intersects(r)) {
                                r.width = 30;
                            }
                        }
                        if (rect.y < r.y) {
                            rect.y -= 8;
                            if (!rect.intersects(r)) {
                                r.width = 35;
                            }
                        }
                        return r;
                    }
                }
            }
        }
        return null;

    }
}
