package com.convolution.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.tools.extcheck.Main;

import sun.security.provider.ConfigFile;

/**
 * Created by bodduakhil on 2017/10/21.
 */

class GameOverScreen implements Screen{

    private Convolution parent;
    Texture gameover;
    SpriteBatch batch;

    public GameOverScreen(Convolution game){
        parent = game;
        batch = new SpriteBatch();

    }
    @Override
    public void show() {

        gameover = new Texture("gameover.png");

    }

    @Override
    public void render(float delta) {


        batch.begin();
        batch.draw(gameover, MainScreen.screenWidth/2, MainScreen.screenHeight/2);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
