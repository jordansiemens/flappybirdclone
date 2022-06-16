package com.mygdx.birdhelpers;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.gameobjects.bird;

public class InputHandler implements InputProcessor {

    private bird _bird;

    public InputHandler(bird __bird){
        _bird = __bird;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        _bird.onClick();
        return false;
    }

    @Override
    public boolean keyDown(int keycode){
        return false;
    }

    @Override
    public boolean keyUp(int keyCode){
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button){
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY){
        return false;
    }

    @Override
    public boolean scrolled(float amountx, float amounty){
        return false;
    }

}
