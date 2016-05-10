package com.coredump.hc.Actors;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 3/12/2016.
 */


    public class GameButton extends Button {

        private HCGame game;

        public GameButton(Drawable up, Drawable down, HCGame game){
            super(up, down);
            this.game = game;
        }

        public HCGame getGame(){
            return game;
        }
    }

