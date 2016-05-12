package com.coredump.hc.Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 3/12/2016.
 */




    public class DebugActor extends Actor {

    private Array<Label> labelArray = new Array<Label>(HCGame.DEBUG_LINES);
    private Table debugTable;
    private HCGame game;

    public DebugActor(HCGame game) {

        this.game = game;
        Pixmap pm2 = new Pixmap(200, 200, Pixmap.Format.RGBA8888);
        pm2.setColor(0f,0f,0f,.7f);

        pm2.fill();
        Texture blackBox2 = new Texture(pm2);
        Sprite bb2 = new Sprite(blackBox2);
        SpriteDrawable sd2 = new SpriteDrawable(bb2);

        debugTable = new Table();
        debugTable.setBackground(sd2);
        debugTable.setHeight(HCGame.V_HEIGHT-64); // 64 = button height
        debugTable.setWidth(HCGame.V_WIDTH);
        debugTable.setX(0);
        debugTable.setY(64); // 64 = button height


    Array<String> debug = game.getDebug();
    for (int i = 0; i <= HCGame.DEBUG_LINES; i++) {
        Label label = new Label((debug.get(i)), new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        labelArray.add(label);
        label.setFontScale(.75f);
        label.setAlignment(Align.left);
        debugTable.add(label).align(Align.topLeft).expandX();
        debugTable.row();
    }

    }
        @Override
        public void draw(Batch batch, float alpha){

            for (int i = 0; i <= HCGame.DEBUG_LINES; i++) {
                labelArray.get(i).setText(game.getDebug().get(i));
            }
            debugTable.draw(batch,alpha);
        }
    }

