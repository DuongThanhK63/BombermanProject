/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uet.userinterface;

import com.uet.effect.CacheDataLoader;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class GameFrame extends JFrame{
    public static final int SCREEN_WIDTH = 1012;
    public static final int SCREEN_HEIGHT = 455;
    
    GamePanel gamePanel;

    public GameFrame() {
        
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        try {
            CacheDataLoader.getInstance().loadData();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
