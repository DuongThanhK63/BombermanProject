package uet.oop.bomberman.gameobject.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.gameobject.GameObject;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.gameobject.LayeredEntity;
import uet.oop.bomberman.gameobject.ObjectManager;
import uet.oop.bomberman.gameobject.ParticularObject.Human.Player;
import uet.oop.bomberman.gameobject.ParticularObject.ParticularObject;
import uet.oop.bomberman.gameobject.StaticObject.Grass;
import uet.oop.bomberman.gameobject.StaticObject.Item.FlameItem;
import uet.oop.bomberman.gameobject.StaticObject.Item.Item;
import uet.oop.bomberman.gameobject.StaticObject.Portal;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Flame extends ParticularObject {
    protected int direction;
    private int radius;
    protected int xOrigin, yOrigin;
    protected FlameSegment[] flameSegments;
    private ObjectManager flameManager;

    public Flame(int x, int y, int direction, int radius, GameWorld gameWorld) {
        super(x, y, gameWorld);
        xOrigin = x;
        yOrigin = y;
        this.direction = direction;
        this.radius = radius;
        setTeamType(LEAGUE_TEAM);
        createFlameSegments();
        flameManager = new ObjectManager(gameWorld);
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
                    if (getGameWorld().getObjectManager().getObjectAt(flameSegments[i].getY(), flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getObjectAt((int) flameSegments[i].getY(), (int) flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addObject(
                            flameSegments[i].getY(), flameSegments[i].getX(), flameSegments[i]);
                } else {
                    flameSegments[i] = new FlameSegment(getX(), getY() - i - 1, direction,
                            false);
                    if (getGameWorld().getObjectManager().getObjectAt(flameSegments[i].getY(), flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getObjectAt(flameSegments[i].getY(), flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addObject(
                            flameSegments[i].getY(), flameSegments[i].getX(), flameSegments[i]);
                }

            }
        }
        if (direction == 1) // Right
        {
            for (int i = 0; i < flameSegments.length; i++) {

                if (i == flameSegments.length - 1) {
                    flameSegments[i] = new FlameSegment(getX() + i + 1, getY(), direction,
                            true);
                    if (getGameWorld().getObjectManager().getObjectAt(flameSegments[i].getY(), flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getObjectAt(flameSegments[i].getY(), flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addObject(
                            flameSegments[i].getY(), flameSegments[i].getX(), flameSegments[i]);
                } else {
                    flameSegments[i] = new FlameSegment(getX() + i + 1, getY(), direction,
                            false);
                    if (getGameWorld().getObjectManager().getObjectAt(flameSegments[i].getY(), flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getObjectAt(flameSegments[i].getY(), flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addObject(
                            flameSegments[i].getY(), flameSegments[i].getX(), flameSegments[i]);
                }
            }
        }
        if (direction == 2) // Down
        {
            for (int i = 0; i < flameSegments.length; i++) {

                if (i == flameSegments.length - 1) {
                    flameSegments[i] = new FlameSegment((int) this.getX(), (int) this.getY() + i + 1, direction,
                            true);
                    if (getGameWorld().getObjectManager().getObjectAt(flameSegments[i].getY(), flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getObjectAt(flameSegments[i].getY(), flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addObject(
                            flameSegments[i].getY(), flameSegments[i].getX(), flameSegments[i]);
                } else {
                    flameSegments[i] = new FlameSegment((int) this.getX(), (int) this.getY() + i + 1, direction,
                            false);
                    if (getGameWorld().getObjectManager().getObjectAt(flameSegments[i].getY(), flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getObjectAt(flameSegments[i].getY(), flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addObject(
                            flameSegments[i].getY(), flameSegments[i].getX(), flameSegments[i]);
                }

            }
        }
        if (direction == 3) // Left
        {
            for (int i = 0; i < flameSegments.length; i++) {
                if (i == flameSegments.length - 1) {
                    flameSegments[i] = new FlameSegment((int) this.getX() - i - 1, (int) this.getY(), direction,
                            true);
                    if (getGameWorld().getObjectManager().getObjectAt(flameSegments[i].getY(), flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getObjectAt(flameSegments[i].getY(), flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addObject(
                            flameSegments[i].getY(), flameSegments[i].getX(), flameSegments[i]);
                } else {
                    flameSegments[i] = new FlameSegment((int) this.getX() - i - 1, (int) this.getY(), direction,
                            false);
                    if (getGameWorld().getObjectManager().getObjectAt(flameSegments[i].getY(), flameSegments[i].getX()) instanceof LayeredEntity) {
                        LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                                .getObjectAt(flameSegments[i].getY(), flameSegments[i].getX());
                        tmp.addToTop(flameSegments[i]);
                    } else getGameWorld().getObjectManager().addObject(
                            flameSegments[i].getY(), flameSegments[i].getX(), flameSegments[i]);
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


//        for(int i = 0; i < getGameWorld().getObjectManager().getParticularObjects().size(); i++){
//            for (int j = 0; j < flameSegments.length; j++) {
//                Rectangle r = flameSegments[j].getBoundForCollisionWithEnemy();
//                r.x *= 32;
//                r.y *= 32;
//                if (r.intersects(getGameWorld().getObjectManager().getParticularObjects().get(i).getBoundForCollisionWithEnemy())) {
//                    getGameWorld().getObjectManager().getParticularObjects().get(i).setState(DEATH);
//                }
//                if (r.intersects(getGameWorld().getPlayer().getBoundForCollisionWithEnemy())) {
//                    getGameWorld().getPlayer().setState(ParticularObject.DEATH);
//                }
//            }
//
//        }



        // Xóa vết lửa sau khi bom nổ
        for (int i = 0; i < flameSegments.length; i++) {
            if (getGameWorld().getObjectManager()
                    .getObjectAt(flameSegments[i].getY(), flameSegments[i].getX()) instanceof LayeredEntity) {
                LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager()
                        .getObjectAt(flameSegments[i].getY(), flameSegments[i].getX());
                if (tmp.getTopEntity() instanceof FlameSegment) {
                    while (!(tmp.getTopEntity() instanceof Grass) && !(tmp.getTopEntity() instanceof Item)
                            && !(tmp.getTopEntity() instanceof Portal)) {
                        tmp.getTopEntity().remove();
                        tmp.update();
                    }
                } else if (tmp.getTopEntity() instanceof Grass) {
                    getGameWorld().getObjectManager().addObject(flameSegments[i].getY(), flameSegments[i].getX()
                            , new Grass(flameSegments[i].getX(), (int) flameSegments[i].getY(),
                            Sprite.grass.getFxImage()));
                }
            } else if (getGameWorld().getObjectManager()
                    .getObjectAt(flameSegments[i].getY(), flameSegments[i].getX()) instanceof FlameSegment) {
                getGameWorld().getObjectManager().addObject(flameSegments[i].getY(), flameSegments[i].getX()
                        , new Grass(flameSegments[i].getX(), flameSegments[i].getY(), Sprite.grass.getFxImage()));
            }
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < flameSegments.length; i++) {
            flameSegments[i].render(gc);
        }
    }

}
