/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uet.userinterface;

import com.uet.effect.Animation;
import com.uet.effect.FrameImage;
import com.uet.gameobject.GameWorld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {
    
    private Thread thread;
    
    private boolean isRunning;
    
    private InputManager inputManager;

    private BufferedImage bufImage;
    private Graphics2D bufGraphics2D;

    public GameWorld gameWorld;

    
    public GamePanel() {
        gameWorld = new GameWorld();
        inputManager = new InputManager(gameWorld);
        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH ,GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bufImage, 0, 0, this);
    }

    public void updateGame(){
        gameWorld.Update();
    }

    public void renderGame() {
        if(bufImage == null){
            bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
        }

        if(bufImage != null){
            bufGraphics2D = (Graphics2D)bufImage.getGraphics();
        }

        if(bufGraphics2D != null){
            bufGraphics2D.setColor(Color.getColor("Green", 0x50A000));
            bufGraphics2D.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
            gameWorld.Render(bufGraphics2D);
        }
    }

    public void startGame() {
            if(thread == null){
                isRunning = true;
                thread = new Thread(this);
                thread.start();
            }
    }

    @Override
    public void run() {
        int a = 0;
        long FPS = 80;
        long period = 1000*1000000/FPS;
        long beginTime;
        long sleepTime;
        
        beginTime = System.nanoTime();
        while(isRunning){
        //    System.out.println("a = " +(a++));

            renderGame();
            updateGame();
            repaint();
            
            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;
            
            try {
                if(sleepTime > 0){
                    Thread.sleep(sleepTime/1000000);
                }
                else{
                    Thread.sleep(period/2000000);
                }
            } catch (InterruptedException ex) {
                beginTime = System.nanoTime();
            }
            
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) { //callback method
        inputManager.processKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.processKeyReleased(e.getKeyCode());
    }

}
