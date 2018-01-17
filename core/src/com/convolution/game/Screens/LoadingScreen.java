package com.convolution.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.convolution.game.Convolution;
import com.convolution.game.Entity;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Random;

import sun.jvm.hotspot.asm.sparc.SPARCRegister;

/**
 * Created by bodduakhil on 2017/10/18.
 */

public class LoadingScreen implements Screen {

    private Convolution parent;

    SpriteBatch batch;
    Texture storyline;
    public static Music welcomemusic;

    /**
     * Constructor
     * @param game
     */
    public LoadingScreen(Convolution game){
        batch = new SpriteBatch();
        welcomemusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/welcomescreen.mp3"));
        welcomemusic.play();
        parent = game;
        storyline = new Texture("storyline.jpg");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(storyline,0,0);
        batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.N)) {
            storyline.dispose();
            batch.dispose();
            parent.changeScreen(Convolution.MENU);
        }


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
