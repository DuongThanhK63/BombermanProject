package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uet.oop.bomberman.effect.Animation;
import uet.oop.bomberman.effect.CacheDataLoader;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sounds.Sound;

import java.io.IOException;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 14;

    private GraphicsContext gc;
    private Canvas canvas;
    private Scene scene;
    private Label time;
    private Label points;
    private Label lives;

    GameWorld gameWorld;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {

        Sound.play("soundtrack");
        GridPane gridPane =new GridPane();
        Button button = new Button();

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        time = new Label("Time: ");
        time.setTextFill(Color.WHITE);
        time.setLayoutX(50);
        time.setLayoutY(5);
        points = new Label("Points: ");
        points.setTextFill(Color.WHITE);
        points.setLayoutX(450);
        points.setLayoutY(5);
        lives = new Label("Lives: ");
        lives.setTextFill(Color.WHITE);
        lives.setLayoutX(850);
        lives.setLayoutY(5);

        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #000000");
        root.getChildren().addAll(time,points,lives);
        gridPane.add(root,0,0);
        gridPane.add(canvas, 0, 1);
        Scene scene = new Scene(gridPane);
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

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


    }


    public void update() {

        time.setText("Time: " + gameWorld.getTime());
        points.setText("Points: " + gameWorld.getPoints());
        lives.setText("Lives : " + gameWorld.getLives());
        gameWorld.update();

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gameWorld.render(gc);

    }
}
