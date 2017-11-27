package com.game_competition.tempus.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game_competition.tempus.Handler;
import com.game_competition.tempus.gfx.Assets;

public class Tile {
//	private Handler handler;
	
	//STATIC
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	
	//CLASS
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	protected int currentTimeFrame;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Handler handler, Graphics g, int x, int y, int currentTimeFrame){
		//this.handler = handler;
		this.currentTimeFrame = currentTimeFrame;
		if(id == 1) {
			texture = dynamicDirt(handler);
		}
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
//		if(id == 1 && currentTimeFrame >= 2) {
//			return true;
//		}else {
//			return false;
//		}
	}
	
	public int getID() {
		return id;
	}
	
	//Dynamic Tile Selectors
	public BufferedImage dynamicDirt(Handler handler) {
		if(handler.getWorld().getTimeFrame().getCurrentTimeFrame()>=2) {
			return Assets.dirtTest[0];
		}else {
			return Assets.dirtTest[1];
		}
		
	}
}
