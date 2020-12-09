package uet.oop.bomberman.entities.StaticObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.GameObject;

public abstract class StaticObject extends GameObject {
    public StaticObject(int x, int y, Image image) {
        super(x, y, image);
    }
    public StaticObject(Image image) {
        super(image);
    }

    @Override
    public void update() {

    }
}
