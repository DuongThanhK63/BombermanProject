/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uet.userinterface;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class GameFrame extends JFrame{
    public static final int SCREEN_WIDTH = 750;
    public static final int SCREEN_HEIGHT = 690;
    
    GamePanel gamePanel;

    public GameFrame() {
        
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds((dimension.width - SCREEN_WIDTH) / 2,
                (dimension.height - SCREEN_HEIGHT) / 2, 
                 SCREEN_WIDTH, SCREEN_HEIGHT);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gamePanel = new GamePanel();
        
        add(gamePanel);
        
        this.addKeyListener(gamePanel);
    }
    
    public void startGame() {
        gamePanel.startGame();
    }
    
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
        gameFrame.startGame();
   
    }
}
