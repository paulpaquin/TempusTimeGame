package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallTopLeftCornerTile extends Tile {

	public WallTopLeftCornerTile(int id) {
		super(Assets.wallTopLeftCorner, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}