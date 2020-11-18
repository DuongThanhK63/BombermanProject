package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Input_Keyboard implements KeyListener {
    protected boolean key[] = new boolean[200];
    protected boolean up, down, left, right, space;

    @Override
    public void keyTyped(KeyEvent keyEvent){};

    @Override
    public void keyPressed(KeyEvent keyEvent){
        key[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent){
        key[keyEvent.getKeyCode()] = false;
    }

    public void update(){
        up = key[KeyEvent.VK_UP] || key[KeyEvent.VK_W];
        down = key[KeyEvent.VK_DOWN] || key[KeyEvent.VK_S];
        left = key[KeyEvent.VK_LEFT] || key[KeyEvent.VK_A];
        right = key[KeyEvent.VK_RIGHT] || key[KeyEvent.VK_D];
        space = key[KeyEvent.VK_SPACE];
    }
}
