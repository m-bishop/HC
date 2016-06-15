package com.coredump.hc.Actors.Buttons;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 3/12/2016.
 */
//TODO get rid of this useless class after creating a gameBoard singleton to contain central game board information

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

