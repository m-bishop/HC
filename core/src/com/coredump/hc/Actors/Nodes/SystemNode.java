package com.coredump.hc.Actors.Nodes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.BooleanArray;
import com.coredump.hc.AnimatedDrawable;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 6/9/2016.
 */
public class SystemNode extends NodeActor {



    public SystemNode(HCGame game){
        super(new AnimatedDrawable(new Animation(1f, Asset.manager.get(Asset.spritePack,TextureAtlas.class).findRegions("SYS_R"))),new AnimatedDrawable(new Animation(1f,Asset.manager.get(Asset.spritePack,TextureAtlas.class).findRegions("SYS_D"))),game);

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
                    this.completeState = CompleteType.SYSTEM;
                    this.getStyle().up = new AnimatedDrawable(new Animation(1f, Asset.manager.get(Asset.spritePack, TextureAtlas.class).findRegions("SYS_G")));
                    this.setSize(getPrefWidth(), getPrefHeight());
                    retVal = true;
                }
                break;
            case SAVE:
                break;
        }

        return retVal;
    }
}
