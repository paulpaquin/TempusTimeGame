package com.game_competition.tempus.worlds;

import java.awt.Graphics;

import com.game_competition.tempus.Handler;
import com.game_competition.tempus.entities.EntityManager;
import com.game_competition.tempus.entities.creatures.Player;
import com.game_competition.tempus.entities.statics.Door;
import com.game_competition.tempus.entities.statics.TimeAltar;
import com.game_competition.tempus.entities.statics.WallWood;
import com.game_competition.tempus.items.ItemManager;
import com.game_competition.tempus.mechanics.Dialogue;
import com.game_competition.tempus.mechanics.TimeFrame;
import com.game_competition.tempus.tiles.Tile;
import com.game_competition.tempus.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	
	//Dialog
	private Dialogue dialogue;
	//Entities
	private EntityManager entityManager;
	//Item
	private ItemManager itemManager;
	//MECHANICS
	private TimeFrame timeFrame;
		
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		timeFrame = new TimeFrame(handler);
		dialogue = new Dialogue(handler);
		
		//ADD ENTITIES HERE
		entityManager.addEntity(new Door(handler, 64*2, 64*3, width, height, -1, true));
		entityManager.addEntity(new Door(handler, 64*6, 64*11, width, height, 1, false));
		entityManager.addEntity(new TimeAltar(handler, (int)(64*4.5), (int)(64*2.5), width, height));
		//entityManager.addEntity(new FloorHole(handler, 64*3, 64*5, width, height, 1, false));
		//entityManager.addEntity(new WallSmallHole(handler, 64*1, 64*6, width, height, true));
		//entityManager.addEntity(new WallBroken(handler, 64*2, 64*6, width, height, 1, false));
		entityManager.addEntity(new WallWood(handler, 64*6, 64*7, width, height, 1, false));
		//entityManager.addEntity(new Stone(handler, 100, 250, width, height));
		//entityManager.addEntity(new Stone(handler, 150, 250, width, height));
		//entityManager.addEntity(new Stone(handler, 200, 250, width, height));
		//entityManager.addEntity(new Stone(handler, 250, 250, width, height));
		
		loadWorld(path);
		
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
	}
		
	public void tick() {
		itemManager.tick();
		entityManager.tick();
		timeFrame.tick();
		dialogue.tick();
	}
		
	public void render(Graphics g) {
		int xStart = (int)Math.max(0, handler.getGameCamera().getxOffset()/Tile.TILEWIDTH);
		int yStart = (int)Math.max(0, handler.getGameCamera().getyOffset()/Tile.TILEHEIGHT);
		int xEnd = (int)Math.min(width-1, (handler.getGameCamera().getxOffset() + handler.getWidth())/Tile.TILEWIDTH)+1;
		int yEnd = (int)Math.min(height-1, (handler.getGameCamera().getyOffset() + handler.getHeight())/Tile.TILEHEIGHT)+1;
		
		for(int y = yStart; y<yEnd; y++) {
			for(int x = xStart; x<xEnd; x++) {
				getTile(x, y).render(handler, g, (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), handler.getWorld().getTimeFrame().getCurrentTimeFrame());
			}

		}
		itemManager.render(g);
		entityManager.render(g);
		timeFrame.render(g);
		dialogue.render(g);
		
		
		
	}
		
	public Tile getTile(int x, int y) {
		if(x<0 || y<0 || x>=width || y>=height) {
			return Tile.floorTile;
		}
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.floorTile;
		return t;
	}
		
		
	private void loadWorld(String path) {
		
		String file = Utils.loadFileAsString(path);
		
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y<height; y++) {
			for(int x = 0; x<width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
	}

	public Dialogue getDialogue() {
		return dialogue;
	}

	public void setDialogue(Dialogue dialogue) {
		this.dialogue = dialogue;
	}
}

