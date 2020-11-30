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

            if(mainCharacter.getPositionX() - getPositionX() > 240){
                setPositionX(mainCharacter.getPositionX() - 240);
                if(getPositionX() > (GameFrame.SCREEN_WIDTH + 90) * 18 / 31) setPositionX((GameFrame.SCREEN_WIDTH + 90) * 18 / 31);
            } else if( mainCharacter.getPositionX() + getPositionX() < 90){
                setPositionX(mainCharacter.getPositionX() - 90);
                if(getPositionX() < 0) setPositionX(0);
            }

            if(mainCharacter.getPositionY() - getPositionY() > 150){
                setPositionY(mainCharacter.getPositionY() - 150);

                if(getPositionY() > (GameFrame.SCREEN_HEIGHT + 90) * 3 / 13) setPositionY((GameFrame.SCREEN_HEIGHT +90)* 3 / 13);
            }
            else if(mainCharacter.getPositionY() - getPositionY() < 90){
                setPositionY(mainCharacter.getPositionY() - 90);
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

    public double getHeightView() {
        return heightView;
    }

    public void setHeightView(double heightView) {
        this.heightView = heightView;
    }
}
