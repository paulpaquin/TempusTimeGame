package com.game_competition.tempus.entities.statics;

import java.awt.Graphics;

import com.game_competition.tempus.Handler;
import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.mechanics.Dialogue;
import com.game_competition.tempus.tiles.Tile;

public class Door extends StaticEntity {
	int openTimeFrame;
	boolean isActiveOnce;
	

	public Door(Handler handler, float x, float y, int width, int height, int openTimeFrame, boolean isActiveOnce) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		this.openTimeFrame = openTimeFrame;
		this.isActiveOnce = isActiveOnce;
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 32*2;
		bounds.height = 8*2;
		
		this.health = -1;
	}
		

	@Override
	public void tick() {
		if(isActiveOnce) {
			if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == openTimeFrame) {
				bounds.x = 24*2;
				bounds.y = 0;
				bounds.width = 8*2;
				bounds.height = 32*2;
			}else {
				bounds.x = 0;
				bounds.y = 24*2;
				bounds.width = 32*2;
				bounds.height = 8*2;
			}
		}else {
			if(!(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == openTimeFrame)) {
				bounds.x = 24*2;
				bounds.y = 0;
				bounds.width = 8*2;
				bounds.height = 32*2;
			}else {
				bounds.x = 0;
				bounds.y = 24*2;
				bounds.width = 32*2;
				bounds.height = 8*2;
			}
		}
		
		
	}
		

	@Override
	public void render(Graphics g) {
		if(isActiveOnce) {
			if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == openTimeFrame) {
				g.drawImage(Assets.doorUp[1], (int)(x-handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
			}else {
				g.drawImage(Assets.doorUp[0], (int)(x-handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
			}

		}else {
			if(!(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == openTimeFrame)) {
				g.drawImage(Assets.doorUp[1], (int)(x-handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
			}else {
				g.drawImage(Assets.doorUp[0], (int)(x-handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
			}
		}
		

		
	}

	@Override
	public void die() {
		
		
	}

}
