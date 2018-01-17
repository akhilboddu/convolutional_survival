package com.convolution.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by bodduakhil on 2017/10/14.
 */

public class Bullet {

    public float x = 0;
    public float y = 0;
    Vector2 velocity = new Vector2(0,0);
    Texture texture;

    public Bullet(float x, float y, Vector2 velocity){
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }

    public void update(){
        x += velocity.x;
        y += velocity.y;
    }

}
