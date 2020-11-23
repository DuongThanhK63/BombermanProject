/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uet.userinterface;

import com.uet.gameobject.Player;

import java.awt.event.KeyEvent;

/**
 *
 * @author Admin
 */
public class InputManager {

    private GamePanel gamePanel;
    public InputManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    
    public void processKeyPressed(int keyCode) {
        
        switch(keyCode){
            case KeyEvent.VK_W:
                gamePanel.player.setDirection(Player.dir_up);
                gamePanel.player.setSpeedY(-1);
                break;
            
            case KeyEvent.VK_S:
                gamePanel.player.setDirection(Player.dir_down);
                gamePanel.player.setSpeedY(1);
                break;
            
            case KeyEvent.VK_A:
                gamePanel.player.setDirection(Player.dir_left);
                gamePanel.player.setSpeedX(-1);
                break;
            
            case KeyEvent.VK_D:
                gamePanel.player.setDirection(Player.dir_right);
                gamePanel.player.setSpeedX(1);
                break;
            case KeyEvent.VK_SPACE:
                
                break;
            
            case KeyEvent.VK_ENTER:
                
                break;
        }
    }
    
    public void processKeyReleased(int keyCode) {
        
        switch(keyCode){
            case KeyEvent.VK_W:
                gamePanel.player.setSpeedY(0);
                break;
            
            case KeyEvent.VK_S:
                gamePanel.player.setSpeedY(0);
                break;
            
            case KeyEvent.VK_A:
                gamePanel.player.setSpeedX(0);
                break;
            
            case KeyEvent.VK_D:
                gamePanel.player.setSpeedX(0);
                break;
            case KeyEvent.VK_SPACE:
                
                break;
            
            case KeyEvent.VK_ENTER:
                
                break;
        }
    }
}
