package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;
import com.uet.userinterface.GameFrame;

import java.awt.*;

public class GameWorld {

    private int [][] phys_map;
    public Player player;
    public PhysicalMap physicalMap;
    public ParticularObjectManager bombList;
    public ParticularObjectManager particularObjectManager;
    public ParticularObject balloom;
    private int numOfEnemy = 0;

    public GameWorld(){

        player = new Player(32,48,this);
        physicalMap = new PhysicalMap(0,0, this);
        bombList = new BombManager(this);
        particularObjectManager = new ParticularObjectManager(this);
        particularObjectManager.addObject(player);
        initEnemies();
        initMap();
    }

    private void initEnemies() {
        balloom = new Balloom(160, 186,this);
        balloom.setDirection(ParticularObject.LEFT_DIR);
        balloom.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(balloom);
        numOfEnemy += 1;

    }

    private void initMap(){
        for(int i = 0;i< this.physicalMap.phys_map.length;i++) {
            for (int j = 0; j < this.physicalMap.phys_map[0].length; j++) {
                switch (this.physicalMap.phys_map[i][j]) {
                    case 1:
                        Wall wall = new Wall(j * 32 + 16, i * 32 + 16, this, 32,32);
                        particularObjectManager.addObject(wall);
                        break;
                    case 2:
                        Brick brick = new Brick(j * 32 + 16, i * 32 + 16, this, 32,32);
                        particularObjectManager.addObject(brick);
                        break;
                    case 3:
                        Portal portal = new Portal(j * 32 + 16, i * 32 + 16, this, 32,32);
                        particularObjectManager.addObject(portal);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;


                }
            }
        }
    }

    public void Update(){

        particularObjectManager.UpdateObject();
        balloom.Update();
        bombList.UpdateObject();
    }

    public void Render(Graphics2D g2){

//        physicalMap.draw(g2);
        particularObjectManager.draw(g2);
        balloom.draw(g2);
        bombList.draw(g2);
    }

    public int getNumOfEnemy() {
        return numOfEnemy;
    }
}
