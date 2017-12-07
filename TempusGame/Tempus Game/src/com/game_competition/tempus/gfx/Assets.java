package com.game_competition.tempus.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static Font font28;
	public static Font font16;
	
	public static BufferedImage menu, controls, tile_dark, tile_light, grass, rock, stone;
	public static BufferedImage wall, wallShaded, wallWood, wallLeft, wallUp, wallRight, wallTopLeftCorner, wallTopRightCorner, wallBottomLeftCorner, 
								wallBottomRightCorner, wallTopLeft, wallTopRight, wallParallel, wallParallelCorners, wallUpsideDownU, floor, floorHole, 
								floorShaded, floorBroken, floorShadedBroken, wallTopRightChannel, wallTopLeftChannel, wallLeftDot, wallRightDot, blank;
	public static BufferedImage[] wallBroken, doorUp, wallSmallHole, heartAltar;
	public static BufferedImage wood, timeHeart;
	public static BufferedImage[] dirtTest;
	public static BufferedImage[] player_down_young, player_up_young, player_left_young, player_right_young, player_attack_young;
	public static BufferedImage[] player_down, player_up, player_left, player_right, player_attack;
	public static BufferedImage[] player_down_old, player_up_old, player_left_old, player_right_old, player_attack_old;
	public static BufferedImage[] startButton, controlsButton, backButton;
	public static BufferedImage inventoryScreen;
	
	public static void init() {
		
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		font16 = FontLoader.loadFont("res/fonts/slkscr.ttf", 16);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet textures = new SpriteSheet(ImageLoader.loadImage("/textures/tempus textures.png"));
		SpriteSheet playerYoung = new SpriteSheet(ImageLoader.loadImage("/textures/player_sprites_young.png"));
		SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/textures/player_sprites.png"));
		SpriteSheet playerOld = new SpriteSheet(ImageLoader.loadImage("/textures/player_sprites_old.png"));
		SpriteSheet buttons = new SpriteSheet(ImageLoader.loadImage("/textures/menu_buttons.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventory_screen.png");
		
		wood = sheet.crop(0, height, width, height);
		
		player_down_young = new BufferedImage[4];
		player_down_young[0] = playerYoung.crop(0, height, width, height);
		player_down_young[1] = playerYoung.crop(width*2, height*2, width, height);
		player_down_young[2] = playerYoung.crop(width, height, width, height);
		player_down_young[3] = player_down_young[1];
		
		player_up_young = new BufferedImage[4];
		player_up_young[0] = playerYoung.crop(width*2, height, width, height);
		player_up_young[1] = playerYoung.crop(width*3, height*2, width, height);
		player_up_young[2] = playerYoung.crop(width*3, height, width, height);
		player_up_young[3] = player_up_young[1];
		
		player_left_young = new BufferedImage[4];
		player_left_young[0] = playerYoung.crop(width*2, 0, width, height);
		player_left_young[1] = playerYoung.crop(width, height*2, width, height);
		player_left_young[2] = playerYoung.crop(width*3, 0, width, height);
		player_left_young[3] = player_left_young[1];
		
		player_right_young = new BufferedImage[4];
		player_right_young[0] = playerYoung.crop(0, 0, width, height);
		player_right_young[1] = playerYoung.crop(0, height*2, width, height);
		player_right_young[2] = playerYoung.crop(width, 0, width, height);
		player_right_young[3] = player_right_young[1];
		
		player_attack_young = new BufferedImage[4];
		player_attack_young[0] = playerYoung.crop(0, height*3, width, height);
		player_attack_young[1] = playerYoung.crop(width, height*3, width, height);
		player_attack_young[2] = playerYoung.crop(width*2, height*3, width, height);
		player_attack_young[3] = playerYoung.crop(width*3, height*3, width, height);
		
		player_down = new BufferedImage[4];
		player_down[0] = player.crop(0, height, width, height);
		player_down[1] = player.crop(width*2, height*2, width, height);
		player_down[2] = player.crop(width, height, width, height);
		player_down[3] = player_down[1];
		
		player_up = new BufferedImage[4];
		player_up[0] = player.crop(width*2, height, width, height);
		player_up[1] = player.crop(width*3, height*2, width, height);
		player_up[2] = player.crop(width*3, height, width, height);
		player_up[3] = player_up[1];
		
		player_left = new BufferedImage[4];
		player_left[0] = player.crop(width*2, 0, width, height);
		player_left[1] = player.crop(width, height*2, width, height);
		player_left[2] = player.crop(width*3, 0, width, height);
		player_left[3] = player_left[1];
		
		player_right = new BufferedImage[4];
		player_right[0] = player.crop(0, 0, width, height);
		player_right[1] = player.crop(0, height*2, width, height);
		player_right[2] = player.crop(width, 0, width, height);
		player_right[3] = player_right[1];
		
		player_attack = new BufferedImage[4];
		player_attack[0] = player.crop(0, height*3, width, height);
		player_attack[1] = player.crop(width, height*3, width, height);
		player_attack[2] = player.crop(width*2, height*3, width, height);
		player_attack[3] = player.crop(width*3, height*3, width, height);
		
		player_down_old = new BufferedImage[4];
		player_down_old[0] = playerOld.crop(0, height, width, height);
		player_down_old[1] = playerOld.crop(width*2, height*2, width, height);
		player_down_old[2] = playerOld.crop(width, height, width, height);
		player_down_old[3] = player_down_old[1];
		
		player_up_old = new BufferedImage[4];
		player_up_old[0] = playerOld.crop(width*2, height, width, height);
		player_up_old[1] = playerOld.crop(width*3, height*2, width, height);
		player_up_old[2] = playerOld.crop(width*3, height, width, height);
		player_up_old[3] = player_up_old[1];
		
		player_left_old = new BufferedImage[4];
		player_left_old[0] = playerOld.crop(width*2, 0, width, height);
		player_left_old[1] = playerOld.crop(width, height*2, width, height);
		player_left_old[2] = playerOld.crop(width*3, 0, width, height);
		player_left_old[3] = player_left_old[1];
		
		player_right_old = new BufferedImage[4];
		player_right_old[0] = playerOld.crop(0, 0, width, height);
		player_right_old[1] = playerOld.crop(0, height*2, width, height);
		player_right_old[2] = playerOld.crop(width, 0, width, height);
		player_right_old[3] = player_right_old[1];
		
		player_attack_old = new BufferedImage[4];
		player_attack_old[0] = playerOld.crop(0, height*3, width, height);
		player_attack_old[1] = playerOld.crop(width, height*3, width, height);
		player_attack_old[2] = playerOld.crop(width*2, height*3, width, height);
		player_attack_old[3] = playerOld.crop(width*3, height*3, width, height);
		
		
		
		
		startButton = new BufferedImage[2];
		startButton[0] = buttons.crop(0, 0, 161, 54);
		startButton[1] = buttons.crop(0, 54, 161, 54);
		
		controlsButton = new BufferedImage[2];
		controlsButton[0] = buttons.crop(161, 0, 161, 54);
		controlsButton[1] = buttons.crop(161, 54, 161, 54);
		
		backButton = new BufferedImage[2];
		backButton[0] = buttons.crop(161*2, 0, 161, 54);
		backButton[1] = buttons.crop(161*2, 54, 161, 54);
		
		dirtTest = new BufferedImage[2];
		dirtTest[0] = sheet.crop(width, 0, width, height);
		dirtTest[1] = sheet.crop(width, height, width, height);
		
		doorUp = new BufferedImage[2];
		doorUp[0] = textures.crop(0, height*3, width, height);
		doorUp[1] = textures.crop(width, height*3, width, height);
		
		floorHole = textures.crop(width*2, height*3, width, height);
		
		wallBroken = new BufferedImage[4];
		wallBroken[0] = textures.crop(width, 0, width, height);
		wallBroken[1] = textures.crop(width*2, 0, width, height);
		wallBroken[2] = textures.crop(width, height, width, height);
		wallBroken[3] = textures.crop(width*2, height, width, height);
		
		wallSmallHole = new BufferedImage[2];
		wallSmallHole[0] = textures.crop(width*3, 0, width, height);
		wallSmallHole[1] = textures.crop(width*3, height, width, height);
		
		heartAltar = new BufferedImage[2];
		heartAltar[0] = textures.crop(0, height*4, width, height);
		heartAltar[1] = textures.crop(width, height*4, width, height);
		
		timeHeart = textures.crop(width*2, height*4, width, height);
		
		wallWood = textures.crop(width*3, height*3, width, height);
		
		
		wall = textures.crop(0, 0, width, height);
		wallShaded = textures.crop(0, height, width, height);
		wallLeft = textures.crop(width*4, 0, width, height);
		wallUp = textures.crop(width*5, 0, width, height);
		wallRight = textures.crop(width*6, 0, width, height);
		wallBottomLeftCorner = textures.crop(width*4, height, width, height);
		wallTopLeftCorner = textures.crop(width*5, height, width, height); 
		wallTopRightCorner = textures.crop(width*6, height, width, height);
		wallBottomRightCorner = textures.crop(width*7, height, width, height);
		wallTopRight = textures.crop(width*4, height*2, width, height);
		wallParallel = textures.crop(width*5, height*2, width, height);
		wallParallelCorners = textures.crop(width*4, height*4, width, height);
		wallUpsideDownU = textures.crop(width*6, height*2, width, height);
		wallTopLeft = textures.crop(width*7, height*2, width, height);
		wallTopRightChannel = textures.crop(width*4, height*3, width, height);
		wallTopLeftChannel = textures.crop(width*7, height*3, width, height);
		wallLeftDot = textures.crop(width*5, height*3, width, height);
		wallRightDot = textures.crop(width*6, height*3, width, height);
		floor = textures.crop(0, height*2, width, height);
		floorShaded = textures.crop(width, height*2, width, height);
		floorBroken = textures.crop(width*2, height*2, width, height);
		floorShadedBroken = textures.crop(width*3, height*2, width, height);
		blank = textures.crop(width*7, 0, width, height);
		
		
		
		
		grass = sheet.crop(width*2, 0, width, height);
		rock = sheet.crop(width*3, 0, width, height);
		stone = sheet.crop(0, 0, width, height);
		
		
		
		menu = ImageLoader.loadImage("/textures/menu.png");
		controls = ImageLoader.loadImage("/textures/controls.png");
		
		
		
	}
}
