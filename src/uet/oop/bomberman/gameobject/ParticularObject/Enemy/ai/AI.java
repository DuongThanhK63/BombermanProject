package uet.oop.bomberman.gameobject.ParticularObject.Enemy.ai;

import java.util.Random;

public abstract class AI {
	
	protected Random random = new Random();
	
	public abstract int calculateDirection();
}
