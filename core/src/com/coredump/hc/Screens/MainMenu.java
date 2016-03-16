package com.coredump.hc.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coredump.hc.Actors.BadActor;
import com.coredump.hc.Asset;
import com.coredump.hc.HCGame;


/**
 * Created by Gregory on 3/3/2016.
 */
public class MainMenu {

    private final float ALERT_WIDTH = 285f;
    private final float ALERT_HEIGHT = 190f;


    public Stage stage;
    private Viewport viewport;
    private Table timeTable;
    private Table buttonTable;

    //assets
    private Skin uiSkin;
    private TextureAtlas buttonAtlas;


    //elements
    private Image background;
    private Label timeLabel;
    private Button hackButton;
    private Button phoneButton;
    private Button bbsButton;


    public MainMenu(SpriteBatch sb){
        viewport = new FitViewport(HCGame.V_WIDTH, HCGame.V_HEIGHT, new
                OrthographicCamera());
        stage = new Stage(viewport, sb);

        background = new Image(new Sprite((Asset.manager.get(Asset.mainBG,Texture.class))));
        buttonAtlas = Asset.manager.get(Asset.buttonAtlas,TextureAtlas.class);
        uiSkin = new Skin();
        uiSkin.addRegions(buttonAtlas);

        timeTable = new Table();
        timeTable.top();
        timeTable.setFillParent(true); // make it the size of the stage.

        timeLabel = new Label("00:00:00.000", new Label.LabelStyle(new BitmapFont(), Color.RED));
        timeLabel.setFontScale(2.5f);

        hackButton = new Button(uiSkin.getDrawable("HackUP"),uiSkin.getDrawable("HackDN"));
        phoneButton = new Button(uiSkin.getDrawable("PhoneUP"),uiSkin.getDrawable("PhoneDN"));
        bbsButton = new Button(uiSkin.getDrawable("SkullUP"),uiSkin.getDrawable("SkullDN"));

        timeTable.add(timeLabel).padTop(40).center();
        timeTable.row().fillY().expandY();
        buttonTable = new Table();
        buttonTable.bottom().setFillParent(true);
        buttonTable.add(hackButton).expandX();
        buttonTable.add(phoneButton).expandX();
        buttonTable.add(bbsButton).expandX();

        Pixmap pm = new Pixmap(200,200, Pixmap.Format.RGBA8888);
        pm.setColor(0f,0f,0f,1f);
        pm.fill();
        Texture blackBox = new Texture(pm);
        Sprite bb = new Sprite(blackBox);
        SpriteDrawable sd = new SpriteDrawable(bb);

        Table listTable = new Table();
        Table container = new Table();
        container.setHeight(ALERT_HEIGHT);
        container.setWidth(ALERT_WIDTH);
        container.setX((HCGame.V_WIDTH / 2) - ALERT_WIDTH /2);
        container.setY((HCGame.V_HEIGHT / 2) - ALERT_HEIGHT /2);

        container.setBackground(sd);

        ScrollPane scroll = new ScrollPane(listTable);

        scroll.setScrollingDisabled(true, false);
        scroll.layout();
        container.add(scroll).fill().expand();


        // TODO: load real alerts
        Label line1 = new Label("Mission Accomplished! 20 Rep earned.  ", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        //line1.setFontScale(1.5f);
        listTable.add(line1);
        listTable.row();
        for (int i = 1; i < 20; i++) {
            Label label = new Label(("New Voice Mail! "+i), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
            //label.setFontScale(1.5f);
            label.setAlignment(Align.left);
            listTable.add(label).align(Align.left);
            listTable.row();
        }
        listTable.row();
        listTable.add(new Button(uiSkin.getDrawable("SkullUP"), uiSkin.getDrawable("SkullDN")));
        listTable.row();
        listTable.add(new Button(uiSkin.getDrawable("SkullUP"), uiSkin.getDrawable("SkullDN")));
        listTable.row();
        Button test = new Button(uiSkin.getDrawable("SkullUP"),uiSkin.getDrawable("SkullDN"));
        test.setX(200);
        test.setY(200);
        listTable.add(test);
        listTable.row();
        listTable.add(new Button(uiSkin.getDrawable("SkullUP"),uiSkin.getDrawable("SkullDN")));

        stage.addActor(background);
        stage.addActor(timeTable);
        stage.addActor(buttonTable);
        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);
    }

    public void dispose(){
        stage.dispose();
    }

    public Label getTimeLabel() {
        return this.timeLabel;
    }


}
