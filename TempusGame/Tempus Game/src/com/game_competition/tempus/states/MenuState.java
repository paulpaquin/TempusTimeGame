package com.game_competition.tempus.states;

import java.awt.Graphics;

import com.game_competition.tempus.Handler;
import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.ui.ClickListener;
import com.game_competition.tempus.ui.UIImageButton;
import com.game_competition.tempus.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(Handler handler){
		super(handler);
		this.uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(80, 218, 161, 53, Assets.start_button, new ClickListener() {
			
			@Override
			public void onClick() {
				//handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
		
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.menu, 0, 0, 640, 480, null);
		uiManager.render(g);
		
		
		
	}
	
}
