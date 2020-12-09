package uet.oop.bomberman;

public class Game {
    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double PLAYERSPEED = 1.0;

    public static final int TIME = 200;
    public static final int POINTS = 0;
    public static final int LIVES = 3;

    protected static int bombRate = BOMBRATE;
    protected static int bombRadius = BOMBRADIUS;
    protected static double playerSpeed = PLAYERSPEED;

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

    public static void setPlayerSpeed(double playerSpeed) {
        Game.playerSpeed = playerSpeed;
    }
}
