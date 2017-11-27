package com.game_competition.tempus.mechanics;

import com.game_competition.tempus.Handler;

public class TimeFrame {
	
	private Handler handler;
	//private static int wheelPosition;
	private static final int TIMEFRAME_MIN = 0;
	private static final int TIMEFRAME_MAX = 4;
	private static int currentTimeFrame = 2;
	private static long lastScrollTimer, scrollWait = 150, scrollTimer = scrollWait;
	
	public TimeFrame(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		currentTimeFrame(handler);
	}
	
	public int currentTimeFrame(Handler handler) {
		scrollTimer += System.currentTimeMillis() -lastScrollTimer;
		lastScrollTimer = System.currentTimeMillis();
		if(scrollTimer < scrollWait) {
			return currentTimeFrame;
		}else {
			scrollTimer = 0;
			if(handler.getKeyManager().downA) {
				currentTimeFrame--;
			}else if(handler.getKeyManager().upA) {
				currentTimeFrame++;
			}
			if(currentTimeFrame<TIMEFRAME_MIN) {
				currentTimeFrame = TIMEFRAME_MIN;
			}else if(currentTimeFrame>TIMEFRAME_MAX) {
				currentTimeFrame = TIMEFRAME_MAX;
			}
			
			return currentTimeFrame;
		}

	}

	public int getCurrentTimeFrame() {
		return currentTimeFrame;
	}

	public void setCurrentTimeFrame(int currentTimeFrame) {
		TimeFrame.currentTimeFrame = currentTimeFrame;
	}

}
