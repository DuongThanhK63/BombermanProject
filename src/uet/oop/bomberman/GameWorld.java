package uet.oop.bomberman;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.gameobject.Map;
import uet.oop.bomberman.gameobject.ParticularObject.Enemy.Enemy;
import uet.oop.bomberman.gameobject.ParticularObject.Human.Player;
import uet.oop.bomberman.gameobject.ParticularObject.Enemy.Balloom;
import uet.oop.bomberman.gameobject.ParticularObject.ParticularObject;
import uet.oop.bomberman.gameobject.ObjectManager;
import uet.oop.bomberman.gameobject.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sounds.Sound;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


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
    private int lives = Game.LIVES;
    private int tmpLive = lives * 100;
    private int points = Game.POINTS;
    public int previousState = state;
    private int beginTime = 100;
    private long time = Game.TIME;
    private long currentTime;
    private int timeWait  = 100;
    public static int level = 1;
    private int changeLevel = 0;
    private double speed = Game.getPlayerSpeed();

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
                gc.fillText("LEVEL " + level, Sprite.SCALED_SIZE * BombermanGame.WIDTH / 2 -100
                        , Sprite.SCALED_SIZE * BombermanGame.HEIGHT / 2);
                if(beginTime++ == 200) {
                    state = GAME;
                    beginTime = 100;
                }
                currentTime = System.currentTimeMillis();
                if(level == 2 && changeLevel == 0){
                    changeLevel++;
                    try{
                        CacheDataLoader.getInstance().loadBackgroundMap();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    map.setMap(CacheDataLoader.getInstance().getBackgroundMap());
                    reset();
                }
                break;
            case GAME:
                if(time <= 0){
                    switchState(GAMEOVER);
                }
                objectManager.draw(gc);
                if(player.getState() == ParticularObject.DEATH){
                    tmpLive -= 1;
                    if(timeWait > 0){
                        timeWait--;
                    } else {
                        if(tmpLive <= 0){
                            switchState(GAMEOVER);
                        } else {
                            switchState(BEGIN);
                            lives -= 1;
                            reset();
                            timeWait = 100;
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

    public void switchState(int state){
        previousState = this.state;
        this.state = state;
    }

    public void setKeyboard(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (state == GAME && player.getState() == ParticularObject.ALIVE)
            switch (event.getCode().getName()) {
                case "D":
                    Sound.play("Move");
                    player.setSpeedX(Game.playerSpeed);
                    player.setSpeedY(0);
                    player.setDirection(ParticularObject.RIGHT_DIR);
                    break;
                case "A":
                    Sound.play("Move");
                    player.setSpeedX(-Game.playerSpeed);
                    player.setSpeedY(0);
                    player.setDirection(ParticularObject.LEFT_DIR);
                    break;
                case "W":
                    Sound.play("Move");
                    player.setSpeedX(0);
                    player.setSpeedY(-Game.playerSpeed);
                    player.setDirection(ParticularObject.UP_DIR);
                    break;
                case "S":
                    Sound.play("Move");
                    player.setSpeedX(0);
                    player.setSpeedY(Game.playerSpeed);
                    player.setDirection(ParticularObject.DOWN_DIR);
                    break;
                case "Space" :
                    player.placeBomb();
            }

            if(player.getState() == ParticularObject.ALIVE){
                switch (event.getCode().getName()) {
                    case "Enter" :
                        if (state == MENU) state = BEGIN;
                        break;
                }
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

    public List<Bomb> getBombList(){
        return getObjectManager().getBombs();
    }

    public void reset(){
        for(int i = 0; i < getObjectManager().getParticularObjects().size(); i++){
            getObjectManager().getParticularObjects().remove(i);
            i--;
        }
        for(int i = 0; i < getObjectManager().getBombs().size(); i++){
            getObjectManager().getBombs().remove(i);
            i--;
        }
        player.setState(ParticularObject.ALIVE);
        Game.resetBombRadius();
        Game.resetBombRate();
        Game.resetPlayerSpeed();
        objectManager.addObject(player);
        player.setX(32);
        player.setY(32);
        map.setNumberOfEnemy(0);
        map.setObjects();
    }

}
