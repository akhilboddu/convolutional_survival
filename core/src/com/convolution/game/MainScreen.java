package com.convolution.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.convolution.game.Screens.MainMenu;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by bodduakhil on 2017/10/21.
 */

class MainScreen implements Screen
{
    public static SpriteBatch batch;

    public static int screenWidth;
    public static int screenHeight;

    public static boolean bulletHasHit = false;
    public static boolean hasCollectedBonusTime = false;
    public static boolean destroyAI = false;

    private float elapsed;
    private float elapsed1;
    private float elapsed2;


    Music mp3Sound;
    Music gameovermusic;
    Music youwin;
    Sound shootSound;
    Sound loseSound;
    Sound powerupSound;

    ArrayList<Bullet> bullets;
    Bullet b;

    Texture bgreen;
    Texture t;


    // 1 = block
    // 0 = empty
    // the x and y coordinate system is not what it seems
    // visually x goes down and y across
    // this will make more sense when you compare it to what is drawn

    //the one on the right side is the top one
//	int[][] gamemap = {
//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//	};

    int[][] gamemap = new int[40][40];

    //basic playscreen variables
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    //Tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    //rectangles
    Rectangle rect_player;
    Rectangle rect_ai;
    Rectangle rect_bullet;
    Rectangle rect_rock;
    Rectangle rect_rock1;
    Rectangle rect_rock2;
    Rectangle rect_rock3;


    Rectangle rect_powerup;
    Rectangle rect_powerupHealth;


    Sprite actualbg;


    int mapWidth = 25;
    int mapHeight = 25;
    int tileSize = 20;
    Texture tileTexture;

    String currentlyFacing = "up";

    Texture bg;

    ArrayList<Entity> entities = new ArrayList<Entity>();
    public ArrayList<Entity> rocks = new ArrayList<Entity>();

    public static ArrayList allTextures = new ArrayList();

    public static boolean level2 = false;

    //FreeTypeFontGenerator generator;

    private HUD hud;

    enum Axis { X, Y };
    enum Direction { U, D, L, R };

    Texture bonustime_popup;
    Texture bonustime_powerup;

    Convolution parent;

    float health = 1;
    Texture blank;


    public MainScreen(Convolution convolution) {
        parent = convolution;
        batch = new SpriteBatch();
//        MainMenu.stage.clear();
//        MainMenu.stage.dispose();
    }

    @Override
    public void show() {

        bg = new Texture("actualbg.jpg");
        allTextures.add(bg);
        bgreen = new Texture("bgreen.png");
        allTextures.add(bgreen);
        blank = new Texture("blank.png");
        allTextures.add(blank);

        bulletHasHit = false;
        hasCollectedBonusTime = false;

        bonustime_popup = new Texture("BONUS_TIME.png");
        allTextures.add(bonustime_popup);
        bonustime_powerup = new Texture("time.png");
        allTextures.add(bonustime_powerup);

        //explosions = new ArrayList<Explosion>();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight()-20;

        bullets = new ArrayList<Bullet>();


        mp3Sound = Gdx.audio.newMusic(Gdx.files.internal("sounds/bg.mp3"));
        allTextures.add(mp3Sound);
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_laser1.ogg"));
        allTextures.add(shootSound);
        loseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_lose.ogg"));
        allTextures.add(loseSound);
        powerupSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_zap.ogg"));
        allTextures.add(powerupSound);
        gameovermusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/dd.mp3"));
        youwin = Gdx.audio.newMusic(Gdx.files.internal("sounds/youwin.mp3"));

        for(int x=0; x<40; x++){
            for(int y=0; y<40; y++){
                gamemap[x][y]=0;
            }
        }

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Convolution.V_WIDTH / Convolution.PPM, Convolution.V_HEIGHT / Convolution.PPM, gamecam);
        gamecam.position.set(0,0,0);

        hud = new HUD(batch);

        // add some entities including a player
        entities.add(new Player(this, 400, 400, 50, 38, 120.0f, new Texture("playerShip1_blue.png")));
        entities.add(new Entity(this, 780, 780, 50, 50, 120.0f, new Texture("ufoGreen.png")));
        entities.add(new Entity(this, -20,250 , 101, 84, 120.0f, new Texture("bigrock.png")));
        entities.add(new Entity(this, 40,-50 , 101, 84, 120.0f, new Texture("bigrock.png")));
        entities.add(new Entity(this, 100,600 , 101, 84, 120.0f, new Texture("silverrock.png")));
        entities.add(new Entity(this, 600,100 , 101, 84, 120.0f, new Texture("silverrock.png")));


