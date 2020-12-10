package uet.oop.bomberman.effect;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.gameobject.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Animation {
    private List<GameObject> gameObjects;
    private String name;

    private boolean isRepeated;

    private int currentFrame;

    private ArrayList<Boolean> ignoreFrames;
    private ArrayList<Double> delayFrames;

    private long beginTime;

    private boolean drawRectFrame;

    public Animation(){
        ignoreFrames = new ArrayList<Boolean>();
        gameObjects = new ArrayList<>();
        delayFrames = new ArrayList<Double>();
        beginTime = 0;
        currentFrame = 0;
        drawRectFrame = false;
        isRepeated = true;
    }


    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setIsRepeated(boolean isRepeated){
        this.isRepeated = isRepeated;
    }

    public boolean getIsRepeated(){
        return isRepeated;
    }

    public boolean isIgnoreFrame(int id){
        return ignoreFrames.get(id);
    }

    public void setIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, true);
    }

    public void unIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, false);
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setCurrentFrame(int currentFrame){
        if(currentFrame >= 0 && currentFrame < gameObjects.size())
            this.currentFrame = currentFrame;
        else this.currentFrame = 0;
    }
    public int getCurrentFrame(){
        return this.currentFrame;
    }

    public void reset(){
        currentFrame = 0;
        beginTime = 0;
    }

    public void add(double delay, GameObject... entity){

        for (GameObject e : entity) {
            ignoreFrames.add(false);
            gameObjects.add(e);
            delayFrames.add(new Double(delay));
        }

    }

    public void setDrawRectFrame(boolean b){
        drawRectFrame = b;
    }


    public GameObject getCurrent(){
        return gameObjects.get(currentFrame);
    }

    public void Update(long currentTime){

        if(beginTime == 0) beginTime = currentTime;
        else{
            if(currentTime - beginTime > delayFrames.get(currentFrame) * 1000000000){
                nextFrame();
                beginTime = currentTime;
            }
        }

    }


    public boolean isLastFrame(){
        if(currentFrame == gameObjects.size() - 1)
            return true;
        else return false;
    }

    private void nextFrame(){

        if(currentFrame >= gameObjects.size() - 1){

            if(isRepeated) currentFrame = 0;
        }
        else currentFrame++;

        if(ignoreFrames.get(currentFrame)) nextFrame();

    }

    public void draw(GraphicsContext gc, int x, int y){
        GameObject object = getCurrent();
        object.render(gc , x, y);
    }

}

