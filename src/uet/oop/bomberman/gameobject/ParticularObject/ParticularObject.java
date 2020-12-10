package uet.oop.bomberman.gameobject.ParticularObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.gameobject.GameObject;

public abstract class ParticularObject extends GameObject {


    public static final int LEAGUE_TEAM  = 1;
    public static final int ENEMY_TEAM = 2;

    public static final int LEFT_DIR = 1;
    public static final int RIGHT_DIR = 0;
    public static final int UP_DIR = 2;
    public static final int DOWN_DIR = 3;


    public static final int ALIVE = 0;
    public static final int DEATH = 1;

    private int state = ALIVE;

    private int speedX;
    private int speedY;

    private int direction;

    private int teamType;

    public ParticularObject(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);

    }

    public ParticularObject(Image image) {
        super(image);
    }

    public ParticularObject(int x, int y, GameWorld gameWorld) {
        super(x,y,gameWorld);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getTeamType() {
        return teamType;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }


    @Override
    public void update() {

    }
    public void draw(GraphicsContext gc) {};
}
