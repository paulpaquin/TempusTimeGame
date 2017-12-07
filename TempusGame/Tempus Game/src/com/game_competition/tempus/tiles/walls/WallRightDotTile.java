package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallRightDotTile extends Tile {

	public WallRightDotTile(int id) {
		super(Assets.wallRightDot, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}