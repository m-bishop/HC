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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.Buttons.ExtinguishButton;
import com.coredump.hc.Actors.Buttons.GameButton;
import com.coredump.hc.Actors.Buttons.IcePickButton;
import com.coredump.hc.Actors.Buttons.SaveButton;
import com.coredump.hc.Actors.Buttons.ScanButton;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 3/12/2016.
 */
public class GameHud {
    private final float BUTTON_WIDTH = 270f;
    private final float BUTTON_HEIGHT = 180f;

    public Stage stage;
    private Viewport viewport;
    private Label timeLabel;

    public GameHud(SpriteBatch sb,HCGame game) {

        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);

        TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack,TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);

        Pixmap pm = new Pixmap(200,200, Pixmap.Format.RGBA8888);
        pm.setColor(0f,0f,0f,1f);
        pm.fill();
        Texture blackBox = new Texture(pm);
        Sprite bb = new Sprite(blackBox);
        SpriteDrawable sd = new SpriteDrawable(bb);

        Table listTable = new Table();
        Table container = new Table();
        container.setHeight(64);
        container.setWidth(HCGame.V_WIDTH);
        container.setX(0);
        container.setY(0);
        //container.setX((HCGame.V_WIDTH / 2) - BUTTON_WIDTH / 2);
        //container.setY((HCGame.V_HEIGHT / 2) - BUTTON_HEIGHT / 2);

        container.setBackground(sd);
        ScrollPane scroll = new ScrollPane(listTable);
        scroll.setScrollingDisabled(false, true);
        scroll.layout();
        container.add(scroll).fill().expand();



        Button exitButton = new GameButton(uiSkin.getDrawable("EXIT_UP"),uiSkin.getDrawable("EXIT_DN"),game);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((GameButton) event.getTarget()).getGame().addDebug(">HUD exit Button Pressed:");
                ((GameButton) event.getTarget()).getGame().setGameState(HCGame.GameState.FAIL);
            }
        });

        exitButton.setX(HCGame.V_WIDTH - exitButton.getWidth());
        exitButton.setY(HCGame.V_HEIGHT - exitButton.getHeight());

        /*
        Button scanButton = new GameButton(uiSkin.getDrawable("Scan_R"),uiSkin.getDrawable("Scan_D"),game);
        //scanButton.setX(10f);
        //scanButton.setY(10f);
        scanButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((GameButton) event.getTarget()).getGame().addDebug(">Scan Button Pressed:");
                ((GameButton) event.getTarget()).getGame().setCurrentAction(new ScanAction());
            }
        });
        */

        ScanButton scanButton = new ScanButton(game);

        Button extinguishButton = new ExtinguishButton(game);

        Button pickButton = new IcePickButton(game);

        Button saveButton = new SaveButton(game);

        listTable.add(scanButton);
        listTable.add(extinguishButton);
        listTable.add(pickButton);
        listTable.add(saveButton);


        //stage.addActor(saveButton);
        //stage.addActor(pickButton);
        //stage.addActor(extinguishButton);
        //stage.addActor(scanButton);

        timeLabel = new Label("00:00:00", new Label.LabelStyle(new BitmapFont(), Color.RED));
        timeLabel.setFontScale(2.5f);
        timeLabel.setAlignment(Align.center);
        timeLabel.setX((HCGame.V_WIDTH / 2) - timeLabel.getWidth() / 2);
        timeLabel.setY(HCGame.V_HEIGHT - timeLabel.getHeight()-5);

        stage.addActor(container);
        stage.addActor(timeLabel);
        stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);

    }

    public Label getTimeLabel() {
        return timeLabel;
    }

}
