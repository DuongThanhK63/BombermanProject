package com.uet.gameobject;

import com.uet.userinterface.GameFrame;

public class Camera extends GameObject{

    private double widthView;

    private boolean isLocked = false;

    public Camera(double positionX, double positionY, double widthView,GameWorld gameWorld) {
        super(positionX, positionY, gameWorld);
        this.widthView = widthView;
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

            if(mainCharacter.getPositionX() - getPositionX() > 240){
                setPositionX(mainCharacter.getPositionX() - 240);
                if(getPositionX() > (GameFrame.SCREEN_WIDTH - 120)) setPositionX(GameFrame.SCREEN_WIDTH - 120);
            } else if( mainCharacter.getPositionX() - getPositionX() < 240){
                setPositionX(mainCharacter.getPositionX() - 240);
                if(getPositionX() < 0) setPositionX(0);
            }

        }

    }

    public double getWidthView() {
        return widthView;
    }

    public void setWidthView(double widthView) {
        this.widthView = widthView;
    }

}
