package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
	private BufferedImage sheet;//Spritesheet buffered image
	
	public SpriteSheet(BufferedImage sheet)
	{
		this.sheet = sheet;
	}
	public BufferedImage crop(int x, int y, int width, int height)// To crop spritesheet
	{
		return sheet.getSubimage(x, y, width, height);
	}
	
	
	
}
