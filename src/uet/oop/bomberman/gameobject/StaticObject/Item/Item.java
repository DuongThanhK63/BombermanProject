package uet.oop.bomberman.gameobject.StaticObject.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.gameobject.StaticObject.StaticObject;

public abstract class Item extends StaticObject {
    public Item(int x, int y, Image image) {
        super(x, y, image);
    }

    public Item(Image image) {
        super(image);
    }
}
