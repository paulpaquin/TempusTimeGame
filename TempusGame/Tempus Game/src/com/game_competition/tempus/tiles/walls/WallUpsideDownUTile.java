package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallUpsideDownUTile extends Tile {

	public WallUpsideDownUTile(int id) {
		super(Assets.wallUpsideDownU, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}