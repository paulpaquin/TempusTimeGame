package com.game_competition.tempus.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game_competition.tempus.Handler;
import com.game_competition.tempus.entities.Entity;
import com.game_competition.tempus.gfx.Animation;
import com.game_competition.tempus.gfx.Assets;
import com.game_competition.tempus.inventory.Inventory;

public class Player extends Creature {
	
	private Animation animDown, animUp, animLeft, animRight;
	public String lastDirection = "down";
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	private final int ATTACK_ANIMATION_TIME = 150;
	private Inventory inventory;
	

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 38;
		bounds.y = 55;
		bounds.width = 18;
		bounds.height = 35;
		
		animDown = new Animation(200, Assets.player_down);
		animUp = new Animation(200, Assets.player_up);
		animLeft = new Animation(200, Assets.player_left);
		animRight = new Animation(200, Assets.player_right);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
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
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), 96, 96, null);
		
		inventory.render(g);
		
//		g.setColor(Color.red);
//		g.fillRect((int)(x+bounds.x-handler.getGameCamera().getxOffset()), (int)(y+bounds.y-handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(attackTimer<ATTACK_ANIMATION_TIME && getLastDirection().equals("down")) {
			return Assets.player_attack[2];
		}else if(attackTimer<ATTACK_ANIMATION_TIME && getLastDirection().equals("up")) {
			return Assets.player_attack[3];
	 	}else if(attackTimer<ATTACK_ANIMATION_TIME && getLastDirection().equals("left")) {
			return Assets.player_attack[1];
		}else if(attackTimer<ATTACK_ANIMATION_TIME && getLastDirection().equals("right")) {
			return Assets.player_attack[0];
		}else {
			if(xMove < 0) {
				return animLeft.getCurrentFrame();
			}else if(xMove > 0) {
				return animRight.getCurrentFrame();
				
			}else if(yMove < 0) {
				return animUp.getCurrentFrame();
			}else if(yMove > 0) {
				return animDown.getCurrentFrame();
			}else {
				if(getLastDirection().equals("left")) {
					return Assets.player_left[1];
				}else if(getLastDirection().equals("right")) {
					return Assets.player_right[1];
				}else if(getLastDirection().equals("up")) {
					return Assets.player_up[1];
				}else {
					return Assets.player_down[1];
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
