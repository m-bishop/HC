package com.coredump.hc.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.coredump.hc.Asset;

/**
 * Created by Gregory on 3/12/2016.
 */


    public class ImageActor extends Actor {
        TextureRegion texture;

        public ImageActor(String imgName){
            TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack,TextureAtlas.class);
            texture = buttonAtlas.findRegion(imgName);
            this.setHeight(texture.getRegionHeight());
            this.setWidth(texture.getRegionWidth());
        }

    @Override
        public void draw(Batch batch, float alpha){
            batch.draw(texture,this.getX(),this.getY(),texture.getRegionWidth() ,texture.getRegionHeight());
        }


    }

