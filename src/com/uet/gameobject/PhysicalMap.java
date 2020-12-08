package com.uet.gameobject;

import com.uet.effect.Animation;
import com.uet.effect.CacheDataLoader;

import javax.imageio.IIOException;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;

public class PhysicalMap extends GameObject{

    public int [][] phys_map;
    private int tileSize;

    public PhysicalMap(double positionX, double positionY, GameWorld gameWorld) {
        super(positionX, positionY, gameWorld);
        this.tileSize = 32;
        phys_map = CacheDataLoader.getInstance().getPhysicalmap();

    }

    public Rectangle haveCollisionWithTopWall(Rectangle rect){

        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;

        //int posY = (rect.y + rect.height)/tileSize;
        int posY = rect.y/tileSize;

        if(posX1 < 0) posX1 = 0;

        if(posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;

        for(int y = posY; y >= 0; y--){
            for(int x = posX1; x <= posX2; x++){
                if(phys_map[y][x] != 0){
                    Rectangle r = new Rectangle((int) getPositionX() + x * tileSize, (int) getPositionY() + y * tileSize, tileSize, tileSize);
                    if(rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }


    public Rectangle haveCollisionWithDownWall(Rectangle rect){

        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;

        int posY = (rect.y + rect.height)/tileSize;

        if(posX1 < 0) posX1 = 0;

        if(posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;
        for(int y = posY; y < phys_map.length;y++){
            for(int x = posX1; x <= posX2; x++){

                if(phys_map[y][x] != 0){
                    Rectangle r = new Rectangle((int) getPositionX() + x * tileSize, (int) getPositionY() + y * tileSize, tileSize, tileSize);
                    if(rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithRightWall(Rectangle rect){

        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;

        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 + 3;
        if(posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;

        if(posY1 < 0) posY1 = 0;
        if(posY2 >= phys_map.length) posY2 = phys_map.length - 1;


        for(int x = posX1; x <= posX2; x++){
            for(int y = posY1; y <= posY2;y++){
                if(phys_map[y][x] != 0){
                    Rectangle r = new Rectangle((int) getPositionX() + x * tileSize, (int) getPositionY() + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;

    }

    public Rectangle haveCollisionWithLeftWall(Rectangle rect){

        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;

        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 - 3;
        if(posX2 < 0) posX2 = 0;

        if(posY1 < 0) posY1 = 0;
        if(posY2 >= phys_map.length) posY2 = phys_map.length - 1;


        for(int x = posX1; x >= posX2; x--){
            for(int y = posY1; y <= posY2; y++){
                if(phys_map[y][x] != 0){
                    Rectangle r = new Rectangle((int) getPositionX() + x * tileSize, (int) getPositionY() + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;

    }

    @Override
    public void Update() {

    }
}
