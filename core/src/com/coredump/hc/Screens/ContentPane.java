package com.coredump.hc.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.Nodes.NodeActor;
import com.coredump.hc.Asset;
import com.coredump.hc.CameraController;
import com.coredump.hc.HCGame;
import com.coredump.hc.Levels.Level;

/**
 * Created by Gregory on 8/1/2016.
 */
public class ContentPane {

    public Stage stage;
    private Viewport viewport;
    public CameraController camControl;
    private HCGame game;
    private Array<Actor> actors;

    public ContentPane(SpriteBatch sb, HCGame game, Array<Actor> actors) {

        this.actors = actors;
        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);
        this.game = game;


        camControl = new CameraController((OrthographicCamera) viewport.getCamera());

        TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack,TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);

        for (Actor actor : actors) {
            stage.addActor(actor);
        }

    }

    public void update(){

        viewport.getCamera().update();
        stage.getBatch().setProjectionMatrix(viewport.getCamera().combined);
        stage.act();
        stage.draw();
        camControl.update();
    }

    public void dispose(){
        for (Actor actor : actors){
            actor.clear(); // overridden clear() for custom actors that need to dispose of Texture objects.
        }
    }
}
