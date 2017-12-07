package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallTopLeftChannelTile extends Tile {

	public WallTopLeftChannelTile(int id) {
		super(Assets.wallTopLeftChannel, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}