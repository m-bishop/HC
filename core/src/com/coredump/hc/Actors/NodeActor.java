package com.coredump.hc.Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.coredump.hc.Actions.Action;
import com.coredump.hc.Actions.NoAction;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 3/12/2016.
 */


    public class NodeActor extends GameButton {

        private TextureRegion keyFrame;
        private float stateTime = 0;
        private Action currentAction;
        private HCGame game;
        private Array<NodeActor> childNodes = new Array<NodeActor>();
        private ShapeRenderer renderer = new ShapeRenderer();

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

        @Override
        public void draw(Batch batch, float alpha){
            //keyFrame = animation.getKeyFrame(stateTime,true);
            //batch.draw(keyFrame, this.getX(), this.getY(), keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
            // draw lines from me to my children
            super.draw(batch,alpha);
            batch.end();

            renderer.setProjectionMatrix(batch.getProjectionMatrix());
            renderer.setTransformMatrix(batch.getTransformMatrix());

            renderer.begin(ShapeRenderer.ShapeType.Filled);

            renderer.setColor(Color.GREEN);    // Set Color before drawing
            //renderer.rectLine(getX()+getWidth()/2,getY()+getHeight()/2,0,0,1);
            for (NodeActor node : childNodes){
                renderer.rectLine(getX()+getWidth()/2,getY()+getHeight()/2,node.getX()+getWidth()/2,node.getY()+getHeight()/2,1);
            }
            renderer.end();

            batch.begin();


        }

        public void clicked(){
            getGame().addDebug(">node Pressed:");
            currentAction = game.getCurrentAction();
        }

        public void setChildren(Array<NodeActor> childNodes) {
            this.childNodes = childNodes;
        }
    }

