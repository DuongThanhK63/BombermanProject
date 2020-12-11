package uet.oop.bomberman;

public class Game {
    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final int PLAYERSPEED = 2;

    public static final int TIME = 200;
    public static final int POINTS = 0;
    public static final int LIVES = 3;

    protected static int bombRate = BOMBRATE;
    protected static int bombRadius = BOMBRADIUS;
    protected static int playerSpeed = PLAYERSPEED;

    public static int resetBombRate(){
        bombRate = BOMBRATE;
        return bombRate;
    }

    public static int resetBombRadius(){
        bombRadius = BOMBRADIUS;
        return bombRadius;
    }

    public static int resetPlayerSpeed(){
        playerSpeed = PLAYERSPEED;
        return playerSpeed;
    }

    public static int getBombRate() {
        return bombRate;
    }

    public static void setBombRate(int bombRate) {
        Game.bombRate += bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static void setBombRadius(int bombRadius) {
        Game.bombRadius += bombRadius;
    }

    public static double getPlayerSpeed() {
        return playerSpeed;
    }

    public static void setPlayerSpeed(int playerSpeed) {
        Game.playerSpeed += playerSpeed;
    }
}
