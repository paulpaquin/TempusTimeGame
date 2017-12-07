package com.game_competition.tempus.mechanics;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.game_competition.tempus.Handler;
import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.gfx.Text;
import com.game_competition.tempus.items.Item;

public class TimeFrame {
	
	private Handler handler;
	//private static int wheelPosition;
	private long lastChangeTimer, screenDuration = 450, screenTimer = screenDuration;
	public static final int TIMEFRAME_MIN = 0;
	public static final int TIMEFRAME_MAX = 2;
	private static int currentTimeFrame = 1;
	private int tempTimeState = currentTimeFrame;
	
	public TimeFrame(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		if(!(handler.getWorld().getEntityManager().getPlayer().getInventory().inventoryItems.contains(Item.timeHeart))){
			return;
		}
		currentTimeFrame(handler);
	}
	
	public void render(Graphics g) {
		if(!(handler.getWorld().getEntityManager().getPlayer().getInventory().inventoryItems.contains(Item.timeHeart))){
			return;
		}
		screenTimer += System.currentTimeMillis() -lastChangeTimer;
		lastChangeTimer = System.currentTimeMillis();
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) {
			
			if(tempTimeState == currentTimeFrame) {
				return;
			}
			tempTimeState = currentTimeFrame;
			screenTimer = 0;
		}
		
		if(screenTimer<screenDuration) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 640, 480);
			Text.drawString(g, timeFrameIntToString(currentTimeFrame), 320, 240, true, Color.BLUE, Assets.font28);
			
			
		}
	}
	
	public int currentTimeFrame(Handler handler) {

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
			currentTimeFrame++;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) {
			currentTimeFrame--;
		}
		
		if(currentTimeFrame<TIMEFRAME_MIN) {
			currentTimeFrame = TIMEFRAME_MIN;
		}else if(currentTimeFrame>TIMEFRAME_MAX) {
			currentTimeFrame = TIMEFRAME_MAX;
		}
		
		return currentTimeFrame;

	}
	
	public String timeFrameIntToString(int timeFrame) {
		if(timeFrame==TIMEFRAME_MIN) {
			return "PAST";
		}else if(timeFrame==TIMEFRAME_MAX) {
			return "FUTURE";
		}else {
			return "PRESENT";
		}
	}

	public int getCurrentTimeFrame() {
		return currentTimeFrame;
	}

	public void setCurrentTimeFrame(int currentTimeFrame) {
		TimeFrame.currentTimeFrame = currentTimeFrame;
	}

}
