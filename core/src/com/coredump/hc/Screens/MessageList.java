package com.coredump.hc.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.Buttons.GameButton;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;
import com.coredump.hc.HCMessage;

/**
 * Created by Gregory on 9/1/2016.
 */
public class MessageList {

    private final float ALERT_WIDTH = 270f;
    private final float ALERT_HEIGHT = 180f;

    public Stage stage;
    private Viewport viewport;
    private Game game;

    public MessageList(SpriteBatch sb, HCGame game){
        this.game = game;
        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);

        TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack,TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);

        Button exitButton = new GameButton(uiSkin.getDrawable("EXIT_UP"),uiSkin.getDrawable("EXIT_DN"),game);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((GameButton) event.getTarget()).getGame().addDebug(">HUD exit Button Pressed:");
                ((GameButton) event.getTarget()).getGame().setGameState(HCGame.GameState.MAIN);
            }
        });

        exitButton.setX(HCGame.V_WIDTH - exitButton.getWidth());
        exitButton.setY(HCGame.V_HEIGHT - exitButton.getHeight());

        //Image msgWindow = new Image(uiSkin.getDrawable("blackbox"));
        Image msgWindow = new Image(uiSkin.getDrawable("MsgWindowFrame"));
        //msgWindow.setX(0);
        //msgWindow.setY(150);
        //msgWindow.setHeight(200);
        //msgWindow.setWidth(HCGame.V_WIDTH);

        Table listTable = new Table();
        Table container = new Table();
        container.setHeight(ALERT_HEIGHT);
        container.setWidth(ALERT_WIDTH);
        container.setX((HCGame.V_WIDTH / 2) - ALERT_WIDTH / 2);
        container.setY((HCGame.V_HEIGHT / 2) - ALERT_HEIGHT / 2);
        msgWindow.setX(container.getX() - 7);
        msgWindow.setY(container.getY() - 5);



        //container.setBackground(msgWindow);

        ScrollPane scroll = new ScrollPane(listTable);

        scroll.setScrollingDisabled(true, false);
        scroll.layout();
        container.add(scroll).fill().expand();


        // TODO: load real alerts

        for (HCMessage message : game.getMessages()){

            GameButton testbutton = new GameButton(uiSkin.getDrawable("Skull_UP"),uiSkin.getDrawable("Skull_DN"),game);
            testbutton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((GameButton) event.getTarget()).getGame().addDebug(">test Button Pressed:");
                    ((GameButton) event.getTarget()).getGame().setGameState(HCGame.GameState.TEXT);
                }
            });

            Label testButtonLabel = new Label(message.getSubject(), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
            testButtonLabel.setAlignment(Align.left);
            listTable.add(testButtonLabel).align(Align.left);
            listTable.add(testbutton).expand().align(Align.right);
            listTable.row();
        }

        stage.addActor(exitButton);
        stage.addActor(msgWindow);
        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);
    }
}
