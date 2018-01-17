package com.convolution.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.convolution.game.Convolution;

/**
 * Created by bodduakhil on 2017/10/18.
 */

public class MainMenu implements Screen {

    private Convolution parent;
    public static SpriteBatch batch;

    public static Stage stage;

    TextButton newGame;
    TextButton preferences;
    TextButton exit;
    Texture bg;

    public MainMenu(Convolution game){
        parent = game;

        batch = new SpriteBatch();

        bg = new Texture("actualbg.jpg");


        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void show() {


        stage.clear();

        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        newGame = new TextButton("New Game", skin);
        preferences = new TextButton("Instructions", skin);
        exit = new TextButton("Exit", skin);

        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
//        table.add(preferences).fillX().uniformX();
//        table.row();
        table.add(exit).fillX().uniformX();

        /**
         * Listeners for relevant buttons
         */
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                parent.changeScreen(Convolution.LEVEL1);

            }
        });

//        preferences.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//
//                parent.changeScreen(Convolution.INSTRUCTIONS);
//            }
//        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(bg, 0 ,0);
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        LoadingScreen.welcomemusic.dispose();
        bg.dispose();
        batch.dispose();
        stage.dispose();
    }

    public void disposeCustom(){
        stage.dispose();
        bg.dispose();
        batch.dispose();

    }

}
