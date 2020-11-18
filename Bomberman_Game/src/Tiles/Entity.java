package Tiles;

import graphics.IRender;
import graphics.Screen;
import graphics.Sprite;

public abstract class Entity implements IRender {
    protected double _x, _y;
    protected boolean removed = false;
    protected Sprite _sprite;

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);

    public double get_x() {
        return _x;
    }

    public double get_y() {
        return _y;
    }

    public void remove(){
        removed = true;
    }
    public boolean isRemoved() {
        return removed;
    }

    public Sprite get_sprite() {
        return _sprite;
    }

    public abstract boolean collided(Entity entity);


}
