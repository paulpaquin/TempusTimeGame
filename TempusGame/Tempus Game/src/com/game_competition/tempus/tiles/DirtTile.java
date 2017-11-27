package com.game_competition.tempus.tiles;

import com.game_competition.tempus.gfx.Assets;

public class DirtTile extends Tile {

	public DirtTile(int id) {
		super(Assets.dirtTest[0], id);
	}
	
	@Override
	public boolean isSolid() {
		if(this.currentTimeFrame>=2) {
			return true;
		}else {
			return false;
		}
		
	}

}
