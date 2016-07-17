package com.coredump.hc.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.coredump.hc.Actors.Nodes.DataBaseNode;
import com.coredump.hc.Actors.Nodes.FirewallNode;
import com.coredump.hc.Actors.Nodes.NodeActor;
import com.coredump.hc.Actors.Nodes.SystemNode;
import com.coredump.hc.HCGame;

/**
 * Created by Gregory on 6/20/2016.
 */
public class Level01 extends Level {

    private float roundTimer;

    public Level01(HCGame game){
        super(game);
    }

    @Override
    public void initialize() {
        SystemNode node0 = new SystemNode(game);
        node0.setX(150f);
        node0.setY(100f);
        node0.setEnabled(true);
        nodes.add(node0);

        FirewallNode node1 = new FirewallNode(game);
        node1.setX(150f);
        node1.setY(184f);
        nodes.add(node1);

        DataBaseNode node2 = new DataBaseNode(game);
        node2.setX(200f);
        node2.setY(268f);
        nodes.add(node2);

        SystemNode node3 = new SystemNode(game);
        node3.setX(100f);
        node3.setY(268f);
        nodes.add(node3);


        node0.setChildren(new Array<NodeActor>(new NodeActor[]{node1}));
        node1.setChildren(new Array<NodeActor>(new NodeActor[]{node1,node2,node3}));
        node2.setChildren(new Array<NodeActor>(new NodeActor[]{node1}));

        roundTimer = 30;
    }

    @Override
    public void checkWinConditions() {
        for (NodeActor node : nodes){
            if (node.getCompleteState() == NodeActor.CompleteType.DATA) {
                game.setGameState(HCGame.GameState.SUCCESS);
            }
        }

    }

    @Override
    public void checkLoseConditions() {
        roundTimer -= Gdx.graphics.getDeltaTime();

        if (roundTimer <= 0){
            game.setGameState(HCGame.GameState.FAIL);
        }

    }

    public float getRoundTimer() {
        return roundTimer;
    }
}
