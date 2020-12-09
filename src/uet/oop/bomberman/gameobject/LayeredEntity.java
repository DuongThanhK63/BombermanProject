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
public class LayeredEntity extends GameObject {
    GameObject grass;

    protected LinkedList<GameObject> entities = new LinkedList<>();

    public LayeredEntity(int x, int y, GameObject... entities) {
        setX(x);
        setY(y);
        grass = new Grass(Sprite.grass.getFxImage());
        for (int i = 0; i < entities.length; i++) {
            this.entities.add(entities[i]);
            if (i > 1) {
                if (entities[i] instanceof Brick)
                    ((Brick) entities[i]).addBelowSprite(entities[i - 1].getImg());
            }
        }
    }

    @Override
    public void update() {
        clearRemoved();
        getTopEntity().update();
    }

    @Override
    public void render(GraphicsContext gc) {
        grass.render(gc, getTopEntity().x, getTopEntity().y);
        getTopEntity().render(gc);
    }

    public GameObject getTopEntity() {
        return entities.getLast();
    }

    public GameObject getBelowTopEntity() {
        if (this.getLayeredSize() >= 2)
            return entities.get(this.getLayeredSize() - 2);
        return null;
    }

    private void clearRemoved() {
        GameObject top = getTopEntity();

        if (top.isRemoved()) {
            entities.removeLast();
        }
    }

    public int getLayeredSize() {
        return entities.size();
    }

    public void addToTop(GameObject e) {
        entities.add(e);
    }

    public void addBeforeTop(GameObject e) {
        entities.add(entities.size() - 1, e);
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

