package com.game_competition.tempus.states;

import java.awt.Graphics;

import com.game_competition.tempus.Handler;
import com.game_competition.tempus.entities.creatures.Player;
import com.game_competition.tempus.entities.statics.Stone;
import com.game_competition.tempus.worlds.World;


public class GameState extends State{
	//private State newMenuState;
	
	@SuppressWarnings("unused")
	private Player player;
	private World world;
	
	@SuppressWarnings("unused")
	private Stone stone;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		player = new Player(handler, 100,100);
		stone = new Stone(handler, 100, 200, 0, 0);
		
	}

	@Override
	public void tick() {
		world.tick();
		if(handler.getKeyManager().escape) {
			State.setState(handler.getGame().menuState);
			return;
		}
		
		System.out.println(handler.getWorld().getTimeFrame().getCurrentTimeFrame());
		//System.out.println(handler.getMouseManager().getWheelPosition());
		
	}

	@Override
	public void render(Graphics g) {
		
		world.render(g);
	}	
}
