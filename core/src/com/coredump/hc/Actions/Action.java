package com.coredump.hc.Actions;


import com.coredump.hc.Actors.NodeActor;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 5/9/2016.
 */
public abstract class Action {

    protected boolean complete = false;

    public Action(){

    }

    public abstract void act(HCGame game,NodeActor node);

    public boolean isComplete() {
        return complete;
    }


}
