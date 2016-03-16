package com.coredump.hc.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.BadActor;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;
import com.coredump.hc.CameraController;

/**
 * Created by Gregory on 3/12/2016.
 */
public class GamePlayField {

    public static int PLAY_HEIGHT = HCGame.V_HEIGHT *2; //silly, I know, but probably very temporary
    public static int PLAY_WIDTH = HCGame.V_WIDTH *2;


    public Stage stage;
    private Viewport viewport;
    public CameraController camControl;


    public GamePlayField(SpriteBatch sb) {

        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);

        camControl = new CameraController((OrthographicCamera) viewport.getCamera());

        Texture img = new Texture("badlogic.jpg");
        Sprite bgSprite = new Sprite(img);
        SpriteDrawable bg = new SpriteDrawable(bgSprite);

        TextureAtlas buttonAtlas = Asset.manager.get(Asset.buttonAtlas,TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);

        Button hackButton = new Button(uiSkin.getDrawable("HackUP"),uiSkin.getDrawable("HackDN"));

        /*
        Table playField = new Table();
        playField.setHeight(PLAY_HEIGHT);
        playField.setWidth(PLAY_WIDTH);

        Table container = new Table();
        container.setHeight(HCGame.V_HEIGHT-100);
        container.setWidth(HCGame.V_WIDTH - 50);
        container.setX((HCGame.V_WIDTH / 2) - container.getWidth() / 2);
        container.setY((HCGame.V_HEIGHT / 2) - container.getHeight() / 2);
        //container.setFillParent(true);

        playField.add(new BadActor()).expandX().expandY().center();


        ScrollPane scroll = new ScrollPane(playField);
//scroll.setScrollingDisabled(false, false);
        scroll.layout();
        container.add(scroll).fill().expand();

        stage.addActor(container);
        */
        hackButton.setX(100f);
        hackButton.setY(100f);
        stage.addActor(hackButton);
        stage.addActor(new BadActor());





        // Gdx.input.setInputProcessor(stage);
        //GestureDetector gestureDetector = new GestureDetector(camControl);
        //Gdx.input.setInputProcessor(gestureDetector);


    }

    public void update(){
        stage.draw();
        stage.act();
        camControl.update();
    }

}
