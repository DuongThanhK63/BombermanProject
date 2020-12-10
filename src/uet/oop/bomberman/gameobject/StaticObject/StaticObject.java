package uet.oop.bomberman.gameobject.StaticObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.gameobject.GameObject;
import uet.oop.bomberman.gameobject.ParticularObject.ParticularObject;

public abstract class StaticObject extends ParticularObject {
    public StaticObject(int x, int y, Image image) {
        super(x, y, image);
    }
    public StaticObject(Image image) {
        super(image);
    }

}
