package com.uet.gameobject;

import com.uet.userinterface.GameFrame;

import java.awt.*;

public class GameWorld {

    public Player player;
    public PhysicalMap physicalMap;

    public Camera camera;

    public GameWorld(){

        player = new Player(75,75,this);
        physicalMap = new PhysicalMap(0,0, this);
        camera = new Camera(0,0, GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,this);
    }

    public void Update(){

        player.Update();
        camera.Update();
    }

    public void Render(Graphics2D g2){

        physicalMap.draw(g2);
        player.draw(g2);
    }

}
