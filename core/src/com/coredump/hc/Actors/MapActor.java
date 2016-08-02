package com.coredump.hc.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.coredump.hc.Asset;

/**
 * Created by Gregory on 7/18/2016.
 */
public class MapActor extends Actor {

    private Drawable map;

    public MapActor() {
        TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack, TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);
        map = uiSkin.getDrawable("SCMap");
    }


    @Override
    public void draw(Batch batch, float alpha){
        map.draw(batch,0,0,map.getMinWidth(),map.getMinHeight());
    }
}