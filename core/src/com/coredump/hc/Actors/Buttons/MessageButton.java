package com.coredump.hc.Actors.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.coredump.hc.Actions.IcePickAction;
import com.coredump.hc.HCGame;
import com.coredump.hc.HCMessage;

/**
 * Created by Gregory on 6/6/2016.
 */
public class MessageButton extends GameButton {

    private HCMessage message;

    public MessageButton(HCGame game){
        // TODO change to mail button art
        super(game.getUiSkin().getDrawable("Icepick_R"),game.getUiSkin().getDrawable("Icepick_D"),game);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                HCGame game = ((MessageButton) (event.getTarget())).getGame();
                game.addDebug(">messge Button Pressed:");
                game.setCurrentMessage(((MessageButton) (event.getTarget())).getMessage());
                ((MessageButton) event.getTarget()).getGame().setGameState(HCGame.GameState.TEXT);
            }
        });
    }

    public HCMessage getMessage() {
        return message;
    }

    public void setMessage(HCMessage message) {
        Gdx.app.log("debug","initializing button:"+message.getSubject());
        this.message = message;
    }
}


