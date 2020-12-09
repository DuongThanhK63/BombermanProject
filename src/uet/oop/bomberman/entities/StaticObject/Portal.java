package uet.oop.bomberman.entities.StaticObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameWorld;

public class Portal extends StaticObject {
    public Portal(int x, int y, Image image, GameWorld gameWorld) {
        super(x, y, image);
        setGameWorld(gameWorld);
    }

    public Portal(Image image) {
        super(image);
    }
}
