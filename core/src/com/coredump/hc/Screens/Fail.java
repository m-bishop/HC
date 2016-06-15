package com.coredump.hc.Screens;

import com.badlogic.gdx.Game;
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
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.CautionActor;
import com.coredump.hc.Actors.Buttons.GameButton;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 2/28/2016.
 */
public class Fail {
    private final float fontScale = 2.5f;
    public Stage stage;
    private Viewport viewport;
    private Game game;

    public Fail(SpriteBatch sb, HCGame game) {

        this.game = game;
        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);

        Pixmap pm = new Pixmap(200,200, Pixmap.Format.RGBA8888);
        pm.setColor(0f,0f,0f,1f);
        pm.fill();
        Texture blackBox = new Texture(pm);
        Sprite bb = new Sprite(blackBox);
        SpriteDrawable sd = new SpriteDrawable(bb);

       Label connectionLabel = new Label("CONNECTION \r\n TERMINATED", new Label.LabelStyle(new BitmapFont(), Color.RED));
        connectionLabel.setFontScale(fontScale);
        connectionLabel.setAlignment(Align.center);
        connectionLabel.setX((HCGame.V_WIDTH / 2) - (connectionLabel.getWidth() / 2));
        connectionLabel.setY((HCGame.V_HEIGHT / 2));

        RepeatAction rAction = new RepeatAction();
        rAction.setCount(RepeatAction.FOREVER);
        rAction.setAction(new RunnableAction() {
            private float delay = 0;
            private boolean visible;

            @Override
            public void run() {
                delay += Gdx.graphics.getDeltaTime();
                if (delay >= .5) {
                    delay = 0;
                    visible = !visible;
                }
                this.getTarget().setVisible(visible);
            }
        });

        connectionLabel.addAction(rAction);

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

        CautionActor cautionTop = new CautionActor(connectionLabel.getY()+(connectionLabel.getHeight()*fontScale),100,true);
        CautionActor cautionBottom = new CautionActor(connectionLabel.getY()-(connectionLabel.getHeight()*fontScale),100,false);


        Image msgWindow = new Image(uiSkin.getDrawable("blackbox"));
        msgWindow.setX(0);
        msgWindow.setY(150);
        msgWindow.setHeight(200);
        msgWindow.setWidth(HCGame.V_WIDTH);

        stage.addActor(msgWindow);
        stage.addActor(cautionTop);
        stage.addActor(connectionLabel);
        stage.addActor(cautionBottom);
        stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);

    }
}