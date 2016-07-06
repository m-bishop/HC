package com.coredump.hc.Actors.Nodes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.coredump.hc.AnimatedDrawable;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 6/9/2016.
 */
public class DataBaseNode extends NodeActor {

    public DataBaseNode(HCGame game){
        super(new AnimatedDrawable(new Animation(1f, Asset.manager.get(Asset.spritePack,TextureAtlas.class).findRegions("DB_R"))),new AnimatedDrawable(new Animation(1f,Asset.manager.get(Asset.spritePack,TextureAtlas.class).findRegions("DB_D"))),game);

    }

    @Override
    public boolean processAttack(AttackType attackType) {
        Boolean retVal = false;

        switch (attackType){

            case SCAN:
                if (state == NodeState.GREEN) {
                    this.enableChildren();
                    retVal = true;
                }
                break;
            case EXTINGUISH:
                break;
            case ICEPICK:
                if (state == NodeState.RED) {
                    this.state = NodeState.GREEN;
                    this.getStyle().up = new AnimatedDrawable(new Animation(1f, Asset.manager.get(Asset.spritePack, TextureAtlas.class).findRegions("DB_G")));
                    this.setSize(getPrefWidth(), getPrefHeight());
                    retVal = true;
                }
                break;
            case SAVE:
                if (state == NodeState.GREEN) {
                    this.completeState = CompleteType.DATA;
                    retVal = true;
                }
                break;
        }

        return retVal;
    }
}
