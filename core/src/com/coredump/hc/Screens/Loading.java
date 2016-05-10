package com.coredump.hc.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.BombActor;
import com.coredump.hc.Actors.Scanline;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 2/28/2016.
 */
public class Loading {
    public Stage stage;
    private Viewport viewport;
    private BombActor bomb;


    public Loading(SpriteBatch sb) {

        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);

        bomb = new BombActor();
        Scanline scanLine = new Scanline();

        //ColorBar artifact = new ColorBar(10000,60);
        //ColorBar artifact2 = new ColorBar(30,60);

        Pixmap pm2 = new Pixmap(200,200, Pixmap.Format.RGBA8888);
        pm2.setColor(0f,0f,0f,.7f);
        pm2.fill();
        Texture blackBox2 = new Texture(pm2);
        Sprite bb2 = new Sprite(blackBox2);
        SpriteDrawable sd2 = new SpriteDrawable(bb2);


        Table debugTable = new Table();
        debugTable.setBackground(sd2);
        debugTable.setHeight(HCGame.V_HEIGHT);
        debugTable.setWidth(HCGame.V_WIDTH);
        debugTable.setX(0);
        debugTable.setY(0);
        //debugTable.setX((HCGame.V_WIDTH / 2));
        //debugTable.setY((HCGame.V_HEIGHT / 2));
/*
        for (int i = 1; i < 34; i++) {
            Label label = new Label((">Entered Command ... command that did something. "+i), new Label.LabelStyle(new BitmapFont(), Color.GREEN));
            label.setFontScale(.75f);
            label.setAlignment(Align.left);
            debugTable.add(label).align(Align.left).expandX();
            debugTable.row();
        }
*/
        //stage.addActor(artifact);
        //stage.addActor(artifact2);
        //stage.addActor(debugTable);
        stage.addActor(bomb);
        stage.addActor(scanLine);


        Gdx.input.setInputProcessor(stage);

    }


}