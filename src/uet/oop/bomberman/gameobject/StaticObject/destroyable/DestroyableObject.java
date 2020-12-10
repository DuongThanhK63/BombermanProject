package uet.oop.bomberman.gameobject.StaticObject.destroyable;

import javafx.scene.image.Image;
import uet.oop.bomberman.gameobject.StaticObject.StaticObject;
import uet.oop.bomberman.graphics.Sprite;

public class DestroyableObject extends StaticObject {

    protected boolean destroyed = false;
    protected int _timeToDisapear = 10;
    protected Image belowImage = Sprite.grass.getFxImage();

    public DestroyableObject(Image image) {
        super(image);
    }

    public DestroyableObject(int x, int y, Image image) {
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
