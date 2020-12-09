package com.uet.gameobject.managerobjects;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ParticularObjectManager {

    protected List<ParticularObject> particularObjects;
    private GameWorld gameWorld;

    public List<ParticularObject> getParticularObjects() {
        return particularObjects;
    }

    public ParticularObjectManager(GameWorld gameWorld){
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

    public void removeObject(ParticularObject particularObject){
        synchronized (particularObjects){
            for(int i = 0; i < particularObjects.size(); i++){
                ParticularObject object = particularObjects.get(i);
                if(object == particularObject){
                    particularObjects.remove(i);
                }
            }
        }
    }

    public ParticularObject getCollisionWithEnemyObject(ParticularObject object){
        synchronized (particularObjects){
            for(int i = 0; i < particularObjects.size(); i++) {
                ParticularObject object1 = particularObjects.get(i);

                if (object.getTeamType() != object1.getTeamType() && object.getBoundForCollisionWithEnemy().intersects(
                        object1.getBoundForCollisionWithEnemy())) {
                    return object1;
                }
            }
        }
        return null;
    }

    public void UpdateObject(){
        synchronized (particularObjects){
            for(int i = 0; i < particularObjects.size(); i++){
                ParticularObject object = particularObjects.get(i);
                object.Update();
                if(object.getState() == ParticularObject.DEATH){
                    particularObjects.remove(i);
                }
            }
        }
    }

    public void draw(Graphics2D g2){
        synchronized (particularObjects){
            for(ParticularObject object: particularObjects){

                    object.draw(g2);

            }
        }
    }
}
