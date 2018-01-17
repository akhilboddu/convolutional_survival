package com.convolution.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.convolution.game.Convolution;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Convolution.V_HEIGHT;
		config.width = Convolution.V_WIDTH;
		config.title = "Convolutional Survival";
		new LwjglApplication(new Convolution(), config);
	}
}
