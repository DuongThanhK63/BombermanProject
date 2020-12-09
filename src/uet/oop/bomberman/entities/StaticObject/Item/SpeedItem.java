package uet.oop.bomberman.entities.StaticObject.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameWorld;

public class SpeedItem extends Item{
    public SpeedItem(int x, int y, Image image, GameWorld gameWorld) {
        super(x, y, image);
        setGameWorld(gameWorld);
    }

    public SpeedItem(Image image) {
        super(image);
    }
}
