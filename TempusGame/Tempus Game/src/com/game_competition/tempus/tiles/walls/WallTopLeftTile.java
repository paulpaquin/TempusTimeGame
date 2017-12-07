package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallTopLeftTile extends Tile {

	public WallTopLeftTile(int id) {
		super(Assets.wallTopLeft, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}