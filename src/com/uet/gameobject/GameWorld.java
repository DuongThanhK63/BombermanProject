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
    ParticularObject balloom;
    public Camera camera;

    public GameWorld(){

        player = new Player(48,75,this);
        physicalMap = new PhysicalMap(0,0, this);
        camera = new Camera(0,0, GameFrame.SCREEN_WIDTH,this);
        bombList = new BombManager(this);
        particularObjectManager = new ParticularObjectManager(this);
        particularObjectManager.addObject(player);
        initEnemies();
    }

    private void initEnemies() {
        balloom = new Balloom(96,75,this);
        balloom.setDirection(ParticularObject.LEFT_DIR);
        balloom.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(balloom);

    }


    public void Update(){

        particularObjectManager.UpdateObject();
        balloom.Update();
        camera.Update();
        bombList.UpdateObject();
    }

    public void Render(Graphics2D g2){

        physicalMap.draw(g2);
        particularObjectManager.draw(g2);
        balloom.draw(g2);
        bombList.draw(g2);
    }

}
