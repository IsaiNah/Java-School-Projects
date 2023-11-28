package gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static final int width = 62, height = 62;// Note: 64 seems too large
	public static final int tileWidth = 64, tileHeight = 64;
 	
	public static BufferedImage [] player_down, player_up, player_left, player_right, menuButtons;
	public static BufferedImage tile1, tile2, tile3, tile4, tile5, tree;
;
	
	//public static BufferedImage tile[];
	
	public static void init()// Will load everything into game, only called once
	{
		
		SpriteSheet uiSheet = new SpriteSheet(ImageLoader.loadImage("/Textures/button-start-spritesheet.png"));
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Textures/Heroe (2) (1)_0.png"));
		SpriteSheet tileSet = new SpriteSheet(ImageLoader.loadImage("/Textures/master-tileset.png"));
		player_down = new BufferedImage[4];
		player_up = new BufferedImage[4];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];
		menuButtons = new BufferedImage[3];
		
		//Loading menu Buttons
		int cropWidth = 201, cropHeight = 71;
		menuButtons[0] = uiSheet.crop(cropWidth * 0 , cropHeight * 0, cropWidth, cropHeight); 
		menuButtons[1] = uiSheet.crop(cropWidth * 0, cropHeight * 1, cropWidth, cropHeight); 
		menuButtons[2] = uiSheet.crop(cropWidth * 0, cropHeight * 2, cropWidth, cropHeight);
	/*	for (int i =0; i < 4; ++i)
			{
				int cropWidth = 200, cropHeight = 70;
				menuButtons[i] = uiSheet.crop(cropWidth, cropHeight * i, cropWidth, cropHeight);
			}*/
		
		//Loading animations into arrays
		for (int i =0; i < 4; ++i)
			player_down[i] = sheet.crop(width * i,height * 0 , width, height);
			for (int i =0; i < 4; ++i)
			player_up[i] = sheet.crop(width * i,height * 1, width, height);
			for (int i =0; i < 4; ++i)
			player_left[i] = sheet.crop(width * i,height * 2 , width, height);
			
			for (int i = 0 ; i < 4; ++i)
			player_right[i] = sheet.crop(width * i,180 , width, height);
			/*for (int i =0; i < 4; ++i)
			player_right[i] = sheet.crop(width * i,height * 3 , width, height);
			*/
	tree = ImageLoader.loadImage("/Textures/tree.png");
			
			// To DO: Change this to array 
		tile1 = tileSet.crop(tileWidth * 0,tileHeight * 2, width, height);
		tile2 = tileSet.crop(tileWidth * 0,tileHeight * 0, width, height);
		tile3 = tileSet.crop(tileWidth * 0,tileHeight * 0, width, height);
		tile4 = tileSet.crop(tileWidth * 3,tileHeight * 3, width, height);
//		tile = new BufferedImage[5];
//		for (int i= 0; i < tile.length; i++)
//			tile[i] = tileSet.crop(tileWidth * i, tileHeight * i, tileWidth, tileHeight);
//		
		
	}
	
}
