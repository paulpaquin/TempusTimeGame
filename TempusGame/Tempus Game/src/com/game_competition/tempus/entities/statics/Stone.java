package com.game_competition.tempus.entities.statics;

import java.awt.Graphics;

import com.game_competition.tempus.Handler;
import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.items.Item;
import com.game_competition.tempus.tiles.Tile;

public class Stone extends StaticEntity {

	public Stone(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, Tile.TILEHEIGHT, Tile.TILEWIDTH);
		
		bounds.x = 10;
		bounds.y = 30;
		bounds.width = 45;
		bounds.height = 25;
		
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int)x, (int)y));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.stone, (int)(x-handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
	}

}
