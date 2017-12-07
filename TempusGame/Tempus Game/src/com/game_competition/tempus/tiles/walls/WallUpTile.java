package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallUpTile extends Tile {

	public WallUpTile(int id) {
		super(Assets.wallUp, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}