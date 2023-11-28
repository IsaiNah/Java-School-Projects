package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gfx.Assets;
import launcher.Game;
import launcher.Handler;
import ui.UIImageButton;
import ui.UIManager;
import ui.ClickListener;

public class MenuState extends State 
{
	public Rectangle playButton = new Rectangle(handler.getGame().getWidth() / 2 + 120, 150,100, 50);
	//Note Above can be deleted
	
	private UIManager uiManager;
	
	
	public MenuState(Handler handler)
	{
	super(handler);
	uiManager = new UIManager(handler);
	handler.getMouseManager().setUIManager(uiManager);
	
	uiManager.addObject(new UIImageButton(350, 200, 64, 64, Assets.menuButtons, new ClickListener(){
		
		public void onClick()
		{
			handler.getMouseManager().setUIManager(null);//Resetting uiManager, else use will still be able to click inivisible button
			State.setState(handler.getGame().gameState);//On the fly implementation
			
		}
	}));
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	/*	System.out.println(handler.getMouseManager().getMouseX() + " " + handler.getMouseManager().getMouseY());
	if (handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed())
		State.setState(handler.getGame().gameState);*/
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
	/*	
	 	Font fnt0 = new Font("arial",Font.BOLD,50);
		g.setFont(fnt0);
		g.setColor(Color.blue);
		g.drawString("TileGame!", handler.getGame().getWidth() / 3, 200);
		g2d.draw(playButton);
		
		g.setColor(Color.red);
		g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
	*/
		uiManager.render(g);
		
		
	}
	

}
