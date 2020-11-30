package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;
import com.uet.userinterface.GameFrame;

import java.awt.*;

public class GameWorld {

    public Player player;
    public PhysicalMap physicalMap;
    public BombManager bomb;
    Animation animation = new Animation();

    public Camera camera;

    public GameWorld(){

        player = new Player(90,90,this);
        physicalMap = new PhysicalMap(0,0, this);
        camera = new Camera(0,0, GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT / 2,this);
        bomb = new BombManager(this);
    }

    public void Update(){

        player.Update();
        camera.Update();
        bomb.UpdateObject();
    }

    public void Render(Graphics2D g2){

        physicalMap.draw(g2);
        player.draw(g2);
        bomb.draw(g2);

    }

}
