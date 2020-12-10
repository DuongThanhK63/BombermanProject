package uet.oop.bomberman;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.gameobject.Map;
import uet.oop.bomberman.gameobject.ParticularObject.Human.Player;
import uet.oop.bomberman.gameobject.ParticularObject.Enemy.Balloom;
import uet.oop.bomberman.gameobject.ParticularObject.ParticularObject;
import uet.oop.bomberman.gameobject.ObjectManager;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GameWorld {
    private Map map;
    private Player player;
    private Balloom balloom;
    private ObjectManager objectManager;

    public static final int MENU = 0;
    public static final int BEGIN = 1;
    public static final int GAME = 2;
    public static final int GAMEOVER = 3;
    public int state = 0;
    private int lives = 1;
    private int points = Game.POINTS;
    public int previousState = state;
    private int beginTime = 100;
    private long time = Game.TIME;
    private long currentTime;
    private int timeWait  = 30;

    public GameWorld() {
        objectManager = new ObjectManager(this);
        map = new Map(0, 0, this);
        player = new Player(1, 2, Sprite.player_up.getFxImage(), this);
        objectManager.addObject(player);
        map.setObjects();
    }

    public Map getMap() {
        return map;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public Player getPlayer() {
        return player;
    }

    public void render(GraphicsContext gc) {
        switch(state) {
            case MENU:
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, Sprite.SCALED_SIZE * BombermanGame.WIDTH
                        , Sprite.SCALED_SIZE * BombermanGame.HEIGHT);
                try {
                    gc.drawImage(new Image(new FileInputStream("res/game.png")), 320, 10);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                gc.setFill(Color.WHITE);
                gc.setFont(new Font(20));
                gc.fillText("PRESS ENTER TO START GAME",360 , 380);
                break;
            case BEGIN:
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, Sprite.SCALED_SIZE * BombermanGame.WIDTH
                        , Sprite.SCALED_SIZE * BombermanGame.HEIGHT);
                gc.setFill(Color.WHITE);
                gc.setFont(new Font(50));
                gc.fillText("LEVEL 1", Sprite.SCALED_SIZE * BombermanGame.WIDTH / 2 -100
                        , Sprite.SCALED_SIZE * BombermanGame.HEIGHT / 2);
                if(beginTime++ == 200) {
                    state = GAME;
                    beginTime = 100;
                }
                currentTime = System.currentTimeMillis();
                break;
            case GAME:
                objectManager.draw(gc);
                if(player.getState() == ParticularObject.DEATH){
                    if(timeWait > 0){
                        timeWait--;
                    } else {
                        if(lives <= 0){
                            switchState(GAMEOVER);
                        } else {
                            switchState(BEGIN);
                            lives -= 1;
                            objectManager.addObject(player);
                            player.setX(32);
                            player.setY(32);
                            player.setState(ParticularObject.ALIVE);
                            timeWait = 30;
                        }

                    }
                }
                break;
            case GAMEOVER:
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, Sprite.SCALED_SIZE * BombermanGame.WIDTH
                        , Sprite.SCALED_SIZE * BombermanGame.HEIGHT);
                gc.setFill(Color.WHITE);
                gc.setFont(new Font(50));
                gc.fillText("GAME OVER!", Sprite.SCALED_SIZE * BombermanGame.WIDTH / 2 -100
                        , Sprite.SCALED_SIZE * BombermanGame.HEIGHT / 2);
                break;
        }

    }

//    private void resetProperties() {
//        point = Game.POINTS;
//        live = Game.LIVES;
//        Player._powerups.clear();
//        game.playerSpeed = 1.0;
//        game.bombRadius = 1;
//        game.bombRate = 1;
//
//    }

    public void switchState(int state){
        previousState = this.state;
        this.state = state;
    }

    public void setKeyboard(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (getPlayer().getState() == ParticularObject.ALIVE)
            switch (event.getCode().getName()) {
                case "D":
                    player.setSpeedX(2);
                    player.setSpeedY(0);
                    player.setDirection(ParticularObject.RIGHT_DIR);
                    break;
                case "A":
                    player.setSpeedX(-2);
                    player.setSpeedY(0);
                    player.setDirection(ParticularObject.LEFT_DIR);
                    break;
                case "W":
                    player.setSpeedX(0);
                    player.setSpeedY(-2);
                    player.setDirection(ParticularObject.UP_DIR);
                    break;
                case "S":
                    player.setSpeedX(0);
                    player.setSpeedY(2);
                    player.setDirection(ParticularObject.DOWN_DIR);
                    break;
                case "Enter" :
                    if (state == MENU) state = BEGIN;
                    break;
                case "Space" :
                    player.placeBomb();
            }

        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.D
                    || event.getCode() == KeyCode.W || event.getCode() == KeyCode.S) {
                player.setSpeedX(0);
                player.setSpeedY(0);
            }
        });
    }

    public void update() {
        if (state == GAME) {
            objectManager.update();
            if (System.currentTimeMillis() - currentTime > 1000) {
                time--;
                currentTime += 1000;
            }
        }
    }

    public long getTime() {
        return time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLives() {
        return lives;
    }

    public int getPoints() {
        return points;
    }
}
