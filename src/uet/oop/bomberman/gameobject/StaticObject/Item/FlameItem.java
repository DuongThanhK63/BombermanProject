package uet.oop.bomberman.gameobject.StaticObject.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameWorld;

public class FlameItem extends Item {
    public FlameItem(int x, int y, Image image, GameWorld gameWorld) {
        super(x, y, image);
        setGameWorld(gameWorld);
    }

    public FlameItem(Image image) {
        super(image);
    }
}
