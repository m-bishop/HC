package com.coredump.hc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;


/**
 * Created by Gregory on 4/29/2016.
 */
public class AnimatedDrawable extends BaseDrawable {

    private Animation animation;
    private TextureRegion keyFrame;
    private float stateTime = 0;

    public AnimatedDrawable(Animation animation){

        this.animation = animation;
        TextureRegion key = animation.getKeyFrame(0);

        this.setLeftWidth(key.getRegionWidth()/2);
        this.setRightWidth(key.getRegionWidth()/2);
        this.setTopHeight(key.getRegionHeight()/2);
        this.setBottomHeight(key.getRegionHeight()/2);
        this.setMinWidth(key.getRegionWidth());
        this.setMinHeight(key.getRegionHeight());

    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height){

        stateTime += Gdx.graphics.getDeltaTime();
        keyFrame = animation.getKeyFrame(stateTime, true);

        batch.draw(keyFrame, x,y, keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
    }
}
