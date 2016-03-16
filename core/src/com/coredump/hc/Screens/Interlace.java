package com.coredump.hc.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.Scanline;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 2/28/2016.
 */
public class Interlace {
    public Stage stage;
    private Viewport viewport;


    public Interlace(SpriteBatch sb) {

        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);

        Scanline scanLine = new Scanline();
        stage.addActor(scanLine);

        Gdx.input.setInputProcessor(stage);

    }

}