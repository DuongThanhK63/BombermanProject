package uet.oop.bomberman;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.entities.Map;
import uet.oop.bomberman.entities.ParticularObject.Human.Player;
import uet.oop.bomberman.entities.ParticularObject.Enemy.Balloom;
import uet.oop.bomberman.entities.ParticularObject.ParticularObject;
import uet.oop.bomberman.entities.ObjectManager;
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
    private int state = 0;
    int beginTime = 100;

    public GameWorld() {
        objectManager = new ObjectManager(this);
        map = new Map(0, 1, this);
        player = new Player(1, 2, Sprite.player_up.getFxImage(), this);
        objectManager.addObject(player);
        map.setEntity();
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

    public void update() {
        if (state == GAME)
        objectManager.update();
    }

    public void render(GraphicsContext gc) {
        switch(state) {
            case MENU:
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, Sprite.SCALED_SIZE * BombermanGame.WIDTH
                        , Sprite.SCALED_SIZE * BombermanGame.HEIGHT);
                try {
                    gc.drawImage(new Image(new FileInputStream("res/game.png")), 130, 10);
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
                }
                break;
            case GAME:
                objectManager.draw(gc);
        }


    }
    int a = 1;
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
}
