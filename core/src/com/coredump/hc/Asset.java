package com.coredump.hc;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;



/**
 * Created by Gregory on 3/3/2016.
 */
public class Asset {

    public static final AssetManager manager = new AssetManager();
    public static final String mainBG = "Main.png";
    public static final String spritePack = "HC.pack";
    //public static final String buttonAtlas = "buttons.atlas";


    public HCGame game;

    public Asset(HCGame hcGame){
        game = hcGame;

    }

    public void load(){

        manager.load(mainBG,Texture.class);
        //manager.load(buttonAtlas,TextureAtlas.class);
        manager.load(spritePack,TextureAtlas.class);

    }

    public void dispose(){
        manager.dispose();
    }


}
