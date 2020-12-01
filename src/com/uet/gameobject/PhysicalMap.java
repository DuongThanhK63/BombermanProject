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
        this.tileSize = 50;
        phys_map = CacheDataLoader.getInstance().getPhysicalmap();

    }

    public int getTileSize() {
        return tileSize;
    }

//        @Override
//    public void Update() {
//
//    }
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
            if(phys_map[y][x] == 1){
                Rectangle r = new Rectangle((int) x + x * tileSize, (int) y + y * tileSize, tileSize, tileSize);
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

                if(phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int) x + x * tileSize, (int) y + y * tileSize, tileSize, tileSize);
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
                if(phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int) x + x * tileSize, (int) y + y * tileSize, tileSize, tileSize);
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
                if(phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int) x + x * tileSize, (int) y + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;

    }

    public void draw(Graphics2D g2){

        Camera camera = getGameWorld().camera;
        g2.setColor(Color.GRAY);
        for(int i = 0;i< phys_map.length;i++) {
            for (int j = 0; j < phys_map[0].length; j++) {
                switch (phys_map[i][j]) {
                    case 1:
                        g2.drawRect((int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                            (int) getPositionY() + i * tileSize , tileSize, tileSize);
                        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("wall").getImage(), (int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                                (int) getPositionY() + i * tileSize, null);
                        break;
                    case 2:
                        g2.drawRect((int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                                (int) getPositionY() + i * tileSize, tileSize, tileSize);
                        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("brick").getImage(), (int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                                (int) getPositionY() + i * tileSize, null);
                        break;
                    case 3:
                        g2.drawRect((int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                                (int) getPositionY() + i * tileSize, tileSize, tileSize);
                        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("portal").getImage(), (int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                                (int) getPositionY() + i * tileSize, null);
                        break;
                    case 4:
                        g2.drawRect((int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                                (int) getPositionY() + i * tileSize, tileSize, tileSize);
                        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("bombitem").getImage(), (int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                                (int) getPositionY() + i * tileSize, null);
                        break;
                    case 5:
                        g2.drawRect((int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                                (int) getPositionY() + i * tileSize - (int) camera.getPositionY(), tileSize, tileSize);
                        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("flameitem").getImage(), (int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                                (int) getPositionY() + i * tileSize, null);
                        break;
                    case 6:
                        g2.drawRect((int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                                (int) getPositionY() + i * tileSize, tileSize, tileSize);
                        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("speeditem").getImage(), (int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
                                (int) getPositionY() + i * tileSize, null);
                        break;
//                if (phys_map[i][j] != 0) g2.fillRect((int) getPositionX() + j * tileSize - (int) camera.getPositionX(),
//                                (int) getPositionY() + i * tileSize - (int) camera.getPositionY(), tileSize, tileSize);
                }
            }
        }
    }

    @Override
    public void Update() {

    }
}
