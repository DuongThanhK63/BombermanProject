package uet.oop.bomberman.gameobject.StaticObject.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameWorld;

import java.awt.*;

public class FlameItem extends Item {
    public FlameItem(int x, int y, Image image, GameWorld gameWorld) {
        super(x, y, image);
        setGameWorld(gameWorld);
        setState(ALIVE);
    }

    public FlameItem(Image image) {
        super(image);
    }
    @Override
    public void update() {
        Rectangle rect = this.getBoundForCollisionWithMap();

        switch (getState()){
            case ALIVE:
                if(rect.intersects(getGameWorld().getPlayer().getBoundForCollisionWithMap())){
                    setState(DEATH);
                }
                break;
            case DEATH:
                break;
        }
    }
}
