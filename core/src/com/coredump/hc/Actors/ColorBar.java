package com.coredump.hc.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;
/**
 * Created by Gregory on 2/28/2016.
 */
public class ColorBar extends Actor{

        //private ShapeRenderer renderer = new ShapeRenderer();


        private float barY = 0f;
        private int speed = 30;
        private int size = 60;
        private Image colorBar;


        public ColorBar(int speed, int size){
            this.speed = speed;
            this.size = size;

            TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack, TextureAtlas.class);
            Skin uiSkin = new Skin();
            uiSkin.addRegions(buttonAtlas);
            colorBar = new Image(uiSkin.getDrawable("colorbar"));
        }

        @Override
        public void draw(Batch batch, float alpha){
            if (barY >= HCGame.V_HEIGHT){barY = -this.size;}
            barY = barY + (speed* Gdx.graphics.getDeltaTime());
            colorBar.setSize(HCGame.V_WIDTH,size);
            colorBar.setX(0);
            colorBar.setY(barY);
            colorBar.draw(batch,alpha);



            

/*
            batch.end();

            renderer.setProjectionMatrix(batch.getProjectionMatrix());
            renderer.setTransformMatrix(batch.getTransformMatrix());
            renderer.translate(getX(), getY(), 0);
            renderer.begin(ShapeRenderer.ShapeType.Filled);

            renderer.setColor(Color.GREEN);

            if (barY >= HCGame.V_HEIGHT){barY = -this.size;}
            barY = barY + (speed* Gdx.graphics.getDeltaTime());

            //barY = ThreadLocalRandom.current().nextInt(0, HCGame.V_HEIGHT + 1);

            renderer.rectLine(0f, barY, (HCGame.V_WIDTH), barY, size);
            renderer.end();
            
            batch.begin();
           */

        }
    }


