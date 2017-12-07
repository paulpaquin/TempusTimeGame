package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallTopRightChannelTile extends Tile {

	public WallTopRightChannelTile(int id) {
		super(Assets.wallTopRightChannel, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}