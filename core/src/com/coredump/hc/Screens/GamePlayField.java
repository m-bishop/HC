package com.coredump.hc.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

    public GamePlayField(SpriteBatch sb, HCGame game) {

        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);
        this.game = game;

        camControl = new CameraController((OrthographicCamera) viewport.getCamera());

        TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack,TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);
        AnimatedDrawable bDraw = new AnimatedDrawable(new Animation(1f,buttonAtlas.findRegions("SYS_B")));
        AnimatedDrawable rDraw = new AnimatedDrawable(new Animation(1f,buttonAtlas.findRegions("SYS_R")));
        GameButton testButton = new GameButton(bDraw,rDraw,game);
        testButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // ((GameButton) event.getTarget()).clicked();
                System.out.println("touched test button");
            }
        });

        NodeActor node = new NodeActor(uiSkin.getDrawable("Hack_UP"),uiSkin.getDrawable("Hack_DN"),new Animation(1f,buttonAtlas.findRegions("SYS_G")),game);
        node.setX(150f);
        node.setY(100f);

        stage.addActor(node);
        stage.addActor(testButton);

    }

    public void update(){
        stage.draw();
        stage.act();
        camControl.update();
    }

}
