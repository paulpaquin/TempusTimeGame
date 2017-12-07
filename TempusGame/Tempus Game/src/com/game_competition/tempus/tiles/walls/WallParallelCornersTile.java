package com.game_competition.tempus.tiles.walls;

import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.tiles.Tile;

public class WallParallelCornersTile extends Tile {

	public WallParallelCornersTile(int id) {
		super(Assets.wallParallelCorners, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}