package states;

import java.awt.Graphics;

import launcher.Game;
import launcher.Handler;

public abstract class State // Similar to prototype in c++ for inherittance
{
	private static State currentState = null;//What is current state
	
	public static void setState(State state)
	{
		currentState = state;// Will change states
	}
	public static State getState()
	{
		return currentState;
	}
	
	
	// FOR CLASS INHERITANCE
	protected Handler handler;
	
	public State(Handler handler)
	{
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
		
}
