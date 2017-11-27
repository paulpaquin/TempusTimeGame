package com.game_competition.tempus.tiles;

import com.game_competition.tempus.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.rock, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}