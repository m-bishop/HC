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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 3/7/2016.
 */
public class MessageView {

    private final float MESSAGE_WIDTH = 285f;
    private final float MESSAGE_HEIGHT = 200f;

    public Stage stage;
    private Viewport viewport;
    private Table buttonTable;

    //assets
    private Skin uiSkin;
    private TextureAtlas buttonAtlas;
    private Image background;

    public MessageView(SpriteBatch sb){
        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);

        background = new Image(new Sprite((Asset.manager.get(Asset.mainBG,Texture.class))));
        buttonAtlas = Asset.manager.get(Asset.spritePack,TextureAtlas.class);
        uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);

        // TODO replace with a sprite
        Pixmap pm = new Pixmap(200,200, Pixmap.Format.RGBA8888);
        pm.setColor(0f,0f,0f,1f);
        pm.fill();
        Texture blackBox = new Texture(pm);
        Sprite bb = new Sprite(blackBox);
        SpriteDrawable sd = new SpriteDrawable(bb);

        Table listTable = new Table();
        Table container = new Table();
        container.setHeight(MESSAGE_HEIGHT);
        container.setWidth(MESSAGE_WIDTH);
        container.setX((HCGame.V_WIDTH / 2) - MESSAGE_WIDTH /2);
        container.setY((HCGame.V_HEIGHT / 2) - MESSAGE_HEIGHT /2);

        container.setBackground(sd);

        ScrollPane scroll = new ScrollPane(listTable);

        scroll.setScrollingDisabled(true, false);
        scroll.layout();
        container.add(scroll).fill().expand();

        // TODO: load real MESSAGES
        Label line1 = new Label("Congrats!\n\n This message is a test!\nWe hope it looks as good as it possibly can.  ", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        //line1.setWrap(true);
        listTable.add(line1).align(Align.left);


        stage.addActor(background);
        //  stage.addActor(buttonTable);
        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);
    }
}
