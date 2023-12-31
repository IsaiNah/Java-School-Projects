package entities.statics;

import java.awt.Graphics;

import gfx.Assets;
import launcher.Handler;
import tiles.Tile;

public class Tree extends StaticEntity{
	
	public Tree(Handler handler, float x, float y)
	{
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT *2);
		
		//Rectangle bound box for the tree in pixils
		bounds.x = 10;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width - 20;
		bounds.height = (int) (height - height / 1.5f);
	
	}

	@Override
	public void tick() {
	
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int)(x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()),width, height, null);
		
	}

	@Override
	public void die() {
		
		
	}
	

}
