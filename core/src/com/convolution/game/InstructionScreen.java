package com.convolution.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.convolution.game.Screens.MainMenu;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

/**
 * Created by bodduakhil on 2017/10/22.
 */

class InstructionScreen implements Screen{

    private Convolution parent;

    Texture bg;
    SpriteBatch batch;

    public InstructionScreen(Convolution convolution) {
        parent = convolution;
        batch = new SpriteBatch();
        bg = new Texture("storyline.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(bg,0,0);
        batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.B)) {
            bg.dispose();
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
