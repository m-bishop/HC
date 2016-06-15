package com.coredump.hc.Actors.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.coredump.hc.Actions.NoAction;
import com.coredump.hc.Actions.ScanAction;
import com.coredump.hc.Actors.Buttons.GameButton;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 6/6/2016.
 */
public class IcePickButton extends GameButton {


    public IcePickButton(HCGame game){
        super(game.getUiSkin().getDrawable("Icepick_R"),game.getUiSkin().getDrawable("Icepick_D"),game);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((GameButton) event.getTarget()).getGame().addDebug(">Icepick Button Pressed:");
                ((GameButton) event.getTarget()).getGame().setCurrentAction(new NoAction());
            }
        });
    }

}


