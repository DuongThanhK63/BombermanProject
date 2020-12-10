package uet.oop.bomberman.gameobject.ParticularObject.Human;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.gameobject.ParticularObject.ParticularObject;
import uet.oop.bomberman.gameobject.bomb.Flame;

public class Human extends ParticularObject {

    public Human(int xUnit, int yUnit, Image img, GameWorld gameWorld) {
        super(xUnit, yUnit, img);
        setGameWorld(gameWorld);
        setTeamType(LEAGUE_TEAM);
    }

    public Human(Image image) {
        super(image);
    }

    @Override
    public void update() {
        switch (getState()) {
            case ALIVE:
                ParticularObject obj = getGameWorld().getObjectManager().getCollisionWidthEnemyObject(this);
                if(obj != null) {
                    setState(DEATH);
                }
            case DEATH:
                break;
        }
    }
}
