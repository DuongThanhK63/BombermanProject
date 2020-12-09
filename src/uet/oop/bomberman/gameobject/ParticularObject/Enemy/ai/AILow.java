package uet.oop.bomberman.gameobject.ParticularObject.Enemy.ai;

public class AILow extends AI {

	@Override
	public int calculateDirection() {
		return random.nextInt(4);
	}

}
