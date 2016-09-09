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
 * Created by Gregory on 8/1/2016.
 */
public class ContentViewer {

    public Stage stage;
    private Viewport viewport;

    public ContentViewer(SpriteBatch sb, HCGame game) {

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
                ((GameButton) event.getTarget()).getGame().setContent(null);
            }
        });

        exitButton.setX(HCGame.V_WIDTH - exitButton.getWidth());
        exitButton.setY(HCGame.V_HEIGHT - exitButton.getHeight());

        stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);

    }


}
