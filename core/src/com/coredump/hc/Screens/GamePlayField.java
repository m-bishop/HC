package com.coredump.hc.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.Nodes.DataBaseNode;
import com.coredump.hc.Actors.Nodes.FirewallNode;
import com.coredump.hc.Actors.Nodes.NodeActor;
import com.coredump.hc.Actors.Nodes.SystemNode;
import com.coredump.hc.AnimatedDrawable;
import com.coredump.hc.Asset;
import com.coredump.hc.CameraController;
import com.coredump.hc.HCGame;
import com.coredump.hc.Levels.Level;

/**
 * Created by Gregory on 3/12/2016.
 */
public class GamePlayField {

    public Stage stage;
    private Viewport viewport;
    public CameraController camControl;
    private HCGame game;
    private Level level;


    public GamePlayField(SpriteBatch sb, Level level, HCGame game) {

        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);
        this.game = game;
        this.level = level;

        camControl = new CameraController((OrthographicCamera) viewport.getCamera());

        TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack,TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);

        for (NodeActor node : level.getNodes()) {
            stage.addActor(node);
        }

    }

    public void update(){
        level.act();
        viewport.getCamera().update();
        stage.getBatch().setProjectionMatrix(viewport.getCamera().combined);
        stage.act();

        for (NodeActor node : level.getNodes()) {
            node.drawNetwork(stage.getBatch());
        }
        stage.draw();

        camControl.update();
    }

}
