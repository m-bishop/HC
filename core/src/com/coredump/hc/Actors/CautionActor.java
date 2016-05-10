package com.coredump.hc.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 3/12/2016.
 */


    public class CautionActor extends Actor {

    private Drawable cautionBar1, cautionBar2;
    private boolean reverse;
    private float speed;
    private float y;
    private float x;

    public CautionActor(float y,float speed,  boolean reverse) {
        this.x = 0; // start at the left most of the screen.
        this.y = y; // set a starting y position on the screen.
        this.speed = speed;
        this.reverse = reverse;


        TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack, TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);
        cautionBar1 = uiSkin.getDrawable("caution");
        cautionBar2 = uiSkin.getDrawable("caution");

    }
        @Override
        public void draw(Batch batch, float alpha){
            x -=  speed* Gdx.graphics.getDeltaTime();
            if ((x + HCGame.V_WIDTH) <= 0 ) { // if bar 1 is to the left of the screen entirely
                x = x + HCGame.V_WIDTH;
            }

            if (reverse){
                cautionBar1.draw(batch,-x,y,HCGame.V_WIDTH,20);
                cautionBar2.draw(batch,(-x)-HCGame.V_WIDTH,y,HCGame.V_WIDTH,20);
            }else {
                cautionBar1.draw(batch, x, y, HCGame.V_WIDTH, 20);
                cautionBar2.draw(batch, x + HCGame.V_WIDTH, y, HCGame.V_WIDTH, 20);
            }
        }
    }

