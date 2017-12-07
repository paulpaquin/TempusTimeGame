package com.game_competition.tempus.states;

import java.awt.Graphics;

import com.game_competition.tempus.Handler;
import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.ui.ClickListener;
import com.game_competition.tempus.ui.UIImageButton;
import com.game_competition.tempus.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	private UIImageButton play;
	private UIImageButton controls, controlsBack, controlsPlay;
	private boolean controlDisplay = false;
	
	
	public MenuState(Handler handler){
		super(handler);
		this.uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		play = new UIImageButton(80, 218, 161, 53, Assets.startButton, new ClickListener() {
			
			@Override
			public void onClick() {
				//handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
				handler.getWorld().getDialogue().setDialogue("Where am I?");
			}
		});
		
		controls = new UIImageButton(400, 218, 161, 53, Assets.controlsButton, new ClickListener() {		
			
			@Override
			public void onClick() {
				controlDisplay = true;
				uiManager.addObject(controlsPlay);
				uiManager.addObject(controlsBack);
				uiManager.removeObject(play);
				uiManager.removeObject(controls);
				
			}
		});

		controlsBack = new UIImageButton(80, 381, 161, 53, Assets.backButton, new ClickListener() {
			
			@Override
			public void onClick() {
				controlDisplay = false;
				uiManager.addObject(play);
				uiManager.addObject(controls);
				uiManager.removeObject(controlsPlay);
				uiManager.removeObject(controlsBack);
				
			}
		});
		
		controlsPlay = new UIImageButton(400, 381, 161, 53, Assets.startButton, new ClickListener() {
			
			@Override
			public void onClick() {
				
//				uiManager.removeObject(controlsPlay);
//				uiManager.removeObject(controlsBack);
//				uiManager.addObject(play);
//				uiManager.addObject(controls);
				
//				controlDisplay = false;

				State.setState(handler.getGame().gameState);
				handler.getWorld().getDialogue().setDialogue("Where am I?");
			}
		});

		uiManager.addObject(play);
		uiManager.addObject(controls);
		
	}

	@Override
	public void tick() {
		uiManager.tick();
		
	}

	@Override
	public void render(Graphics g) {
		if(controlDisplay) {
			g.drawImage(Assets.controls, 0, 0, 640, 480, null);
		}else {
			g.drawImage(Assets.menu, 0, 0, 640, 480, null);
		}
		uiManager.render(g);
		
		
		
	}
	
}
