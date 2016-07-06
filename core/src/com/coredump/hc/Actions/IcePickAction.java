package com.coredump.hc.Actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.coredump.hc.Actors.Nodes.NodeActor;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 7/2/2016.
 */

public class IcePickAction extends Action {

    private float stateTime = 0;
    private Animation actAnimation;

    public IcePickAction(){
        TextureAtlas buttonAtlas = Asset.manager.get(Asset.spritePack,TextureAtlas.class);
        Skin uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);

        // TODO: change to new animation once you create one!
        actAnimation = new Animation(1f,buttonAtlas.findRegions("SYS_B"));

    }

    @Override
    public void act(HCGame game, NodeActor node) {
        stateTime += Gdx.graphics.getDeltaTime();
        game.addDebug("Ice Pick Processed:"+stateTime);
        if (stateTime >= 1.0) {
            complete = true;
            node.processAttack(NodeActor.AttackType.ICEPICK);
        }
    }

    @Override
    public TextureRegion getKeyFrame(){
        return actAnimation.getKeyFrame(stateTime, true);
    }

    @Override
    public boolean hasKeyframe(){
        return true;
    }
}