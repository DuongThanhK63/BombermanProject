package uet.oop.bomberman.gameobject.StaticObject.destroyable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.effect.Animation;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.gameobject.GameObject;
import uet.oop.bomberman.gameobject.StaticObject.Grass;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends DestroyableEntity {
    GameObject entity;
    Animation brick_exploded;
    int time_disappear = 10;

    public Brick(int x, int y, Image image) {
        super(x, y, image);
        entity = new Grass(Sprite.grass.getFxImage());
        brick_exploded = CacheDataLoader.getInstance().getAnimation("brick_exploded");
    }

    public Brick(Image img) {
        super(img);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(GraphicsContext gc) {
        if (destroyed) {
            entity.render(gc, getX(), getY());
            brick_exploded.Update(System.nanoTime());
            brick_exploded.draw(gc, getX(), getY());
        } else {
            super.render(gc);
        }

    }
}
