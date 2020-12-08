package com.uet.gameobject;

import com.uet.effect.CacheDataLoader;

import java.awt.*;

public class BalloomManager extends ParticularObjectManager{

    private int [][] phys_map = CacheDataLoader.getInstance().getPhysicalmap();


    public BalloomManager(GameWorld gameWorld) {
        super(gameWorld);
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.GRAY);
        for(int i = 0;i< phys_map.length;i++) {
            for (int j = 0; j < phys_map[0].length; j++) {
                switch (phys_map[i][j]) {
                    case 9:



                        break;

                }
            }
        }
    }

    @Override
    public void UpdateObject() {
        super.UpdateObject();
        synchronized (particularObjects){
            for(int i = 0; i < particularObjects.size(); i++){
                ParticularObject object = particularObjects.get(i);
                if(object.getState() == ParticularObject.DEATH){
                    particularObjects.remove(i);
                }
            }
        }
    }
}
