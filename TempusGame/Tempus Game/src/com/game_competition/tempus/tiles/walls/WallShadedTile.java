package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallShadedTile extends Tile {

	public WallShadedTile(int id) {
		super(Assets.wallShaded, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}