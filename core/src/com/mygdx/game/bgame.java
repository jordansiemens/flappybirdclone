package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.birdhelpers.AssetLoader;
import com.mygdx.screens.GameScreen;

public class bgame extends Game {

    @Override
    public void create(){
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose(){
        super.dispose();
        AssetLoader.dispose();
    }

}
