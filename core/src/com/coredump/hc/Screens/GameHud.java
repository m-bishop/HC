package com.coredump.hc.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 3/12/2016.
 */
public class GameHud {
    public Stage stage;
    private Viewport viewport;

    public GameHud(SpriteBatch sb) {

        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);

        TextureAtlas buttonAtlas = Asset.manager.get(Asset.buttonAtlas,TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);

        Button hackButton = new Button(uiSkin.getDrawable("HackUP"),uiSkin.getDrawable("HackDN"));
        hackButton.setX(200f);
        hackButton.setY(10f);
        stage.addActor(hackButton);



        Gdx.input.setInputProcessor(stage);

    }

}
