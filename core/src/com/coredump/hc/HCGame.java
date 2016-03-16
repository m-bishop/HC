package com.coredump.hc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.coredump.hc.Screens.GameHud;
import com.coredump.hc.Screens.GamePlayField;
import com.coredump.hc.Screens.Interlace;
import com.coredump.hc.Screens.MainMenu;
import com.coredump.hc.Screens.MessageView;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import sun.applet.Main;

public class HCGame extends ApplicationAdapter {

	public enum GameState{LOADING,MAIN,TEXT,PHONE,BBS,PLAY}
	public static final int V_HEIGHT = 480;
	public static final int V_WIDTH = 320;

	private int screenHeight;
	private int screenWidth;

	private Asset asset;
	private GameState gameState;
	private float loadTimer;
	private SpriteBatch batch;
	private Texture img;

	private SimpleDateFormat sd;



	// screens
	private MainMenu mainMenu;
	private Interlace interlace;
	private MessageView messageView;
	private GamePlayField playField;
	private GameHud gameHud;
	
	@Override
	public void create () {
		loadTimer = 0;
		batch = new SpriteBatch();
		interlace = new Interlace(batch);
		img = new Texture("Loading.png");
		gameState = GameState.LOADING;
		asset = new Asset(this);
		asset.load();
		sd = new SimpleDateFormat ("hh:mm:ss:SS");

		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		switch (gameState){
			case LOADING:
				batch.begin();
				batch.draw(img, 0, 0,V_WIDTH,V_HEIGHT);
				loadTimer += Gdx.graphics.getDeltaTime();
				if (Asset.manager.update() && loadTimer > 3.0f){
					gameState = GameState.MAIN;
				}
				batch.end();
				break;
			case MAIN:
				if (mainMenu == null){
					mainMenu = new MainMenu(batch);
				}
				mainMenu.getTimeLabel().setText(sd.format(new Date()));
				mainMenu.stage.draw();
				mainMenu.stage.act();
			break;
			case TEXT:
				break;
			case PHONE:
				break;
			case BBS:
				break;
			case PLAY:
				if (playField == null){
					playField = new GamePlayField(batch);
				}
				if (gameHud == null){
					gameHud = new GameHud(batch);
					GestureDetector gestureDetector = new GestureDetector(playField.camControl);
					InputMultiplexer inputMultiplexer = new InputMultiplexer(Gdx.input.getInputProcessor());

					inputMultiplexer.addProcessor(gestureDetector);
					inputMultiplexer.addProcessor(playField.stage);
					inputMultiplexer.addProcessor(gameHud.stage);

					Gdx.input.setInputProcessor(inputMultiplexer);
				}
				playField.update();
				gameHud.stage.draw();
				gameHud.stage.act();
				break;
		}

		/*
		if (gameState != gameState.LOADING){
			if (messageView == null){
				messageView = new MessageView(batch);
			}
			messageView.stage.draw();
			messageView.stage.act();
		}
		*/

		//render interlacing
		batch.setProjectionMatrix(interlace.stage.getCamera().combined);
		interlace.stage.draw();
	}

	public void dispose(){
		super.dispose();
		asset.dispose();
		if (mainMenu != null) {
			mainMenu.dispose();
		}
	}

	@Override
	public void pause(){

	}

	@Override
	public void resume(){
		System.out.println("resume");
		//mainMenu.stage.setViewport(new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new OrthographicCamera()));

		/*
		mainMenu.stage.getViewport().setScreenSize(screenHeight, screenWidth);
		mainMenu.stage.getViewport().setWorldWidth(HCGame.V_WIDTH);
		mainMenu.stage.getViewport().setWorldHeight(HCGame.V_HEIGHT);
		*/

		mainMenu = new MainMenu(batch);



	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
}
