package com.coredump.hc.Actors.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.coredump.hc.Actions.ScanAction;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 6/6/2016.
 */
public class ScanButton extends GameButton {


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
