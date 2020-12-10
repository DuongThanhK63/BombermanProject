package uet.oop.bomberman.gameobject.ParticularObject.Human;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameWorld;
import uet.oop.bomberman.gameobject.LayeredEntity;
import uet.oop.bomberman.gameobject.ParticularObject.ParticularObject;
import uet.oop.bomberman.gameobject.StaticObject.Item.FlameItem;
import uet.oop.bomberman.gameobject.StaticObject.Item.Item;
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
                if (getGameWorld().getObjectManager().getObjectAt(getGameWorld().getPlayer().getY() / 32, getGameWorld().getPlayer().getX() / 32) instanceof LayeredEntity) {
                    LayeredEntity tmp = (LayeredEntity) getGameWorld().getObjectManager().getObjectAt(getGameWorld().getPlayer().getY() / 32, getGameWorld().getPlayer().getX() / 32);
                    if (tmp.getTopEntity() instanceof Item) {
                        if (tmp.getTopEntity() instanceof FlameItem) {

                        }
                        tmp.getTopEntity().remove();
                    }
                }
            case DEATH:
                break;
        }
    }
}
