package com.coredump.hc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Array;
import com.coredump.hc.Actions.Action;
import com.coredump.hc.Actions.NoAction;
import com.coredump.hc.Screens.Fail;
import com.coredump.hc.Screens.GameHud;
import com.coredump.hc.Screens.GamePlayField;
import com.coredump.hc.Screens.Interlace;
import com.coredump.hc.Screens.Loading;
import com.coredump.hc.Screens.MainMenu;
import com.coredump.hc.Screens.MessageView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HCGame extends Game {

    public enum GameState{LOADING,MAIN,TEXT,PHONE,BBS,PLAY,FAIL}
    public static final int V_HEIGHT = 480;
    public static final int V_WIDTH = 320;
    public static final int DEBUG_LINES = 27;

    private Action currentAction = new NoAction();


    private Asset asset;
    private GameState gameState;
    private float loadTimer;
    private SpriteBatch batch;


    private SimpleDateFormat sd;
    private Array<String> debugArray;



    // screens
    private Loading loading;
    private MainMenu mainMenu;
    private Interlace interlace;
    private MessageView messageView;
    private GamePlayField playField;
    private GameHud gameHud;
    private Fail fail;

    //input
    private GestureDetector gestureDetector ;
    private InputMultiplexer inputMultiplexer;

    @Override
    public void create () {
        debugArray = new Array<String>(DEBUG_LINES);
        for (int i = 0; i <= DEBUG_LINES; i++) {
            debugArray.add("initializing...");
        }
        loadTimer = 0;
        batch = new SpriteBatch();
        //interlace = new Interlace(batch, this);

        gameState = GameState.LOADING;
        asset = new Asset(this);
        asset.load();
        //sd = new SimpleDateFormat ("hh:mm:ss:SS");
        sd = new SimpleDateFormat ("hh:mm:ss");

    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, .1f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (interlace != null) {
            //render interlacing
            batch.setProjectionMatrix(interlace.stage.getCamera().combined);
            interlace.stage.draw();
            interlace.stage.act();
        }

        switch (gameState){
            case LOADING:
                this.addDebug("Loading...");
                if (loading == null){
                    loading = new Loading(batch);
                }
                loading.stage.draw();
                loadTimer += Gdx.graphics.getDeltaTime();
                if (Asset.manager.update() && loadTimer > 3.0f){
                    this.addDebug("Assets loaded.");
                    this.addDebug("Loading Main Menu.");
                    this.addDebug("Test1");
                    this.addDebug("Test2");
                    this.addDebug("Test3");
                    gameState = GameState.MAIN;
                    //gameState = GameState.FAIL;
                    loading.stage.dispose();
                    loading = null;
                    interlace = new Interlace(batch, this);
                }

                break;
            case MAIN:
                if (mainMenu == null){
                    mainMenu = new MainMenu(batch,this);
                }
                Gdx.input.setInputProcessor(mainMenu.stage);
                mainMenu.getTimeLabel().setText(sd.format(new Date()));
                batch.setProjectionMatrix(mainMenu.stage.getCamera().combined);
                mainMenu.stage.draw();
                mainMenu.stage.act();
                break;
            case FAIL:
                if (fail == null){
                    fail = new Fail(batch,this);
                }
                Gdx.input.setInputProcessor(fail.stage);
                fail.stage.draw();
                fail.stage.act();
                break;
            case TEXT:
                break;
            case PHONE:
                break;
            case BBS:
                break;
            case PLAY:
                if (playField == null){
                    playField = new GamePlayField(batch,this);
                }
                if (gameHud == null){
                    gameHud = new GameHud(batch,this);
                    gestureDetector = new GestureDetector(playField.camControl);
                    inputMultiplexer = new InputMultiplexer(Gdx.input.getInputProcessor());

                    inputMultiplexer.addProcessor(playField.stage);
                    inputMultiplexer.addProcessor(gestureDetector);
                    inputMultiplexer.addProcessor(gameHud.stage);

                }

                Gdx.input.setInputProcessor(inputMultiplexer);
                playField.update();
                //playField.stage.act();
                //playField.stage.draw();
                gameHud.stage.act();
                gameHud.stage.draw();
                break;
        }

    }

    public void addDebug(String debug){
        for (int i=0; i < DEBUG_LINES; i++){
            debugArray.set(i,debugArray.get(i+1));
        }
        debugArray.set(DEBUG_LINES, debug);
    }

    public Array<String> getDebug(){
        return this.debugArray;
    }

    public void dispose(){
        super.dispose();
        asset.dispose();
        if (mainMenu != null) {
            mainMenu.dispose();
        }
    }

    @Override
    public void resize(int width, int height){
        if (mainMenu != null) {
            mainMenu.stage.getViewport().update(width, height);
        }
        if (interlace != null) {
            interlace.stage.getViewport().update(width, height);
        }
    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){
        System.out.println("resume");

        mainMenu = new MainMenu(batch,this);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Action getCurrentAction() {
        Action tempAction = currentAction;
        this.currentAction = new NoAction();
        return tempAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }
}