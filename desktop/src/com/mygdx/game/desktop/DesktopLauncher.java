package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.bgame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Flappy Bird";
		config.useGL30 = false;
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new bgame(), config);
	}
}
