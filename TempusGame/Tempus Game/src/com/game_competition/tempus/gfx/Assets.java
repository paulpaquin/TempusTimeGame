package com.game_competition.tempus.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static Font font28;
	
	public static BufferedImage menu, tile_dark, tile_light, grass, rock, stone;
	public static BufferedImage wood;
	public static BufferedImage[] dirtTest;
	public static BufferedImage[] player_down, player_up, player_left, player_right, player_attack;
	public static BufferedImage[] start_button;
	public static BufferedImage inventoryScreen;
	
	public static void init() {
		
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/textures/player_sprites.png"));
		SpriteSheet buttons = new SpriteSheet(ImageLoader.loadImage("/textures/menu_buttons.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventory_screen.png");
		
		wood = sheet.crop(0, height, width, height);
		
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
		
		
		start_button = new BufferedImage[2];
		start_button[0] = buttons.crop(0, 0, 161, 54);
		start_button[1] = buttons.crop(0, 54, 161, 54);
		
		dirtTest = new BufferedImage[2];
		dirtTest[0] = sheet.crop(width, 0, width, height);
		dirtTest[1] = sheet.crop(width, height, width, height);
		
		
		grass = sheet.crop(width*2, 0, width, height);
		rock = sheet.crop(width*3, 0, width, height);
		stone = sheet.crop(0, 0, width, height);
		
		
		
		menu = ImageLoader.loadImage("/textures/menu.png");
		
		
		
	}
}
