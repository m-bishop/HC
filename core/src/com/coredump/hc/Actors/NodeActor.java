package com.coredump.hc.Actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.coredump.hc.Actions.Action;
import com.coredump.hc.Actions.NoAction;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 3/12/2016.
 */


    public class NodeActor extends GameButton {
        private Animation animation;
        private TextureRegion keyFrame;
        private float stateTime = 0;
        private Action currentAction;
        private HCGame game;

        public NodeActor(Drawable up,Drawable down,Animation animation, HCGame game){
            super(up,down,game);
            this.game = game;
            this.animation = animation;
            this.currentAction = new NoAction();

            addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((NodeActor) event.getTarget()).clicked();
                    System.out.println("touched from inside");


                }
            });
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            stateTime += delta;
            currentAction.act(game,this);
            if (currentAction.isComplete()){
                currentAction = new NoAction();
            }
        }

        @Override
        public void draw(Batch batch, float alpha){
            keyFrame = animation.getKeyFrame(stateTime,true);
            batch.draw(keyFrame, this.getX(), this.getY(), keyFrame.getRegionWidth(), keyFrame.getRegionHeight());

        }

        public void clicked(){
            getGame().addDebug(">node Pressed:");
            currentAction = game.getCurrentAction();
        }
    }

