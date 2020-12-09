package uet.oop.bomberman.gameobject;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.gameobject.ParticularObject.ParticularObject;
import uet.oop.bomberman.gameobject.bomb.Bomb;
import uet.oop.bomberman.GameWorld;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ObjectManager {
    private List<ParticularObject> particularObjects;
    private List<Bomb> bombs;
    private GameWorld gameWorld;
    private GameObject[][] entities = new GameObject[13][31];

    public GameObject[][] getEntities() {
        return entities;
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
    public void addEntity(int x, int y, GameObject e)
    {
        entities[x][y] = e;
    }
    public GameObject getEntityAt(int x, int y)
    {
        return entities[x][y];
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


    public void removeObject(ParticularObject movableObject){
        synchronized(particularObjects){

            for(int id = 0; id < particularObjects.size(); id++){

                ParticularObject object = particularObjects.get(id);
                if(object == movableObject)
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

int i = 0;

    public void update() {
        synchronized (entities) {
            for (int i = 0; i < entities.length; i++) {
                for (int j = 0; j < entities[i].length; j++) {
                    entities[i][j].update();
                }
            }
        }

        synchronized (particularObjects) {
            for (int id = 0; id < particularObjects.size(); id++) {
                ParticularObject object = particularObjects.get(id);
                object.update();
                if (object.getState() == ParticularObject.DEATH) {
                    if (i == 100)
                        particularObjects.remove(id);
                    i++;
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
    }

    public void draw(GraphicsContext gc){
//        synchronized(movableObjects){
//            for(Movable object: movableObjects)
//                object.draw(gc);
//        }
        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[i].length; j++) {
                entities[i][j].render(gc);
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
}
