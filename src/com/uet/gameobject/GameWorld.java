package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;
import com.uet.userinterface.GameFrame;

import java.awt.*;

public class GameWorld {

    public Player player;
    public PhysicalMap physicalMap;
    public ParticularObjectManager bombList;

    public Camera camera;

    public GameWorld(){

        player = new Player(75,75,this);
        physicalMap = new PhysicalMap(0,0, this);
        camera = new Camera(0,0, GameFrame.SCREEN_WIDTH,this);
        bombList = new BombManager(this);
    }

    public void Update(){

        player.Update();
        camera.Update();
        bombList.UpdateObject();
    }

    public void Render(Graphics2D g2){

        physicalMap.draw(g2);
        player.draw(g2);
        bombList.draw(g2);

    }

}
