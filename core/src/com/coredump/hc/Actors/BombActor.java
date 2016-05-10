package com.coredump.hc.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Gregory on 3/12/2016.
 */


    public class BombActor extends Actor {
        Texture texture = new Texture(Gdx.files.internal("Loading.png"));
        @Override
        public void draw(Batch batch, float alpha){
            batch.draw(texture,0,0);
        }
    }

