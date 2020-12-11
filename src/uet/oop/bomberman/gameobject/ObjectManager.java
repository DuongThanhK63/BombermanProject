package uet.oop.bomberman.gameobject;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.gameobject.ParticularObject.ParticularObject;
import uet.oop.bomberman.gameobject.StaticObject.Item.BombItem;
import uet.oop.bomberman.gameobject.StaticObject.Item.FlameItem;
import uet.oop.bomberman.gameobject.StaticObject.Item.Item;
import uet.oop.bomberman.gameobject.StaticObject.Item.SpeedItem;
import uet.oop.bomberman.gameobject.StaticObject.Portal;
import uet.oop.bomberman.gameobject.bomb.Bomb;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.gameobject.bomb.FlameSegment;
import uet.oop.bomberman.sounds.Sound;

import java.awt.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ObjectManager {
    private List<ParticularObject> particularObjects;
    private List<Bomb> bombs;
    private GameWorld gameWorld;
    private GameObject[][] gameObjects = new GameObject[13][31];
    private int timeDeath = 20;

    public GameObject[][] getGameObjects() {
        return gameObjects;
    }

    public ObjectManager(GameWorld gameWorld){
        bombs = Collections.synchronizedList(new LinkedList<Bomb>());
        particularObjects = Collections.synchronizedList(new LinkedList<ParticularObject>());
        this.gameWorld = gameWorld;
    }

    public GameWorld getGameWorld(){
        return gameWorld;
    }

    public void addObject(ParticularObject particularObject){
        synchronized(particularObjects){
            particularObjects.add(particularObject);
        }
    }
    public void addObject(int x, int y, GameObject e)
    {
        gameObjects[x][y] = e;
    }

    public GameObject getObjectAt(int x, int y) {
        return gameObjects[x][y];
    }
    public void addBomb(Bomb bomb) {
        synchronized(particularObjects){
            bombs.add(bomb);
        }
    }
    public List<Bomb> getBombs()
    {
        return bombs;
    }
//    public void addObject(Movable movableObject, int x, int y){
//        synchronized(movableObjects){
//            movableObjects.add(movableObject);
//            movableObject.setX(x);
//            movableObject.setY(y);
//        }
//    }

    public Bomb getBombAt(double x, double y) {
        Iterator<Bomb> bs = bombs.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.getX() == (int) x && b.getY() == (int) y)
                return b;
        }
        return null;
    }

    public void removeObject(ParticularObject particularObject){
        synchronized(particularObjects){

            for(int id = 0; id < particularObjects.size(); id++){

                ParticularObject object = particularObjects.get(id);
                if(object == particularObject)
                    particularObjects.remove(id);

            }
        }
    }

    public ParticularObject getCollisionWidthEnemyObject(ParticularObject object){
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){

                ParticularObject objectInList = particularObjects.get(id);

                if(object.getTeamType() != objectInList.getTeamType() &&
                        object.getBoundForCollisionWithMap().intersects(objectInList.getBoundForCollisionWithEnemy())){
                    return objectInList;
                }
            }
        }
        return null;
    }

    public ParticularObject getCollisionWidthFlameObject(ParticularObject object){
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){

                ParticularObject objectInList = particularObjects.get(id);

                if(object.getTeamType() == objectInList.getTeamType() &&
                        object.getBoundForCollisionWithMap().intersects(objectInList.getBoundForCollisionWithEnemy())){
                    return objectInList;
                }
            }
        }
        return null;
    }

int i = 0;

    public void update() {
        synchronized (gameObjects) {
            for (int i = 0; i < gameObjects.length; i++) {
                for (int j = 0; j < gameObjects[i].length; j++) {
                    gameObjects[i][j].update();
                }
            }
        }

        synchronized (particularObjects) {
            for (int id = 0; id < particularObjects.size(); id++) {
                ParticularObject object = particularObjects.get(id);
                object.update();
                if (object.getState() == ParticularObject.DEATH) {
                    if (timeDeath == 0) {
                        timeDeath = 20;
                        particularObjects.remove(id);
                        System.out.println(particularObjects.size());
                        System.out.println(id);
                    }
                    timeDeath--;
                }
            }
        }
        synchronized (bombs) {
            for (int id = 0; id < bombs.size(); id++) {
                Bomb bomb = bombs.get(id);
                bomb.update();
//                if (object.getState() == Movable.DEATH) {
//                    if (i == 100)
//                        movableObjects.remove(id);
//                    i++;
//                }
            }
        }
        synchronized (gameObjects) {
            for (int i = 0; i < gameObjects.length; i++) {
                for (int j = 0; j < gameObjects[i].length; j++) {
                    if (gameObjects[i][j] instanceof LayeredObject
                            && (((LayeredObject) gameObjects[i][j]).getTopObject() instanceof Item)) {
                        Rectangle rect = gameObjects[i][j].getBoundForCollisionWithEnemy();
                        rect.x *= 32;
                        rect.y *= 32;
                        if (getGameWorld().getPlayer().getBoundForCollisionWithMap().intersects(rect)) {
                            Item item = (Item) ((LayeredObject) gameObjects[i][j]).getTopObject();
                            Sound.play("Item");
                            if (item instanceof FlameItem) Game.setBombRadius(1);
                            if (item instanceof BombItem) Game.setBombRate(1);
                            if (item instanceof SpeedItem) Game.setPlayerSpeed(1);
                            System.out.println(Game.getPlayerSpeed());
                            item.remove();

                        }
                    }
                    if (gameObjects[i][j] instanceof LayeredObject
                            && (((LayeredObject) gameObjects[i][j]).getTopObject() instanceof Portal)) {
                        Rectangle rect = (gameObjects[i][j]).getBoundForCollisionWithEnemy();
                        rect.x *= 32;
                        rect.y *= 32;
                        System.out.println(gameWorld.getMap().getNumberOfEnemyReal() + " " + getParticularObjects().size());
                        if (getGameWorld().getPlayer().getBoundForCollisionWithMap().intersects(rect) && gameWorld.getMap().getNumberOfEnemyReal() + 1 >= getParticularObjects().size()){
                            Sound.play("CRYST_UP");
                            GameWorld.level = 2;
                            gameWorld.switchState(gameWorld.BEGIN);
                        }
                    }
                }

            }
        }
    }

    public void draw(GraphicsContext gc){

        for (int i = 0; i < gameObjects.length; i++) {
            for (int j = 0; j < gameObjects[i].length; j++) {
                gameObjects[i][j].render(gc);
            }
        }
        synchronized(bombs){
            for(int id = 0; id < bombs.size(); id++){
                Bomb bomb = bombs.get(id);
                bomb.render(gc);
            }
        }
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){
                ParticularObject object = particularObjects.get(id);
                object.draw(gc);
            }
        }

    }

    public List<ParticularObject> getParticularObjects() {
        return particularObjects;
    }
}
