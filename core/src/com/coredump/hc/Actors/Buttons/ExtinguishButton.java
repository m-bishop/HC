package com.coredump.hc.Actors.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.coredump.hc.Actions.ExtinguishAction;
import com.coredump.hc.Actions.NoAction;
import com.coredump.hc.Actions.ScanAction;
import com.coredump.hc.Actors.Buttons.GameButton;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 6/6/2016.
 */
public class ExtinguishButton extends GameButton {


    public ExtinguishButton(HCGame game){
        super(game.getUiSkin().getDrawable("Extinguish_R"),game.getUiSkin().getDrawable("Extinguish_D"),game);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((GameButton) event.getTarget()).getGame().addDebug(">Extinguish Button Pressed:");
                ((GameButton) event.getTarget()).getGame().setCurrentAction(new ExtinguishAction());
            }
        });
    }

}