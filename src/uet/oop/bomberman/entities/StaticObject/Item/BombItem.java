package uet.oop.bomberman.entities.StaticObject.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameWorld;

public class BombItem extends Item{
    public BombItem(int x, int y, Image image, GameWorld gameWorld) {
        super(x, y, image);
        setGameWorld(gameWorld);
    }

    public BombItem(Image image) {
        super(image);
    }
}
