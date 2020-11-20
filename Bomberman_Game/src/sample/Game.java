package sample;

import input.Input_Keyboard;

import java.awt.*;

public class Game extends Canvas {
    /*
    | Options and Configurations
    | __________________________________________________________________________________________________________________
     */
    public static final double Ver = 2.0;
    public static final int TILE_SIZE = 16,
                            WIDTH = TILE_SIZE * 15,
                            HEIGHT = 13 * TILE_SIZE;

    public static int GAME_SCALE = 3;

    public static final String TITLE = "Bomberman" + Ver + "By Trung and Thanh";

    //initial config
    private static final int BombRate = 1;
    private static final int BombRadius = 1;
    private static final double PlayerSpeed = 1.0;

    public static final int Time = 300;
    public static final int Points = 0;
    public static final int Lives = 3;

    protected static int ScreenDelay = 3;

    //variable can be modified with bonus
    protected static int bombRate = BombRate;
    protected static int bombRadius = BombRadius;
    protected static double playerSpeed = PlayerSpeed;

    //Time in the level screen in seconds
    protected int _screenDelay = ScreenDelay;

    private Input_Keyboard _input;
    private boolean _running = false;
    private boolean _paused = true;


}
