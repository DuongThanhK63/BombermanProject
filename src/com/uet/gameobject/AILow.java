package com.uet.gameobject;

public class AILow extends AI{


    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }
}
