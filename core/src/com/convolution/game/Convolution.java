package com.convolution.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.convolution.game.Screens.LoadingScreen;
import com.convolution.game.Screens.MainMenu;

import java.util.ArrayList;

public class Convolution extends Game {

	public static int V_HEIGHT = 800;
	public static int V_WIDTH = 800;
	public static final int PPM = 100;

	public final static int MENU = 0;
	public final static int LEVEL1 = 1;
	public final static int LOADLEVEL2 = 2;
	public final static int GAMEOVER = 3;
	public final static int INSTRUCTIONS = 4;



	private LoadingScreen loadingScreen;
	private MainMenu mainMenu;
	private MainScreen mainScreen;
	private Level1CompleteScreen l1screen;
	private GameOverScreen gameoverscreen;
	private InstructionScreen instructionScreen;

	public SpriteBatch mainbatch;





	@Override
	public void create () {
		mainbatch = new SpriteBatch();
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	public void changeScreen(int screen){
		switch(screen){
			case MENU:
				if(mainMenu == null) {
					mainMenu = new MainMenu(this);
					this.setScreen(mainMenu);
				}
				break;
			case LEVEL1:
				if(mainScreen == null) {
					mainScreen = new MainScreen(this);
					this.setScreen(mainScreen);
				}
				break;
			case LOADLEVEL2:
				if(l1screen == null) {
					l1screen = new Level1CompleteScreen(this);
					this.setScreen(mainScreen);
				}
				break;
			case GAMEOVER:
				if(gameoverscreen == null) {
					gameoverscreen = new GameOverScreen(this);
					this.setScreen(gameoverscreen);
				}
				break;

		}
	}

}
