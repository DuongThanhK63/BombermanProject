package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.GameObject;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.StaticObject.Grass;
import uet.oop.bomberman.entities.StaticObject.Item.Item;
import uet.oop.bomberman.entities.StaticObject.Portal;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Flame extends GameObject {
    protected int direction;
    private int radius;
    protected int xOrigin, yOrigin;
    protected FlameSegment[] flameSegments;

    public Flame(int x, int y, int direction, int radius, GameWorld gameWorld) {
        super(x, y, gameWorld);
        xOrigin = x;
        yOrigin = y;
        this.direction = direction;
        this.radius = radius;
        createFlameSegments();
    }

    private void createFlameSegments() {
        /**
         * tính toán độ dài Flame, tương ứng với số lượng segment
         */
        flameSegments = new FlameSegment[calculatePermitedDistance()];

        /**
         * biến last dùng để đánh dấu cho segment cuối cùng
         */

        // TODO: tạo các segment dưới đây
        if (direction == 0) // Up
        {
            for (int i = 0; i < flameSegments.length; i++) {

                if (i == flameSegments.length - 1) {
                    flameSegments[i] = new FlameSegment(getX(), getY()- i - 1, direction,
                            true);
                    if (getGameWorld().getObjectManager().getEntityAt(flameSegments[i].getY() - 1,
                            flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getEntityAt((int) flameSegments[i].getY() - 1,
                                (int) flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addEntity(
                            flameSegments[i].getY() - 1, flameSegments[i].getX(), flameSegments[i]);
                } else {
                    flameSegments[i] = new FlameSegment(getX(), getY() - i - 1, direction,
                            false);
                    if (getGameWorld().getObjectManager().getEntityAt(flameSegments[i].getY() - 1,
                            flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getEntityAt(flameSegments[i].getY() - 1, flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addEntity(
                            flameSegments[i].getY() - 1, flameSegments[i].getX(), flameSegments[i]);
                }

            }
        }
        if (direction == 1) // Right
        {
            for (int i = 0; i < flameSegments.length; i++) {

                if (i == flameSegments.length - 1) {
                    flameSegments[i] = new FlameSegment(getX() + i + 1, getY(), direction,
                            true);
                    if (getGameWorld().getObjectManager().getEntityAt(flameSegments[i].getY() - 1,
                            flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getEntityAt(flameSegments[i].getY() - 1, flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addEntity(
                            flameSegments[i].getY() - 1, flameSegments[i].getX(), flameSegments[i]);
                } else {
                    flameSegments[i] = new FlameSegment(getX() + i + 1, getY(), direction,
                            false);
                    if (getGameWorld().getObjectManager().getEntityAt(flameSegments[i].getY() - 1,
                            flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getEntityAt(flameSegments[i].getY() - 1, flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addEntity(
                            flameSegments[i].getY() -1, flameSegments[i].getX(), flameSegments[i]);
                }
            }
        }
        if (direction == 2) // Down
        {
            for (int i = 0; i < flameSegments.length; i++) {

                if (i == flameSegments.length - 1) {
                    flameSegments[i] = new FlameSegment((int) this.getX(), (int) this.getY() + i + 1, direction,
                            true);
                    if (getGameWorld().getObjectManager().getEntityAt(flameSegments[i].getY() - 1,
                            flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getEntityAt(flameSegments[i].getY() - 1, flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addEntity(
                            flameSegments[i].getY() - 1, flameSegments[i].getX(), flameSegments[i]);
                } else {
                    flameSegments[i] = new FlameSegment((int) this.getX(), (int) this.getY() + i + 1, direction,
                            false);
                    if (getGameWorld().getObjectManager().getEntityAt(flameSegments[i].getY() - 1,
                            flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getEntityAt(flameSegments[i].getY() - 1, flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addEntity(
                            flameSegments[i].getY() -1, flameSegments[i].getX(), flameSegments[i]);
                }

            }
        }
        if (direction == 3) // Left
        {
            for (int i = 0; i < flameSegments.length; i++) {
                if (i == flameSegments.length - 1) {
                    flameSegments[i] = new FlameSegment((int) this.getX() - i - 1, (int) this.getY(), direction,
                            true);
                    if (getGameWorld().getObjectManager().getEntityAt(flameSegments[i].getY() - 1,
                            flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getEntityAt(flameSegments[i].getY() - 1, flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addEntity(
                            flameSegments[i].getY() - 1, flameSegments[i].getX(), flameSegments[i]);
                } else {
                    flameSegments[i] = new FlameSegment((int) this.getX() - i - 1, (int) this.getY(), direction,
                            false);
                    if (getGameWorld().getObjectManager().getEntityAt(flameSegments[i].getY() - 1,
                            flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getEntityAt(flameSegments[i].getY() - 1, flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addEntity(
                            flameSegments[i].getY() - 1, flameSegments[i].getX(), flameSegments[i]);
                }
            }
        }
    }

    /**
     * Tính toán độ dài của Flame, nếu gặp vật cản là Brick/Wall, độ dài sẽ bị cắt
     * ngắn
     *
     * @return
     */
    private int calculatePermitedDistance() {
        return radius;
    }

    public FlameSegment flameSegmentAt(int x, int y) {
        for (int i = 0; i < flameSegments.length; i++) {
            if (flameSegments[i].getX() == x && flameSegments[i].getY() == y)
                return flameSegments[i];
        }
        return null;
    }

    @Override
    public void update() {
        //Kiểm tra va chạm của bom, lửa với các entity khác
        Rectangle rect = this.getBoundForCollisionWithEnemy();
        rect.x *= 32;
        rect.y *= 32;
//        System.out.println(rect.x + " " + rect.y + "a");
//        System.out.println(getGameWorld().getPlayer().getBoundForCollisionWithMap().x
//                + " "+ getGameWorld().getPlayer().getBoundForCollisionWithMap().y + "b");
        if (rect.intersects(getGameWorld().getPlayer().getBoundForCollisionWithMap())) {
            System.out.println("a");
        }

//        for (int i = 0; i < flameSegments.length; i++) {
//            if (_board.getBombAt(_flameSegments[i].getX(), _flameSegments[i].getY()) != null)
//                _board.getBombAt(_flameSegments[i].getX(), _flameSegments[i].getY()).collide(this);
//        }
        // Xóa vết lửa sau khi bom nổ
        for (int i = 0; i < flameSegments.length; i++) {
            if (getGameWorld().getObjectManager()
                    .getEntityAt(flameSegments[i].getY() - 1, flameSegments[i].getX()) instanceof LayeredEntity) {
                LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                        .getEntityAt(flameSegments[i].getY() - 1, flameSegments[i].getX());
                if (tmp.getTopEntity() instanceof FlameSegment) {
                    while (!(tmp.getTopEntity() instanceof Grass) && !(tmp.getTopEntity() instanceof Item)
                            && !(tmp.getTopEntity() instanceof Portal)) {
                        tmp.getTopEntity().remove();
                        tmp.update();
                    }
                } else if (tmp.getTopEntity() instanceof Grass) {
                    getGameWorld().getObjectManager().addEntity(flameSegments[i].getY() - 1, flameSegments[i].getX()
                            , new Grass(flameSegments[i].getX(), (int) flameSegments[i].getY(),
                            Sprite.grass.getFxImage()));
                }
            } else if (getGameWorld().getObjectManager()
                    .getEntityAt(flameSegments[i].getY() - 1, flameSegments[i].getX()) instanceof FlameSegment) {
                getGameWorld().getObjectManager().addEntity(flameSegments[i].getY() - 1, flameSegments[i].getX()
                        , new Grass(flameSegments[i].getX(), flameSegments[i].getY(), Sprite.grass.getFxImage()));
            }
        }
        //Xóa entity ở vị trí đặt bom
//        if (_board.getEntityAt(this._x, this._y) instanceof LayeredEntity) {
//            LayeredEntity tmp = (LayeredEntity) _board.getEntityAt(this._x, this._y);
//            if (tmp.getTopEntity() instanceof Portal) {
//                _board.addEntity((int) (this._x + this._y * _board.getLevel().getWidth()), new LayeredEntity((int) this._x, (int) this._y, new Grass((int) this._x, (int) this._y, Sprite.grass), new Portal((int) this._x, (int) this._y, Sprite.portal, _board)));
//            } else {
//                _board.addEntity((int) (this._x + this._y * _board.getLevel().getWidth()), new Grass((int) this._x, (int) this._y, Sprite.grass));
//            }
//        }
//        for (int i = 0; i < _board._entities.length; i++) {
//            if (_board._entities[i] instanceof FlameSegment) {
//                for (int j = 0; j < _board._characters.size(); j++) {
//                    if (_board._entities[i].getBounds().intersects(_board._characters.get(j).getBounds())) {
//                        _board._entities[i].collide(_board._characters.get(j));
//                    }
//                }
//                _board.addEntity(i, new Grass((int) _board._entities[i].getX(), (int) _board._entities[i].getY(), Sprite.grass));
//            }
//        }
    }


    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < flameSegments.length; i++) {
            flameSegments[i].render(gc);
        }
    }

//    @Override
//    public boolean collide(Entity e) {
//        if (e instanceof Character) {
//            ((Character) e).kill();
//            return true;
//        }
//        return false;
//    }
}
