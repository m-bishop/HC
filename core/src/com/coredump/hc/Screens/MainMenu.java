package com.coredump.hc.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.Buttons.GameButton;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;


/**
 * Created by Gregory on 3/3/2016.
 */
public class MainMenu {

    private final float ALERT_WIDTH = 270f;
    private final float ALERT_HEIGHT = 180f;


    public Stage stage;
    private Viewport viewport;
    private Table timeTable;
    private Table buttonTable;
    private HCGame game;

    //assets
    private Skin uiSkin;
    private TextureAtlas buttonAtlas;


    //elements
    private Image background;
    private Label timeLabel;
    private Button hackButton;
    private Button phoneButton;
    private Button bbsButton;


    public MainMenu(SpriteBatch sb,HCGame game){
        this.game = game;
        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);

        //background = new Image(new Sprite((Asset.manager.get(Asset.mainBG,Texture.class))));

        buttonAtlas = Asset.manager.get(Asset.spritePack,TextureAtlas.class);
        uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);

        Pixmap pm = new Pixmap(200,200, Pixmap.Format.RGBA8888);
        pm.setColor(0f,0f,0f,1f);
        pm.fill();
        Texture blackBox = new Texture(pm);
        Sprite bb = new Sprite(blackBox);
        SpriteDrawable sd = new SpriteDrawable(bb);

        background = new Image(uiSkin.getDrawable("Main"));
        Image msgWindow = new Image(uiSkin.getDrawable("MsgWindowFrame"));



        timeLabel = new Label("00:00:00.000", new Label.LabelStyle(new BitmapFont(), Color.RED));
        timeLabel.setFontScale(2.5f);
        timeLabel.setAlignment(Align.center);
        timeLabel.getStyle().background = sd;
        timeLabel.setX((HCGame.V_WIDTH / 2) - timeLabel.getWidth() / 2);
        timeLabel.setY((HCGame.V_HEIGHT / 5) * 4);

        Image clockBorder = new Image(uiSkin.getDrawable("clockBorder"));
        clockBorder.setWidth(150);
        clockBorder.setHeight(50);
        clockBorder.setX(((HCGame.V_WIDTH / 2) - clockBorder.getWidth() / 2) - 2);
        clockBorder.setY(((timeLabel.getY()) - ((timeLabel.getHeight() * 2.5f) / 2) + (clockBorder.getHeight() / 2)) - 15);


        hackButton = new GameButton(uiSkin.getDrawable("Hack_UP"),uiSkin.getDrawable("Hack_DN"),game);
        hackButton.addListener(new ClickListener() {
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                       ((GameButton) event.getTarget()).getGame().addDebug(">Hack Button Pressed:");
                                       ((GameButton) event.getTarget()).getGame().setGameState(HCGame.GameState.PLAY);
                                   }
                               });

        phoneButton = new GameButton(uiSkin.getDrawable("Phone_UP"),uiSkin.getDrawable("Phone_DN"),game);
        phoneButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((GameButton) event.getTarget()).getGame().addDebug(">phone Button  Pressed:");
                ((GameButton) event.getTarget()).getGame().setGameState(HCGame.GameState.PHONE);
            }
        });
        bbsButton = new GameButton(uiSkin.getDrawable("Skull_UP"),uiSkin.getDrawable("Skull_DN"),game);
        bbsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((GameButton) event.getTarget()).getGame().addDebug(">bbs Button Pressed:");
                ((GameButton) event.getTarget()).getGame().setGameState(HCGame.GameState.BBS);
            }
        });


        //timeTable.add(timeLabel).padTop(40).center();
        //timeTable.row().fillY().expandY();
        buttonTable = new Table();
        buttonTable.bottom().setFillParent(true);
        buttonTable.add(hackButton).expandX();
        buttonTable.add(phoneButton).expandX();
        buttonTable.add(bbsButton).expandX();


        Table listTable = new Table();
        Table container = new Table();
        container.setHeight(ALERT_HEIGHT);
        container.setWidth(ALERT_WIDTH);
        container.setX((HCGame.V_WIDTH / 2) - ALERT_WIDTH / 2);
        container.setY((HCGame.V_HEIGHT / 2) - ALERT_HEIGHT / 2);
        msgWindow.setX(container.getX() - 7);
        msgWindow.setY(container.getY() - 5);



        container.setBackground(sd);

        ScrollPane scroll = new ScrollPane(listTable);

        scroll.setScrollingDisabled(true, false);
        scroll.layout();
        container.add(scroll).fill().expand();


        // TODO: load real alerts
        Label line1 = new Label("Mission Accomplished! 20 Rep earned.  ", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        //line1.setFontScale(1.5f);
        listTable.add(line1);
        listTable.row();
        for (int i = 1; i < 20; i++) {
            Label label = new Label(("New Voice Mail! "+i), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
            //label.setFontScale(1.5f);
            label.setAlignment(Align.left);
            listTable.add(label).align(Align.left);
            listTable.row();
        }
        listTable.row();
        listTable.add(new Button(uiSkin.getDrawable("Skull_UP"), uiSkin.getDrawable("Skull_DN")));
        listTable.row();
        listTable.add(new Button(uiSkin.getDrawable("Skull_UP"), uiSkin.getDrawable("Skull_DN")));
        listTable.row();
        Button test = new Button(uiSkin.getDrawable("Skull_UP"),uiSkin.getDrawable("Skull_DN"));
        test.setX(200);
        test.setY(200);
        listTable.add(test);
        listTable.row();
        listTable.add(new Button(uiSkin.getDrawable("Skull_UP"), uiSkin.getDrawable("Skull_DN")));

        Image buttonBack = new Image(uiSkin.getDrawable("blackbox"));
        buttonBack.setX(0);
        buttonBack.setY(0);
        buttonBack.setHeight(64);
        buttonBack.setWidth(HCGame.V_WIDTH);

        stage.addActor(clockBorder);
        stage.addActor(timeLabel);
        stage.addActor(buttonBack);
        stage.addActor(buttonTable);
        stage.addActor(msgWindow);
        stage.addActor(container);





        Gdx.input.setInputProcessor(stage);
    }

    public void dispose(){
        stage.dispose();
    }

    public Label getTimeLabel() {
        return this.timeLabel;
    }


}
