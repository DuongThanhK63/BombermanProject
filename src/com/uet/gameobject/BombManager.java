package com.uet.gameobject;

import java.awt.*;

public class BombManager extends  ParticularObjectManager{

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