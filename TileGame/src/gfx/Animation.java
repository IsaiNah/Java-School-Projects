package gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private long lastTime, timer;
	private int speed, index;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames)
	{
		this.speed = speed;
		this.frames = frames;
		index = 0;
		lastTime = System.currentTimeMillis();
		timer = 0;
	}
	
	public void tick()
	{
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if (timer > speed)
		{
			index++;
			timer = 0;
			if (index >=frames.length)//Making sure doessnt go out of bounds
			{
				index =0;
			}
		}
	}
	
	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}
	
}
 