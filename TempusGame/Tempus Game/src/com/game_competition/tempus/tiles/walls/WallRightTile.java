package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallRightTile extends Tile {

	public WallRightTile(int id) {
		super(Assets.wallRight, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}