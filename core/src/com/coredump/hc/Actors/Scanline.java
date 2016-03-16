package com.coredump.hc.Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 2/28/2016.
 */
public class Scanline extends Actor{

        private ShapeRenderer renderer = new ShapeRenderer();

        private boolean even;


        @Override
        public void draw(Batch batch, float alpha){


            batch.end();

            renderer.setProjectionMatrix(batch.getProjectionMatrix());
            renderer.setTransformMatrix(batch.getTransformMatrix());
            renderer.translate(getX(), getY(), 0);
            renderer.begin(ShapeRenderer.ShapeType.Filled);

            renderer.setColor(Color.BLACK);

            float i = 0;
            if (even){
                i = 1;
                even = false;
            }else {even = true;}
            for (; i<(HCGame.V_HEIGHT);i+=2) {
                renderer.rectLine(0f, i, (HCGame.V_WIDTH ), i, 1f);
            }

            renderer.end();
            
            batch.begin();

        }
    }


