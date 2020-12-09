package uet.oop.bomberman.gameobject.StaticObject.destroyable;

import javafx.scene.image.Image;
import uet.oop.bomberman.gameobject.StaticObject.StaticObject;
import uet.oop.bomberman.graphics.Sprite;

public class DestroyableEntity extends StaticObject {
    private final int MAX_ANIMATE = 7500;
    private int _animate = 0;
    protected boolean destroyed = false;
    protected int _timeToDisapear = 20;
    protected Image belowImage = Sprite.grass.getFxImage();

    public DestroyableEntity(Image image) {
        super(image);
    }

    public DestroyableEntity(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void update() {
        if (destroyed) {
            if (_timeToDisapear > 0)
                _timeToDisapear--;
            else remove();
        }
    }

    public void destroy() {
        destroyed = true;
    }

    public void addBelowSprite(Image image) {
        belowImage = image;
    }

}
