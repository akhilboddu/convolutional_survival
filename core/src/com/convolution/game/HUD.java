package com.convolution.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages_pt_BR;
//import com.mygdx.game.MyGdxGame;

/**
 * Created by bodduakhil on 2017/10/12.
 */

public class HUD implements Disposable{

    //Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    private Viewport viewport;

    //Mario score/time Tracking Variables
    public static Integer worldTimer;
    private boolean timeUp; // true when the world timer reaches 0
    private float timeCount;
    public static Integer score;

    //Scene2D widgets
    private Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;
    private Label healthLabel;
    private Label emptyLabel;
    public static int wave = 1;
    //String level = "1";

    public HUD(SpriteBatch sb){
        //define our tracking variables
        worldTimer = 200;
        timeCount = 0;
        score = 0;


        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        //viewport = new FitViewport(610, 640, new OrthographicCamera());
        viewport = new FitViewport(Convolution.V_WIDTH, Convolution.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //table.setDebug(true);
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        //define our labels using the String, and a Label style consisting of a font and color
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel =new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(wave + "", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WAVE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        marioLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthLabel = new Label("HEALTH", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        emptyLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(marioLabel).expandX().padTop(5);
        table.add(worldLabel).expandX().padTop(5);
        table.add(timeLabel).expandX().padTop(5);
        //add a second row to our table
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();
        //adding a row
        table.row();
        table.add(emptyLabel).expandX().padTop(3);
        table.add(emptyLabel).expandX().padTop(3);
        table.add(emptyLabel).expandX().padTop(3);

        //add our table to the stage
        stage.addActor(table);

    }

    public void update(float delta){
        timeCount += delta;
        if(timeCount>=1){
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
        scoreLabel.setText(String.format("%06d", score));
        levelLabel.setText(wave + "");
//        if(MainScreen.level2 == true){
//            levelLabel.setText("SURVIVE");
//        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
