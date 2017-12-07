package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallBottomRightCornerTile extends Tile {

	public WallBottomRightCornerTile(int id) {
		super(Assets.wallBottomRightCorner, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}