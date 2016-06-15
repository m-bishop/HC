package com.coredump.hc.Actors.Nodes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.coredump.hc.Actions.Action;
import com.coredump.hc.Actions.NoAction;
import com.coredump.hc.Actors.Buttons.GameButton;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 3/12/2016.
 */


    public class NodeActor extends GameButton {

        private float stateTime = 0;
        private Action currentAction;
        private HCGame game;
        private Array<NodeActor> childNodes = new Array<NodeActor>();
        private ShapeRenderer renderer = new ShapeRenderer();
        private boolean enabled = false;

        public NodeActor(Drawable up,Drawable down, HCGame game){
            super(up,down,game);
            this.game = game;

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

        public void drawNetwork(Batch batch){
            if (this.isEnabled()) {
                // draw lines from me to my children
               // batch.end();

                renderer.setProjectionMatrix(batch.getProjectionMatrix());
                renderer.setTransformMatrix(batch.getTransformMatrix());

                renderer.begin(ShapeRenderer.ShapeType.Filled);

                renderer.setColor(Color.GREEN);    // Set Color before drawing
                //renderer.rectLine(getX()+getWidth()/2,getY()+getHeight()/2,0,0,1);
                for (NodeActor node : childNodes) {
                    if (node.isEnabled()) {
                        renderer.rectLine(getX() + getWidth() / 2, getY() + getHeight() / 2, node.getX() + getWidth() / 2, node.getY() + getHeight() / 2, 1);
                    }
                }
                renderer.end();

               // batch.begin();
            }
        }

        @Override
        public void draw(Batch batch, float alpha){

            if (this.isEnabled()) {
                super.draw(batch, alpha);
                //draw action keyframe over current node keyframe
                if (this.currentAction.hasKeyframe()) {
                    TextureRegion keyFrame = this.currentAction.getKeyFrame();
                    batch.draw(keyFrame, this.getX(), this.getY(), keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
                }
            }

        }

        public void clicked(){
            getGame().addDebug(">node Pressed:");
            currentAction = game.getCurrentAction();
        }

        public void enableChildren(){
            for (NodeActor node : childNodes) {
                node.setEnabled(true);
            }
        }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

        public void setChildren(Array<NodeActor> childNodes) {
            this.childNodes = childNodes;
        }
    }

