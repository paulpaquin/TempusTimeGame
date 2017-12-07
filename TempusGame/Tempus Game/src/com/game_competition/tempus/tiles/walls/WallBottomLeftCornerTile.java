package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallBottomLeftCornerTile extends Tile {

	public WallBottomLeftCornerTile(int id) {
		super(Assets.wallBottomLeftCorner, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}