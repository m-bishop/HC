package com.coredump.hc.Actors.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.coredump.hc.Actions.NoAction;
import com.coredump.hc.Actions.SaveAction;
import com.coredump.hc.Actions.ScanAction;
import com.coredump.hc.Actors.Buttons.GameButton;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 6/6/2016.
 */
public class SaveButton extends GameButton {


    public SaveButton(HCGame game){
        super(game.getUiSkin().getDrawable("Save_R"),game.getUiSkin().getDrawable("Save_D"),game);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((GameButton) event.getTarget()).getGame().addDebug(">Save Button Pressed:");
                ((GameButton) event.getTarget()).getGame().setCurrentAction(new SaveAction());
            }
        });
    }

}





