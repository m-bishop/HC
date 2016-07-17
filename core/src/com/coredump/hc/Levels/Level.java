package com.coredump.hc.Levels;

import com.badlogic.gdx.utils.Array;
import com.coredump.hc.Actors.Nodes.NodeActor;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 6/20/2016.
 */
public abstract class Level {

    protected Array<NodeActor> nodes = new Array<NodeActor>();
    protected HCGame game;

    public Level(HCGame game){
        this.game = game;
        this.initialize();
    }

    public abstract void initialize();

    public abstract void checkWinConditions();

    public abstract void checkLoseConditions();

    public void act(){
        this.checkWinConditions();
        this.checkLoseConditions();
    }

    public Array<NodeActor> getNodes() {
        return nodes;
    }

    public float getRoundTimer() {
        return -1; // Default for if a game is not timed.
    }

    
}
