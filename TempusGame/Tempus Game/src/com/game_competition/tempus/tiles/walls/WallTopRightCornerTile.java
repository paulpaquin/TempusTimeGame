package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallTopRightCornerTile extends Tile {

	public WallTopRightCornerTile(int id) {
		super(Assets.wallTopRightCorner, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}