package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallTopRightTile extends Tile {

	public WallTopRightTile(int id) {
		super(Assets.wallTopRight, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}