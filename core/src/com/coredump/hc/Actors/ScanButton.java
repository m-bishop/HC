package com.coredump.hc.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.coredump.hc.Actions.ScanAction;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 6/6/2016.
 */
public class ScanButton extends GameButton{


    public ScanButton(HCGame game){
        super(game.getUiSkin().getDrawable("Scan_R"),game.getUiSkin().getDrawable("Scan_D"),game);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((GameButton) event.getTarget()).getGame().addDebug(">Scan Button Pressed:");
                ((GameButton) event.getTarget()).getGame().setCurrentAction(new ScanAction());
            }
        });
    }

}
