package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallLeftDotTile extends Tile {

	public WallLeftDotTile(int id) {
		super(Assets.wallLeftDot, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}