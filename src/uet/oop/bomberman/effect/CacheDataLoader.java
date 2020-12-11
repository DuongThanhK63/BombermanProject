package uet.oop.bomberman.effect;

import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.gameobject.ParticularObject.Enemy.Oneal;
import uet.oop.bomberman.gameobject.ParticularObject.Human.Player;
import uet.oop.bomberman.gameobject.GameObject;
import uet.oop.bomberman.gameobject.ParticularObject.Enemy.Balloom;
import uet.oop.bomberman.gameobject.StaticObject.destroyable.Brick;
import uet.oop.bomberman.gameobject.StaticObject.Grass;
import uet.oop.bomberman.gameobject.StaticObject.Wall;
import uet.oop.bomberman.gameobject.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class CacheDataLoader {
    private static CacheDataLoader instance = null;
    private Hashtable<String, Animation> animations;
    private Hashtable<String, GameObject> gameObjects;
    private String backgroundmapfile = "res/levels/Level" + GameWorld.level + ".txt";
    private int[][] background_map;
    private int level;
    private CacheDataLoader() {}

    public static CacheDataLoader getInstance(){
        if(instance == null)
            instance  = new CacheDataLoader();
        return instance;
    }
    public void loadData()throws IOException{
        loadFrame();
        loadAnimation();
        loadBackgroundMap();
    }
    public void loadFrame() {
        gameObjects = new Hashtable<String, GameObject>();
        gameObjects.put("wall", new Wall(Sprite.wall.getFxImage()));
        gameObjects.put("brick", new Brick( Sprite.brick.getFxImage()));
        gameObjects.put("grass", new Grass( Sprite.grass.getFxImage()));
        gameObjects.put("player_up", new Player( Sprite.player_up.getFxImage()));
        gameObjects.put("player_down", new Player( Sprite.player_down.getFxImage()));
        gameObjects.put("player_right", new Player( Sprite.player_right.getFxImage()));
        gameObjects.put("player_left", new Player(Sprite.player_left.getFxImage()));
        gameObjects.put("balloom_right", new Balloom( Sprite.balloom_right1.getFxImage()));
        gameObjects.put("balloom_left", new Grass(Sprite.balloom_left1.getFxImage()));
    }
    public void loadAnimation() throws IOException {
        animations = new Hashtable<String, Animation>();
        Animation player_right = new Animation();
        player_right.add(0.3, new Player(Sprite.player_right_1.getFxImage())
                ,new Player(Sprite.player_right_2.getFxImage()));
        animations.put("player_right", player_right);
        Animation player_left = new Animation();
        player_left.add(0.3,new Player(Sprite.player_left_1.getFxImage())
                ,new Player(Sprite.player_left_2.getFxImage()));
        animations.put("player_left", player_left);
        Animation player_up = new Animation();
        player_up.add(0.3,new Player(Sprite.player_up_1.getFxImage())
                , new Player(Sprite.player_up_2.getFxImage()));
        animations.put("player_up", player_up);
        Animation player_down = new Animation();
        player_down.add(0.3,new Player(Sprite.player_down_1.getFxImage())
                , new Player( Sprite.player_down_2.getFxImage()));
        animations.put("player_down", player_down);
        Animation player_dead = new Animation();
        player_dead.add(0.3,new Player(Sprite.player_dead1.getFxImage())
                , new Player( Sprite.player_dead2.getFxImage()), new Player( Sprite.player_dead3.getFxImage()));
        animations.put("player_dead", player_dead);


        Animation balloom_left = new Animation();
        balloom_left.add(0.4, new Balloom(Sprite.balloom_left1.getFxImage())
                , new Balloom(Sprite.balloom_left2.getFxImage()), new Balloom(Sprite.balloom_left3.getFxImage()));
        animations.put("balloom_left", balloom_left);
        Animation balloom_right = new Animation();
        balloom_right.add(0.4, new Balloom(Sprite.balloom_right1.getFxImage())
                , new Balloom(Sprite.balloom_right2.getFxImage()), new Balloom(Sprite.balloom_right3.getFxImage()));
        animations.put("balloom_right", balloom_right);
        Animation balloom_dead = new Animation();
        balloom_dead.add(0.4, new Balloom(Sprite.balloom_dead.getFxImage()), new Balloom(Sprite.mob_dead1.getFxImage()),
                new Balloom(Sprite.mob_dead2.getFxImage()), new Balloom(Sprite.mob_dead3.getFxImage()));
        animations.put("balloom_dead",balloom_dead);

        Animation oneal_left = new Animation();
        oneal_left.add(0.4, new Oneal(Sprite.oneal_left1.getFxImage()), new Oneal(Sprite.oneal_left2.getFxImage()),
                new Oneal(Sprite.oneal_left3.getFxImage()));
        animations.put("oneal_left", oneal_left);
        Animation oneal_right = new Animation();
        oneal_right.add(0.4, new Oneal(Sprite.oneal_right1.getFxImage()), new Oneal(Sprite.oneal_right2.getFxImage()),
                new Oneal(Sprite.oneal_right3.getFxImage()));
        animations.put("oneal_right", oneal_right);
        Animation oneal_die = new Animation();
        oneal_die.add(0.4, new Oneal(Sprite.oneal_dead.getFxImage()), new Oneal(Sprite.mob_dead1.getFxImage()),
                new Oneal(Sprite.mob_dead2.getFxImage()), new Oneal(Sprite.mob_dead3.getFxImage()));
        animations.put("oneal_die", oneal_die);

        Animation bomb = new Animation();
        bomb.add(0.4, new Bomb(Sprite.bomb.getFxImage())
                , new Bomb(Sprite.bomb_1.getFxImage()), new Bomb(Sprite.bomb_2.getFxImage()));
        animations.put("bomb", bomb);

        Animation brick_explo = new Animation();
        brick_explo.add(0.2, new Bomb(Sprite.brick_exploded.getFxImage())
                , new Bomb(Sprite.brick_exploded1.getFxImage()), new Bomb(Sprite.brick_exploded2.getFxImage()));
        animations.put("brick_exploded", brick_explo);
    }
    public Animation getAnimation(String name){

        Animation animation = instance.animations.get(name);
        return animation;
    }
    public GameObject getObject(String name){

        GameObject gameObject = instance.gameObjects.get(name);
        return gameObject;
    }

    public int[][] getBackgroundMap(){
        return instance.background_map;
    }

    public void loadBackgroundMap() throws IOException{

        backgroundmapfile = "res/levels/Level" + GameWorld.level + ".txt";
        FileReader fr = new FileReader(backgroundmapfile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        line = br.readLine();
        String a[] = line.split(" ");
        int numberOfRows = Integer.parseInt(a[1]);
        int numberOfColumns = Integer.parseInt(a[2]);


        instance.background_map = new int[numberOfRows][numberOfColumns];

        for(int i = 0;i < numberOfRows;i++){
            line = br.readLine();
            for(int j = 0;j<numberOfColumns;j++)
                instance.background_map[i][j] = line.charAt(j);
        }

        for(int i = 0;i < numberOfRows;i++){

            for(int j = 0;j<numberOfColumns;j++)
                System.out.print(" "+(char)instance.background_map[i][j]);

            System.out.println();
        }

        br.close();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}