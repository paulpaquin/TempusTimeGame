package com.game_competition.tempus.entities.creatures;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game_competition.tempus.Handler;
import com.game_competition.tempus.entities.Entity;
import com.game_competition.tempus.gfx.Animation;
import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.inventory.Inventory;
import com.game_competition.tempus.mechanics.TimeFrame;

public class Player extends Creature {
	private int currentTimeFrame;
	
	private Animation animDown, animUp, animLeft, animRight;
	private Animation animDownYoung, animUpYoung, animLeftYoung, animRightYoung;
	private Animation animDownOld, animUpOld, animLeftOld, animRightOld;
	public String lastDirection = "down";
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	private final int ATTACK_ANIMATION_TIME = 150;
	private Inventory inventory;
	
	private float kidSpeed = 4.5f;
	private float adultSpeed = 3.0f;
	private float elderSpeed = 1.5f;
	

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 22;
		bounds.y = 35;
		bounds.width = 20;
		bounds.height = 25;
		
		animDownYoung = new Animation(200, Assets.player_down_young);
		animUpYoung = new Animation(200, Assets.player_up_young);
		animLeftYoung = new Animation(200, Assets.player_left_young);
		animRightYoung = new Animation(200, Assets.player_right_young);
		
		animDown = new Animation(200, Assets.player_down);
		animUp = new Animation(200, Assets.player_up);
		animLeft = new Animation(200, Assets.player_left);
		animRight = new Animation(200, Assets.player_right);
		
		animDownOld = new Animation(200, Assets.player_down_old);
		animUpOld = new Animation(200, Assets.player_up_old);
		animLeftOld = new Animation(200, Assets.player_left_old);
		animRightOld = new Animation(200, Assets.player_right_old);
		
		inventory = new Inventory(handler);
		
		
		
		
	}

	@Override
	public void tick() {
		
		//Animations
		if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
			animDownYoung.tick();
			animUpYoung.tick();
			animLeftYoung.tick();
			animRightYoung.tick();
		}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
			animDownOld.tick();
			animUpOld.tick();
			animLeftOld.tick();
			animRightOld.tick();
		}else {
			animDown.tick();
			animUp.tick();
			animLeft.tick();
			animRight.tick();
		}
		
//		animDown.tick();
//		animUp.tick();
//		animLeft.tick();
//		animRight.tick();
		
		
		//Movement
		getLastDirection();
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		//Attack
		checkAttacks();
		
		//Inventory
		inventory.tick();
	}
	
	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() -lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown) {
			return;
		}
		
		if(inventory.isActive()) {
			return;
		}
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().space && getLastDirection().equals("up")) {
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y - arSize;
			
		}else if(handler.getKeyManager().space && getLastDirection().equals("down")) {
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y + cb.height;
			
		}else if(handler.getKeyManager().space && getLastDirection().equals("left")) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height/2 - arSize/2;
			
		}else if(handler.getKeyManager().space && getLastDirection().equals("right")) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height/2 - arSize/2;
		
		}else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) {
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}
	}
	
	
	@Override
	public void die() {
		System.out.println("You Lose");
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(inventory.isActive()) {
			return;
		}
		
		if(handler.getWorld().getTimeFrame().getCurrentTimeFrame()==TimeFrame.TIMEFRAME_MIN) {
			this.speed = kidSpeed;
		}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame()==TimeFrame.TIMEFRAME_MAX) {
			this.speed = elderSpeed;
		}else {
			this.speed = adultSpeed;
		}
		
		
		if(handler.getKeyManager().up) {
			yMove = -speed;
		}
		if(handler.getKeyManager().down) {
			yMove = speed;
		}
		if(handler.getKeyManager().left) {
			xMove = -speed;
		}
		if(handler.getKeyManager().right) {
			xMove = speed;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), 64, 64, null);
		
		inventory.render(g);
		
		//g.setColor(Color.red);
		//g.fillRect((int)(x+bounds.x-handler.getGameCamera().getxOffset()), (int)(y+bounds.y-handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(attackTimer<ATTACK_ANIMATION_TIME && getLastDirection().equals("down")) {
			if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
				return Assets.player_attack_young[2];
			}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
				return Assets.player_attack_old[2];
			}else {
				return Assets.player_attack[2];
			}
//			return Assets.player_attack[2];
		}else if(attackTimer<ATTACK_ANIMATION_TIME && getLastDirection().equals("up")) {
			if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
				return Assets.player_attack_young[3];
			}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
				return Assets.player_attack_old[3];
			}else {
				return Assets.player_attack[3];
			}
//			return Assets.player_attack[3];
	 	}else if(attackTimer<ATTACK_ANIMATION_TIME && getLastDirection().equals("left")) {
			if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
				return Assets.player_attack_young[1];
			}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
				return Assets.player_attack_old[1];
			}else {
				return Assets.player_attack[1];
			}
//			return Assets.player_attack[1];
		}else if(attackTimer<ATTACK_ANIMATION_TIME && getLastDirection().equals("right")) {
			if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
				return Assets.player_attack_young[0];
			}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
				return Assets.player_attack_old[0];
			}else {
				return Assets.player_attack[0];
			}
//			return Assets.player_attack[0];
		}else {
			if(xMove < 0) {
				if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
					return animLeftYoung.getCurrentFrame();
				}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
					return animLeftOld.getCurrentFrame();
				}else {
					return animLeft.getCurrentFrame();
			}
//				return animLeft.getCurrentFrame();
			}else if(xMove > 0) {
				if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
					return animRightYoung.getCurrentFrame();
				}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
					return animRightOld.getCurrentFrame();
				}else {
					return animRight.getCurrentFrame();
				}
//				return animRight.getCurrentFrame();
			}else if(yMove < 0) {
				if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
					return animUpYoung.getCurrentFrame();
				}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
					return animUpOld.getCurrentFrame();
				}else {
					return animUp.getCurrentFrame();
				}
//				return animUp.getCurrentFrame();
			}else if(yMove > 0) {
				if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
					return animDownYoung.getCurrentFrame();
				}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
					return animDownOld.getCurrentFrame();
				}else {
					return animDown.getCurrentFrame();
				}
//				return animDown.getCurrentFrame();
			}else {
				if(getLastDirection().equals("left")) {
					if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
						return Assets.player_left_young[1];
					}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
						return Assets.player_left_old[1];
					}else {
						return Assets.player_left[1];
					}
//					return Assets.player_left[1];
				}else if(getLastDirection().equals("right")) {
					if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
						return Assets.player_right_young[1];
					}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
						return Assets.player_right_old[1];
					}else {
						return Assets.player_right[1];
					}
//					return Assets.player_right[1];
				}else if(getLastDirection().equals("up")) {
					if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
						return Assets.player_up_young[1];
					}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
						return Assets.player_up_old[1];
					}else {
						return Assets.player_up[1];
					}
//					return Assets.player_up[1];
				}else {
					if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MIN) {
						return Assets.player_down_young[1];
					}else if(handler.getWorld().getTimeFrame().getCurrentTimeFrame() == handler.getWorld().getTimeFrame().TIMEFRAME_MAX) {
						return Assets.player_down_old[1];
					}else {
						return Assets.player_down[1];
					}
//					return Assets.player_down[1];
				}
			}
		}
		
	}
	
	public String getLastDirection() {
		String lastDirection = this.lastDirection;
		if(xMove < 0) {
			this.lastDirection = "left";
		}else if(xMove > 0) {
			this.lastDirection = "right";
			
		}else if(yMove < 0) {
			this.lastDirection = "up";
		}else if(yMove > 0) {
			this.lastDirection = "down";
		}
		return lastDirection;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
