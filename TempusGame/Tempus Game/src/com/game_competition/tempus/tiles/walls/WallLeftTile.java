package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallLeftTile extends Tile {

	public WallLeftTile(int id) {
		super(Assets.wallLeft, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}