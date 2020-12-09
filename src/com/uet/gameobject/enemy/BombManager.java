package com.uet.gameobject.enemy;

import com.uet.gameobject.managerobjects.GameWorld;
import com.uet.gameobject.managerobjects.ParticularObject;
import com.uet.gameobject.managerobjects.ParticularObjectManager;

public class BombManager extends ParticularObjectManager {

    public BombManager(GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    public void UpdateObject() {
        super.UpdateObject();
        synchronized (particularObjects){
            for(int i = 0; i < particularObjects.size(); i++){
                ParticularObject object = particularObjects.get(i);
                if( object.getState() == ParticularObject.DEATH){
                    particularObjects.remove(i);
                }
            }
        }
    }
}