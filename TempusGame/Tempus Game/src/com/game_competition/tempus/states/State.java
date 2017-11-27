package com.game_competition.tempus.states;

import java.awt.Graphics;

import com.game_competition.tempus.Handler;

public abstract class State {
	
	//GAME STATE MANAGER
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	//CLASSES FOR STATES
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
}
