package com.uet.gameobject;

import com.uet.userinterface.GameFrame;

public class Camera extends GameObject{

    private double widthView, heightView;

    private boolean isLocked = false;

    public Camera(double positionX, double positionY, double widthView, double heightView,GameWorld gameWorld) {
        super(positionX, positionY, gameWorld);
        this.widthView = widthView;
        this.heightView = heightView;
    }

    public void lock(){
        isLocked = true;
    }

    public void unlock(){
        isLocked = false;
    }

    @Override
    public void Update() {

        if(!isLocked){

            Player mainCharacter = getGameWorld().player;

            if(mainCharacter.getPositionX() - getPositionX() > 180){
                setPositionX(mainCharacter.getPositionX() - 180);
                if(getPositionX() < 0) setPositionX(0);
                if(getPositionX() > GameFrame.SCREEN_WIDTH + 90) setPositionX(GameFrame.SCREEN_WIDTH + 90);
//                if(getPositionY() < 0) setPositionY(0);
//                if(getPositionY() > GameFrame.SCREEN_HEIGHT + 75) setPositionY(GameFrame.SCREEN_HEIGHT + 75);
            }
            if(mainCharacter.getPositionX() - getPositionX() < 90){
                setPositionX(mainCharacter.getPositionX() - 90);
                if(getPositionX() < 0) setPositionX(0);
//                if(getPositionX() > GameFrame.SCREEN_WIDTH) setPositionX(GameFrame.SCREEN_WIDTH);
//                System.out.println(getPositionX());
//                if(getPositionY() < 0) setPositionY(0);
//                if(getPositionY() > GameFrame.SCREEN_HEIGHT) setPositionY(GameFrame.SCREEN_HEIGHT);
            }

            if(mainCharacter.getPositionY() - getPositionY() > 180){
                setPositionY(mainCharacter.getPositionY() - 180);
//                if(getPositionX() < 0) setPositionX(0);
//                if(getPositionX() > GameFrame.SCREEN_WIDTH) setPositionX(GameFrame.SCREEN_WIDTH);
                if(getPositionY() < 0) setPositionY(0);
                if(getPositionY() > GameFrame.SCREEN_HEIGHT / 2 + 90) setPositionY(GameFrame.SCREEN_HEIGHT / 2 + 90);
            }
            if(mainCharacter.getPositionY() - getPositionY() < 90){
                setPositionY(mainCharacter.getPositionY() - 90);
//                if(getPositionX() < 0) setPositionX(0);
//                if(getPositionX() > GameFrame.SCREEN_WIDTH) setPositionX(GameFrame.SCREEN_WIDTH);
                if(getPositionY() < 0) setPositionY(0);
                if(getPositionY() > GameFrame.SCREEN_HEIGHT / 2 + 90) setPositionY(GameFrame.SCREEN_HEIGHT / 2 + 90);
            }

        }

    }

    public double getWidthView() {
        return widthView;
    }

    public void setWidthView(double widthView) {
        this.widthView = widthView;
    }

    public double getHeightView() {
        return heightView;
    }

    public void setHeightView(double heightView) {
        this.heightView = heightView;
    }
}
