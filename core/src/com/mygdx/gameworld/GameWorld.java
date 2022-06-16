package com.mygdx.gameworld;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameobjects.bird;
import com.mygdx.gameobjects.scrollHandler;

public class GameWorld {

    private bird _bird;
    private scrollHandler scroller;

    public GameWorld(int midpoint){
        _bird = new bird(33, midpoint-5,17,12);
        scroller = new scrollHandler(midpoint + 66);
    }

    public void update(float delta){
        _bird.update(delta);
        scroller.update(delta);
    }

    public bird getBird(){
        return _bird;
    }

    public scrollHandler getScroller(){
        return scroller;
    }
}
