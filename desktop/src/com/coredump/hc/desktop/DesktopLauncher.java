package com.coredump.hc.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.coredump.hc.HCGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Title";
		config.height = HCGame.V_HEIGHT;
		config.width = HCGame.V_WIDTH;
		new LwjglApplication(new HCGame(), config);
	}
}
