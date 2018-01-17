package com.convolution.game;

import com.badlogic.gdx.graphics.Texture;
//import com.convolution.game.Convolution.Direction;

public class Entity {
    //public Convolution game;
    public MainScreen game;
    public float x;
    public float y;
    public float dx;
    public float dy;
    public int width;
    public int height;
    public float speed;
    public Texture texture;

    public static boolean isIntersecting = false;
    public static boolean hasCollided = false;


    public Entity(MainScreen game, float x, float y, int width, int height, float speed, Texture texture) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.texture = texture;
        isIntersecting = false;
        hasCollided = false;


        if(!this.texture.getTextureData().isPrepared())
            this.texture.getTextureData().prepare();
//        this.bitmask = new BitMaskInitiator(this.texture.getTextureData().consumePixmap());


    }

    public void setPlayerTexture(Texture t){
        this.texture=t;
    }

    public Texture getPlayerTexture(){
        return texture;
    }

    public void update(float delta) {

    }

    public void move(float newX, float newY) {
        x = newX;
        y = newY;
    }

    public void render() {

    }

//    public void tileCollision(int tile, int tileX, int tileY, float newX, float newY, Direction direction) {
//        System.out.println("tile collision at: " + tileX + " " + tileY);
//
//
//
//        if(direction == Direction.U) {
//            y = tileY * game.tileSize + game.tileSize;
//        }
//        else if(direction == Direction.D) {
//            y = tileY * game.tileSize - height;
//        }
//        else if(direction == Direction.L) {
//            x = tileX * game.tileSize + game.tileSize;
//        }
//        else if(direction == Direction.R) {
//            x = tileX * game.tileSize - width;
//        }
//    }
//
//    public boolean entityCollision(Entity e2, float newX, float newY, Direction direction) {
//        isIntersecting = true;
//
//        boolean collision = EntityHandler.bitChecking(e2,newX,newY,this.bitmask,this.height);
//
//        //move(newX, newY);
//        // could also resolve entity collisions in the same we do tile collision resolution
////
////        if(direction == Direction.U) {
////            y = e2.y * e2.y + e2.height;
////        }
////        else if(direction == Direction.D) {
////            y = e2.y * e2.y - e2.height;
////        }
////        else if(direction == Direction.L) {
////            x = e2.x * e2.x + e2.width;
////        }
////        else if(direction == Direction.R) {
////            x = e2.x * e2.x - e2.width;
////        }
//
//        return collision;
//
//    }
//
//    public void myentityCollision(Entity e2, float newX, float newY, Direction direction) {
//        isIntersecting = true;
//
//        //boolean collision = EntityHandler.bitChecking(e2,newX,newY,this.bitmask,this.height);
//
//        move(newX, newY);
//        // could also resolve entity collisions in the same we do tile collision resolution
//
//        if(direction == Direction.U) {
//            y = e2.y + e2.height;
//        }
//        else if(direction == Direction.D) {
//            y = e2.y - e2.height;
//        }
//        else if(direction == Direction.L) {
//            x = e2.x + e2.width;
//        }
//        else if(direction == Direction.R) {
//            x = e2.x - e2.width;
//        }
//
//        //return collision;
//
//    }



}

