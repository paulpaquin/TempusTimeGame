package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallTile extends Tile {

	public WallTile(int id) {
		super(Assets.wall, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}