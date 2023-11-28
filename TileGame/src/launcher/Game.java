package launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import display.Display;
import gfx.Assets;
import gfx.GameCamera;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import input.KeyManager;
import input.MouseManager;
import states.GameState;
import states.MenuState;
import states.State;

public class Game implements Runnable //<- this implements thread
{
	// Main class of game (lots of stuff here)
	private Display display;
	private int width, height;//For display, width and height
	public String title;
	private boolean running = false; //Is it runing. Default false
	private Thread thread;// Thread
	private BufferStrategy bs;//Buffer strategy tells the comptuer how to run the screen
	private Graphics g;
	
/*	private BufferedImage testImage;
	private BufferedImage heroImage;
	private BufferedImage randomImage;*/ // <- For testcode

	//States
	public State gameState;
	public State menuState;
	public State settingsState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	
	public Game(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() // init graphics
	{
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
 		display.getFrame().addMouseMotionListener(mouseManager);
 		display.getCanvas().addMouseListener(mouseManager);//Both canvas JFrame are needed
 		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		
		gameCamera = new GameCamera(handler, 0,0);
	
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		settingsState = new MenuState(handler);
		State.setState(menuState);
		// this is for testcode
		//testImage = ImageLoader.loadImage("/textures/boss-preview.png");
		//heroImage = ImageLoader.loadImage("/textures/golem-walk.png");
		//randomImage = ImageLoader.loadImage("/textures/house.png");
		//Setting up testDroid
		//testDroid = ImageLoader.loadImage("/textures/protocoldroid1.png");
		//sheet = new SpriteSheet(testDroid);
	}
	

	
	private void tick()
	{
		keyManager.tick();//Important to check 
		
		if (State.getState() != null)//Current state exists
		{
			State.getState().tick();
		}
	}
	
	private void render()//Will loop over and over
	{
		bs = display.getCanvas().getBufferStrategy();// Buffer, hidden back screen to prevent fleckering like old games
		if (bs == null)
		{
		display.getCanvas().createBufferStrategy(2);// No need to have more than 3 buffers
	//	bs = display.getCanvas().getBufferStrategy();
			return; 
		}
		
		g = bs.getDrawGraphics(); // Graphics, draws to canvas
		//Clear Screen, must be done b4 drawing
		//Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw here:
		
		if (State.getState() != null)//Important, if even null. Throw an error
		{
			State.getState().render(g);
		}
		
//		g.drawImage(Assets.player1, 20, 20, null);
//		g.drawImage(Assets.player2, 25, 60, null);
//		g.drawImage(Assets.player3, 50, 90, null);
//		g.drawImage(Assets.testNoPng, 60, 200, null);
		
		
		//Below is testcode
/*		g.setColor(Color.red);
		g.fillRect(10, 50, 50, 70);//Will draw rectangle
		g.setColor(Color.green);
		g.fillRect(0,0,10,10);
		
		g.drawImage(testImage, 50, 50, null); // image, posx, posy, imageObserver
		g.drawImage(heroImage, 100, 50, null);
		g.drawImage(randomImage, 50, 250, null);
		*/
		// End of Drawing
		bs.show();
		g.dispose();
		
		
	}
	
	public void run()//This is Thread. (Must be implemented for thread)
	{
		init();
		
		int fps = 60; // TIck and render speed
		double timePerTick = 1000000000 / fps; // 9billion nanosecs in a second, 1 sec / fps. Gives us max amount of time allowed to run
		double delta = 0;//Amount of time till have to call tick and render
		long now;//Current computer time
		long lastTime = System.nanoTime(); // Returns computer time in nano seconds
		long timer = 0;
		int ticks = 0;
		
		while(running)
		{
		now = System.nanoTime();
		delta += (now - lastTime ) / timePerTick;//Time left before to call tick and render. Time passed to see if methods need to be called
		timer += now - lastTime; // adds how many secods passed since last called
		lastTime = now;// Resetting lastTime
		
		if (delta >= 1)// If delta 1 or more than 1, tick and render
		{
			tick();
			render();
			ticks++;
			delta--;
		}	
			
		if (timer >= 1000000000)//if running for 1 seconds
		{
			System.out.println("Ticks and Frames: " + ticks);
			ticks = 0;//Restting
			timer = 0;
		}
			
		}
		stop();//Just in case thread hasn't been stopped, this will stop it
		
	}
	
	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	public MouseManager getMouseManager()
	{
		return mouseManager;
	}
	
	public GameCamera getGameCamera()
	{
		return gameCamera;
	}
	
	public int getWidth()
	{
		return width;
	} 
	public int getHeight()
	{
		return height;
	}
	
	public synchronized void start()// Starts thread, like join
	{
		if (running)
			return; //If already running, do not do anything below 
		running = true;
		thread = new Thread(this); // Initing thread with this game class
		thread.start();//Starting thread (run())
	}
	
	public synchronized void stop()// Stops thread
	{
		if(!running) // If not running just return
			return;
		running = false; // Safety
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
