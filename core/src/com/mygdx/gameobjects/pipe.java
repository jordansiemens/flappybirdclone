package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class pipe extends scrollable{

    private Random r;

    private Rectangle skullUp, skullDown, barUp, barDown;

    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;

    private float groundY;

    public pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x,y,width,height,scrollSpeed);
        r = new Random();
        skullUp = new Rectangle();
        skullDown = new Rectangle();
        barUp = new Rectangle();
        barDown = new Rectangle();
        groundY = this.groundY;
    }

    @Override
    public void reset(float newX){
        super.reset(newX);
        height = r.nextInt(90) + 15;
    }

    @Override
    public void update(float delta){
        super.update(delta);
        barUp.set(position.x,position.y,width,height);
        barDown.set(position.x,position.y + height + VERTICAL_GAP, width, height + position.y + groundY + VERTICAL_GAP);
        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height
                - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,
                SKULL_WIDTH, SKULL_HEIGHT);
    }

    public Rectangle getSkullDown() {
        return skullDown;
    }

    public Rectangle getSkullUp() {
        return skullUp;
    }

    public Rectangle getBarUp(){
        return barUp;
    }

    public Rectangle getBarDown(){
        return barDown;
    }
}
