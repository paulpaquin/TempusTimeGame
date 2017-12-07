package com.game_competition.tempus;

import com.game_competition.tempus.gfx.GameCamera;
import com.game_competition.tempus.input.KeyManager;
import com.game_competition.tempus.input.MouseManager;
import com.game_competition.tempus.worlds.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public Handler(Game game){
		this.game = game;
		
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
