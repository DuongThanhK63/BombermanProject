package uet.oop.bomberman.gameobject.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.gameobject.GameObject;
import uet.oop.bomberman.graphics.Sprite;

public class FlameSegment extends GameObject {

    GameObject grass;
    protected boolean last;

    /**
     * @param x
     * @param y
     * @param direction
     * @param last      cho biết segment này là cuối cùng của Flame hay không,
     *                  segment cuối có sprite khác so với các segment còn lại
     */
    public FlameSegment(int x, int y, int direction, boolean last) {
        setX(x);
        setY(y);
        last = last;
        grass = CacheDataLoader.getInstance().getObject("grass");
        switch (direction) {
            case 0:
                if (!last) {
                     img = Sprite.explosion_vertical2.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_top_last2.getFxImage();
                }
                break;
            case 1:
                if (!last) {
                    img = Sprite.explosion_horizontal2.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal_right_last2.getFxImage();
                }
                break;
            case 2:
                if (!last) {
                    img = Sprite.explosion_vertical2.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_down_last2.getFxImage();
                }
                break;
            case 3:
                if (!last) {
                    img = Sprite.explosion_horizontal2.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal_left_last2.getFxImage();
                }
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        grass.render(gc, getX() * 32, getY() * 32);
        super.render(gc, getX() * 32, getY() * 32);
    }

    @Override
    public void update() {

    }

}
