package com.coredump.hc.Actors.Nodes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.coredump.hc.AnimatedDrawable;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 6/9/2016.
 */
public class FirewallNode extends NodeActor {

    public FirewallNode(HCGame game){
        super(new AnimatedDrawable(new Animation(1f, Asset.manager.get(Asset.spritePack,TextureAtlas.class).findRegions("FW_G"))),new AnimatedDrawable(new Animation(1f,Asset.manager.get(Asset.spritePack,TextureAtlas.class).findRegions("FW_D"))),game);

    }
}

