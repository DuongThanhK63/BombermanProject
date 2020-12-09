package uet.oop.bomberman.entities.StaticObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Grass extends StaticObject {

    public Grass(int x, int y, Image image) {
        super(x, y ,image);
    }
    public Grass( Image img) {
        super( img);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}