        rect_player = new Rectangle(100,150,50,38);
        rect_ai = new Rectangle(50, 25, 50, 50);
        rect_bullet = new Rectangle(100,150, 48,46);
        rect_rock = new Rectangle(-20, 250, 101, 84);
        rect_rock1 = new Rectangle(40, -50, 101, 84);
        rect_rock2 = new Rectangle(100, 600, 101, 84);
        rect_rock3 = new Rectangle(600, 100, 101, 84);
        rect_powerup = new Rectangle(800,800, 30, 30);
        rect_powerupHealth = new Rectangle(800,800, 30, 30);

        mp3Sound.play();
        mp3Sound.setVolume(0.5f);

    }

    public void moveEntity(Entity e, float newX, float newY) {

        e = entities.get(0);
        Vector2 v = null;
        if(currentlyFacing.equals("up"))
            v = new Vector2(0,20);
        else if(currentlyFacing.equals("down"))
            v = new Vector2(0,-20);
        else if(currentlyFacing.equals("left"))
            v = new Vector2(-20,0);
        else if(currentlyFacing.equals("right"))
            v = new Vector2(20,0);

        // move
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            currentlyFacing = "up";
            t = new Texture("playerShip1_blue.png");
            if(level2==true) t = new Texture("shipup.png");

            e.setPlayerTexture(t);

            v = new Vector2(0,20);
            if(e.y<screenHeight - 80) {
                e.y += 1.8;
                if(level2==true)
                    e.y+=2.5;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            currentlyFacing = "down";
            t = new Texture("playerShip1_blue_down.png");
            if(level2==true) t = new Texture("shipdown.png");
            e.setPlayerTexture(t);

            v = new Vector2(0,-20);

            if(e.y>0) {
                e.y -= 1.8;
                if(level2==true)
                    e.y-=2.5;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentlyFacing = "left";
            t = new Texture("playerShip1_blue_left.png");
            if(level2==true) t = new Texture("shipleft.png");
            e.setPlayerTexture(t);

            v = new Vector2(-20,0);

            if(e.x>0) {
                e.x -= 1.8;
                if(level2==true)
                    e.x-=2.5;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentlyFacing = "right";
            t = new Texture("playerShip1_blue_right.png");
            if(level2==true) t = new Texture("shipright.png");
            e.setPlayerTexture(t);

            v = new Vector2(20,0);

            if(e.x<screenWidth - e.width) {
                e.x += 1.8;
                if(level2==true)
                    e.x+=2.5;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            shootSound.play(0.5f);
            Bullet b1 = new Bullet(e.x, e.y, v);
            bulletHasHit = false;
            bullets.add(b1);
        }


    }

    @Override
    public void render(float delta) {

        // update all entities
        for(int i = 2; i >= 0; i--) {
            Entity e = entities.get(i);
            e.update(delta);
            moveEntity(e, e.x + e.dx, e.y + e.dy);
        }




        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(bg,0,0);
        batch.end();



        batch.begin();

//		// draw tile map
//		// go over each row bottom to top
//		for(int y = 0; y < 40; y++) {
//			// go over each column left to right
//			for(int x = 0; x < 40; x++) {
//				// tile
//				if(gamemap[x][y] == 1) {
//					//batch.draw(tileTexture, x * tileSize, y * tileSize);
//				}
//				// draw other types here...
//			}
//		}

        // draw all entities
        for(int i = entities.size() - 1; i >= 0; i--) {
            Entity e = entities.get(i);
            batch.draw(e.getPlayerTexture(), e.x, e.y);
        }


        for(int i=0; i<bullets.size(); i++){
            bullets.get(i).update();
            BulletCollision(bullets.get(i), entities.get(1));
            if(bulletHasHit==false)
                batch.draw(bgreen, bullets.get(i).x, bullets.get(i).y);

        }

        for(int i = rocks.size() - 1; i >= 0; i--) {
            Entity e = rocks.get(i);
            batch.draw(e.getPlayerTexture(), e.x, e.y);
        }


        moveRock();

        playerCollision(delta);
        updateAI();
        AICollision();

        levelup(delta);
        updateScore();
        hud.update(delta);

        healthPack();

        if(health > 0.6f){
            batch.setColor(Color.GREEN);
        }
        else if(health > 0.2f){
            batch.setColor(Color.ORANGE);
        }
        else{
            batch.setColor(Color.RED);
        }

        batch.draw(blank, 0, screenHeight-50, (screenWidth)*health, 100);
        batch.setColor(Color.WHITE);

        batch.end();

        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        checkGameOver(delta);






    }

    public void updateScore(){
        if(hud.score>=900 && !hasCollectedBonusTime){
            int x = 60;
            int y = 700;
            rect_powerup.set(x,y,30,30);
            batch.draw(new Texture("time.png"), x, y);

        }
    }

    public void healthPack(){
        if(health<=0.3f && health>0.0f){
            int x = 300;
            int y = 70;
            rect_powerupHealth.set(x,y,30,30);
            batch.draw(new Texture("health.png"), x, y);
        }
    }

    boolean gamewin = false;
    public void checkGameOver(float delta){
        Entity p = entities.get(0);
        Entity e = entities.get(1);
        if(health<=0 && gamewin==false){

            disposeSounds();
            gameovermusic.play();
            batch.begin();
            batch.draw(bg,0,0);
            batch.draw(new Texture("gameover.png"), screenWidth/2-300, screenHeight/2 );
            batch.end();
        }
        if(hud.score>7000){
            gamewin = true;

            disposeSounds();
            youwin.play();
            Texture f = new Texture("youwin.png");
            destroyAI = true;

            elapsed2 += delta;

            batch.begin();
            batch.draw(bg,0,0);
            batch.draw(f, screenWidth/2-300, screenHeight/2 );
            batch.end();

        }
    }

    public void levelup(float delta){

        Entity p = entities.get(0);
        Entity e = entities.get(1);

        if(hud.score>=3000){
            Texture s = new Texture("levelup.png");
            Texture l = new Texture("superspeed.png");

            elapsed1 += delta;
            if (elapsed1 > 0.0 && elapsed1 < 1.3) {
                batch.draw(s, screenWidth/2-200, screenHeight/2 - 150);
            }

            if (elapsed1 > 1.9 && elapsed1 < 3.1) {
                batch.draw(l, screenWidth/2 - 150 , screenHeight/2 -150 );
            }

            level2 = true;
            HUD.wave = 2;
        }
        if(hud.score>6500){
            Texture l = new Texture("explode.png");
            e.setPlayerTexture(l);
        }
    }

    public  void BulletCollision(Bullet b, Entity p) {
        rect_bullet.set(b.x, b.y, 48,46);

        if(rect_bullet.overlaps(rect_ai)){
            hud.score+=100;
            bulletHasHit = true;
            bullets.clear();
            Texture t = new Texture("ufoRed.png");
            p.setPlayerTexture(t);
        }

        if(rect_bullet.overlaps(rect_rock)){
            bulletHasHit = true;
            bullets.clear();
        }

        if(rect_bullet.overlaps(rect_rock1)){
            bulletHasHit = true;
            bullets.clear();
        }

        if(rect_bullet.overlaps(rect_rock2)){
            bulletHasHit = true;
            bullets.clear();
        }

        if(rect_bullet.overlaps(rect_rock3)){
            bulletHasHit = true;
            bullets.clear();
        }

    }


    public void playerCollision(float delta){

        Entity p = entities.get(0);
        Entity e = entities.get(1);
        Entity roc = entities.get(2);
        Entity roc1 = entities.get(3);
        Entity roc2 = entities.get(4);


        rect_player.set(entities.get(0).x,entities.get(0).y,50,38);
        rect_ai.set(entities.get(1).x,entities.get(1).y,50,50);
        rect_rock.set(entities.get(2).x, entities.get(2).y, 101, 84);
        rect_rock1.set(entities.get(3).x, entities.get(3).y, 101, 84);

        if(rect_player.overlaps(rect_ai)) {

            loseSound.play();
            health -= 0.1;
            Texture t = new Texture("playerShip1_blue.png");
            if(level2==true) t = new Texture("shipup.png");
            p.setPlayerTexture(t);
            Texture t1 = new Texture("ufoGreen.png");
            e.setPlayerTexture(t1);

            p.x = 400;
            p.y = 600;

            e.x = 400;
            e.y = 25;

            rect_player.set(100,150,50,38);
            rect_ai.set(100, 25, 50, 50);
        }

        if(rect_player.overlaps(rect_rock)){
            loseSound.play();
            health -= 0.1;

            if(p.x<roc.x) p.x = p.x-50;
            if(p.y<roc.y) p.y = p.y-50;
            if(p.x>roc.x) p.x = p.x+50;
            if(p.y>roc.y) p.y = p.y+50;

            rect_player.set(p.x, p.y, 50,38);

        }

        if(rect_player.overlaps(rect_rock1)){
            loseSound.play();
            health -= 0.1;

            if(p.x<roc1.x) p.x = p.x-50;
            if(p.y<roc1.y) p.y = p.y-50;
            if(p.x>roc1.x) p.x = p.x+50;
            if(p.y>roc1.y) p.y = p.y+50;

            rect_player.set(p.x, p.y, 50,38);

        }

        if(rect_player.overlaps(rect_rock2)){
            loseSound.play();
            health -= 0.1;

            if(p.x<roc2.x) p.x = p.x-50;
            if(p.y<roc2.y) p.y = p.y-50;
            if(p.x>roc2.x) p.x = p.x+50;
            if(p.y>roc2.y) p.y = p.y+50;

            rect_player.set(p.x, p.y, 50,38);

        }

        if(rect_player.overlaps(rect_rock3)){
            loseSound.play();
            health -= 0.1;

            if(p.x<roc2.x) p.x = p.x-50;
            if(p.y<roc2.y) p.y = p.y-50;
            if(p.x>roc2.x) p.x = p.x+50;
            if(p.y>roc2.y) p.y = p.y+50;

            rect_player.set(p.x, p.y, 50,38);

        }

        if(rect_player.overlaps(rect_powerup)){
            hasCollectedBonusTime = true;
            hud.worldTimer+=25;
            powerupSound.play();

            batch.draw(bonustime_popup, screenWidth / 2 - 300, screenHeight / 2);

            elapsed += delta;
            if (elapsed > 0.3 && elapsed <0.8) {
                rect_powerup.set(800,800,30,30);


            }
        }

        if(rect_player.overlaps(rect_powerupHealth)){
            powerupSound.play();
            health = 1.0f;
            rect_powerupHealth.set(800,800,30,30);
        }
    }

    public void updateAI(){
        if(entities.get(0).x > entities.get(1).x) {

            if(level2==true && destroyAI==false)
                entities.get(1).x+=3;
            else if(destroyAI==false)
                entities.get(1).x+=2;

        }
        else {

            if(level2==true)
                entities.get(1).x-=3;
            else if(destroyAI==false)
                entities.get(1).x-=2;
        }
        if(entities.get(0).y > entities.get(1).y) {

            if(level2==true)
                entities.get(1).y+=3;
            else if(destroyAI==false)
                entities.get(1).y+=2;
        }
        else{
            if(level2==true)
                entities.get(1).y-=3;
            else if(destroyAI==false)
                entities.get(1).y-=2;
        }
    }

    public void AICollision(){

        Entity e = entities.get(1);
        Entity roc1 = entities.get(2);
        Entity roc2 = entities.get(3);
        Entity roc3 = entities.get(4);
        Entity roc4 = entities.get(4);

        rect_ai.set(e.x,e.y,50,50);
        rect_rock.set(roc1.x, roc1.y, 101, 84);
        rect_rock1.set(roc2.x, roc2.y, 101, 84);


        if(rect_ai.overlaps(rect_rock)){
            loseSound.play();

            if(e.x<roc1.x) e.x = e.x-50;
            if(e.y<roc1.y) e.y = e.y-50;
            if(e.x>roc1.x) e.x = e.x+50;
            if(e.y>roc1.y) e.y = e.y+50;

            rect_ai.set(e.x, e.y, 50,38);

        }

        if(rect_ai.overlaps(rect_rock1)){
            loseSound.play();

            if(e.x<roc2.x) e.x = e.x-50;
            if(e.y<roc2.y) e.y = e.y-50;
            if(e.x>roc2.x) e.x = e.x+50;
            if(e.y>roc2.y) e.y = e.y+50;

            rect_ai.set(e.x, e.y, 50,38);

        }

        if(rect_ai.overlaps(rect_rock2)){
            loseSound.play();

            if(e.x<roc3.x) e.x = e.x-50;
            if(e.y<roc3.y) e.y = e.y-50;
            if(e.x>roc3.x) e.x = e.x+50;
            if(e.y>roc3.y) e.y = e.y+50;

            rect_ai.set(e.x, e.y, 50,38);

        }

        if(rect_ai.overlaps(rect_rock3)){
            loseSound.play();

            if(e.x<roc4.x) e.x = e.x-50;
            if(e.y<roc4.y) e.y = e.y-50;
            if(e.x>roc4.x) e.x = e.x+50;
            if(e.y>roc4.y) e.y = e.y+50;

            rect_ai.set(e.x, e.y, 50,38);

        }

    }

    public void moveRock(){
        Entity r;
        r = entities.get(2);
        if(r.x>screenWidth && r.y>screenHeight){ r.x = -50; r.y = 250;}
        else { r.x = r.x + 0.4f; r.y = r.y + 0.4f; }

        r = entities.get(3);
        if(r.x>screenWidth && r.y>screenHeight){ r.x = 100; r.y = -50;}
        else { r.x = r.x + 0.7f; r.y = r.y + 0.4f; }

//		r = rocks.get(0);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = -70; r.y = -70;}
//		else { r.x = r.x + 0.4f; r.y = r.y + 0.5f; }
//
//		r = rocks.get(1);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = 200; r.y = -20;}
//		else { r.x = r.x + 1f; r.y = r.y + 1f; }
//
//		r = rocks.get(2);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = -20; r.y = 200;}
//		else { r.x = r.x + 0.2f; r.y = r.y + 0.5f; }
//
//		r = rocks.get(3);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = -20; r.y = 600;}
//		else { r.x = r.x + 0.2f; r.y = r.y + 0.5f; }
//
//		r = rocks.get(4);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = 400; r.y = -20;}
//		else { r.x = r.x + 0.8f; r.y = r.y + 0.4f; }
//
//		r = rocks.get(5);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = 500; r.y = -40;}
//		else { r.x = r.x + nextValue(); r.y = r.y + nextValue(); }
//
//		r = rocks.get(5);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = 400; r.y = -40;}
//		else { r.x = r.x + nextValue(); r.y = r.y + nextValue(); }
//		r = rocks.get(5);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = 500; r.y = -45;}
//		else { r.x = r.x + nextValue(); r.y = r.y + nextValue(); }
//		r = rocks.get(5);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = 550; r.y = -40;}
//		else { r.x = r.x + nextValue(); r.y = r.y + nextValue(); }
//		r = rocks.get(5);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = -50; r.y = 120;}
//		else { r.x = r.x + nextValue(); r.y = r.y + nextValue(); }
//		r = rocks.get(5);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = 400; r.y = -40;}
//		else { r.x = r.x + nextValue(); r.y = r.y + nextValue(); }
//
//		r = rocks.get(3);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = -40; r.y = 400;}
//		else { r.x = r.x + nextValue(); r.y = r.y + nextValue(); }
//		r = rocks.get(4);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = -20; r.y = 450;}
//		else { r.x = r.x + nextValue(); r.y = r.y + nextValue(); }
//		r = rocks.get(1);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = -55; r.y = 700;}
//		else { r.x = r.x + nextValue(); r.y = r.y + nextValue(); }
//		r = rocks.get(2);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = 700; r.y = -10;}
//		else { r.x = r.x + nextValue(); r.y = r.y + nextValue(); }
//		r = rocks.get(3);
//		if(r.x>screenWidth && r.y>screenHeight){ r.x = 400; r.y = -40;}
//		else { r.x = r.x + nextValue(); r.y = r.y + nextValue(); }
//
//
//
//
//		for(int i=0; i<rocks.size(); i++){
//			r = rocks.get(i);
//
//			if(r.x>screenWidth && r.y>screenHeight)
//			{
//				r.x = randomStart();
//				r.y = -20;
//
//			}
//			else {
//				r.x = r.x + nextValue();
//				r.y = r.y + nextValue();
//			}
//
//		}


    }



    public int randomStart(int high, int low){
        Random rs = new Random();
        int High = high;
        int Low = low;
        int Result = rs.nextInt(High-Low) + Low;
        return Result;
    }

    public float nextValue(){
        Random rand = new Random();
        return rand.nextFloat();
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
        bg.dispose();
        bgreen.dispose();
        blank.dispose();
        bonustime_popup.dispose();
        bonustime_powerup.dispose();
        mp3Sound.dispose();
        shootSound.dispose();
        loseSound.dispose();
        powerupSound.dispose();
        batch.dispose();
    }

    public void disposeSounds(){
        mp3Sound.dispose();
        shootSound.dispose();
        loseSound.dispose();
        powerupSound.dispose();
    }
}
