package states;

import java.awt.Graphics;



import entities.creature.Player;
import entities.statics.Tree;
import gfx.Assets;
import launcher.Game;
import launcher.Handler;
import tiles.Tile;
import world.World;

public class GameState extends State //Java inheritances
{
	//private Player player; WIll be chagned to entityManager
	private World world;

	
	public GameState(Handler handler)
	{
		super(handler);
		world = new World(handler, "resources/world/world1.txt");
		handler.setWorld(world);
		//player = new Player(handler, 100, 100, 32, 32);
		
		
		
	}
	
	public void tick()
	{
		world.tick();
		//player.tick();
	
	}
	public void render(Graphics g)
	{
	//g.drawImage(Assets.player1, 0, 0,null);
	world.render(g);
	//player.render(g);

	}
	

}
