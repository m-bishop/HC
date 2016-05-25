package com.coredump.hc.Actions;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.coredump.hc.Actors.NodeActor;
import com.coredump.hc.HCGame;

import javafx.animation.KeyFrame;

/**
 * Created by Gregory on 5/9/2016.
 */
public abstract class Action {

    protected boolean complete = false;

    public Action(){

    }

    public boolean hasKeyframe(){
        return false;
    }

    public TextureRegion getKeyFrame(){
        return new TextureRegion();
    }

    public abstract void act(HCGame game,NodeActor node);

    public boolean isComplete() {
        return complete;
    }


}
