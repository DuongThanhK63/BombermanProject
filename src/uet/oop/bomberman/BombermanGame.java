package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uet.oop.bomberman.effect.Animation;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 14;

    private GraphicsContext gc;
    private Canvas canvas;
    private Scene scene;

    GameWorld gameWorld;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        GridPane gridPane =new GridPane();
        Button button = new Button();

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
//        Group root = new Group();
//        root.getChildren().add(canvas);

        // Tao scene
//        scene = new Scene(root);
//        gridPane.add(button, 0, 0);
        gridPane.add(canvas, 0, 1);
        Scene scene = new Scene(gridPane);
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
//        stage.setScene(scene1);
//        stage.show();
        try {
            CacheDataLoader.getInstance().loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameWorld = new GameWorld();
        gameWorld.setKeyboard(scene);

        AnimationTimer timer = new AnimationTimer() {
            final long FPS = 80;
            final long period = 1000 * 1000000 / FPS;
            long beginTime = System.nanoTime();
            long sleepTime;
            @Override
            public void handle(long l) {

                update();
                render();

                long deltaTime = System.nanoTime() - beginTime;
                sleepTime = period - deltaTime;
                try {
                    if (sleepTime > 0) {
                        Thread.sleep(sleepTime / 1000000);
                    } else {
                        Thread.sleep(period / 2000000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                beginTime = System.nanoTime();
            }
        };
        timer.start();

//        createMap();



    }

//    public void createMap() {
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                Entity object;
//                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                }
//                else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
//                stillObjects.add(object);
//            }
//        }
//    }

    public void update() {

        gameWorld.update();

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gameWorld.render(gc);

    }
}
