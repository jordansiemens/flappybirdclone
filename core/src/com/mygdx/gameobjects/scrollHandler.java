package com.mygdx.gameobjects;

public class scrollHandler {

    private grass frontGrass,backGrass;
    private pipe pipe1,pipe2,pipe3;

    public static final int SCROLL_SPEED = -59;
    public static final int PIPE_GAP = 49;

    public scrollHandler(float yPos){
        frontGrass = new grass(0,yPos,143,11,SCROLL_SPEED);
        backGrass = new grass(frontGrass.getTailX(),yPos,143,11,
                SCROLL_SPEED);
        pipe1 = new pipe(210,0,22,60,SCROLL_SPEED,yPos);
        pipe2 = new pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED,yPos);
        pipe3 = new pipe(pipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED,yPos);

    }
    public void update(float delta){
        frontGrass.update(delta);
        backGrass.update(delta);
        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);

        if (pipe1.isScrolledLeft()){
            pipe1.reset(pipe3.getTailX() + PIPE_GAP);
        }else if (pipe2.isScrolledLeft()){
            pipe2.reset(pipe1.getTailX() + PIPE_GAP);
        } else if (pipe3.isScrolledLeft()) {
            pipe3.reset(pipe2.getTailX() + PIPE_GAP);
        }
        if (frontGrass.isScrolledLeft()) {
            frontGrass.reset(backGrass.getTailX());
        } else if (backGrass.isScrolledLeft()){
            backGrass.reset((frontGrass.getTailX()));
        }
    }
    public grass getFrontGrass(){
        return frontGrass;
    }
    public grass getBackGrass(){
        return backGrass;
    }
    public pipe getPipe1(){
        return pipe1;
    }

    public pipe getPipe2() {
        return pipe2;
    }

    public pipe getPipe3() {
        return pipe3;
    }
}
