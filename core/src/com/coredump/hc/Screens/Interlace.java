package com.coredump.hc.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.ColorBar;
import com.coredump.hc.Actors.DebugActor;
import com.coredump.hc.Actors.Scanline;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 2/28/2016.
 */
public class Interlace {
    public Stage stage;
    private Viewport viewport;
    private Game game;

    public Interlace(SpriteBatch sb, HCGame game) {

        this.game = game;
        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);

        Scanline scanLine = new Scanline();

        ColorBar artifact = new ColorBar(10000,60);
        ColorBar artifact2 = new ColorBar(30,60);

        DebugActor debugTable = new DebugActor(game);

        TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack, TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);
        Image main = new Image(uiSkin.getDrawable("Main"));



        stage.addActor(artifact);
        stage.addActor(artifact2);
        stage.addActor(debugTable);
        stage.addActor(scanLine);
        stage.addActor(main);


        Gdx.input.setInputProcessor(stage);

    }


}