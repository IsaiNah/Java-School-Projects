package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader //Loads images 
{

	public static BufferedImage loadImage(String path)
	{
		try{
			return ImageIO.read(ImageLoader.class.getResource(path));	
		}catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);//Exit if image is not loaded
		}
		return null;
		
	}
	
}
