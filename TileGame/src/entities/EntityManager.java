package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import entities.creature.Player;
import launcher.Handler;

public class EntityManager {

	private Handler handler;
	private Player player;//Access to player in entities list
	private ArrayList<Entity> entities;// Like vector in c++
	private Comparator<Entity> renderSorter = new Comparator<Entity>()
	{
		public int compare(Entity a, Entity b)
		{
			if (a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1; //a should be rendered before b
			else return 1;// a should be after b
		}
	};
	
	public EntityManager(Handler handler, Player player)
	{
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();//Like vector in c++
		addEntity(player);
	}
	
	public void tick()
	{
		for (int i = 0; i < entities.size(); i++)
		{
			Entity e = entities.get(i);//Accessing
			e.tick();//ticking all
			if (!e.isActive())
			{
				entities.remove(e);//Removing entity from entity manager
			}
		}
	
		entities.sort(renderSorter);
	}
	public void render(Graphics g)
	{
		for ( Entity e : entities)// like in c++ 11
		{
			e.render(g);
		}
	
		
	}
	
	public void addEntity(Entity e)
	{
		//Adding to arry list
		entities.add(e);//this is all
	}

	// Getters and Setters
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
