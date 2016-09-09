package com.coredump.hc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.coredump.hc.Actions.Action;
import com.coredump.hc.Actions.NoAction;
import com.coredump.hc.Actors.ImageActor;
import com.coredump.hc.Actors.Buttons.GameButton;
import com.coredump.hc.Actors.UnmanagedActor;
import com.coredump.hc.Levels.Level;
import com.coredump.hc.Screens.Alert;
import com.coredump.hc.Screens.ContentPane;
import com.coredump.hc.Screens.ContentViewer;
import com.coredump.hc.Screens.GameHud;
import com.coredump.hc.Screens.GamePlayField;
import com.coredump.hc.Screens.Interlace;
import com.coredump.hc.Screens.Loading;
import com.coredump.hc.Screens.MainMenu;
import com.coredump.hc.Screens.MessageList;
import com.coredump.hc.Screens.MessageView;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HCGame extends Game {

    public enum GameState{LOADING,MAIN,TEXT,PHONE,BBS,PLAY,FAIL,SUCCESS}
    public static final int V_HEIGHT = 480;
    public static final int V_WIDTH = 320;
    public static final int DEBUG_LINES = 27;

    private Action currentAction = new NoAction();


    private Asset asset;
    private GameState gameState;
    private float loadTimer;
    private SpriteBatch batch;
    private Skin uiSkin;
    private Level currentLevel;
    private HCMessage currentMessage;

    private SimpleDateFormat sd;
    private Array<String> debugArray;
    private Array<HCMessage> messages;

    // screens
    private Loading loading;
    private MainMenu mainMenu;
    private Interlace interlace;
    private MessageView messageView;
    private GamePlayField playField;
    private GameHud gameHud;
    private Alert fail;
    private Alert success;
    private ContentViewer viewer;
    private ContentPane content;
    private MessageList messageList;

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
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
                    TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack,TextureAtlas.class);
                    uiSkin = new Skin();
                    uiSkin.addRegions(buttonAtlas);
                    if (currentLevel == null){
                        try {
                            this.loadMessages();
                            Gdx.app.log("Loading","level"+ Asset.getLevel());
                            Constructor<?> cons = Class.forName("com.coredump.hc.Levels.Level"+Asset.getLevel()).getConstructor(HCGame.class);
                            currentLevel = (Level) cons.newInstance(this);

                        }
                        catch (Exception e){
                            Gdx.app.log("Error creating level",e.getMessage());
                            e.printStackTrace();
                            //TODO: Create an error message system for users
                        }
                    }
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
                    fail = new Alert(batch,this,"CONNECTION \r\n TERMINATED", Color.RED);
                }
                playField = null;
                gameHud = null;
                Gdx.input.setInputProcessor(fail.stage);
                fail.stage.draw();
                fail.stage.act();
                break;
            case SUCCESS:
                if (success == null){
                    success = new Alert(batch,this,"MISSION \r\n COMPLETE", Color.GREEN);
                }
                playField = null;
                gameHud = null;
                Gdx.input.setInputProcessor(success.stage);
                success.stage.draw();
                success.stage.act();

                break;
            case TEXT:
                if (messageView == null){
                    messageView = new MessageView(batch,this);
                }
                Gdx.input.setInputProcessor(messageView.stage);
                messageView.stage.draw();
                messageView.stage.act();
                break;
            case PHONE:
                if (messageList == null){
                    messageList = new MessageList(batch,this);
                }
                if (messageView != null){
                    messageView = null;
                }
                Gdx.input.setInputProcessor(messageList.stage);
                messageList.stage.draw();
                messageList.stage.act();
                break;
            case BBS:
                if (content == null){
                    Array<Actor> actors = new Array<Actor>();
                    //actors.add(new MapActor());

                    GameButton hackButton = new GameButton(uiSkin.getDrawable("Hack_UP"),uiSkin.getDrawable("Hack_DN"),this);
                    hackButton.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            HCGame game = ((GameButton) event.getTarget()).getGame();
                            game.addDebug("Forum Button Pressed");
                            Array<Actor> actors = new Array<Actor>();
                            actors.add(new UnmanagedActor("Page01.png"));
                            ContentPane content2 = new ContentPane(batch,game,actors);
                            ((GameButton) event.getTarget()).getGame().setContent(content2);
                        }
                    });

                    actors.add(new UnmanagedActor("Index.png"));
                    actors.add(hackButton);
                    content = new ContentPane(batch,this,actors);
                }
                if (viewer == null){
                    viewer = new ContentViewer(batch,this);
                    gestureDetector = new GestureDetector(content.camControl);
                    inputMultiplexer = new InputMultiplexer(Gdx.input.getInputProcessor());

                    inputMultiplexer.addProcessor(content.stage);
                    inputMultiplexer.addProcessor(gestureDetector);
                    inputMultiplexer.addProcessor(viewer.stage);

                }

                Gdx.input.setInputProcessor(inputMultiplexer);
                content.update();

                viewer.stage.act();
                viewer.stage.draw();
                break;
            case PLAY:
                if (playField == null){
                    currentLevel.initPlay();
                    playField = new GamePlayField(batch,currentLevel,this);
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
                if (currentLevel.getRoundTimer() >= 0) { // only display positive time values
                    gameHud.getTimeLabel().setText(String.format(Locale.US, "%.2f", currentLevel.getRoundTimer()));
                } else {
                    gameHud.getTimeLabel().setVisible(false);
                }
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
        //TODO save state
    }

    @Override
    public void resume(){
        System.out.println("resume");
        asset = new Asset(this);
        asset.load();
        this.setGameState(GameState.LOADING);
    }

    public void loadMessages(){
        this.messages = new Array<HCMessage>();
        this.messages.add(new HCMessage("Test0","This is message 0","EXIT_UP"));
        this.messages.add(new HCMessage("Test1","This is message 1","Skull_UP"));
        this.messages.add(new HCMessage("Test2","This is message 2","EXIT_UP"));
        this.messages.add(new HCMessage("Test3","This is message 3","Skull_UP"));
        this.messages.add(new HCMessage("Test4","This is message 4","EXIT_UP"));
        this.messages.add(new HCMessage("Test5","This is message 5","Skull_UP"));

    };

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

    public Skin getUiSkin() {
        return uiSkin;
    }



    public void setCurrentAction(Action currentAction) {

        this.currentAction = currentAction;
        this.addDebug("Action set to:"+currentAction.getClass());
    }
 
    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setContent(ContentPane content) {
        ContentPane c = this.content; // calls clear() on all actors, custom actors must override clear to dispose of Texture objects!
        this.content = content;
        c.dispose();
        viewer = null;
    }

    public HCMessage getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(HCMessage currentMessage) {
        Gdx.app.log("debug","setting current message:"+currentMessage.getSubject());
        this.currentMessage = currentMessage;
    }

    public Array<HCMessage> getMessages() {
        return messages;
    }
}