package com.mygdx.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.mygdx.birdhelpers.AssetLoader;
import com.mygdx.gameobjects.bird;
import com.mygdx.gameobjects.grass;
import com.mygdx.gameobjects.pipe;
import com.mygdx.gameobjects.scrollHandler;

public class GameRenderer {

    private GameWorld _world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batch;

    private int ymidpoint;
    private int gameheight;

    //Game obj
    private bird _bird;
    private scrollHandler scroller;
    private grass frontGrass, backGrass;
    private pipe pipe1,pipe2,pipe3;

    //Game assets
    private TextureRegion bg,grass;
    private Animation<TextureRegion> birdAnimation;
    private TextureRegion birdMid, birdDown, birdUp;
    private TextureRegion skullUp, skullDown, bar;

    private void drawGrass(){
        batch.draw(grass,frontGrass.getX(),frontGrass.getY(),
                frontGrass.getWidth(),frontGrass.getHeight());
        batch.draw(grass,backGrass.getX(),backGrass.getY(),
                backGrass.getWidth(),backGrass.getHeight());
    }

    private void drawSkulls() {

        batch.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batch.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batch.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batch.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batch.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batch.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);

    }

    private void drawPipes(){
        batch.draw(bar,pipe1.getX(),pipe1.getY(),pipe1.getWidth(),
                pipe1.getHeight());
        batch.draw(bar,pipe1.getX(),pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), ymidpoint + 66 - (pipe1.getHeight() + 45));

        batch.draw(bar,pipe2.getX(),pipe2.getY(),pipe2.getWidth(),
                pipe2.getHeight());
        batch.draw(bar,pipe2.getX(),pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), ymidpoint + 66 - (pipe2.getHeight() + 45));

        batch.draw(bar,pipe3.getX(),pipe3.getY(),pipe3.getWidth(),
                pipe3.getHeight());
        batch.draw(bar,pipe3.getX(),pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), ymidpoint + 66 - (pipe3.getHeight() + 45));
    }


    public GameRenderer (GameWorld world, int gameheight, int ymidpoint){
        _world = world;

        this.gameheight = gameheight;
        this.ymidpoint = ymidpoint;

        camera = new OrthographicCamera();
        camera.setToOrtho(true,136,204);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
    }

    public void render(float runTime){

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //bg color
        shapeRenderer.setColor(55/255.0f,80/255.0f,100/255.0f,1);
        shapeRenderer.rect(0,0,136,ymidpoint + 66);

        //grass color
        shapeRenderer.setColor(111/255.0f,186/255.0f,45/255.0f,1);
        shapeRenderer.rect(0,ymidpoint + 66,136,11);

        //dirt color
        shapeRenderer.setColor(147/255.0f,80/255.0f,27/255.0f,1);
        shapeRenderer.rect(0,ymidpoint + 77,136,52);

        shapeRenderer.end();

        batch.begin();

        batch.disableBlending();
        batch.draw(bg,0,ymidpoint + 23, 136, 43);

        drawGrass();
        drawPipes();
        batch.enableBlending();
        drawSkulls();

        if (_bird.shouldntFlap()){
            batch.draw(birdMid, _bird.getX(), _bird.getY(),_bird.getWidth()/2.0f,_bird.getHeight()/2.0f,
                    _bird.getWidth(),_bird.getHeight(),1,1,_bird.getRotation());
        } else {
            batch.draw(birdAnimation.getKeyFrame(runTime),_bird.getX(),
                    _bird.getY(),_bird.getWidth()/2.0f,_bird.getHeight()/2.0f,
                    _bird.getWidth(),_bird.getHeight(),1,1,_bird.getRotation());
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(_bird.getBoundingCircle().x,_bird.getBoundingCircle().y,_bird.getBoundingCircle().radius);

        shapeRenderer.rect(pipe1.getBarUp().x, pipe1.getBarUp().y,
                pipe1.getBarUp().width, pipe1.getBarUp().height);
        shapeRenderer.rect(pipe2.getBarUp().x, pipe2.getBarUp().y,
                pipe2.getBarUp().width, pipe2.getBarUp().height);
        shapeRenderer.rect(pipe3.getBarUp().x, pipe3.getBarUp().y,
                pipe3.getBarUp().width, pipe3.getBarUp().height);

        // Bar down for pipes 1 2 and 3
        shapeRenderer.rect(pipe1.getBarDown().x, pipe1.getBarDown().y,
                pipe1.getBarDown().width, pipe1.getBarDown().height);
        shapeRenderer.rect(pipe2.getBarDown().x, pipe2.getBarDown().y,
                pipe2.getBarDown().width, pipe2.getBarDown().height);
        shapeRenderer.rect(pipe3.getBarDown().x, pipe3.getBarDown().y,
                pipe3.getBarDown().width, pipe3.getBarDown().height);

        // Skull up for Pipes 1 2 and 3
        shapeRenderer.rect(pipe1.getSkullUp().x, pipe1.getSkullUp().y,
                pipe1.getSkullUp().width, pipe1.getSkullUp().height);
        shapeRenderer.rect(pipe2.getSkullUp().x, pipe2.getSkullUp().y,
                pipe2.getSkullUp().width, pipe2.getSkullUp().height);
        shapeRenderer.rect(pipe3.getSkullUp().x, pipe3.getSkullUp().y,
                pipe3.getSkullUp().width, pipe3.getSkullUp().height);

        // Skull down for Pipes 1 2 and 3
        shapeRenderer.rect(pipe1.getSkullDown().x, pipe1.getSkullDown().y,
                pipe1.getSkullDown().width, pipe1.getSkullDown().height);
        shapeRenderer.rect(pipe2.getSkullDown().x, pipe2.getSkullDown().y,
                pipe2.getSkullDown().width, pipe2.getSkullDown().height);
        shapeRenderer.rect(pipe3.getSkullDown().x, pipe3.getSkullDown().y,
                pipe3.getSkullDown().width, pipe3.getSkullDown().height);

        shapeRenderer.end();
        shapeRenderer.end();
    }
    private void initGameObjects(){
        _bird = _world.getBird();
        scroller = _world.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
    }

    private void initAssets(){
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        birdAnimation = AssetLoader.birdAnimation;
        birdMid = AssetLoader.bird;
        birdDown = AssetLoader.birdDown;
        birdUp = AssetLoader.birdUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }
}
