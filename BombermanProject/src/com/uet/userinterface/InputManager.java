/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uet.userinterface;

import com.uet.gameobject.GameWorld;
import com.uet.gameobject.Player;

import java.awt.event.KeyEvent;

/**
 *
 * @author Admin
 */
public class InputManager {

    private GameWorld gameWorld;
    public InputManager(GameWorld gameWorld){

        this.gameWorld = gameWorld;
    }
    
    public void processKeyPressed(int keyCode) {
        
        switch(keyCode){
            case KeyEvent.VK_W:
                gameWorld.player.setDirection(gameWorld.player.UP_DIR);
                gameWorld.player.moving();
                break;
            
            case KeyEvent.VK_S:
                gameWorld.player.setDirection(gameWorld.player.DOWN_DIR);
                gameWorld.player.moving();
                break;
            
            case KeyEvent.VK_A:
                gameWorld.player.setDirection(gameWorld.player.LEFT_DIR);
                gameWorld.player.moving();
                break;
            
            case KeyEvent.VK_D:
                gameWorld.player.setDirection(gameWorld.player.RIGHT_DIR);
                gameWorld.player.moving();
                break;
            case KeyEvent.VK_SPACE:
                
                break;
        }
    }
    
    public void processKeyReleased(int keyCode) {
        
        switch(keyCode){
            case KeyEvent.VK_W:
                gameWorld.player.setDirection(gameWorld.player.UP_DIR);
                gameWorld.player.stopMoving();
                break;
            
            case KeyEvent.VK_S:
                gameWorld.player.setDirection(gameWorld.player.DOWN_DIR);
                gameWorld.player.stopMoving();
                break;
            
            case KeyEvent.VK_A:
                gameWorld.player.setDirection(gameWorld.player.LEFT_DIR);
                gameWorld.player.stopMoving();
                break;
            
            case KeyEvent.VK_D:
                gameWorld.player.setDirection(gameWorld.player.RIGHT_DIR);
                gameWorld.player.stopMoving();
                break;
            case KeyEvent.VK_SPACE:
                
                break;
        }
    }
}
