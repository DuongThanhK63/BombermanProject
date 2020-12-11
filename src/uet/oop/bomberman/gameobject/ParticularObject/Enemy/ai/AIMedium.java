package uet.oop.bomberman.gameobject.ParticularObject.Enemy.ai;

import uet.oop.bomberman.gameobject.ParticularObject.Enemy.Enemy;
import uet.oop.bomberman.gameobject.ParticularObject.Human.Player;

public class AIMedium extends AI {
    Player player;
    Enemy enemy;

    public AIMedium(Player player, Enemy e) {
        this.player = player;
        enemy = e;
    }

    @Override
    public int calculateDirection() {

        if (player == null)
            return random.nextInt(4);
        int vertical = random.nextInt(2);
        if (vertical == 1) {
            int v = calculateRowDirection();
            if (v != -1)
                return v;
            else
                return calculateColDirection();

        } else {
            int h = calculateColDirection();

            if (h != -1)
                return h;
            else
                return calculateRowDirection();
        }

    }

    protected int calculateColDirection() {
        if(player.getX() < enemy.getX())
            return 3;
        else if(player.getX() > enemy.getX())
            return 1;

        return -1;
    }

    protected int calculateRowDirection() {
        if(player.getY() < enemy.getY())
            return 0;
        else if(player.getY() > enemy.getY())
            return 2;
        return -1;
    }


}
