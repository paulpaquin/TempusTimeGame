package com.game_competition.tempus;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.game_competition.tempus.display.Display;
import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.gfx.GameCamera;
import com.game_competition.tempus.input.KeyManager;
import com.game_competition.tempus.input.MouseManager;
import com.game_competition.tempus.mechanics.TimeFrame;
import com.game_competition.tempus.states.GameState;
import com.game_competition.tempus.states.MenuState;
import com.game_competition.tempus.states.State;

@SuppressWarnings("unused")
public class Game implements Runnable {
	
	private  Display display;
	private int width, height;
	public String title;
	
	boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	
	//STATES
	public State gameState;
	public State menuState;
	public State controlsState;
	
	//INPUT
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//CAMERA
	private GameCamera gameCamera;
	
	//HANDLER
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getFrame().addMouseWheelListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseWheelListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		//settingsState = new settingsState(handler);
		State.setState(menuState);
		
		
	}
	
	private void tick() {
		keyManager.tick();
		if(State.getState() != null) {
			State.getState().tick();
		}
	}
	
	private void render() {
		
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		
		//Draw Here!
		
		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run() {
		init();
		int fps = 60;
		double timePerClick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerClick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if (timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
