package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener //Allows to listen for keys
{
private boolean[] keys;
public boolean up, down, left, right, esc;// Note ESC not inmplemented yet
public boolean aUp, aDown, aLeft, aRight;

	public KeyManager()
	{
		keys =  new boolean[256];//Key Id's
	}
	
	public void tick()
	{
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left  = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		esc = keys[KeyEvent.VK_ESCAPE];
		
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft  = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
	keys[e.getKeyCode()] = true;
	System.out.println(e + " : Button was pressed");
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}
