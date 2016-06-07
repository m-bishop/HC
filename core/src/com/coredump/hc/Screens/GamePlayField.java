package com.coredump.hc.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.GameButton;
import com.coredump.hc.Actors.NodeActor;
import com.coredump.hc.AnimatedDrawable;
import com.coredump.hc.Asset;
import com.coredump.hc.CameraController;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 3/12/2016.
 */
public class GamePlayField {

    public Stage stage;
    private Viewport viewport;
    public CameraController camControl;
    private HCGame game;
    private Array<NodeActor> nodes = new Array<NodeActor>();


    public GamePlayField(SpriteBatch sb, HCGame game) {

        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);
        this.game = game;

        camControl = new CameraController((OrthographicCamera) viewport.getCamera());

        TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack,TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);


        NodeActor node0 = new NodeActor(new AnimatedDrawable(new Animation(1f,buttonAtlas.findRegions("SYS_G"))),new AnimatedDrawable(new Animation(1f,buttonAtlas.findRegions("SYS_D"))),game);
        node0.setX(150f);
        node0.setY(100f);
        node0.setEnabled(true);
        nodes.add(node0);

        NodeActor node1 = new NodeActor(new AnimatedDrawable(new Animation(1f,buttonAtlas.findRegions("SYS_G"))),new AnimatedDrawable(new Animation(1f,buttonAtlas.findRegions("SYS_D"))),game);
        node1.setX(150f);
        node1.setY(184f);
        nodes.add(node1);

        NodeActor node2 = new NodeActor(new AnimatedDrawable(new Animation(1f,buttonAtlas.findRegions("SYS_G"))),new AnimatedDrawable(new Animation(1f,buttonAtlas.findRegions("SYS_D"))),game);
        node2.setX(200f);
        node2.setY(268f);
        nodes.add(node2);

        node0.setChildren(new Array<NodeActor>(new NodeActor[]{node1}));
        node1.setChildren(new Array<NodeActor>(new NodeActor[]{node1,node2}));
        node2.setChildren(new Array<NodeActor>(new NodeActor[]{node1}));

        this.game.startRound(30);

        stage.addActor(node0);
        stage.addActor(node1);
        stage.addActor(node2);

    }

    public void update(){
        viewport.getCamera().update();
        stage.getBatch().setProjectionMatrix(viewport.getCamera().combined);
        stage.act();

        for (NodeActor node : nodes) {
            node.drawNetwork(stage.getBatch());
        }
        stage.draw();

        camControl.update();
    }

}
