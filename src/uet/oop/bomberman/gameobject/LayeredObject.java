package uet.oop.bomberman.gameobject;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.gameobject.StaticObject.Grass;
import uet.oop.bomberman.gameobject.StaticObject.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;
//import uet.oop.bomberman.entities.bomb.Bomb;

import java.util.LinkedList;

/**
 * Chứa và quản lý nhiều Entity tại cùng một vị trí Ví dụ: tại vị trí dấu Item,
 * có 3 Entity [Grass, Item, Brick]
 */
public class LayeredObject extends GameObject {
    GameObject grass;

    protected LinkedList<GameObject> gameObjects = new LinkedList<>();

    public LayeredObject(int x, int y, GameObject... entities) {
        setX(x);
        setY(y);
        grass = new Grass(Sprite.grass.getFxImage());
        for (int i = 0; i < entities.length; i++) {
            this.gameObjects.add(entities[i]);
            if (i > 1) {
                if (entities[i] instanceof Brick)
                    ((Brick) entities[i]).addBelowSprite(entities[i - 1].getImg());
            }
        }
    }

    public GameObject getFirstObject() {
        return gameObjects.get(0);
    }

    @Override
    public void update() {
        clearRemoved();
        getTopObject().update();
    }

    @Override
    public void render(GraphicsContext gc) {
        //grass.render(gc, getTopEntity().x, getTopEntity().y);
        getTopObject().render(gc);
    }

    public GameObject getTopObject() {
        return gameObjects.getLast();
    }

    public GameObject getBelowTopEntity() {
        if (this.getLayeredSize() >= 2)
            return gameObjects.get(this.getLayeredSize() - 2);
        return null;
    }

    private void clearRemoved() {
        GameObject top = getTopObject();

        if (top.isRemoved()) {
            gameObjects.removeLast();
        }
    }

    public int getLayeredSize() {
        return gameObjects.size();
    }

    public void addToTop(GameObject e) {
        gameObjects.add(e);
    }

    public void addBeforeTop(GameObject e) {
        gameObjects.add(gameObjects.size() - 1, e);
    }

//    @Override
//    public boolean collide(Entity e) {
//        Entity topMost = this.getTopEntity();
//        if (!(topMost instanceof Grass)) {
//            return topMost.collide(e);
//        } else if (this.getBelowTopEntity() instanceof Bomb) {
//            return this.getBelowTopEntity().collide(e);
//        } else return false;
//    }

}

