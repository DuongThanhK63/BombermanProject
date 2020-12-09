package uet.oop.bomberman.gameobject.StaticObject;

import javafx.scene.image.Image;

public class Wall extends StaticObject {
    public Wall(int x, int y, Image image) {
        super(x, y, image);
    }

    public Wall(Image img) {
        super(img);
    }

    @Override
    public void update() {

    }
}
