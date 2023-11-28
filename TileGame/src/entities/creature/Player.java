package entities.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.w3c.dom.Entity;

import gfx.Animation;
import gfx.Assets;
import launcher.Game;
import launcher.Handler;


public class Player extends Creature
{
	
	// Animations
	private Animation animDown, animUp, animLeft, animRight;

	public Player(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		//Can be commented to cover whole player
		bounds.x = 32;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;

		//Animation speed
		animDown = new Animation(500, Assets.player_down);//half a second
		animUp = new Animation(500, Assets.player_up);//half a second
		animLeft = new Animation(500, Assets.player_left);//half a second
		animRight = new Animation(500, Assets.player_right);//half a second
	}

	@Override
	public void tick() {
		// Handles input & will be changed
		animDown.tick();//Ticking animation
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
	
		
		getInput();//Getting input
		move();
		handler.getGameCamera().centerOnEntity(this);
		//Attack
	}
	private void checkAttacks()
	{
		Rectangle cb = getCollisionBounds(0,0);//Coordinate of player collsion box
		Rectangle ar = new Rectangle(); //Attack Rectangle
		int arSize = 20; // size of attack Rectangle
		ar.width = arSize;
		ar.height = arSize;
		
		if (handler.getKeyManager().aUp)
		{
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}else if (handler.getKeyManager().aDown)
		{
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if (handler.getKeyManager().aLeft)
		{
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize /2;
		} else if (handler.getKeyManager().aRight)
		{
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize /2;
		}else
		{
			return;
		}
		
		//Checking for attack
		
		for (entities.Entity e : handler.getWorld().getEntityManager().getEntities())
		{
			if (e.equals(this))
				continue; // skip, eg break
			if (e.getCollisionBounds(0,0).intersects(ar))
			{
				e.hurt(1);
				return;
			}
			
		}
		
	}
	
	private void getInput()
	{
		xMove = 0;
		yMove =0;
		
		if (handler.getKeyManager().up)
		yMove = -speed;
		if (handler.getKeyManager().down)
			yMove = speed;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right)
			xMove = speed;
	//	if (handler.getKeyManager().esc)
			//handler.getGame().menuState;
		//NOTE ABOVE: set variable in player to be able to call this


	}

	@Override
	public void render(Graphics g) {
	
		g.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
		//Collision box - FOR DEBUGGING
	/*	g.setColor(Color.red);
		g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
			(int)(y + bounds.y - handler.getGameCamera().getyOffset()),
					bounds.width, bounds.height);*/
	}
	
	private BufferedImage getCurrentAnimationFrame()
	{
		if (xMove < 0)// moving left (negarive num)
		{
			return animLeft.getCurrentFrame();
		}else if (xMove > 0)
		{
			return animRight.getCurrentFrame();
		}else if (yMove < 0)
		{
			return animUp.getCurrentFrame();
		}else
		{
			return animDown.getCurrentFrame();
		}
	}

	@Override
	public void die() {
		System.out.println("You lose!");
		
	}

}
