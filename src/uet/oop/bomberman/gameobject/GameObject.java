package uet.oop.bomberman.gameobject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.GameWorld;

import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected Image img;
    protected boolean removed = false;

    private GameWorld gameWorld;

    public int getX() {
        return x;
    }

    public Image getImg() {
        return img;
    }

    public void remove()
    {
        removed = true;
    }

    public boolean isRemoved()
    {
        return removed;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public GameObject() {}
    public GameObject(int xUnit, int yUnit, Image img) {

        x = 32 * xUnit;
        y = 32 * yUnit;
        this.img = img;
    }
    public GameObject(Image image) {
        img = image;
    }
    public GameObject(int xUnit, int yUnit, Image img, int a) {

        x =xUnit;
        y =yUnit;
        this.img = img;
    }
    public GameObject(int xUnit, int yUnit, GameWorld gameWorld) {

        x = xUnit;
        y = yUnit;
        this.gameWorld = gameWorld;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public void render(GraphicsContext gc, int x, int y) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
    public Rectangle getBoundForCollisionWithMap(){
        Rectangle bound = new Rectangle();
        bound.x = getX();
        bound.y = getY();
        bound.width = 22;
        bound.height = 32;
        return bound;
    }
    public Rectangle getBoundForCollisionWithEnemy(){
        Rectangle bound = new Rectangle();
        bound.x = getX();
        bound.y = getY();
        bound.width = 32;
        bound.height = 32;
        return bound;
    }
}
